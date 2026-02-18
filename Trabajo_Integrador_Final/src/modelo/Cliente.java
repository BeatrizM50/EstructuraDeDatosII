package modelo;

public class Cliente {
    private String carnet_identidad;
    private String nombre;
    private String apellido;
    private int id_taller;

    public Cliente(String carnet_identidad, String nombre, String apellido, int id_taller) {
        this.carnet_identidad = carnet_identidad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.id_taller = id_taller;
    }

    public String getCarnet_identidad() {
        return carnet_identidad;
    }

    public void setCarnet_identidad(String carnet_identidad) {
        this.carnet_identidad = carnet_identidad;
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

    public int getId_taller() {
        return id_taller;
    }

    public void setId_taller(int id_taller) {
        this.id_taller = id_taller;
    }

    
}
