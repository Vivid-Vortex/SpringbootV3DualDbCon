package com.demo.jpabasics.commonconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.demo.jpabasics.transmgmt.crud_app"
        , entityManagerFactoryRef = "transactionDemoEntityManager"
)
public class TransactionJpaConfig {

    @Bean(name = "transactionDemoDataSource")
    public DataSource transactionDemoDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/transaction_demo?allowPublicKeyRetrieval=true&useSSL=false")
                .username("root")
                .password("Admin@12345")
                .build();
    }

    @Bean(name = "transactionDemoJpaProperties")
    public JpaProperties transactionDemoJpaProperties() {
        JpaProperties properties = new JpaProperties();
        setCommonJpaProperties(properties);
        return properties;
    }

    private void setCommonJpaProperties(JpaProperties properties) {
        Map<String, String> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");

        //Hibernate will use the dialect type to generate the queries based on the db vendor. In this case it's MySQL
        /**
         * This is springboot version 3.2.2. Pleae check the date timestamp.
         2024-02-17T14:09:12.089+05:30  WARN 83724 --- [  restartedMain] org.hibernate.orm.deprecation            :
         HHH90000025: MySQL8Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)

         2024-02-17T14:09:12.090+05:30  WARN 83724 --- [  restartedMain] org.hibernate.orm.deprecation            :
         HHH90000026: MySQL8Dialect has been deprecated; use org.hibernate.dialect.MySQLDialect instead
         */
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");//Deprecated
//        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");//Picked up by default by springboot. No need to mention explicitly

        properties.setProperties(hibernateProperties);
        properties.setShowSql(true);
        properties.setOpenInView(false);
    }

    @Autowired
    @Qualifier("transactionDemoJpaProperties")
    private JpaProperties transactionDemoJpaProperties;

    @Bean(name = "transactionDemoEntityManager")
    public LocalContainerEntityManagerFactoryBean transactionDemoEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(transactionDemoDataSource());
        em.setPackagesToScan("com.demo.jpabasics.transmgmt.crud_app");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(transactionDemoJpaProperties.getProperties());

        return em;
    }
}
