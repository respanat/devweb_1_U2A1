package Controllers;

import Models.Entities.Computador;
import Models.Entities.Usuario;
import Models.Services.ComputadorService;
import Models.Repositories.ComputadorRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/computadores/*")
public class ComputadorController extends HttpServlet {
    private ComputadorService computadorService;

    @Override
    public void init() throws ServletException {
        computadorService = new ComputadorService(new ComputadorRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/usuarios/login");
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            listarTodosComputadores(request, response);
        } else if (pathInfo.equals("/agregar")) {
            mostrarFormularioAgregarComputador(request, response);
        } else if (pathInfo.equals("/buscar")) {
            mostrarFormularioBuscarComputador(request, response);
        } else if (pathInfo.startsWith("/editar/")) {
            obtenerComputadorParaEditar(request, response);
        } else if (pathInfo.startsWith("/eliminar/")) {
            eliminarComputador(request, response);
        } else if (pathInfo.equals("/listar")) {
            listarTodosComputadores(request, response);
        } else if (pathInfo.equals("/listar-personalizado")) {
            // Lógica para listar computadores con criterios personalizados
            response.getWriter().println("Funcionalidad de listado personalizado de computadores aún no implementada.");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/usuarios/login");
            return;
        }

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (pathInfo.equals("/agregar")) {
            agregarComputador(request, response);
        } else if (pathInfo.equals("/editar")) {
            actualizarComputador(request, response);
        } else if (pathInfo.equals("/buscar")) {
            buscarComputador(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void mostrarFormularioAgregarComputador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/computadores/agregar.jsp").forward(request, response);
    }

    private void agregarComputador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String marcaCpu = request.getParameter("marcaCpu");
        String velocidadCpu = request.getParameter("velocidadCpu");
        String tecnologiaRam = request.getParameter("tecnologiaRam");
        String capacidadRam = request.getParameter("capacidadRam");
        String tecnologiaDisco = request.getParameter("tecnologiaDisco");
        String capacidadDisco = request.getParameter("capacidadDisco");
        int numPuertosUSB = Integer.parseInt(request.getParameter("numPuertosUSB"));
        int numPuertosHDMI = Integer.parseInt(request.getParameter("numPuertosHDMI"));
        String MarcaMonitor = request.getParameter("MarcaMonitor");
        double pulgadas = Double.parseDouble(request.getParameter("pulgadas"));
        double precio = Double.parseDouble(request.getParameter("precio"));

        Computador computador = new Computador(0, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio);
        computadorService.saveComputador(computador);
        response.sendRedirect(request.getContextPath() + "/computadores/listar");
    }

    private void listarTodosComputadores(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Computador> computadores = computadorService.findAllComputadores();
        request.setAttribute("computadores", computadores);
        request.getRequestDispatcher("/Views/forms/computadores/listar_todo.jsp").forward(request, response);
    }

    private void mostrarFormularioBuscarComputador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/computadores/buscar.jsp").forward(request, response);
    }

    private void buscarComputador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterio = request.getParameter("criterio");
        String valor = request.getParameter("valor");
        List<Computador> computadoresEncontrados = null;

        if ("id".equals(criterio)) {
            try {
                Computador computador = computadorService.findComputadorById(Integer.parseInt(valor));
                if (computador != null) {
                    computadoresEncontrados = List.of(computador);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El ID debe ser un número.");
            }
        } else if ("marca".equals(criterio)) {
            computadoresEncontrados = computadorService.findComputadoresByMarca(valor);
        } else if ("categoria".equals(criterio)) {
            computadoresEncontrados = computadorService.findComputadoresByCategoria(valor);
        }

        request.setAttribute("computadores", computadoresEncontrados);
        request.getRequestDispatcher("/Views/forms/computadores/buscar.jsp").forward(request, response);
    }

    private void obtenerComputadorParaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getPathInfo().substring("/editar/".length());
            int id = Integer.parseInt(idStr);
            Computador computador = computadorService.findComputadorById(id);
            if (computador != null) {
                request.setAttribute("computador", computador);
                request.getRequestDispatcher("/Views/forms/computadores/agregar.jsp").forward(request, response); // Reutilizamos el formulario de agregar para editar
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Computador no encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de computador inválido.");
        }
    }

    private void actualizarComputador(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String marca = request.getParameter("marca");
        String categoria = request.getParameter("categoria");
        String marcaCpu = request.getParameter("marcaCpu");
        String velocidadCpu = request.getParameter("velocidadCpu");
        String tecnologiaRam = request.getParameter("tecnologiaRam");
        String capacidadRam = request.getParameter("capacidadRam");
        String tecnologiaDisco = request.getParameter("tecnologiaDisco");
        String capacidadDisco = request.getParameter("capacidadDisco");
        int numPuertosUSB = Integer.parseInt(request.getParameter("numPuertosUSB"));
        int numPuertosHDMI = Integer.parseInt(request.getParameter("numPuertosHDMI"));
        String MarcaMonitor = request.getParameter("MarcaMonitor");
        double pulgadas = Double.parseDouble(request.getParameter("pulgadas"));
        double precio = Double.parseDouble(
            request.getParameter("precio"));

        Computador computador = new Computador(id, marca, categoria, marcaCpu, velocidadCpu, tecnologiaRam, capacidadRam, tecnologiaDisco, capacidadDisco, numPuertosUSB, numPuertosHDMI, MarcaMonitor, pulgadas, precio);
        computadorService.updateComputador(computador);
        response.sendRedirect(request.getContextPath() + "/computadores/listar");
    }

    private void eliminarComputador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idStr = request.getPathInfo().substring("/eliminar/".length());
            int id = Integer.parseInt(idStr);
            computadorService.deleteComputador(id);
            response.sendRedirect(request.getContextPath() + "/computadores/listar");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de computador inválido.");
        }
    }
}