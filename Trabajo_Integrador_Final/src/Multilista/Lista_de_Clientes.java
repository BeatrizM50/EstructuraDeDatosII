package Multilista;

import modelo.Cliente;

public class Lista_de_Clientes {

    private Lista_de_Clientes next;
    private Cliente cliente;
    private Lista_de_Motorinas listaMotorinas;

    public Lista_de_Clientes(Cliente cliente) {
        this.next = null;
        this.cliente = cliente;
        this.listaMotorinas = null;
    }

    public Lista_de_Clientes getNext() {
        return next;
    }

    public void setNext(Lista_de_Clientes next) {
        this.next = next;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Lista_de_Motorinas getListaMotorinas() {
        return listaMotorinas;
    }

    public void setListaMotorinas(Lista_de_Motorinas listaMotorinas) {
        this.listaMotorinas = listaMotorinas;
    }
}
