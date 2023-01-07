package dao;

import io.vavr.control.Either;
import model.Code;
import model.Login;

public interface DaoLogin {
    Either<String, Login> login(String userName, String password);

    Either<String, Login> register(Login log, Code code);

    Either<String, Code> activarCuenta(Code code);

    Either<String, Login> recuperarContrasena(Login log);

}
