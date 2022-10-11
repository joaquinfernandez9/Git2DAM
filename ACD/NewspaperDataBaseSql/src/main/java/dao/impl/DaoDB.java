package dao.impl;

import config.Configuration;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.sql.*;

@Log4j2
public class DaoDB {

    private final Configuration config;

    @Inject
    public DaoDB(Configuration configuration) {
        this.config = configuration;
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
        log.log(Level.INFO,"Releasing all open resources ...");
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


}
