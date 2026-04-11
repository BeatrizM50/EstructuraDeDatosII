import java.sql.SQLOutput;
import java.util.*;

import java.util.ArrayList;

import java.util.ArrayList;

public abstract class GrafoAbstracto<T> {
    protected LinkedList<Nodo<T>> nodos;
    protected int[][] matrizAdyacencia;

    public GrafoAbstracto(int capacidadInicial) {
        this.nodos = new LinkedList<>();
        this.matrizAdyacencia = new int[capacidadInicial][capacidadInicial];
    }

    public int indiceDeNodo(T dato) {
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getDato().equals(dato)) {
                return i;
            }
        }
        return -1;
    }

    public Nodo<T> buscarNodo(T dato) {
        for (Nodo<T> nodo : nodos) {
            if (nodo.getDato().equals(dato)) {
                return nodo;
            }
        }
        return null;
    }
    public boolean existeArista(T origen, T destino) {
        int i = indiceDeNodo(origen);
        int j = indiceDeNodo(destino);


        if (i == -1 || j == -1 || matrizAdyacencia[i][j] == 0) {
            return false;
        }
        return true;
    }
    public boolean agregarNodo(T dato) {
        if (indiceDeNodo(dato) != -1) return false;

        if (nodos.size() >= matrizAdyacencia.length) {
            redimensionarMatriz();
        }

        nodos.add(new Nodo<>(dato));
        return true;
    }

    protected void redimensionarMatriz() {
        int nuevoTamano = matrizAdyacencia.length * 2;
        int[][] nuevaMatriz = new int[nuevoTamano][nuevoTamano];

        for (int i = 0; i < nuevoTamano; i++) {
            for (int j = 0; j < nuevoTamano; j++) {
                nuevaMatriz[i][j] = 0;
            }
        }

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            System.arraycopy(matrizAdyacencia[i], 0, nuevaMatriz[i], 0, matrizAdyacencia[i].length);
        }

        matrizAdyacencia = nuevaMatriz;
    }
    public void imprimirMatrizAdyacencia() {

        System.out.print("    ");
        for (Nodo<T> nodo : nodos) {
            System.out.print(nodo.getDato() + "  ");
        }
        System.out.println();


        for (int i = 0; i < nodos.size(); i++) {
            System.out.print(nodos.get(i).getDato() + "  ");
            for (int j = 0; j < nodos.size(); j++) {
                System.out.print(" " + matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }

    public abstract boolean agregarArista(T origen, T destino, int peso);

    public Nodo<T> obtenerNodo(T dato) {
        if (dato == null) return null;

        for (Nodo<T> nodo : nodos) {
            if (dato.equals(nodo.getDato())) {
                return nodo;
            }
        }
        return null;
    }
    public boolean eliminarArista(T origen, T destino) {
        int i = indiceDeNodo(origen);
        int j = indiceDeNodo(destino);

        if (i == -1 || j == -1) return false;

        matrizAdyacencia[i][j] = 0;
        return true;
    }

    public boolean eliminarNodo(T dato) {

        int indiceEliminar = indiceDeNodo(dato);
        if (indiceEliminar == -1) return false;


        nodos.remove(indiceEliminar);


        eliminarAristasDeNodo(indiceEliminar);

        return true;
    }
    public boolean actualizarNodo(T datoViejo, T datoNuevo) {

        int indice = indiceDeNodo(datoViejo);
        if (indice == -1) return false;


        if (indiceDeNodo(datoNuevo) != -1) return false;


        nodos.get(indice).setDato(datoNuevo);



        return true;
    }

    private void eliminarAristasDeNodo(int indiceEliminar) {
        int tamaño = nodos.size() ;



        for (int i = indiceEliminar; i < tamaño -1; i++) {
            matrizAdyacencia[i] = matrizAdyacencia[i + 1];
        }


        for (int j = indiceEliminar; j < tamaño - 1; j++) {
            for (int i = 0; i < tamaño; i++) {
                matrizAdyacencia[i][j] = matrizAdyacencia[i][j + 1];
            }
        }

        for (int i = 0; i < tamaño; i++) {
            matrizAdyacencia[tamaño][i] = 0;
            matrizAdyacencia[i][tamaño] = 0;
        }


    }

    public void DFS(Nodo<T> inicio) {
        Stack<Nodo<T>> pila = new Stack<>();
        LinkedList<Nodo<T>> visitados = new LinkedList<>();
        pila.push(inicio);

        while (!pila.isEmpty()) {
            Nodo<T> actual = pila.pop();


            if (visitados.contains(actual)) {
                continue;
            }

            visitados.add(actual);
//            System.out.println("Actual");
            System.out.println(actual.getDato().toString());

//            System.out.println("Prueba de vecinos");
//
//            for (Nodo nodo:
//                    obtenerVecinos(actual) ){
//                System.out.println(actual.getDato().toString());
//            }

            for (Nodo<T> vecino : obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    pila.push(vecino);
                }
            }
        }
    }
    public void DFSrecursivo(T dato){
        Nodo<T> nodo=buscarNodo(dato);
        LinkedList<Nodo<T>> visitados=new LinkedList<>();
        if (nodo==null){
            System.out.println("No existe el nodo");
            return;
        }
        DFSrecursivo(nodo,visitados);

    }
    private void DFSrecursivo(Nodo<T> nodo,LinkedList<Nodo<T>> visitados){
        if (nodo==null){
            return;
        }
        int a =indiceDeNodo(nodo.getDato());
        System.out.println(nodo.getDato().toString());
        for (Nodo<T> nodo2:obtenerVecinos(nodo)){
            DFSrecursivo(nodo2,visitados);
        }

    }

    public void BFS(Nodo<T> inicio) {
        Queue<Nodo<T>> cola = new LinkedList<>();
        LinkedList<Nodo<T>> visitados = new LinkedList<>();
        cola.add(inicio);

        while (!cola.isEmpty()) {
            Nodo<T> actual = cola.poll();


            if (visitados.contains(actual)) {
                continue;
            }

            visitados.add(actual);
            System.out.println(actual.getDato());


            for (Nodo<T> vecino : obtenerVecinos(actual)) {
                if (!visitados.contains(vecino)) {
                    cola.add(vecino);
                }
            }
        }
    }

    protected abstract List<Nodo<T>> obtenerVecinos(Nodo<T> nodo);

}


