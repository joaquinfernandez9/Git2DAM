package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Login;

public interface DaoLogin {
    Single<Either<String, Login>> log(Login log);

    Single<Either<String, Login>> register(Login log);
}
