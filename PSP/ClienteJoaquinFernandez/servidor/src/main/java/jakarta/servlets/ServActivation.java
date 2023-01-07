package jakarta.servlets;

import domain.services.LoginServ;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Code;

import java.io.IOException;

@WebServlet(name="ServActivation", value = "/activation")
public class ServActivation extends HttpServlet {

    private final LoginServ serv;

    @Inject
    public ServActivation(LoginServ serv) {
        this.serv = serv;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Code code= new Code();
        code.setGenerated_code(req.getParameter("code"));

        Either<String, Code> activate = serv.activarCuenta(code);
        if (activate.isLeft()){
            resp.getWriter().println(activate.getLeft());
        } else {
            resp.getWriter().println("El usuario se ha activado. Nombre de cuenta: " +activate.get().getUser_code());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
