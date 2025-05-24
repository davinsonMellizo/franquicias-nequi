package co.com.nequi.r2dbc.config;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MysqlConnectionProperties {
    private String host;
    private Integer port;
    private String dbname;
    private String schema;
    private String username;
    private String password;
}
