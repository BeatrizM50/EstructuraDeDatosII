package modelo;


import java.time.LocalDateTime;

public class HistorialCambios {

    private int id_historial;
    private int id_taller;
    private String descripcion;
    private LocalDateTime fechaHora;

    public HistorialCambios(int id_historial, int id_taller, String descripcion, LocalDateTime fechaHora) {
        this.id_historial = id_historial;
        this.id_taller = 1;
        this.descripcion = descripcion;
        this.fechaHora = fechaHora;
    }

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public int getId_taller() {
        return id_taller;
    }

    public void setId_taller(int id_taller) {
        this.id_taller = id_taller;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
   

}
