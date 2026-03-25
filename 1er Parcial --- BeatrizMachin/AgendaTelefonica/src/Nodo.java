public class Nodo {
    Contacto contacto;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(Contacto contacto) {
        this.contacto = contacto;
        this.izquierda = null;
        this.derecha = null;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
}