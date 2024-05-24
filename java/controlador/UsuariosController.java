package controlador;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuarios;
import modelo.UsuariosDAO;
@WebServlet(name = "UsuariosController", urlPatterns = {"/UsuariosController"})
public class UsuariosController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuariosDAO usuariosDAO = new UsuariosDAO();
        String accion;
        RequestDispatcher dispatcher = null;
        accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("Usuarios/index_usuarios.jsp");
            List<Usuarios> listaUsuarios = usuariosDAO.listarUsuarios();
            request.setAttribute("lista", listaUsuarios);
        } else if (accion.equals("nuevo_usuarios")) {
            dispatcher = request.getRequestDispatcher("Usuarios/nuevo_usuarios.jsp");
        } else if (accion.equals("insertar_usuarios")) {
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String contraseña = request.getParameter("contraseña");
            String cargo = request.getParameter("cargo");
            Usuarios usuario = new Usuarios(0, codigo, nombre, contraseña, cargo);
            usuariosDAO.insertar_usuario(usuario);
            dispatcher = request.getRequestDispatcher("Usuarios/index_usuarios.jsp");
            List<Usuarios> listaUsuarios = usuariosDAO.listarUsuarios();
            request.setAttribute("lista", listaUsuarios);
        } else if (accion.equals("modificar_usuarios")) {
            dispatcher = request.getRequestDispatcher("Usuarios/modificar_usuarios.jsp");
            int id = Integer.parseInt(request.getParameter("id"));
            Usuarios usuario = usuariosDAO.mostarUsuarios(id);
            request.setAttribute("usuario", usuario);
        } else if (accion.equals("actualizar_usuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String contraseña = request.getParameter("contraseña");
            String cargo = request.getParameter("cargo");
            Usuarios usuario = new Usuarios(id, codigo, nombre, contraseña, cargo);
            usuariosDAO.actualizar_usuario(usuario);
            dispatcher = request.getRequestDispatcher("Usuarios/index_usuarios.jsp");
            List<Usuarios> listaUsuarios = usuariosDAO.listarUsuarios();
            request.setAttribute("lista", listaUsuarios);
        } else if (accion.equals("eliminar_usuarios")) {
            int id = Integer.parseInt(request.getParameter("id"));
            usuariosDAO.eliminar_usuario(id);
            dispatcher = request.getRequestDispatcher("Usuarios/index_usuarios.jsp");
            List<Usuarios> listaUsuarios = usuariosDAO.listarUsuarios();
            request.setAttribute("lista", listaUsuarios);
        } else {
            dispatcher = request.getRequestDispatcher("Usuarios/index_usuarios.jsp");
            List<Usuarios> listaUsuarios = usuariosDAO.listarUsuarios();
            request.setAttribute("lista", listaUsuarios);
        }
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
