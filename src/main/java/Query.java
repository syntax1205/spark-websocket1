import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public Query(){
    }
    private ResultSet query(String sql, String username, String password) throws SQLException {
        try {
            Database database = new Database();
            DataSource dataSource = database.initDatabase(username, password);
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.getString(1);
                resultSet.getString(2);
                resultSet.getString(3);
            }
        }catch (SQLException sqlException){
            throw sqlException;
        }
        return resultSet;
    }
}
