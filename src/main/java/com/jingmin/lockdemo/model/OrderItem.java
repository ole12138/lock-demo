package com.jingmin.lockdemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * null
 *
 * @TableName order_item
 */
@Data
@NoArgsConstructor
public class OrderItem implements Serializable {
    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    private Long id;

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    private Long orderId;

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    private Long prodId;

    /**
     * @mbg.generated 2021-03-15 14:10:27
     */
    private Long prodNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table order_item
     *
     * @mbg.generated 2021-03-15 14:10:27
     */
    private static final long serialVersionUID = 1L;

    public OrderItem(Long orderId, Long prodId, Long prodNum) {
        this.orderId = orderId;
        this.prodId = prodId;
        this.prodNum = prodNum;
    }
}