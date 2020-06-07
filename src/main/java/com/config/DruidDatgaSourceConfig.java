package com.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
@MapperScan(basePackages="com.dao")
public class DruidDatgaSourceConfig{
    
    @Bean     //声明其为Bean实例  
    public PooledDataSource dataSource(){
    	
    	PooledDataSource datasource = new PooledDataSource();  
        
    	datasource.setDriver("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong");
        datasource.setUsername("root");
        datasource.setPassword("root");
        datasource.setPoolMaximumActiveConnections(5);
        datasource.setPoolMaximumIdleConnections(3); 
  
        return datasource;  
    }
    
    //mybatis的配置
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException{
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();//mybatis-plus插件类
        sqlSessionFactoryBean.setDataSource(dataSource());//数据源
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:mappers/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("powerx.io.model");//别名，让*Mpper.xml实体类映射可以不加上具体包名
        return sqlSessionFactoryBean;
    }
}
