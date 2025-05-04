package Models.Repositories;

import Models.Entities.Usuario;
import java.util.List;

public interface UsuarioRepository {
    Usuario findById(int id);
    List<Usuario> findAll();
    void save(Usuario usuario);
    void update(Usuario usuario);
    void delete(int id);
    Usuario findByUsername(String username);
    Usuario findByEmail(String email);
}