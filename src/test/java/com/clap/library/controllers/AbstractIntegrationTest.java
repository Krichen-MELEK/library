package com.clap.library.controllers;

import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIntegrationTest {

    @Container
    private static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("integration-tests-db").withUsername("username").withPassword("password");

    static {
        postgreSQLContainer.start();
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void testContainerIsRunning(){
        assertTrue(postgreSQLContainer.isRunning());
        log.info(postgreSQLContainer.getJdbcUrl());
        log.info(postgreSQLContainer.getUsername());
        log.info(postgreSQLContainer.getPassword());
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

}
