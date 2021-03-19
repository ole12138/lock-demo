package com.jingmin.lockdemo.dao;

import com.jingmin.lockdemo.model.Custmer;

/**
 * @Entity com.jingmin.lockdemo.model.Custmer
 */
public interface CustmerDao {
    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insert(Custmer record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int insertSelective(Custmer record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    Custmer selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKeySelective(Custmer record);

    /**
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    int updateByPrimaryKey(Custmer record);
}