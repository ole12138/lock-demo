package com.jingmin.lockdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author : wangjm
 * @date : 2021/3/17 14:13
 */
@Configuration
//@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class MultiDataSourceConfig {
    /**
     * dataSource 这个数据源交给 MybatisAutoConfiguration中默认的sqlSessionFactory使用
     */
//    @Primary 还是不加的好，使用时不明确指定名称的话会报错
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * dataSource2 这个数据源给自定义的sqlSessionFactory2使用
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 这个才是我们最后要用到的事务管理器,使用链式事务管理器进行管理各个数据源的事务。
     * 如果出现跨数据源中操作，所有操作都进行完后，才统一进行事务的提交。
     * 如果操作过程中出错，所有操作都可以回滚。
     * 但是要注意：统一提交的过程中，仍有可能出错，这时已经提交的事务就无法回滚了，只能回滚还未提交的。
     * 所以，链式事务管理，并不能解决分布式事务的数据一致性问题，只是尽最大可能一次提交。
     */
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("dataSource2") DataSource dataSource2) {
        PlatformTransactionManager transactionManager1 = new DataSourceTransactionManager(dataSource);
        PlatformTransactionManager transactionManager2 = new DataSourceTransactionManager(dataSource2);
        return new ChainedTransactionManager(transactionManager1, transactionManager2);
    }
}
