package com.jingmin.lockdemo.dao2;

import com.jingmin.lockdemo.model2.Dept;

/**
 * @Entity com.jingmin.lockdemo.model2.Dept
 */
public interface DeptDao {
    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    int insert(Dept record);

    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    int insertSelective(Dept record);

    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    Dept selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    int updateByPrimaryKeySelective(Dept record);

    /**
     *
     * @mbg.generated 2021-03-17 15:38:30
     */
    int updateByPrimaryKey(Dept record);
}