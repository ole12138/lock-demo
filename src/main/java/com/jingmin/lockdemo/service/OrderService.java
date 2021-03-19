package com.jingmin.lockdemo.service;

import com.jingmin.lockdemo.dao.CustmerDao;
import com.jingmin.lockdemo.dao.OrderDao;
import com.jingmin.lockdemo.dao.OrderItemDao;
import com.jingmin.lockdemo.dao.ProductDao;
import com.jingmin.lockdemo.dao2.EmpDao;
import com.jingmin.lockdemo.model.Order;
import com.jingmin.lockdemo.model.OrderItem;
import com.jingmin.lockdemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : wangjm
 * @date : 2021/3/15 15:02
 */
@Service
public class OrderService {
    @Autowired
    CustmerDao custmerDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    EmpDao empDao;

    /**
     * 客户下单，不考虑并发，存在并发不一致问题（商品超卖啥的）
     * 其实就是 ：
     * INSERT 一条订单
     * for products
     * SELECT 商品数量
     * if 数量足够
     * INSERT 一条订单项
     * UPDATE 对应商品表商品数量
     * ELSE
     * break；
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderNotSecure(Long custId, List<Product> productList) throws Exception {
        //生成订单
        Order order = new Order(custId);
        orderDao.insert(order);
        for (Product productWanted : productList) {
            //不考虑并发（普通SELECT语句）
            Product productStored = productDao.selectByPrimaryKey(productWanted.getId());
            if (productStored.getAmount() - productWanted.getAmount() >= 0) {
                //生成订单项
                OrderItem orderItem = new OrderItem(order.getId(), productWanted.getId(), productWanted.getAmount());
                orderItemDao.insert(orderItem);
                //更新产品数量
                productStored.setAmount(productStored.getAmount() - productWanted.getAmount());
                productDao.updateByPrimaryKey(productStored);
            } else {
                throw new Exception("更新失败，数量已不足");
            }
        }
    }

    /**
     * 客户下单，考虑并发（Java悲观锁可以保证同应用实例的并发一致性）
     * 上面的方法设为synchronized方法锁定更新，缺陷：只有单应用实例时有效，分布式情况不能把证并发一致性
     */
    @Transactional(rollbackFor = Exception.class)
    public synchronized void orderSecureWithJavaPessimisticLock(Long custId, List<Product> productList) throws Exception {
        Thread.sleep(1000);
        orderNotSecure(custId, productList);
    }

    /**
     * 考虑并发，客户下单（mysql悲观锁可以保证同一数据库的并发一致性）
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderSecureWithMysqlPessimisticLock(Long custId, List<Product> productList) throws Exception {
        //生成订单
        Order order = new Order(custId);
        orderDao.insert(order);
        for (Product productWanted : productList) {
            //考虑并发（使用SELECT...FOR UPDATE显式加行锁定，注意对应列要有唯一索引，否则会变成表锁）
            Product productStored = productDao.selectByPrimaryKeyForUpdate(productWanted.getId());
            if (productStored.getAmount() - productWanted.getAmount() >= 0) {
                //生成订单项
                OrderItem orderItem = new OrderItem(order.getId(), productWanted.getId(), productWanted.getAmount());
                orderItemDao.insert(orderItem);
                //更新产品数量
                productStored.setAmount(productStored.getAmount() - productWanted.getAmount());
                productDao.updateByPrimaryKey(productStored);
            } else {
                throw new Exception("更新失败，数量已不足");
            }
        }
    }

    /**
     * 考虑并发，客户下单（用乐观锁+version/时间戳保证并发一致性，要求隔离级别为读已提交）
     * 注意：这里需要修改隔离级别为：读已提交
     */
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED)
    public void orderSecureWithJavaOptimisticLock(Long custId, List<Product> productList) throws Exception {
        //生成订单
        Order order = new Order(custId);
        orderDao.insert(order);
        for (Product productWanted : productList) {
            while (true) {
                //考虑并发(product表中增加了一列update_time时间戳)
                Product productStored = productDao.selectByPrimaryKey(productWanted.getId());
                if (productStored.getAmount() - productWanted.getAmount() >= 0) {
                    //生成订单项
                    OrderItem orderItem = new OrderItem(order.getId(), productWanted.getId(),
                            productWanted.getAmount());
                    orderItemDao.insert(orderItem);
                    //更新产品数量
                    productStored.setAmount(productStored.getAmount() - productWanted.getAmount());
                    //更新时要同时校验时间戳
                    int updateNum = productDao.updateByPrimaryKeyAndCheckTimeStamp(productStored);
                    if (updateNum > 0) {
                        break;
                    }
                } else {
                    //数量已不足，回滚
                    throw new Exception("更新失败，数量已不足");
                }
            }
        }
    }

    /**
     * 顾客下单，员工加薪1元
     * 这里使用了多数据源，分别给配了一个事务管理器，然后使用链式事务管理器综合管理
     */
    @Transactional(rollbackFor = Exception.class)
    public void safeOrderAndEmpAddSalary(Long custId, List<Product> productList, Long empId) throws Exception {
        //这里是对数据源1中数据进行操作
        orderSecureWithMysqlPessimisticLock(custId, productList);
//        if (false)
//            throw new Exception("测试在这个步骤出错,能否保证数据一致性，即前面的事务能否回滚");
        //这里是对数据源2中数据进行操作
        empDao.addSalary(empId, new BigDecimal(1));
//        if (true)
//            throw new Exception("测试在这个步骤出错,能否保证数据一致性，即前面的事务能否回滚");
    }
}
