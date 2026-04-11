import java.util.LinkedList;

public abstract class GrafoNoDirigido<T> extends GrafoAbstracto<T> {

    public GrafoNoDirigido(int capacidadInicial) {
        super(capacidadInicial);
    }


    @Override
    public boolean agregarArista(T origen, T destino, int peso) {
        int i = indiceDeNodo(origen);
        int j = indiceDeNodo(destino);

        if (i == -1 || j == -1) return false;


        matrizAdyacencia[i][j] = peso;
        matrizAdyacencia[j][i] = peso;
        return true;
    }

    @Override
    public boolean eliminarArista(T origen, T destino) {
        int i = indiceDeNodo(origen);
        int j = indiceDeNodo(destino);

        if (i == -1 || j == -1) return false;

        matrizAdyacencia[i][j] = 0;
        matrizAdyacencia[j][i] = 0;
        return true;
    }

    public boolean esSimetrico() {
        for (int i = 0; i < nodos.size(); i++) {
            for (int j = i + 1; j < nodos.size(); j++) {
                if (matrizAdyacencia[i][j] != matrizAdyacencia[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public boolean existeArista(T origen, T destino) {
        int i = indiceDeNodo(origen);
        int j = indiceDeNodo(destino);


        if (i == -1 || j == -1 || matrizAdyacencia[i][j] == 0 || matrizAdyacencia[j][i] == 0) {
            return false;
        }
        return true;
    }

    @Override
    protected LinkedList<Nodo<T>> obtenerVecinos(Nodo<T> nodo) {
        LinkedList<Nodo<T>> vecinos = new LinkedList<>();
        int i = nodos.indexOf(nodo);
        for (int j = 0; j < matrizAdyacencia.length; j++) {
            if (matrizAdyacencia[i][j] != 0 || matrizAdyacencia[j][i] != 0) {
                vecinos.add(nodos.get(j));
            }
        }
        return vecinos;
    }

    public int gradoNodo(Nodo<T> nodo){
        if (nodo == null || !nodos.contains(nodo)) return -1;
        return obtenerVecinos(nodo).size();
    }


}