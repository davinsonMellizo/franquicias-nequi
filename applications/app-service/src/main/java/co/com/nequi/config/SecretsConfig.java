package co.com.nequi.config;

import co.com.bancolombia.secretsmanager.api.GenericManagerAsync;
import co.com.bancolombia.secretsmanager.api.exceptions.SecretException;
import co.com.bancolombia.secretsmanager.config.AWSSecretsManagerConfig;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import co.com.nequi.r2dbc.config.MysqlConnectionProperties;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.regions.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretsConfig {

  @Bean
  public GenericManagerAsync getSecretManager(@Value("${aws.region}") String region) {
    return new AWSSecretManagerConnectorAsync(getConfig(region));
  }

  private AWSSecretsManagerConfig getConfig(String region) {
    return AWSSecretsManagerConfig.builder()
      .region(Region.of(region))
      .cacheSize(5)
      .cacheSeconds(3600)
      .build();
  }

  @Bean
  public MysqlConnectionProperties mysqlConnectionProperties(final GenericManagerAsync connector,
                                                    @Value("${aws.rdsSecretName}") String secretName)
          throws SecretException {

    return connector.getSecret(secretName, MysqlConnectionProperties.class)
            .onErrorResume(e-> Mono.just(MysqlConnectionProperties.builder()
                    .host("mysql-franquicias")
                    .database("franquicias")
                    .port(3306)
                    .password("password")
                    .username("root")
                    .build()
            ))//Eliminar para ambiente productivo
            .block();
  }
}
