package com.xboot.config.jpa;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author xinjinwei
 *双mybatis，单jpa，jpa默认使用第一个mybatis的数据源
 *因为jpa不提供事务处理，所以要自己管理
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagersConfig {
    @Autowired
    EntityManagerFactory emf;
    @Autowired
    private DataSource firstDataSource;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm =
                new JpaTransactionManager();
        tm.setEntityManagerFactory(emf);
        tm.setDataSource(firstDataSource);
        return tm;
    }
}