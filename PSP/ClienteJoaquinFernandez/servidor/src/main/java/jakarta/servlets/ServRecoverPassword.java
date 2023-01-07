package jakarta.servlets;

import domain.services.LoginServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Code;

import java.io.IOException;

@WebServlet(name="ServRecoverPassword", value = "/passRecover")
public class ServRecoverPassword extends HttpServlet {

    private final LoginServ serv;

    @Inject
    public ServRecoverPassword(LoginServ serv) {
        this.serv = serv;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
