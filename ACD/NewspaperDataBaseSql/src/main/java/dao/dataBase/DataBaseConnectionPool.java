package dao.dataBase;

import config.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

import jakarta.inject.Singleton;
import org.apache.commons.dbcp2.BasicDataSource;

import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class DataBaseConnectionPool {

    private Configuration config;
    private DataSource hikariDataSource;
    private BasicDataSource basicDataSource;

    @Inject
    public DataBaseConnectionPool(Configuration config) {
        this.config = config;
        hikariDataSource = getHikariPool();
        basicDataSource = getBasicPool();
    }

    public Connection getConnection() {
        Connection con=null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
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


    private BasicDataSource getBasicPool() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(config.getProperty("user_name"));
        basicDataSource.setPassword(config.getProperty("password"));
        basicDataSource.setUrl(config.getProperty("urlDB"));

        return basicDataSource;
    }

    public DataSource getDataSource() {
        return hikariDataSource;
    }



}
