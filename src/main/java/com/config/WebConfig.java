package com.config;

import com.service.ITestService;
import com.service.impl.TestServiceImpl;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.*"})
@EnableTransactionManagement
public class WebConfig implements TransactionManagementConfigurer {

    @Bean
    public InternalResourceViewResolver jspResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }

    @Bean
    public MappingJackson2JsonView jsonView(){
        MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
        jsonView.setContentType("application/json;charset=UTF-8");
        return jsonView;
    }

    @Bean
    public PooledDataSource dataSource(){
        PooledDataSource dataSource=new PooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setPoolMaximumActiveConnections(5);
        dataSource.setPoolMaximumIdleConnections(3);
        return dataSource;

    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sessionFactoryBean(){
        try{
            SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource());
            Resource[] mapperLocations=new PathMatchingResourcePatternResolver().getResources("classpath:mapper/testdb/*.xml");
            sessionFactoryBean.setMapperLocations(mapperLocations);
            return sessionFactoryBean;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;

    }

    @Bean
    public MapperScannerConfigurer scannerConfigurer(){
        MapperScannerConfigurer scannerConfigurer=new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.dao");
        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return scannerConfigurer;
    }



    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}