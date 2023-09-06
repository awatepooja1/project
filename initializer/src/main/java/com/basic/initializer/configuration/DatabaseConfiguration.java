/*
 * package com.basic.initializer.configuration;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.boot.jdbc.DataSourceBuilder; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.PropertySource;
 * 
 * @Configuration
 * 
 * @PropertySource("./application.properties") public class
 * DatabaseConfiguration {
 * 
 * @Value("${spring.datasource.username}") public String username;
 * 
 * @Value("${spring.datasource.password}") public String password;
 * 
 * @Value("${spring.datasource.url}") public String url;
 * 
 * @Value("${spring.datasource.driver-class-name}") public String driver;
 * 
 * @Bean public DataSource getDataSource() { DataSourceBuilder dataSourceBuilder
 * = DataSourceBuilder.create(); dataSourceBuilder.driverClassName(driver);
 * dataSourceBuilder.url(url); dataSourceBuilder.username(username);
 * dataSourceBuilder.password(password); return dataSourceBuilder.build(); }
 * 
 * }
 */