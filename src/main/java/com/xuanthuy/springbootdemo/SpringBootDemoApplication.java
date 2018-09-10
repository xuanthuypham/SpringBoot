package com.xuanthuy.springbootdemo;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication

@EnableAutoConfiguration(exclude= {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class SpringBootDemoApplication {

	@Autowired
	private Environment environment;
	
	
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		
		datasource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		datasource.setUrl(environment.getProperty("spring.datasource.url"));
		datasource.setUsername(environment.getProperty("spring.datasource.username"));
		datasource.setPassword(environment.getProperty("spring.datasource.password"));
		return datasource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
		
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		properties.put("current_session_context_class", //
                environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));		
		
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		
		factoryBean.setPackagesToScan("com.xuanthuy.springbootdemo.entity");
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		
		
		SessionFactory sessionFactory = factoryBean.getObject();
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(" Session Factory "+sessionFactory);
		System.out.println("+++++++++++++++++++++++++++++");
		return sessionFactory;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
