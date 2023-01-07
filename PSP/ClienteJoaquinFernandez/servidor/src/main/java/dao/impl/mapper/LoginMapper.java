package dao.impl.mapper;

import jakarta.filter.Log;
import model.Code;
import model.Login;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper implements RowMapper<Login> {
    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login activacion = new Login();
        activacion.setUser(rs.getString("user"));
        activacion.setPassword(rs.getString("password"));

        return activacion;
    }
}
