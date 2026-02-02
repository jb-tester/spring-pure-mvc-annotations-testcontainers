package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Properties;

import com.mytests.spring.pureAnnotationBasedMVC.test.AppConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;

@ContextConfiguration(classes = TestContainersTest.TestConfig.class)
@ExtendWith(SpringExtension.class)
@org.testcontainers.junit.jupiter.Testcontainers
class TestContainersTest {


  @Container
  private static final PostgreSQLContainer<?> postgresqlContainer =
          new PostgreSQLContainer("postgres:16.3-alpine")
          .withDatabaseName("foo")
          .withUsername("foo")
          .withPassword("secret");

@Autowired DataSource dataSource;
  @Autowired UserService userService;


  @Test
  void test() {
    assertEquals(0, userService.findAll().size());
  }

  @Configuration
  @EnableTransactionManagement
  @EnableJpaRepositories(basePackages = "com.mytests.spring.pureAnnotationBasedMVC.test.repositories")
  @ComponentScan(basePackages = "com.mytests.spring.pureAnnotationBasedMVC.test.service")
  static class TestConfig {

    @Bean
    public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl(postgresqlContainer.getJdbcUrl());
      dataSource.setUsername(postgresqlContainer.getUsername());
      dataSource.setPassword(postgresqlContainer.getPassword());
      return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
      emf.setDataSource(dataSource());
      emf.setPackagesToScan("com.mytests.spring.pureAnnotationBasedMVC.test.model");
      emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      Properties jpaProperties = new Properties();
      jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
      jpaProperties.put("hibernate.show_sql", "true");
      emf.setJpaProperties(jpaProperties);
      return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
      return new JpaTransactionManager(emf);
    }
  }
}