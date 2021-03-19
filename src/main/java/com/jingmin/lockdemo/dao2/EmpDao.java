package com.jingmin.lockdemo.dao2;

import com.jingmin.lockdemo.model2.Emp;

import java.math.BigDecimal;

/**
 * @Entity com.jingmin.lockdemo.model2.Emp
 */
public interface EmpDao {
    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    int insert(Emp record);

    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    int insertSelective(Emp record);

    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    Emp selectByPrimaryKey(Long id);

    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    int updateByPrimaryKeySelective(Emp record);

    /**
     * @mbg.generated 2021-03-17 15:38:30
     */
    int updateByPrimaryKey(Emp record);

    /**
     * 加薪
     * @param id 员工id
     * @param addSalary 增加的薪水
     * @return 影响的行数
     */
    int addSalary(Long id, BigDecimal addSalary);
}
