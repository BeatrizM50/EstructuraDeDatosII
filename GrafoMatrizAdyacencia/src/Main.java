public class Main {
    public static void main(String[] args) {

        System.out.println("\n=== Grafo No Dirigido No Ponderado ===");
        pruebaGrafoNoDirigidoNoPonderado();
    }

    private static void pruebaGrafoNoDirigidoNoPonderado() {
        GrafoNoDirigidoNoPonderado<String> grafo = new GrafoNoDirigidoNoPonderado<>(5);

        grafo.agregarNodo("A");
        grafo.agregarNodo("B");
        grafo.agregarNodo("C");
        grafo.agregarNodo("D");
        grafo.agregarNodo("E");
        grafo.agregarNodo("F");
        grafo.agregarNodo("G");

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.agregarArista("C", "D");
        grafo.agregarArista("C", "E");
        grafo.agregarArista("D", "F");
        grafo.agregarArista("E", "F");
        grafo.agregarArista("F", "G");
        System.out.println("============================================");
        System.out.println("¿Existe arista A<->B? " + grafo.existeArista("A", "B")); // true
        System.out.println("============================================");
        System.out.println("BFS desde A: ");
        grafo.BFS(grafo.buscarNodo("A"));
        System.out.println("============================================");
        System.out.println("DFS desde A: ");
        grafo.DFS(grafo.buscarNodo("A"));
        System.out.println("============================================");
        System.out.println("\nActualizar nodo F a R:");
        grafo.actualizarNodo("F", "R");
        System.out.println("============================================");
        System.out.println("BFS desde A despues de actualizar: ");
        grafo.BFS(grafo.buscarNodo("A"));
        System.out.println("============================================");
        System.out.println("DFS desde A despues de actualizar: ");
        grafo.DFS(grafo.buscarNodo("A"));
    }
}