package dao.dataBase;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Configuration;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
@Named("database")
public class DataBaseConnectionPool {

    private final Configuration config;
    private final DataSource hikariDataSource;

    @Inject
    public DataBaseConnectionPool(Configuration config) {
        this.config = config;
        hikariDataSource = getHikariPool();
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
        hikariConfig.setJdbcUrl(config.getUrl());
        hikariConfig.setUsername(config.getUser());
        hikariConfig.setPassword(config.getPassword());
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setMaximumPoolSize(4);
        hikariConfig.addDataSourceProperty(Const.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(Const.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(Const.PREP_STMT_CACHE_SQL_LIMIT, 2048);
        return new HikariDataSource(hikariConfig);
    }


    public DataSource getDataSource() {
        return hikariDataSource;
    }

    @PreDestroy
    public void close() {
        ((HikariDataSource) hikariDataSource).close();
    }


}
