package dao.dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import javax.sql.DataSource;
import java.sql.*;

@Log4j2
public class DaoDB {

    private final Configuration config;
    private DataSource hikariDataSource;

    @Inject
    public DaoDB(Configuration configuration) {
        this.config = configuration;
        hikariDataSource = getHikariPool();
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getProperty("urlDB"));
        hikariConfig.setUsername(config.getProperty("user_name"));
        hikariConfig.setPassword(config.getProperty("password"));
        hikariConfig.setDriverClassName(config.getProperty("driver"));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return new HikariDataSource(hikariConfig);
    }


    //open connection
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager
                .getConnection(config.getProperty("urlDB"), config.getProperty("user_name"), config.getProperty("password"));
        log.log(Level.INFO, "Connected to DB");
        return conn;
    }

    //close connection
    public void closeConnection(Connection connArg) {
        log.log(Level.INFO, "Releasing all open resources ...");
        try {
            if (connArg != null) {
                connArg.close();
            }
        } catch (SQLException sqle) {
            log.log(Level.ERROR, sqle.getMessage());
        }
    }

    public void releaseResource(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void releaseResource(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void releaseResource(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }


}
