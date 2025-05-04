package Models.Repositories;

import Models.Entities.Computador;
import java.util.List;

public interface ComputadorRepository {
    Computador findById(int id);
    List<Computador> findAll();
    void save(Computador computador);
    void update(Computador computador);
    void delete(int id);
    List<Computador> findByMarca(String marca);
    List<Computador> findByCategoria(String categoria);
}