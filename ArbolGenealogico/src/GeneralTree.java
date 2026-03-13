import java.util.LinkedList;
import java.util.Queue;

public class GeneralTree {

        private Node<Persona> root;

        public GeneralTree(Node<Persona> root){
            this.root=root;
        }


        public Node<Persona> getRoot() {
            return root;
        }

        public void setRoot(Node<Persona> root) {
            this.root = root;
        }

        public Node<Persona> buscarNodo(String info){
            if (root==null){
                System.out.println("No hay nodo raiz");
                return null;
            } else{
                Queue<Node<Persona>> colaHijos=new LinkedList<>();
                colaHijos.add(root);
                while(!colaHijos.isEmpty()){
                    Node<Persona> nodoTemporal=colaHijos.poll();
                    if (nodoTemporal.getInfo().getNombre().equals(info)){
                        return nodoTemporal;
                    }
                    for (Node<Persona> hijo: nodoTemporal.getChildren()){
                        colaHijos.add(hijo);
                    }
                }

            }
            return null;
        }

        public int calcularGeneracionProfunda(Node<Persona> nodo) {
            if (nodo == null) {
                return -1;
            }

            if (nodo.getChildren().isEmpty()) {
                return 0;
            }

            int alturamaxima = 0;
            for (Node<Persona> hijo : nodo.getChildren()) {
                int alturaHijo = calcularGeneracionProfunda(hijo);
                if (alturaHijo > alturamaxima) {
                    alturamaxima = alturaHijo;
                }
            }
            return alturamaxima + 1;
        }

        public boolean eliminarRamaFamiliar(String info){
            Node<Persona> P=buscarNodo(info);
            if (P==null){
                System.out.println("No se encuentra el nodo");
                return false;
            }
            if (root==null){
                System.out.println("No hay raiz");
                return false;
            }

            if (root.getInfo().getNombre().equals(info)){
                root=null;
                return true;
            }

            return eliminarNodo(root,info);
        }

        private boolean eliminarNodo(Node<Persona> nodo, String info){
            LinkedList<Node<Persona>> hijos=nodo.getChildren();

            for (int i =0;i<hijos.size();i++){
                Node<Persona> hijo=hijos.get(i);

                if (hijo.getInfo().getNombre().equals(info)){
                    hijos.remove(i);
                    return true;
                }

                if (eliminarNodo(hijo,info)){
                    return true;
                }
            }

            return false;
        }

        public void BFS(){
            Queue<Node<Persona>> cola=new LinkedList<>();
            cola.add(root);
            int gen=0;
            while(!cola.isEmpty()){
                System.out.println("Generacion: "+gen);
                int size=cola.size();
                for (int i = 0; i < size; i++) {
                    Node<Persona> actual=cola.poll();
                    Persona P=actual.getInfo();
                    System.out.println(P.getGenero());
                    System.out.println(P.getNombre());
                    System.out.println(P.getFecha());
                    for (Node<Persona> hijo:actual.getChildren()){
                        cola.add(hijo);
                    }
                }
                gen++;

            }
        }

        public void addNodo(Persona info, String padre) {
            Node<Persona> nodoPadre = buscarNodo(padre);
            Node<Persona> nodoHijo =buscarNodo(info.getNombre());
            if (nodoHijo!=null){
                System.out.println("Ya existe ese hijo");
                return;
            }
            if (nodoPadre != null) {
                Node<Persona> nodo = new Node<>(info);
                nodoPadre.getChildren().add(nodo);
            } else {
                System.out.println("El padre no existe");
            }
        }

        public void Descendientes(String info){
            Node<Persona> nodo=buscarNodo(info);
            if (nodo==null){
                System.out.println("No se encuentro el nodo");
                return ;
            }
            Queue<Node<Persona>> colaHijos=new LinkedList<>();
            colaHijos.add(nodo);
            int generacionactual=0;
            int nodosactuales=1;
            int nodosposteriores=0;

            while(!colaHijos.isEmpty()){
                Node<Persona> nodoTemporal=colaHijos.poll();
                System.out.println("Generacion: " +generacionactual);
                System.out.println(nodoTemporal.getInfo().getNombre());
                System.out.println(nodoTemporal.getInfo().getGenero());
                System.out.println(nodoTemporal.getInfo().getFecha());
                for (Node<Persona> hijo: nodoTemporal.getChildren()){
                    colaHijos.add(hijo);
                    nodosposteriores++;
                }
                nodosactuales--;
                if (nodosactuales==0){
                    generacionactual++;
                    nodosactuales=nodosposteriores;
                    nodosposteriores=0;
                }
            }
        }

        public void Antepasados(String nombre){
            LinkedList<Persona> antepasados=new LinkedList<>();
            Queue<Node<Persona>> cola=new LinkedList<>();
            cola.add(root);
            while(!cola.isEmpty()){
                Node<Persona> actual=cola.poll();
                //System.out.println(actual.getInfo().getNombre());
                if (buscarAntepasado(actual,nombre)){
                    //System.out.println("Es antepasado :"+actual.getInfo().getNombre());
                    antepasados.add(actual.getInfo());
                }
                for (Node<Persona> hijo:actual.getChildren()){
                    cola.add(hijo);
                }
            }
            imprimirAntepasados(antepasados);

        }

        public void imprimirAntepasados(LinkedList<Persona> lista){

            for (int i = lista.size()-1; i >= 0; i--) {
                Persona P=lista.get(i);
                System.out.println(P.getNombre());
                System.out.println(P.getFecha());
                System.out.println(P.getGenero());
            }
        }

        private boolean buscarAntepasado(Node<Persona> nodo, String nombre){
            if (nodo==null){
                return false;
            }
            if (nodo.getInfo().getNombre().equals(nombre)){
                return true;
            }
            for (Node<Persona> hijo : nodo.getChildren()){
                return buscarAntepasado(hijo,nombre);
            }
            return false;
        }

    }


