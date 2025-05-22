package co.com.nequi.r2dbc.config;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

import java.time.Duration;

@Configuration
public class MySQLConnectionPool {
    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;

    @Bean
    public ConnectionFactory connectionFactory(MysqlConnectionProperties properties) {
        MySqlConnectionConfiguration config = MySqlConnectionConfiguration.builder()
                .host(properties.host())
                .port(properties.port())
                .username(properties.username())
                .password(properties.password())
                .database(properties.database())
                .build();

        return new ConnectionPool(
                ConnectionPoolConfiguration.builder(MySqlConnectionFactory.from(config))
                        .initialSize(INITIAL_SIZE)
                        .maxSize(MAX_SIZE)
                        .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                        .build()
        );
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }
}
