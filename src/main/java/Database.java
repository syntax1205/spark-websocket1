import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class Database {
    private static String connectionString;
    private static HikariConfig config;
    public Database(){

    }
    public DataSource initDatabase(String username, String password){
        config = new HikariConfig();
        config.setJdbcUrl(connectionString);
        config.setUsername(username);
        config.setPassword(password);
        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
