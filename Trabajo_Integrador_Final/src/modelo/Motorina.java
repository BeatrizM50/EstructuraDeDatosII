package modelo;

public class Motorina {

    private int id;
    private String modelo;
    private String categoria;
    private String motor;
    private String bateria;
    private String carnetIdentidad;
     private int idTaller;
    private boolean pendiente;

    public Motorina(int id,  String modelo, String categoria, String motor, String bateria, String carnetIdentidad,int idTaller, boolean pendiente) {
        this.id = id;
        this.idTaller = idTaller;
        this.modelo = modelo;
        this.categoria = categoria;
        this.motor = motor;
        this.bateria = bateria;
        this.carnetIdentidad = carnetIdentidad;
        this.pendiente = pendiente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(int idTaller) {
        this.idTaller = idTaller;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getBateria() {
        return bateria;
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }

    public String getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(String carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    public boolean isPendiente() {
        return pendiente;
    }

    public void setPendiente(boolean pendiente) {
        this.pendiente = pendiente;
    }
    
}