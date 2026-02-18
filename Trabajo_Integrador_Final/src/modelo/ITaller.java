package modelo;

public interface ITaller {

    int contadorTrabajador();

    int contadorCambios();

    int contadorPendientes();

    int contadorMotorinas();

    boolean login(String usuario, String contraseña);

    void registrarTrabajador(String usuario, String nombre, String apellido, String contrasena);

    void cargarDatos();

    boolean existeIdCliente(String idCliente);
    
    boolean existeUsuario(String Usuaraio);

    void agregarMotorina(boolean clienteExistente, String idCliente, String nombreCliente, String apellidoCliente, String modelo, String categoria, String motor, String bateria, boolean isPendiente);

    boolean atenderMotorina(int idMotorina);

    void eliminarCliente(String idCliente);

    void cerrarSesion();

    Cliente buscarClientePorId(String idCliente);

   Trabajador buscarTrabajadorPorUsuario(String Usuario);
}
