package com.jingmin.lockdemo.dao;

import com.jingmin.lockdemo.model.OrderItem;

/**
 * @Entity com.jingmin.lockdemo.model.OrderItem
 */
public interface OrderItemDao {
    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insert(OrderItem record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insertSelective(OrderItem record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    OrderItem selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKey(OrderItem record);
}