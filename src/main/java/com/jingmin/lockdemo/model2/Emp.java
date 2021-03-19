package com.jingmin.lockdemo.model2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * null
 * @TableName emp
 */
@Data
public class Emp implements Serializable {
    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private Long id;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private String name;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private String email;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private BigDecimal salary;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private Long did;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private LocalDateTime createTime;

    /**
     * 
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private LocalDateTime updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table emp
     *
     * @mbg.generated 2021-03-19 13:50:27
     */
    private static final long serialVersionUID = 1L;
}