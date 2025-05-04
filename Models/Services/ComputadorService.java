package Models.Services;

import Models.Entities.Computador;
import Models.Repositories.ComputadorRepository;
import java.util.List;

public class ComputadorService {
    private final ComputadorRepository computadorRepository;

    public ComputadorService(ComputadorRepository computadorRepository) {
        this.computadorRepository = computadorRepository;
    }

    public Computador findComputadorById(int id) {
        return computadorRepository.findById(id);
    }

    public List<Computador> findAllComputadores() {
        return computadorRepository.findAll();
    }

    public void saveComputador(Computador computador) {
        // Aquí podrías agregar validaciones o lógica de negocio antes de guardar
        computadorRepository.save(computador);
    }

    public void updateComputador(Computador computador) {
        // Aquí podrías agregar validaciones o lógica de negocio antes de actualizar
        computadorRepository.update(computador);
    }

    public void deleteComputador(int id) {
        // Aquí podrías agregar lógica de negocio antes de eliminar
        computadorRepository.delete(id);
    }

    public List<Computador> findComputadoresByMarca(String marca) {
        return computadorRepository.findByMarca(marca);
    }

    public List<Computador> findComputadoresByCategoria(String categoria) {
        return computadorRepository.findByCategoria(categoria);
    }

    // Otros métodos de lógica de negocio relacionados con computadores
}