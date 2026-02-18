package modelo;

public class Trabajador {
    private int id_trabajador;
    private String usuario;
    private String nombre;
    private String apellido;
    private String contrasena;
    private int id_taller;

    public Trabajador(int id_trabajador, String usuario, String nombre, String apellido, String contraseña, int id_taller) {
        this.id_trabajador = id_trabajador;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contraseña;
        this.id_taller = id_taller;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contrasena;
    }

    public void setContraseña(String contraseña) {
        this.contrasena = contraseña;
    }

    public int getId_taller() {
        return id_taller;
    }

    public void setId_taller(int id_taller) {
        this.id_taller = id_taller;
    }
}
