package Multilista;

import modelo.Trabajador;

public class Lista_de_Trabajadores {

    private Lista_de_Trabajadores next;
    private Trabajador trabajador;
    private Lista_de_Clientes listaClientes;

    public Lista_de_Trabajadores(Trabajador trabajador) {
        this.next = null;
        this.trabajador = trabajador;
        this.listaClientes = null;
    }

    public Lista_de_Trabajadores getNext() {
        return next;
    }

    public void setNext(Lista_de_Trabajadores next) {
        this.next = next;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Lista_de_Clientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Lista_de_Clientes listaClientes) {
        this.listaClientes = listaClientes;
    }
}
