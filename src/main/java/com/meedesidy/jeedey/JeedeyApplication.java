package com.meedesidy.jeedey;

import javax.annotation.Resource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
@ComponentScan
public class JeedeyApplication implements TransactionManagementConfigurer{
	
	@Resource(name="txManager")
    private PlatformTransactionManager txManager;
	
	@Bean(name = "txManager")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(JeedeyApplication.class, args);
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager;
	}
}
