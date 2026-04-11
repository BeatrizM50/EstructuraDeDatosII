import java.util.LinkedList;

public class Nodo<T> {
    private T dato;
    private int peso;
    private LinkedList<Arista<T>> adyacentes;

    public Nodo(T dato, int peso) {
        this.dato = dato;
        this.peso = peso;
        this.adyacentes = new LinkedList<>();
    }

    public T getDato() {
        return dato;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public LinkedList<Arista<T>> getAdyacentes() {
        return adyacentes;
    }

    public void agregarAdyacente(Nodo<T> destino, int peso) {
        adyacentes.add(new Arista<>(destino, peso));
    }

    public void eliminarAdyacente(Nodo<T> destino) {
        adyacentes.removeIf(arista -> arista.getDestino().equals(destino));
    }
}