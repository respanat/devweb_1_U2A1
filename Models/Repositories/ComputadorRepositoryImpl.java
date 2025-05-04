package Models.Repositories;

import Models.Entities.Computador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputadorRepositoryImpl implements ComputadorRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/act2_devweb?serverTimezone=UTC";
    private static final String JDBC_USER = "ramiro_espana";
    private static final String JDBC_PASSWORD = "AbcdeUdeC";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    @Override
    public Computador findById(int id) {
        String sql = "SELECT id, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio FROM Computadores WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToComputador(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Computador> findAll() {
        String sql = "SELECT id, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio FROM Computadores";
        List<Computador> computadores = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                computadores.add(mapResultSetToComputador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadores;
    }

    @Override
    public void save(Computador computador) {
        String sql = "INSERT INTO Computadores (marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, computador.getMarca());
            pstmt.setString(2, computador.getCategoria());
            pstmt.setString(3, computador.getMarcaCpu());
            pstmt.setString(4, computador.getVelocidadCpu());
            pstmt.setString(5, computador.getTecnologiaRam());
            pstmt.setString(6, computador.getCapacidadRam());
            pstmt.setString(7, computador.getTecnologiaDisco());
            pstmt.setString(8, computador.getCapacidadDisco());
            pstmt.setInt(9, computador.getNumPuertosUSB());
            pstmt.setInt(10, computador.getNumPuertosHDMI());
            pstmt.setString(11, computador.getMarcaMonitor());
            pstmt.setDouble(12, computador.getPulgadas());
            pstmt.setDouble(13, computador.getPrecio());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Computador computador) {
        String sql = "UPDATE Computadores SET marca = ?, categoria = ?, marcaCpu = ?, velocidadCpu = ?, tecnologiaRam = ?, capacidadRam = ?, tecnologiaDisco = ?, capacidadDisco = ?, numPuertosUSB = ?, numPuertosHDMI = ?, MarcaMonitor = ?, pulgadas = ?, precio = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, computador.getMarca());
            pstmt.setString(2, computador.getCategoria());
            pstmt.setString(3, computador.getMarcaCpu());
            pstmt.setString(4, computador.getVelocidadCpu());
            pstmt.setString(5, computador.getTecnologiaRam());
            pstmt.setString(6, computador.getCapacidadRam());
            pstmt.setString(7, computador.getTecnologiaDisco());
            pstmt.setString(8, computador.getCapacidadDisco());
            pstmt.setInt(9, computador.getNumPuertosUSB());
            pstmt.setInt(10, computador.getNumPuertosHDMI());
            pstmt.setString(11, computador.getMarcaMonitor());
            pstmt.setDouble(12, computador.getPulgadas());
            pstmt.setDouble(13, computador.getPrecio());
            pstmt.setInt(14, computador.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Computadores WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Computador> findByMarca(String marca) {
        String sql = "SELECT id, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio FROM Computadores WHERE marca = ?";
        List<Computador> computadores = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, marca);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                computadores.add(mapResultSetToComputador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadores;
    }

    @Override
    public List<Computador> findByCategoria(String categoria) {
        String sql = "SELECT id, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio FROM Computadores WHERE categoria = ?";
        List<Computador> computadores = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                computadores.add(mapResultSetToComputador(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computadores;
    }

    private Computador mapResultSetToComputador(ResultSet rs) throws SQLException {
        Computador computador = new Computador();
        computador.setId(rs.getInt("id"));
        computador.setMarca(rs.getString("marca"));
        computador.setCategoria(rs.getString("categoria"));
        computador.setMarcaCpu(rs.getString("marcaCpu"));
        computador.setVelocidadCpu(rs.getString("velocidadCpu"));
        computador.setTecnologiaRam(rs.getString("tecnologiaRam"));
        computador.setCapacidadRam(rs.getString("capacidadRam"));
        computador.setTecnologiaDisco(rs.getString("tecnologiaDisco"));
        computador.setCapacidadDisco(rs.getString("capacidadDisco"));
        computador.setNumPuertosUSB(rs.getInt("numPuertosUSB"));
        computador.setNumPuertosHDMI(rs.getInt("numPuertosHDMI"));
        computador.setMarcaMonitor(rs.getString("MarcaMonitor"));
        computador.setPulgadas(rs.getDouble("pulgadas"));
        computador.setPrecio(rs.getDouble("precio"));
        return computador;
    }
}