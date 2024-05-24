package modelo;
public class Usuarios {
    private int id;
    private String codigo, nombre,contraseña, cargo;
    public Usuarios(int id, String codigo, String nombre, String contraseña, String cargo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.cargo = cargo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}