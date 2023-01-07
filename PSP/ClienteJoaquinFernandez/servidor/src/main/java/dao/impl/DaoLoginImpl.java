package dao.impl;

import dao.DaoLogin;
import dao.impl.mapper.CodeMapper;
import domain.modelo.CommonException;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.extern.log4j.Log4j2;
import dao.dataBase.DataBaseConnectionPool;
import model.Code;
import model.Login;
import model.LoginMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;

@Log4j2
public class DaoLoginImpl implements DaoLogin {

    private final Pbkdf2PasswordHash passwordHash;
    private final DataBaseConnectionPool pool;

    @Inject
    public DaoLoginImpl(DataBaseConnectionPool pool, Pbkdf2PasswordHash passwordHash) {
        this.pool = pool;
        this.passwordHash = passwordHash;
    }

    @Override
    public Either<String, Login> login(String username, String password) {
        JdbcTemplate jtm =  new JdbcTemplate(pool.getDataSource());
        Login user = jtm.query("Select from loginConHash where user=?", new LoginMapper(), username)
                .stream().findFirst().orElse(null);
        Either<String, Login> response;
        if (user==null){
            response = Either.left("No hay usuarios con ese nombre");
        } else if (passwordHash.verify(password.toCharArray(), user.getPassword())) {
            response = Either.right(new Login(username, password));
        } else {
            response = Either.left("La contraseña no es correcta");
        }
        return response;
    }

    @Override
    public Either<String, Login> register(Login log, Code code) {
//        TransactionDefinition txDef = new DefaultTransactionDefinition();
//        DataSourceTransactionManager txManager =
//                new DataSourceTransactionManager(pool.getDataSource());
//        TransactionStatus txStatus = txManager.getTransaction(txDef);
        try {
            Either<String, Login> response;
            SimpleJdbcInsert insert = new SimpleJdbcInsert(pool.getDataSource())
                    .withTableName("loginConHash");
            Map<String, Object> param = new HashMap<>();
            param.put("user", log.getUser());
            param.put("password", passwordHash.generate(log.getPassword().toCharArray()));
            param.put("id_reader", log.getId_reader());
            param.put("email", log.getEmail());
            int r = insert.execute(param);

            if (r > 0){
                SimpleJdbcInsert codeInsert = new SimpleJdbcInsert(pool.getDataSource())
                        .withTableName("codeTable");
                Map<String, Object> paramCode = new HashMap<>();
                paramCode.put("generated_code", code.getGenerated_code());
                paramCode.put("expiration_date", code.getExpiration_date());
                paramCode.put("user_code", log.getUser());
                codeInsert.execute(paramCode);
                response = Either.right(new Login(log.getUser(), log.getPassword()));
            } else {
                response = Either.left("Error");
            }
            return response;
        } catch (Exception ex){
//            txManager.rollback(txStatus);
            throw new CommonException(ex.getMessage());
        }
    }

    @Override
    public Either<String, Code> activarCuenta(Code code){
        Either<String, Code> response;
        JdbcTemplate template = new JdbcTemplate(pool.getDataSource());
        Code activation = template.query("select * from codeTable where generated_code=?",
                        new CodeMapper(), code.getGenerated_code())
                .stream().findFirst().orElse(null);
        if (activation==null){
            response = Either.left("El codigo es incorrecto");
        } else if (activation.getExpiration_date().before(Time.valueOf(LocalTime.now()))){
            response = Either.left("Ha concluido el tiempo para activar la cuenta");
        } else {
            template.update("update loginConHash set active=1 where user=?", activation.getUser_code());
            template.update("delete from codeTable where generated_code=?", code.getGenerated_code());
            response = Either.right(activation);
        }
        return response;
    }

    @Override
    public Either<String, Login> recuperarContrasena(Login login){
        Either<String, Login> response;
        JdbcTemplate template = new JdbcTemplate(pool.getDataSource());
        Login log = template.query("select from loginConHash where user=?", new LoginMapper(), login.getUser())
                .stream().findFirst().orElse(null);

        if (log != null){
            template.update("update loginConHash set password=? where user=?",
                    login.getPassword(), login.getUser());
            response = Either.right(log);
        }  else {
            response = Either.left("Error al cambiar la contraseña, no existe el usuario");
        }

        return response;
    }





}
