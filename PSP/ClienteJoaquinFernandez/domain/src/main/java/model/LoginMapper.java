package model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper<Login>{

    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login u = new Login();

        u.setUser(rs.getString("user"));

        return u;
    }
}
