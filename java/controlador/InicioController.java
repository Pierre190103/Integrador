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
@WebServlet(name = "InicioController", urlPatterns = {"/InicioController"})
public class InicioController extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion;
        RequestDispatcher dispatcher = null;
        accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
        } else if (accion.equals("inicio")) {
            dispatcher = request.getRequestDispatcher("Inicio/Iniciar_Sesion.jsp");
        }else if (accion.equals("registro")) {
            dispatcher = request.getRequestDispatcher("Inicio/Registrarme.jsp");
        } else {
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