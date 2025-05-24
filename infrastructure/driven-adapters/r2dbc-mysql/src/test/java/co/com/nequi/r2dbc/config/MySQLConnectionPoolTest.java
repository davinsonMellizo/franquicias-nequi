package co.com.nequi.r2dbc.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MySQLConnectionPoolTest {

    @InjectMocks
    private MySQLConnectionPool connectionPool;

    @Mock
    private MysqlConnectionProperties properties;


    @BeforeEach
    void setUp() {

        when(properties.getHost()).thenReturn("localhost");
        when(properties.getPort()).thenReturn(5432);
        when(properties.getDbname()).thenReturn("dbName");
        when(properties.getUsername()).thenReturn("username");
        when(properties.getPassword()).thenReturn("password");
    }

    @Test
    void getConnectionConfigSuccess() {
        assertNotNull(connectionPool.connectionFactory(properties));
    }
}
