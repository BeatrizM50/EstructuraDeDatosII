package Multilista;

import modelo.Motorina;

public class Lista_de_Motorinas {

    private Lista_de_Motorinas next;
    private Motorina motorina;

    public Lista_de_Motorinas(Motorina motorina) {
        this.next = null;
        this.motorina = motorina;
    }

    public Lista_de_Motorinas getNext() {
        return next;
    }

    public void setNext(Lista_de_Motorinas next) {
        this.next = next;
    }

    public Motorina getMotorina() {
        return motorina;
    }

    public void setMotorina(Motorina motorina) {
        this.motorina = motorina;
    }
}
