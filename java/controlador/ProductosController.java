package controlador;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Productos;
import modelo.ProductosDAO;
import java.sql.Date;
@WebServlet(name = "ProductosController", urlPatterns = {"/ProductosController"})
public class ProductosController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductosDAO productosDAO = new ProductosDAO();
        String accion;
        RequestDispatcher dispatcher = null;
        accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            dispatcher = request.getRequestDispatcher("Productos/index_productos.jsp");
            List<Productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        } else if (accion.equals("listar_eventos")) {
            dispatcher = request.getRequestDispatcher("Productos/index_productos_eventos.jsp");
            List<Productos> listaProductos_eventos = productosDAO.listarProductos_eventos();
            request.setAttribute("lista_Eventos", listaProductos_eventos);
        } else if (accion.equals("listar_cursos")) {
            dispatcher = request.getRequestDispatcher("Productos/index_productos_cursos.jsp");
            List<Productos> listaProductos_cursos = productosDAO.listarProductos_cursos();
            request.setAttribute("lista_Cursos", listaProductos_cursos);
        } else if (accion.equals("listar_comidas")) {
            dispatcher = request.getRequestDispatcher("Productos/index_productos_comidas.jsp");
            List<Productos> listaProductos_comidas = productosDAO.listarProductos_comidas();
            request.setAttribute("lista_Comidas", listaProductos_comidas);
        } else if (accion.equals("listar_calendario")) {
            dispatcher = request.getRequestDispatcher("Productos/index_calendario.jsp");
            List<Productos> listaProductos_calendario = productosDAO.listarCalendario();
            request.setAttribute("lista_Calendario", listaProductos_calendario);
        }  else if (accion.equals("nuevo")) {
            dispatcher = request.getRequestDispatcher("Productos/nuevo_productos.jsp");
        } else if (accion.equals("insertar")) {
            String tipoCodigo = request.getParameter("tipoCodigo");
            String numeroCodigo = request.getParameter("numeroCodigo");
            String codigo = tipoCodigo + numeroCodigo;
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            java.sql.Date fecha = java.sql.Date.valueOf(request.getParameter("fecha"));
            Productos producto = new Productos(0, codigo, nombre, precio, existencia, fecha);
            productosDAO.insertar(producto);
            dispatcher = request.getRequestDispatcher("Productos/index_productos.jsp");
            List<Productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        } else if (accion.equals("modificar")) {
            dispatcher = request.getRequestDispatcher("Productos/modificar_productos.jsp");
            int id = Integer.parseInt(request.getParameter("id"));
            Productos producto = productosDAO.mostrarProducto(id);
            request.setAttribute("producto", producto);
        } else if (accion.equals("actualizar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            Double precio = Double.parseDouble(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            Date fecha = null;
            try {
                fecha = Date.valueOf(request.getParameter("fecha"));
            } catch (IllegalArgumentException e) {
                System.out.println("Error al convertir la fecha: " + e.toString());
            }
            Productos producto = new Productos(id, codigo, nombre, precio, existencia, fecha);
            productosDAO.actualizar(producto);
            dispatcher = request.getRequestDispatcher("index.html");
            List<Productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            productosDAO.eliminar(id);
            dispatcher = request.getRequestDispatcher("Productos/index_productos.jsp");
            List<Productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
        } else if (accion.equals("eliminar_comidas")) {
            int id = Integer.parseInt(request.getParameter("id"));
            productosDAO.eliminar(id);
            dispatcher = request.getRequestDispatcher("Productos/index_productos_comidas.jsp");
            List<Productos> listaProductos_comidas = productosDAO.listarProductos_comidas();
            request.setAttribute("lista_Comidas", listaProductos_comidas);
        } else if (accion.equals("eliminar_eventos")) {
            int id = Integer.parseInt(request.getParameter("id"));
            productosDAO.eliminar(id);
            dispatcher = request.getRequestDispatcher("Productos/index_productos_eventos.jsp");
            List<Productos> listaProductos_eventos = productosDAO.listarProductos_eventos();
            request.setAttribute("lista_Eventos", listaProductos_eventos);
        } else if (accion.equals("eliminar_cursos")) {
            int id = Integer.parseInt(request.getParameter("id"));
            productosDAO.eliminar(id);
            dispatcher = request.getRequestDispatcher("Productos/index_productos_cursos.jsp");
            List<Productos> listaProductos_cursos = productosDAO.listarProductos_cursos();
            request.setAttribute("lista_Cursos", listaProductos_cursos);
        } else if (accion.equals("calendario")) {
            dispatcher = request.getRequestDispatcher("Productos/index_calendario.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("Productos/index_productos.jsp");
            List<Productos> listaProductos = productosDAO.listarProductos();
            request.setAttribute("lista", listaProductos);
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