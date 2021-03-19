package com.jingmin.lockdemo.dao;

import com.jingmin.lockdemo.model.Order;

/**
 * @Entity com.jingmin.lockdemo.model.Order
 */
public interface OrderDao {
    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    long insert(Order record);

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insertSelective(Order record);

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    Order selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKey(Order record);
}