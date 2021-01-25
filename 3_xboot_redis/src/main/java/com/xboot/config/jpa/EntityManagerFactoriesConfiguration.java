package com.xboot.config.jpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * 
 * @author xinjinwei
 *双mybatis，单jpa，jpa默认使用第一个mybatis的数据源
 *因为jpa不提供事务处理，所以要自己管理
 */
@Configuration
public class EntityManagerFactoriesConfiguration {
    @Autowired
    private DataSource firstDataSource;

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(firstDataSource);
        emf.setPackagesToScan(
                new String[]{"com.xboot.repository"});  //扫描路径
        emf.setJpaVendorAdapter(
                new HibernateJpaVendorAdapter());
        return emf;
    }
}