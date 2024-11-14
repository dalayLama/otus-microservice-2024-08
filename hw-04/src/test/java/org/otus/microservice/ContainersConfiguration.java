package org.otus.microservice;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfiguration {

    @Bean
    @ServiceConnection
    public PostgreSQLContainer container() {
        return new PostgreSQLContainer<>("postgres:16.3");
    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("file:hw-04/migration/changelog-master.yaml");
        liquibase.setLiquibaseSchema("public");
        liquibase.setDefaultSchema("public");
        return liquibase;
    }

}
