package com.jingmin.lockdemo.dao;

import com.jingmin.lockdemo.model.Custmer;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : wangjm
 * @date : 2021/3/15 14:41
 */
@SpringBootTest
class CustmerDaoTest {
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    void selectByPrimaryKey() {
        CustmerDao mapper = sqlSessionTemplate.getMapper(CustmerDao.class);
        Custmer custmer = mapper.selectByPrimaryKey(1L);
        System.out.println(custmer);
    }
}