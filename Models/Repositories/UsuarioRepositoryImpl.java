package Models.Repositories;

import Models.Entities.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/act1_devweb?serverTimezone=UTC";
    private static final String JDBC_USER = "ramiro_espana";
    private static final String JDBC_PASSWORD = "AbcdeUdeC";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public Usuario findById(int id) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password")); // Considerar no devolver la contraseña directamente
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password")); // Considerar no devolver la contraseña directamente
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void save(Usuario usuario) {
        String sql = "INSERT INTO Usuario (username, password, nombre, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword()); // En una aplicación real, esto debería ser un hash
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET username = ?, password = ?, nombre = ?, email = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getUsername());
            pstmt.setString(2, usuario.getPassword()); // En una aplicación real, esto debería ser un hash
            pstmt.setString(3, usuario.getNombre());
            pstmt.setString(4, usuario.getEmail());
            pstmt.setInt(5, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario findByUsername(String username) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE username = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password")); // Considerar no devolver la contraseña directamente
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Usuario findByEmail(String email) {
        String sql = "SELECT id, username, password, nombre, email FROM Usuario WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password")); // Considerar no devolver la contraseña directamente
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEmail(rs.getString("email"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}