package com.jingmin.lockdemo.dao;

import com.jingmin.lockdemo.model.Product;

/**
 * @Entity com.jingmin.lockdemo.model.Product
 */
public interface ProductDao {
    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insert(Product record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insertSelective(Product record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    Product selectByPrimaryKey(Long id);

    /**
     * 悲观锁定行
     */
    Product selectByPrimaryKeyForUpdate(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKey(Product record);

    int updateByPrimaryKeyAndCheckTimeStamp(Product record);
}
