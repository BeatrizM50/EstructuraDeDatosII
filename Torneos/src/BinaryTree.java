import java.util.LinkedList;
import java.util.Queue;
import java.math.*;
public class BinaryTree {
        private Node root;

        public BinaryTree() {
            this.root = null;
        }
        public BinaryTree(Node root) {
            this.root = root;
        }

        public Node buscarNodo(String info) {
            if (root == null) {
                System.out.println("No hay nodo raiz");
                return null;
            } else {
                Queue<Node> colaHijos = new LinkedList<>();
                colaHijos.add(root);
                while (!colaHijos.isEmpty()) {
                    Node nodoTemporal = colaHijos.poll();
                    if (nodoTemporal.getValue().getRonda().equals(info)) {
                        return nodoTemporal;
                    }
                    if (nodoTemporal.getLeft() != null) {
                        colaHijos.add(nodoTemporal.getLeft());

                    }
                    if (nodoTemporal.getRight() != null) {
                        colaHijos.add(nodoTemporal.getRight());

                    }
                }
                return null;
            }
        }

        public void simularRonda(Node nodo){
            double win=Math.random();
            Partido P=nodo.getValue();
            if (win>0.5){
                P.setGanador(P.getEquipo1());
            } else{
                P.setGanador(P.getEquipo2());
            }
            System.out.printf("El ganador de la ronda %s es : %s,",P.getRonda(),P.getGanador());
        }

        public void addNodo(Partido info,String padre,boolean isleft){
            Node nodoPadre = buscarNodo(padre);
            Node nodoHijo =buscarNodo(info.getRonda());
            if (nodoHijo!=null){
                System.out.println("Ya existe ese hijo");
                return;
            }
            if (nodoPadre != null) {
                Node nodo = new Node(info);
                if (isleft){
                    nodoPadre.setLeft(nodo);
                } else{
                    nodoPadre.setRight(nodo);
                }

            } else {
                System.out.println("El padre no existe");
            }

        }

        public boolean eliminarNodo(String info){
            Node P=buscarNodo(info);
            if (P==null){
                System.out.println("No se encuentra el nodo");
                return false;
            }
            if (root==null){
                System.out.println("No hay raiz");
                return false;
            }

            if (root.getValue().getRonda().equals(info)){
                root=null;
                return true;
            }

            return eliminarNodo(root,info);
        }


        public void MostrarBracket(){
            if (root ==null){
                System.out.println("El arbol esta vacio");
                return;
            }
            mostrarBracketRecursivo(root,0,new LinkedList<>());
        }

        private void mostrarBracketRecursivo(Node nodo, int nivel, LinkedList<Boolean> esUltimo) {

            for (int i = 0; i < nivel; i++) {
                if (i < esUltimo.size() - 1) {
                    System.out.print(esUltimo.get(i) ? "    " : "│   ");
                }
            }


            if (nivel > 0) {
                System.out.print(esUltimo.get(esUltimo.size() - 1) ? "└── " : "├── ");
            }


            Partido partido = nodo.getValue();
            System.out.printf("%s: %s vs %s → Ganador: %s%n",
                    partido.getRonda(),
                    partido.getEquipo1() != null ? partido.getEquipo1() : "Por definir",
                    partido.getEquipo2() != null ? partido.getEquipo2() : "Por definir",
                    partido.getGanador() != null ? partido.getGanador() : "Sin resultado");


            if (nodo.getLeft() != null || nodo.getRight() != null) {
                LinkedList<Boolean> nuevosEsUltimo = new LinkedList<>(esUltimo);


                if (nodo.getLeft() != null) {
                    nuevosEsUltimo.add(nodo.getRight() == null);
                    mostrarBracketRecursivo(nodo.getLeft(), nivel + 1, nuevosEsUltimo);
                    nuevosEsUltimo.remove(nuevosEsUltimo.size() - 1);
                }


                if (nodo.getRight() != null) {
                    nuevosEsUltimo.add(true);
                    mostrarBracketRecursivo(nodo.getRight(), nivel + 1, nuevosEsUltimo);
                    nuevosEsUltimo.remove(nuevosEsUltimo.size() - 1);
                }
            }
        }

        public void RecorrerYSimular() {

            Postorden(root);
        }



        private void Postorden(Node node){
            if (node!=null){
                Postorden(node.getLeft());
                Postorden(node.getRight());
                if (node.getLeft()!=null ){
                    node.getValue().setEquipo1(node.getLeft().getValue().getGanador());

                }
                if (node.getRight()!=null){
                    node.getValue().setEquipo2(node.getRight().getValue().getGanador());
                }
                simularRonda(node);
                System.out.println();
            }
        }
        private boolean eliminarNodo(Node nodo, String info){
            Node left=nodo.getLeft();
            Node right=nodo.getRight();


            if (left.getValue().getRonda().equals(info)){
                nodo.setLeft(null);
                return true;
            }
            if (right.getValue().getRonda().equals(info)){
                nodo.setRight(null);
                return true;
            }

            if (eliminarNodo(left,info)) {
                return true;
            }
            if (eliminarNodo(right,info)) {
                return true;
            }

            return false;
        }






        public void inOrder(){
            inOrderRec(root);
        }
        private void inOrderRec(Node root){
            if (root !=null){
                inOrderRec(root.left);
                System.out.println(root.value+" ");
                inOrderRec(root.right);
            }
        }

        public void preOrder() {
            preOrderRec(root);
        }

        private void preOrderRec(Node raiz) {
            if (raiz != null) {
                System.out.print(raiz.getValue().getRonda() + " ");
                preOrderRec(raiz.getLeft());
                preOrderRec(raiz.getRight());
            }
        }

        public void postOrder() {
            postOrderRec(root);
        }

        private void postOrderRec(Node raiz) {
            if (raiz != null) {
                postOrderRec(raiz.getLeft());
                postOrderRec(raiz.getRight());
                System.out.print(raiz.getValue().getRonda() + " ");
            }
        }







    }


