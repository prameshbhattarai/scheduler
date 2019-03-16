package com.scheduler.scheduler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dataSource")
    @Autowired
    public DataSource getDataSource(DataSource dataSource1, DataSource dataSource2, DataSource dataSource3) {

        System.out.println("-- Create DataSource from dataSource1, dataSource2, dataSource3");

        RoutingDataSourceConfig dataSource = new RoutingDataSourceConfig();
        dataSource.initDataSources(dataSource1, dataSource2, dataSource3);
        return dataSource;

    }

    @Bean(name = "dataSource1")
    public DataSource getDataSource1() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.client1"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.client1"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.client1"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.client1"));

        System.out.println("-- DataSource1: " + dataSource);
        return dataSource;
    }

    @Bean(name = "dataSource2")
    public DataSource getDataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.client2"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.client2"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.client2"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.client2"));

        System.out.println("-- DataSource2: " + dataSource);
        return dataSource;
    }

    @Bean(name = "dataSource3")
    public DataSource getDataSource3() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.client3"));
        dataSource.setUrl(env.getProperty("spring.datasource.url.client3"));
        dataSource.setUsername(env.getProperty("spring.datasource.username.client3"));
        dataSource.setPassword(env.getProperty("spring.datasource.password.client3"));

        System.out.println("-- DataSource3: " + dataSource);
        return dataSource;
    }
}
