package com.rolandopalermo.facturacion.ec.init;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.rolandopalermo.facturacion.ec.entity.SQLXMLType;



@Configuration
@ComponentScan({ "com.rolandopalermo.facturacion.ec" })
@PropertySource("classpath:application.properties")
public class WebAppConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "admin";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:postgresql://localhost:5433/sri_data_base";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "postgres";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.rolandopalermo.facturacion.ec.entity";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_MAPPING = "Doc.hbm.xml";

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = null;
		try {
			dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
			dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
			dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
			dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dataSource;

	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = null;
		try {
			sessionFactoryBean = new LocalSessionFactoryBean();
			sessionFactoryBean.setDataSource(dataSource());
			sessionFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
			sessionFactoryBean.setMappingResources(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_MAPPING);
			sessionFactoryBean.setHibernateProperties(hibProperties());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sessionFactoryBean;
	}

	private Properties hibProperties() {
		Properties properties = null;
		try {
			properties = new Properties();
			properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, PROPERTY_NAME_HIBERNATE_DIALECT);
			properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, PROPERTY_NAME_HIBERNATE_SHOW_SQL);
		
		} catch (Exception e) {
			// TODO: handle exception
		}

		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}