package com.vincent.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import com.vincent.config.SecurityConfig;

@EnableWebMvc
@Configuration
@ComponentScan({"com.vincent.*"})
@EnableTransactionManagement
@Import({SecurityConfig.class})
public class AppConfig {

	@Bean
	public SessionFactory sessionFactory(){
		LocalSessionFactoryBuilder builder = 
				new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.vincent.users.model")
			.addProperties(getHibernateProperties());
		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", 
            "org.hibernate.dialect.MySQL5Dialect");
        return properties;
	}

	@Bean(name="dataSource")
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/vicnentcompany");
		ds.setUsername("root");
		ds.setPassword("Yukirin0715");
		return ds;
	}
	
	// Create transaction manager
	@Bean
	public HibernateTransactionManager txManager(){
		return new HibernateTransactionManager(sessionFactory());
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver =
				new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
