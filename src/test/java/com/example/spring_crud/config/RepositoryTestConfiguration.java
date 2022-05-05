package com.example.spring_crud.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.data.repository.config.ImplementationDetectionConfiguration;
import org.springframework.data.repository.config.ImplementationLookupConfiguration;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.data.repository.config.RepositoryConfigurationSource;
import org.springframework.data.util.Streamable;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootConfiguration
@EnableAutoConfiguration
public class RepositoryTestConfiguration implements RepositoryConfiguration {

    @Bean(initMethod = "start")
    public PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse("postgres:13.3"));
    }

    @Bean
    public DataSource dataSource() {
        final PostgreSQLContainer<?> postgresContainer = postgresContainer();

        final HikariConfig config = new HikariConfig();
        config.setDriverClassName(postgresContainer.getDriverClassName());
        config.setJdbcUrl(postgresContainer.getJdbcUrl());
        config.setUsername(postgresContainer.getUsername());
        config.setPassword(postgresContainer.getPassword());

        return new HikariDataSource(config);
    }

    @Override
    public Streamable<String> getBasePackages() {
        return null;
    }

    @Override
    public Streamable<String> getImplementationBasePackages() {
        return null;
    }

    @Override
    public String getRepositoryInterface() {
        return null;
    }

    @Override
    public Object getQueryLookupStrategyKey() {
        return null;
    }

    @Override
    public Optional<String> getNamedQueriesLocation() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getRepositoryBaseClassName() {
        return Optional.empty();
    }

    @Override
    public String getRepositoryFactoryBeanClassName() {
        return null;
    }

    @Override
    public Object getSource() {
        return null;
    }

    @Override
    public RepositoryConfigurationSource getConfigurationSource() {
        return null;
    }

    @Override
    public boolean isLazyInit() {
        return false;
    }

    @Override
    public boolean isPrimary() {
        return false;
    }

    @Override
    public Streamable<TypeFilter> getExcludeFilters() {
        return null;
    }

    @Override
    public ImplementationDetectionConfiguration toImplementationDetectionConfiguration(MetadataReaderFactory factory) {
        return null;
    }

    @Override
    public ImplementationLookupConfiguration toLookupConfiguration(MetadataReaderFactory factory) {
        return null;
    }

    @Override
    public String getResourceDescription() {
        return null;
    }
}