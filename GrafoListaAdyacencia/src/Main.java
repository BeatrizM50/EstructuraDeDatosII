public class Main {
    public static void main(String[] args) {
        System.out.println("\n=== Grafo Dirigido Ponderado ===");
        pruebaGrafoDirigidoPonderado();
    }

    private static void pruebaGrafoDirigidoPonderado() {
        GrafoDirigidoPonderado<String> grafo = new GrafoDirigidoPonderado<>();

        grafo.agregarNodo("A", 10);
        grafo.agregarNodo("B", 20);
        grafo.agregarNodo("C", 30);
        grafo.agregarNodo("D", 40);
        grafo.agregarNodo("E", 50);
        grafo.agregarNodo("F", 60);

        grafo.agregarArista("A", "F", 30);
        grafo.agregarArista("A", "D", 50);
        grafo.agregarArista("B", "A", 20);
        grafo.agregarArista("B", "C", 10);
        grafo.agregarArista("C", "A", 25);
        grafo.agregarArista("C", "D", 30);
        grafo.agregarArista("D", "F", 25);
        grafo.agregarArista("D", "E", 10);
        grafo.agregarArista("F", "E", 5);
        grafo.agregarArista("F", "C", 45);

        System.out.println("\nDFS desde A:");
        grafo.DFS("A");
        System.out.println("============================================");
        System.out.println("\nBFS desde A:");
        grafo.BFS("A");
        System.out.println("============================================");
        System.out.println("\nEliminar nodo C:");
        grafo.eliminarNodo("C");
        System.out.println("============================================");
        System.out.println("DFS desde A después de eliminar C:");
        grafo.DFS("A");
    }
}