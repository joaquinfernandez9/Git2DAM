package dao;

import domainServ.error.ContraseñaErronea;
import domainServ.error.NotFoundException;
import domain.modelo.Usuario;

import java.util.List;
import java.util.Set;

public class DaoLogin {
    private final List<Usuario> usuarios = List.of(
            new Usuario("curioso", "curioso", Set.of("curioso")),
            new Usuario("biologo", "biologo", Set.of("biologo")),
            new Usuario("raton", "raton", Set.of("raton")),
            new Usuario("espia", "espia", Set.of("espia")),
            new Usuario("informe", "informe", Set.of("informe", "NIVEL 1")),
            new Usuario("informe2", "informe2", Set.of("informe", "NIVEL 2"))
    );


    public List<Usuario> getAll(){
        return usuarios;
    }

    //get user
    public Usuario comprobarUsuario(String name) {
        return usuarios.stream()
                .filter(usuario ->
                        usuario.getUsuario().equals(name))
                .findFirst()
                .orElseThrow(() -> {
                    throw new NotFoundException("no user found");
                });
    }

    //validate user
    public Usuario validate(String name, String password) {
        Usuario user = comprobarUsuario(name);
        if (!user.getContraseña().equals(password)) {
            throw new ContraseñaErronea("Not a valid password");
        } else return user;
    }


}
