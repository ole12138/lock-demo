package com.jingmin.lockdemo.dao2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author : wangjm
 * @date : 2021/3/19 14:00
 */
@SpringBootTest
class EmpDaoTest {
    @Autowired
    EmpDao empDao;

    @Test
    void addSalary() {
        System.out.println(empDao.addSalary(11L, new BigDecimal(15)));
    }
}