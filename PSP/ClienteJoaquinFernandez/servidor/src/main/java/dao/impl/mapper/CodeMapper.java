package dao.impl.mapper;

import model.Code;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeMapper implements RowMapper<Code> {

    @Override
    public Code mapRow(ResultSet rs, int rowNum) throws SQLException {
        Code activacion = new Code();
        activacion.setGenerated_code(rs.getString("generated_code"));
        activacion.setExpiration_date(rs.getTime("expiration_date"));
        activacion.setUser_code(rs.getString("user_code"));

        return activacion;
    }
}
