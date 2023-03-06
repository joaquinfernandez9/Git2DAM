package dao;

import domainServ.error.NotFoundException;
import domainServ.error.SinPermisoException;
import domain.modelo.Informe;
import domain.modelo.Usuario;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoInformes {

    private static final List<Informe> informes = new ArrayList<>();

    static {
        informes.add(new Informe("Informe 1", LocalDate.now(), "NIVEL 1"));
        informes.add(new Informe("Informe 2", LocalDate.now(), "NIVEL 2"));
        informes.add(new Informe("Informe 3", LocalDate.now(), "NIVEL 1"));
        informes.add(new Informe("Informe 4", LocalDate.now(), "NIVEL 1"));
        informes.add(new Informe("Informe 5", LocalDate.now(), "NIVEL 2"));
    }

    public DaoLogin login;

    @Inject
    public DaoInformes(DaoLogin login) {
        this.login = login;
    }

    //Una peticion de get informes donde según el role se le dará los informes correspondientes,
    //Los informes tienen dos tipos de roles NIVEL1, y NIVEL2, si alguien tiene el role NIVEL2 tb
    //tiene permiso para los de NIVEL1.
    public List<Informe> getInformes(String nombreUsuario) {
        Usuario user = login.comprobarUsuario(nombreUsuario);
        if (user.getRoles().contains("NIVEL 1")) {
            return informes.stream().filter(informe ->
                    informe.getRol().equals("Nivel 1")).toList();
        } else return informes;
    }

    //Una peticion de get de un informe concreto por id, filtrado por el role del usuario que lo
    //pida.
    public Informe getInforme(String nombreUsuario, String nombreInforme) {
        Usuario user = login.comprobarUsuario(nombreUsuario);
        Informe informe = informes.stream().filter(informe1 ->
                informe1.getNombre().equals(nombreInforme)).toList().get(0);
        if (informe != null) {
            if (informe.getRol().equals("NIVEL 1") && user.getRoles().contains("NIVEL 1")) {
                return informe;
            } else if (informe.getRol().equals("NIVEL 1") || informe.getRol().equals("NIVEL 2") && user.getRoles().contains("NIVEL 2")) {
                return informe;
            } else if (informe.getRol().equals("NIVEL 2") && user.getRoles().contains("NIVEL 1")) {
                throw new SinPermisoException("Tu nivel es muy bajo para ver ese informe");
            } else {
                return null;
            }
        } else {
            throw new NotFoundException("No se ha encontrado el informe");
        }
    }

    //Una peticion de añadir informe que solo lo podrán hacer los roles ESPIA.
    public Informe addInforme(Informe informe) {
        informes.add(informe);
        return informe;
    }

}
