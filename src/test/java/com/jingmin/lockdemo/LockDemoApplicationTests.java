package com.jingmin.lockdemo;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class LockDemoApplicationTests {
    @Autowired
    DataSource dataSource;
    //    @Autowired
//    JdbcTemplate jdbcTemplate;
    @Autowired
    SqlSessionTemplate sqlSessionTemplate;
    @Autowired
//    @Qualifier("dataSource2")
    DataSource dataSource2;
    @Autowired
    @Qualifier("sqlSessionTemplate2")
    SqlSessionTemplate sqlSessionTemplate2;
    @Autowired
    List<SqlSessionFactory> sqlSessionFactorys;
    @Autowired
    List<SqlSessionTemplate> sqlSessionTemplates;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    @Disabled
    void contextLoads() {
//        Integer a = 1000;
//        Integer b = 1000;
//        System.out.println(a == b);
//        String c = "hello";
//        String d = "hello";
//        System.out.println(c == d);
//        String e = new String(c);
//        String f = new String(d);
//        System.out.println(e == f);
//        String g = new String("hello");
//        String h = new String("hello");
//        System.out.println(g == h);
    }

    @Test
    @Disabled
    public void singleDataSourceTest() {
        System.out.println(dataSource.getClass());
//        System.out.println(jdbcTemplate.getClass());
        System.out.println(sqlSessionTemplate.getClass());
    }

    @Test
    public void MultiDataSourceTest() {
        System.out.println(sqlSessionFactorys);
        System.out.println(sqlSessionTemplates);
    }

}
