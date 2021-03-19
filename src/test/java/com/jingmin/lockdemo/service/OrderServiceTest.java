package com.jingmin.lockdemo.service;

import com.jingmin.lockdemo.model.Product;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : wangjm
 * @date : 2021/3/15 15:38
 */
@SpringBootTest
@EnableTransactionManagement
class OrderServiceTest {

    @Autowired
    OrderService orderService;


    @Test
    void orderNotSecure() {
        long custId = 1L;
        Product product1 = new Product(1L, 1L);
        Product product2 = new Product(2L, 1L);
        List<Product> productList = Arrays.asList(product1, product2);
        try {
            //商品下单，不保证并发一致性的方式（并发情况下会发生数据错误）
            orderService.orderNotSecure(custId, productList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("执行到这里看看有没有提交");
    }

    @Test
    void orderSecureWithJavaOptimisticLock() {
        long custId = 2L;
        Product product1 = new Product(1L, 2L);
        Product product2 = new Product(2L, 2L);
        List<Product> productList = Arrays.asList(product1, product2);
        try {
            //商品下单，用（Java乐观锁+数据库version/时间戳列）保证并发情况下的数据一致性
            orderService.orderSecureWithJavaOptimisticLock(custId, productList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void orderSecureWithMysqlPessimisticLock() {
        long custId = 3L;
        Product product1 = new Product(1L, 2L);
        Product product2 = new Product(2L, 2L);
        List<Product> productList = Arrays.asList(product1, product2);
        try {
            //商品下单，用Mysql悲观锁保证并发情况下的数据一致性
            orderService.orderSecureWithMysqlPessimisticLock(custId, productList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void orderSecureWithJavaPessimisticLock() {
        long custId = 3L;
        Product product1 = new Product(1L, 2L);
        Product product2 = new Product(2L, 2L);
        List<Product> productList = Arrays.asList(product1, product2);
        Callable<Boolean> task1 = new MyCallable(custId, productList, orderService);
        Callable<Boolean> task2 = new MyCallable(custId, productList, orderService);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Boolean> future1 = executorService.submit(task1);
        Future<Boolean> future2 = executorService.submit(task2);
        try {
            future1.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            future2.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        executorService.shutdown();
    }

    public static class MyCallable implements Callable<Boolean> {
        private long custId;
        private List<Product> productList;
        private OrderService orderService;

        public MyCallable(long custId, List<Product> productList, OrderService orderService) {
            this.custId = custId;
            this.productList = productList;
            this.orderService = orderService;
        }

        @Override
        public Boolean call() throws Exception {
            orderService.orderSecureWithJavaPessimisticLock(custId, productList);
            return Boolean.TRUE;
        }
    }

    @Test
    void safeOrderAndEmpAddSalary() throws Exception {
        long custId = 3L;
        Product product1 = new Product(5L, 2L);
        Product product2 = new Product(6L, 2L);
        List<Product> productList = Arrays.asList(product1, product2);
        orderService.safeOrderAndEmpAddSalary(custId,productList,12L);
    }
}