public class Main {
    public static void main(String[] args) {

        BinaryTree arbol = new BinaryTree(new Node(new Partido("Final")));
        arbol.addNodo(new Partido("Semifinal1"), "Final", true);
        arbol.addNodo(new Partido("Semifinal2"), "Final", false);

        arbol.addNodo(new Partido("Pinar del Rio", "La Habana", "QF1"), "Semifinal1", true);
        arbol.addNodo(new Partido("Matanzas", "Cienfuegos", "QF2"), "Semifinal1", false);
        arbol.addNodo(new Partido("Villa Clara", "Sancti Spiritus", "QF3"), "Semifinal2", true);
        arbol.addNodo(new Partido("Camaguey", "Las Tunas", "QF4"), "Semifinal2", false);

        System.out.println("Mostrando torneo antes de realizarse en bracket");
        arbol.MostrarBracket();


        System.out.println("Recorriendo en preorden");
        arbol.preOrder();

        System.out.println();

        System.out.println("Simulando torneo");
        arbol.RecorrerYSimular();

        System.out.println();
        System.out.println("Mostrando resultados del torneo");
        arbol.MostrarBracket();
    }

        }
