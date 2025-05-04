package Models.Services;

import Models.Entities.Usuario;
import Models.Repositories.UsuarioRepository;
import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void saveUsuario(Usuario usuario) {
        package Models.Services;

import Models.Entities.Usuario;
import Models.Repositories.UsuarioRepository;
import java.util.List;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public void saveUsuario(Usuario usuario) {
        // Estas son validaciones (lógica de negocio) recomendable antes de guardar
        usuarioRepository.save(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        // Esta es la lógica del negocio previa al guardado
        usuarioRepository.update(usuario);
    }

    public void deleteUsuario(int id) {
        // Esta es la lógica del negocio previa al eliminar
        usuarioRepository.delete(id);
    }

    public Usuario findUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Otros métodos de lógica de negocio relacionados con usuarios
}
        usuarioRepository.save(usuario);
    }

    public void updateUsuario(Usuario usuario) {
        // Aquí podrías agregar validaciones o lógica de negocio antes de actualizar
        usuarioRepository.update(usuario);
    }

    public void deleteUsuario(int id) {
        // Aquí podrías agregar lógica de negocio antes de eliminar
        usuarioRepository.delete(id);
    }

    public Usuario findUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario findUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Otros métodos de lógica de negocio relacionados con usuarios
}