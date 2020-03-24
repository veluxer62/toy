package com.example.demo

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class R2bcConfiguration : AbstractR2dbcConfiguration() {
    @Bean
    override fun connectionFactory() = PostgresqlConnectionFactory(
        PostgresqlConnectionConfiguration.builder()
            .host("127.0.0.1")
            .port(5432)
            .database("postgres")
            .username("postgres")
            .password("1123")
            .build()
    )

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val populator = ResourceDatabasePopulator(ClassPathResource("sql/db-schema.sql"))
        initializer.setDatabasePopulator(populator)
        return initializer
    }
}