package modelo;
import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuariosDAO {
    Connection conexion;
    public UsuariosDAO() {
        Conexion con = new Conexion();
        conexion = con.getConexion();
    }
    public List<Usuarios> listarUsuarios() {
        PreparedStatement ps;
        ResultSet rs;
        List<Usuarios> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, contraseña, cargo FROM usuarios");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String contraseña = rs.getString("contraseña");
                String cargo = rs.getString("cargo");
                Usuarios usuarios = new Usuarios(id, codigo, nombre, contraseña, cargo);
                lista.add(usuarios);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.toString());
            return null;
        }
    }
    public Usuarios mostarUsuarios(int _id) {
        PreparedStatement ps;
        ResultSet rs;
        Usuarios usuario = null;
        List<Usuarios> lista = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("SELECT id, codigo, nombre, contraseña, cargo FROM usuarios WHERE id=?");
            ps.setInt(1, _id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String nombre = rs.getString("nombre");
                String contraseña = rs.getString("contraseña");
                String cargo = rs.getString("cargo");
                usuario = new Usuarios(id, codigo, nombre, contraseña, cargo);
            }
            return usuario;
        } catch (SQLException e) {
            System.out.println("Error al mostrar usuarios: " + e.toString());
            return null;
        }
    }
    public boolean insertar_usuario(Usuarios usuario) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("INSERT INTO usuarios (codigo, nombre, contraseña, cargo) VALUES (?,?,?,?)");
            ps.setString(1, usuario.getCodigo());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getContraseña());
            ps.setString(4, usuario.getCargo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar usuarios: " + e.toString());
            return false;
        }
    }
    public boolean actualizar_usuario(Usuarios usuario) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("UPDATE usuarios SET codigo=?, nombre=?, contraseña=?, cargo=? WHERE id=?");
            ps.setString(1, usuario.getCodigo());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getContraseña());
            ps.setString(4, usuario.getCargo());
            ps.setInt(5, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    public boolean eliminar_usuario(int _id) {
        PreparedStatement ps;
        try {
            ps = conexion.prepareStatement("DELETE FROM usuarios WHERE id=?");
            ps.setInt(1, _id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuarios: " + e.toString());
            return false;
        }
    }
}