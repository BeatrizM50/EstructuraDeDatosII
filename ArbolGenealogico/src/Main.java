public class Main {
    public static void main(String[] args) {

                GeneralTree arbol=new GeneralTree(new Node<>(new Persona("Yanet","1","F")));
                System.out.println("Calculando altura con arbol con raiz");
                System.out.println(arbol.calcularGeneracionProfunda(arbol.getRoot()));

        System.out.println("----------------------------------------------------------------------");
                System.out.println("Agregando hijos");
                arbol.addNodo(new Persona("Betty","2","F"),"Yanet");
                arbol.addNodo(new Persona("Brian","2","M"),"Yanet");

                arbol.addNodo(new Persona("Alejandro","3","M"),"Brian");
                arbol.addNodo(new Persona("Andy","3","M"),"Betty");
        System.out.println("----------------------------------------------------------------------");
                System.out.println("Recorrido BFS:");
                arbol.BFS();
        System.out.println("----------------------------------------------------------------------");
                System.out.println("Altura del arbol:");
                System.out.println(arbol.calcularGeneracionProfunda(arbol.getRoot()));

        System.out.println("----------------------------------------------------------------------");
                System.out.println("Descendientes de Brian");
                arbol.Descendientes("Brian");
        System.out.println("----------------------------------------------------------------------");

                arbol.addNodo(new Persona("Leo","2","F"),"Alejandro");
                System.out.println("Antepasados de Leo:");
                arbol.Antepasados("Leo");


            }



        }

