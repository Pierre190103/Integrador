package modelo;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
public class ProductosDAO {
    Connection conexion;
    public ProductosDAO() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    public List<Productos> listarProductos() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia, fecha FROM productos WHERE codigo NOT LIKE 'COM%' AND codigo NOT LIKE 'CUR%' AND codigo NOT LIKE 'EVT%';");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                Date fecha = rs.getDate("fecha");
                Productos producto = new Productos(id, codigo, nombre, precio, existencia, fecha);
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
            return null;
        }
    }
    public List<Productos> listarCalendario() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia, fecha FROM productos ;");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                Date fecha = rs.getDate("fecha");
                Productos producto = new Productos(id, codigo, nombre, precio, existencia, fecha);
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
            return null;
        }
    }
    public List<Productos> listarProductos_eventos() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia,fecha FROM productos WHERE codigo LIKE 'EVT%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                Date fecha = rs.getDate("fecha");
                Productos producto = new Productos(id, codigo, nombre, precio, existencia, fecha);
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
            return null;
        }
    }
    public List<Productos> listarProductos_cursos() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia,fecha FROM productos WHERE codigo LIKE 'CUR%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                Date fecha = rs.getDate("fecha");
                Productos producto = new Productos(id, codigo, nombre, precio, existencia,  fecha);
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
            return null;
        }
    }
    public List<Productos> listarProductos_comidas() {
        PreparedStatement ps;
        ResultSet rs;
        List<Productos> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia,fecha FROM productos WHERE codigo LIKE 'COM%'");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                Double precio = rs.getDouble("precio");
                int existencia = rs.getInt("existencia");
                Date fecha = rs.getDate("fecha");
                Productos producto = new Productos(id, codigo, nombre, precio, existencia,  fecha);
                lista.add(producto);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.toString());
            return null;
        }
    }
    public Productos mostrarProducto(int _id) {
    PreparedStatement ps;
    ResultSet rs;
    Productos producto = null;
    try {
        ps = conexion.prepareStatement("SELECT id, codigo, nombre, precio, existencia, fecha FROM productos WHERE id=?");
        ps.setInt(1, _id);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String codigo = rs.getString("codigo");
            String nombre = rs.getString("nombre");
            Double precio = rs.getDouble("precio");
            int existencia = rs.getInt("existencia");
            Date fecha = rs.getDate("fecha");
            producto = new Productos(id, codigo, nombre, precio, existencia,  fecha);
        }
        return producto;
    } catch (SQLException e) {
        System.out.println("Error al mostrar producto: " + e.toString());
        return null;
    }
}
    public boolean insertar(Productos producto) {
    PreparedStatement ps;
    try {
        ps = conexion.prepareStatement("INSERT INTO productos (codigo, nombre, precio, existencia, fecha) VALUES (?,?,?,?,?)");
        ps.setString(1, producto.getCodigo());
        ps.setString(2, producto.getNombre());
        ps.setDouble(3, producto.getPrecio());
        ps.setInt(4, producto.getExistencia());
        ps.setDate(5, new java.sql.Date(producto.getFecha().getTime()));
        ps.execute();
        return true;
    } catch (SQLException e) {
        System.out.println("Error al insertar producto: " + e.toString());
        return false;
    }
}
    public boolean actualizar(Productos producto) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("UPDATE productos SET codigo=?, nombre=?, precio=?, existencia=?, fecha=? WHERE id=?");
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getExistencia());
            ps.setDate(5, (java.sql.Date) producto.getFecha());
            ps.setInt(6, producto.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean eliminar(int id) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("DELETE FROM productos WHERE id=?");
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.toString());
            return false;
        }
    }
}