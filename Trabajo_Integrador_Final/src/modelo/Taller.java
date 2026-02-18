package modelo;

import Multilista.*;
import Estructuras.*;
import DAO.*;
import java.time.LocalDateTime;
import java.util.List;

public class Taller implements ITaller {

    private int id_taller = 1;

    private Trabajador trabajadorActivo;

    private Lista_de_Trabajadores listaTrabajadores;
    private Cola<Motorina> pendientes;
    private Pila<Motorina> atendidos;
    private Pila<HistorialCambios> historialCambios;

    private HistorialCambiosDAO historialDAO;
    private TrabajadorDAO trabajadorDAO;
    private ClienteDAO clienteDAO;
    private MotorinaDAO motorinaDAO;
    private TallerDAO tallerDAO;

    public Taller(int id_taller,
            HistorialCambiosDAO historialDAO,
            TrabajadorDAO trabajadorDAO,
            ClienteDAO clienteDAO,
            MotorinaDAO motorinaDAO,
            TallerDAO tallerDAO) {

        this.historialDAO = historialDAO;
        this.trabajadorDAO = trabajadorDAO;
        this.clienteDAO = clienteDAO;
        this.motorinaDAO = motorinaDAO;
        this.tallerDAO = tallerDAO;
        this.id_taller = id_taller;
        this.trabajadorActivo = null;
        this.listaTrabajadores = new Lista_de_Trabajadores(null);
        this.pendientes = new Cola<>();
        this.atendidos = new Pila<>();
        this.historialCambios = new Pila<>();
    }

    private void registrarCambio(HistorialCambios descripcion) {
        historialCambios.Push(descripcion);
    }

    @Override
    public int contadorMotorinas() {
       int contador = 0 ;
         Cola<Motorina> motoAux = motorinaDAO.listarTodas();
         Cola<Motorina> aux = new Cola<>();
          if (motoAux == null) {
             return 0;
           }else{
              while (motoAux!=null) {
                contador++;
                aux.add(motoAux.poll());
            }
              return contador;
          }
        
    }

    @Override
    public int contadorCambios() {
        if (historialCambios.isEmpty()) {
            return 0;
        } else {
            int contador = 0;
            Pila<HistorialCambios> auxiliar1 = new Pila<>();
            while (!historialCambios.isEmpty()) {
                contador++;
                auxiliar1.Push(historialCambios.Pop());
            }
            while (!auxiliar1.isEmpty()) {
                historialCambios.Push(auxiliar1.Pop());
            }

            return contador;
        }
    }

    @Override
    public int contadorTrabajador() {
        if (listaTrabajadores == null || listaTrabajadores.getTrabajador() == null) {
            return 0;
        }

        int contador = 0;
        Lista_de_Trabajadores aux = listaTrabajadores;

        while (aux != null && aux.getTrabajador() != null) {
            contador++;
            aux = aux.getNext();
        }

        return contador;
    }

    @Override
    public int contadorPendientes() {
        if (pendientes.isEmpty()) {
            return 0;
        } else {
            int contador = 0;
            Cola<Motorina> auxiliar1 = new Cola<>();
            while (!pendientes.isEmpty()) {
                contador++;
                auxiliar1.add(pendientes.poll());
            }
            while (!auxiliar1.isEmpty()) {
                pendientes.add(auxiliar1.poll());
            }

            return contador;
        }
    }

    @Override
    public boolean login(String usuario, String contrasena) {
        List<Trabajador> trabajadores = trabajadorDAO.listarPorTaller(id_taller);
        System.out.println("Trabajadores cargados : " + trabajadores.size());
        for (Trabajador t : trabajadores) {
            if (t.getUsuario().equals(usuario)
                    && t.getContraseña().equals(contrasena)) {
                
                cargarDatos();
                trabajadorActivo = t;
                int nh = contadorCambios();
                HistorialCambios h = new HistorialCambios(nh, 1, nh + " -Trabajador " + t.getNombre() + " inicio sesion.", LocalDateTime.now());
                historialDAO.insertar(h);

                return true;
            }
        }
        return false;
    }

    @Override
    public void registrarTrabajador(String usuario, String nombre, String apellido, String contraseña) {
        int id_trabajador = contadorTrabajador();
        Trabajador nuevo = new Trabajador(0 + id_trabajador++, usuario, nombre, apellido, contraseña, this.id_taller);
        trabajadorDAO.insertar(nuevo);

        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, 1, nh + " -Trabajador " + nuevo.getNombre() + " Registrado.", LocalDateTime.now());
        historialDAO.insertar(h);

