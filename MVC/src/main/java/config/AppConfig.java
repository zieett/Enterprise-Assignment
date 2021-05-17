package main.java.config;


import main.java.service.Service;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.util.Properties;

/**
 * Created by CoT on 10/13/17.
 */

@Configuration
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public Service service(){
        return new Service();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "update");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();


        //Change the port-number (9999) to your port-number, also change username and password if there is a difference
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:9999/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("baobao");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        sessionFactoryBean.setHibernateProperties(properties);
        sessionFactoryBean.setPackagesToScan("main.java.entity");


        return  sessionFactoryBean;
    }


    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }


}