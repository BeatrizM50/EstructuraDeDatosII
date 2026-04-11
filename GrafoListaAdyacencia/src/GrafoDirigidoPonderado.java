import java.util.*;

public class GrafoDirigidoPonderado<T> {
    private LinkedList<Nodo<T>> nodos;

    public GrafoDirigidoPonderado() {
        this.nodos = new LinkedList<>();
    }

    public boolean agregarNodo(T dato, int peso) {
        if (buscarNodo(dato) == null) {
            nodos.add(new Nodo<>(dato, peso));
            return true;
        }
        return false;
    }

    public Nodo<T> buscarNodo(T dato) {
        for (Nodo<T> nodo : nodos) {
            if (nodo.getDato().equals(dato)) {
                return nodo;
            }
        }
        return null;
    }

    public int getPesoNodo(T dato) {
        Nodo<T> nodo = buscarNodo(dato);
        return (nodo != null) ? nodo.getPeso() : -1;
    }

    public boolean agregarArista(T origen, T destino, int peso) {
        Nodo<T> nodoOrigen = buscarNodo(origen);
        Nodo<T> nodoDestino = buscarNodo(destino);

        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }

        nodoOrigen.agregarAdyacente(nodoDestino, peso);
        return true;
    }

    public boolean eliminarNodo(T dato) {
        Nodo<T> nodoEliminar = buscarNodo(dato);
        if (nodoEliminar == null) return false;

        for (Nodo<T> nodo : nodos) {
            nodo.eliminarAdyacente(nodoEliminar);
        }

        nodos.remove(nodoEliminar);
        return true;
    }
    public void DFS(T inicio) {
        Nodo<T> nodoInicio = buscarNodo(inicio);
        if (nodoInicio == null) return;

        Set<Nodo<T>> visitados = new HashSet<>();
        Stack<Nodo<T>> pila = new Stack<>();
        pila.push(nodoInicio);

        while (!pila.isEmpty()) {
            Nodo<T> actual = pila.pop();
            if (visitados.contains(actual)) continue;

            visitados.add(actual);
            System.out.println(actual.getDato() + " (Peso nodo: " + actual.getPeso() + ")");

            for (Arista<T> arista : actual.getAdyacentes()) {
                Nodo<T> vecino = arista.getDestino();
                if (!visitados.contains(vecino)) {
                    pila.push(vecino);
                }
            }
        }
    }
    public void BFS(T inicio) {
        Nodo<T> nodoInicio = buscarNodo(inicio);
        if (nodoInicio == null) return;

        Set<Nodo<T>> visitados = new HashSet<>();
        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(nodoInicio);

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();
            if (visitados.contains(actual)) continue;

            visitados.add(actual);
            System.out.println(actual.getDato() + " (Peso nodo: " + actual.getPeso() + ")");

            for (Arista<T> arista : actual.getAdyacentes()) {
                Nodo<T> vecino = arista.getDestino();
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                }
            }
        }
    }
}