        cargarDatos();

    }

    @Override
    public void cargarDatos() {

        // limpiar memoria---------------------
        historialCambios = new Pila<>();
        listaTrabajadores = null;
        pendientes = new Cola<>();
        atendidos = new Pila<>();

        //Trabajadores ------------------
        List<Trabajador> trabajadores = trabajadorDAO.listarPorTaller(id_taller);
        Lista_de_Trabajadores headTrab = null;

        for (Trabajador t : trabajadores) {
            Lista_de_Trabajadores nodo = new Lista_de_Trabajadores(t);
            nodo.setNext(headTrab);
            headTrab = nodo;
        }
        listaTrabajadores = headTrab;

        if (listaTrabajadores == null) {
            return;
        }

        //  Clientes y Motorinas---------------------------
        List<Cliente> clientes = clienteDAO.listarPorTaller(1);
        Lista_de_Clientes headClientes = null;

        for (Cliente c : clientes) {

            List<Motorina> motorinas = motorinaDAO.listarPorCliente(c.getCarnet_identidad());
            Lista_de_Motorinas headMot = null;

            for (Motorina m : motorinas) {
                Lista_de_Motorinas nodoM = new Lista_de_Motorinas(m);
                nodoM.setNext(headMot);
                headMot = nodoM;
            }

            Lista_de_Clientes nodoC = new Lista_de_Clientes(c);
            nodoC.setListaMotorinas(headMot);
            nodoC.setNext(headClientes);
            headClientes = nodoC;
        }

        // clientes cuelgan del primer trabajador
        listaTrabajadores.setListaClientes(headClientes);

        // Pendientes-----------------------------
        Cola<Motorina> listaPendientes = motorinaDAO.motorinasPendientes();
        if (listaPendientes != null) {
            while (!listaPendientes.isEmpty()) {
                pendientes.add(listaPendientes.poll());
            }
        }

        // Atendidos---------------------------------
        Cola<Motorina> listaAtendidas = motorinaDAO.motorinasAtendidas();
        if (listaAtendidas != null) {
            while (!listaAtendidas.isEmpty()) {
                atendidos.Push(listaAtendidas.poll());
            }
        }
        //Historial
        Cola<HistorialCambios> historialAux = historialDAO.listarTodos();
        if (historialAux != null) {
            while (!historialAux.isEmpty()) {
                HistorialCambios h = historialAux.poll();
                historialCambios.Push(h);
            }
        }

    }

    @Override
    public boolean existeIdCliente( String idCliente){
     Cliente c = clienteDAO.buscarPorId(idCliente);
     if(c==null){
     return false;
     }
     return true;
    }

    public void modificarMotorina(Motorina m) {
        if (m == null) return;
        motorinaDAO.actualizar(m);
        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, id_taller, nh + " - Motorina " + m.getId() + " modificada.", LocalDateTime.now());
        historialDAO.insertar(h);
        cargarDatos();
    }

    public void modificarCliente(Cliente c) {
        if (c == null) return;
        clienteDAO.actualizarCliente(c);
        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, id_taller, nh + " - Cliente " + c.getCarnet_identidad() + " modificado.", LocalDateTime.now());
        historialDAO.insertar(h);
        cargarDatos();
    }
    
    @Override
    public boolean existeUsuario(String Usuaraio){
        Trabajador t = trabajadorDAO.buscarPorUsuario(Usuaraio);
        if(t==null){
            return false;
        }
    return true;
    }
    
    @Override
    public void agregarMotorina(boolean clienteExistente, String idCliente, String nombre, String apellido,
            String modelo, String categoria, String motor, String bateria, boolean isPendiente) {
        if (!clienteExistente) {
             Cliente c = new Cliente(idCliente, nombre, apellido, this.id_taller);
            clienteDAO.insertar(c);
        }
        Motorina m = new Motorina(0, modelo, categoria, motor, bateria, idCliente, this.id_taller, true);
        motorinaDAO.insertar(m);

        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, 1, nh + " - Motorina : " + m.getId() + ": Agregada.", LocalDateTime.now());
        historialDAO.insertar(h);

        cargarDatos();
    }

    @Override
    public boolean atenderMotorina(int idTrabajador) {

        if (pendientes.isEmpty()) {
            return false;
        }

        Motorina p = pendientes.poll();
        p.setPendiente(false);
        motorinaDAO.cambiar_a_Atendido(p.getId());
        atendidos.Push(p);

        // Si, tras atender esta motorina, todas las motorinas del cliente están atendidas,
        // entonces eliminar al cliente
        String carnet = p.getCarnetIdentidad();
        boolean tienePendientes = false;
        List<Motorina> motorinasCliente = motorinaDAO.listarPorCliente(carnet);
        if (motorinasCliente != null) {
            for (Motorina m : motorinasCliente) {
                if (m.isPendiente()) {
                    tienePendientes = true;
                    break;
                }
            }
        }

        if (!tienePendientes) {
            // eliminar cliente de la base de datos y registrar en historial
            clienteDAO.eliminarPorId(carnet);
            cargarDatos();
            int nhDel = contadorCambios();
            HistorialCambios hDel = new HistorialCambios(nhDel, id_taller, nhDel + " - Cliente " + carnet + " eliminado (todas sus motorinas atendidas).", LocalDateTime.now());
            historialDAO.insertar(hDel);
        }

        // registrar en historial
        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, id_taller, nh + "- Motorina " + p.getId() + " atendido", LocalDateTime.now());
        historialDAO.insertar(h);
 
        cargarDatos();
        return true;
    }

    @Override
    public void eliminarCliente(String carnetId) {
        System.out.println("id :" + carnetId);
        clienteDAO.eliminarPorId(carnetId);

         cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, 1, nh + " -Cliente Eliminado.", LocalDateTime.now());
        historialDAO.insertar(h);

        cargarDatos();
    }

    @Override
    public void cerrarSesion() {
        cargarDatos();
        int nh = contadorCambios();
        HistorialCambios h = new HistorialCambios(nh, 1, nh + " -Sesion Cerrada.", LocalDateTime.now());
        registrarCambio(h);
        historialDAO.insertar(h);

        cargarDatos();
    }

    @Override
    public Cliente buscarClientePorId(String idCliente) {
        return clienteDAO.buscarPorId(idCliente);
    }

    @Override
    public Trabajador buscarTrabajadorPorUsuario(String Usuario) {
        return trabajadorDAO.buscarPorUsuario(Usuario);
    }

    public int getId_taller() {
        return id_taller;
    }

    public Trabajador getTrabajadorActivo() {
        return trabajadorActivo;
    }

    public Cola<Motorina> getPendientes() {
        return pendientes;
    }

    public Pila<Motorina> getAtendidos() {
        return atendidos;
    }

    public Pila<HistorialCambios> getHistorialCambios() {
        return historialCambios;
    }

    private void eliminarMotorinaDeMemoria(int idMotorina) {
        Lista_de_Trabajadores auxTrab = listaTrabajadores;

        while (auxTrab != null && auxTrab.getTrabajador() != null) {
            Lista_de_Clientes actualC = auxTrab.getListaClientes();

            while (actualC != null && actualC.getCliente() != null) {
                Lista_de_Motorinas actualM = actualC.getListaMotorinas();
                Lista_de_Motorinas previoM = null;

                while (actualM != null && actualM.getMotorina() != null) {
                    if (actualM.getMotorina().getId() == idMotorina) {
                        if (previoM == null) {
                            actualC.setListaMotorinas(actualM.getNext());
                        } else {
                            previoM.setNext(actualM.getNext());
                        }
                        return; // motorina eliminada, salimos
                    }
                    previoM = actualM;
                    actualM = actualM.getNext();
                }

                actualC = actualC.getNext();
            }

            auxTrab = auxTrab.getNext();
        }
    }

    public Lista_de_Trabajadores getListaTrabajadores() {
        return listaTrabajadores;
    }

    public void setListaTrabajadores(Lista_de_Trabajadores listaTrabajadores) {
        this.listaTrabajadores = listaTrabajadores;
    }

    public TrabajadorDAO getTrabajadorDAO() {
        return trabajadorDAO;
    }

    public void setTrabajadorDAO(TrabajadorDAO trabajadorDAO) {
        this.trabajadorDAO = trabajadorDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public MotorinaDAO getMotorinaDAO() {
        return motorinaDAO;
    }

    public void setMotorinaDAO(MotorinaDAO motorinaDAO) {
        this.motorinaDAO = motorinaDAO;
    }

    public TallerDAO getTallerDAO() {
        return tallerDAO;
    }

    public void setTallerDAO(TallerDAO tallerDAO) {
        this.tallerDAO = tallerDAO;
    }

}
