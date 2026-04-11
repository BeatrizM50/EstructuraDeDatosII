public class Nodo<T> {
    private T dato;

    public Nodo(T dato) {
        this.dato = dato;
    }

    public T getDato() {
        return dato;
    }


    public void setDato(T dato) {
        this.dato = dato;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nodo<?> otro = (Nodo<?>) obj;
        return dato.equals(otro.dato);
    }

}
