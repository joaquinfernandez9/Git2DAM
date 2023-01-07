package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.java.Log;
import model.Login;
import io.reactivex.rxjava3.schedulers.Schedulers;
import services.LoginServ;

public class LoginViewModel {

    private final LoginServ serv;

    private final ObjectProperty<LoginState> state;


    @Inject
    public LoginViewModel(LoginServ serv) {
        this.serv = serv;
        state = new SimpleObjectProperty<>(new LoginState(null, null));
    }


    public void login(String username, String password){
        Login log = new Login(username, password);
        serv.log(log)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        state.set(new LoginState(either.getLeft(), null));
                    else
                        state.set(new LoginState(null, either.get()));
                });
    }

    public void register(String username, String password, String email, int idReader){
        Login log = new Login(username,password,email,idReader);
        serv.register(log)
                .subscribeOn(Schedulers.single())
                .subscribe(either -> {
                    if (either.isLeft())
                        //sacar alert?
                        state.set(new LoginState(either.getLeft(), null));
                    else
                        state.set(new LoginState(null, either.get()));
                });

    }

}
