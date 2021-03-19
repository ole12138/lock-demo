package com.jingmin.lockdemo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : wangjm
 * @date : 2021/3/17 14:34
 */
@Configuration
@MapperScan("com.jingmin.lockdemo.dao")
public class SingleDataSourceConfig {
    //MybatisAutoConfiguration中已经配好
}
