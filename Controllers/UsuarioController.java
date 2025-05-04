package Controllers;

import Models.Entities.Usuario;
import Models.Services.UsuarioService;
import Models.Repositories.UsuarioRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios/*")
public class UsuarioController extends HttpServlet {
    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException {
        usuarioService = new UsuarioService(new UsuarioRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // Redirigir a alguna página por defecto o mostrar una lista
            listarTodosUsuarios(request, response);
        } else if (pathInfo.equals("/agregar")) {
            mostrarFormularioAgregarUsuario(request, response);
        } else if (pathInfo.equals("/buscar")) {
            mostrarFormularioBuscarUsuario(request, response);
        } else if (pathInfo.startsWith("/editar/")) {
            obtenerUsuarioParaEditar(request, response);
        } else if (pathInfo.startsWith("/eliminar/")) {
            eliminarUsuario(request, response);
        } else if (pathInfo.equals("/listar")) {
            listarTodosUsuarios(request, response);
        } else if (pathInfo.equals("/listar-personalizado")) {
            // Lógica para listar usuarios con criterios personalizados
            response.getWriter().println("Funcionalidad de listado personalizado de usuarios aún no implementada.");
        } else if (pathInfo.equals("/login")) {
            mostrarFormularioLogin(request, response);
        } else if (pathInfo.equals("/recordar-password")) {
            mostrarFormularioRecordarPassword(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            // No debería haber un POST a la raíz de /usuarios
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (pathInfo.equals("/agregar")) {
            agregarUsuario(request, response);
        } else if (pathInfo.equals("/editar")) {
            actualizarUsuario(request, response);
        } else if (pathInfo.equals("/buscar")) {
            buscarUsuario(request, response);
        } else if (pathInfo.equals("/login")) {
            procesarLogin(request, response);
        } else if (pathInfo.equals("/recordar-password")) {
            procesarRecordarPassword(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void mostrarFormularioAgregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/agregar.jsp").forward(request, response);
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario(0, username, password, nombre, email); // El ID se generará en la BD
        usuarioService.saveUsuario(usuario);
        response.sendRedirect(request.getContextPath() + "/usuarios/listar");
    }

    private void listarTodosUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioService.findAllUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/Views/forms/usuarios/listar_todo.jsp").forward(request, response);
    }

    private void mostrarFormularioBuscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/buscar.jsp").forward(request, response);
    }

    private void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String criterio = request.getParameter("criterio");
        String valor = request.getParameter("valor");
        Usuario usuarioEncontrado = null;

        if ("id".equals(criterio)) {
            try {
                int id = Integer.parseInt(valor);
                usuarioEncontrado = usuarioService.findUsuarioById(id);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "El ID debe ser un número.");
            }
        } else if ("username".equals(criterio)) {
            usuarioEncontrado = usuarioService.findUsuarioByUsername(valor);
        } else if ("email".equals(criterio)) {
            usuarioEncontrado = usuarioService.findUsuarioByEmail(valor);
        }

        request.setAttribute("usuario", usuarioEncontrado);
        request.getRequestDispatcher("/Views/forms/usuarios/buscar.jsp").forward(request, response);
    }

    private void obtenerUsuarioParaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getPathInfo().substring("/editar/".length());
            int id = Integer.parseInt(idStr);
            Usuario usuario = usuarioService.findUsuarioById(id);
            if (usuario != null) {
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/Views/forms/usuarios/agregar.jsp").forward(request, response); // Reutilizamos el formulario de agregar para editar
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuario inválido.");
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario(id, username, password, nombre, email);
        usuarioService.updateUsuario(usuario);
        response.sendRedirect(request.getContextPath() + "/usuarios/listar");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String idStr = request.getPathInfo().substring("/eliminar/".length());
            int id = Integer.parseInt(idStr);
            usuarioService.deleteUsuario(id);
            response.sendRedirect(request.getContextPath() + "/usuarios/listar");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuario inválido.");
        }
    }

    private void mostrarFormularioLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/login.jsp").forward(request, response);
    }

    private void procesarLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password"); // En una aplicación real, comparar con el hash

        Usuario usuario = usuarioService.findUsuarioByUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", usuario);
            response.sendRedirect(request.getContextPath() + "/computadores/listar"); // Redirigir a la lista de computadores tras el login
        } else {
            request.setAttribute("error", "Credenciales inválidas.");
            request.getRequestDispatcher("/Views/forms/usuarios/login.jsp").forward(request, response);
        }
    }

    private void mostrarFormularioRecordarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Views/forms/usuarios/recordar_password.jsp").forward(request, response);
    }

    private void procesarRecordarPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lógica para recordar la contraseña (envío de correo, etc.)
        response.getWriter().println("Funcionalidad de recordar contraseña aún no implementada.");
    }
}