import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GeneralTree<E> {
    Node<E> raiz;

    //Constructor
    public GeneralTree(Node<E> raiz) {
        this.raiz = raiz;
    }
    public GeneralTree(){
        raiz=null;
    }

    //Get y set
    public Node<E> getRaiz() {
        return raiz;
    }
    public void setRaiz(Node<E> raiz) {
        this.raiz = raiz;
    }


    public Node<E> BuscarNodoRecursivo(Node<E> nodo_actual, E valor){
        if(nodo_actual==null){
            return null;
        }

        if (nodo_actual.getInfo().equals(valor)){
            return nodo_actual;
        }
        for (Node<E> hijo: nodo_actual.getHijos()){
            Node<E> resultado= BuscarNodoRecursivo(hijo,valor);
            if (resultado !=null){
                return resultado;
            }
        }
        return null;
    }

    //Busqueda en Amplitud
    public Node<E> buscarNodoBFS(Node<E> nodo_actual, E valor){
        Queue<Node<E>> cola=new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()){
            Node<E> nodoTemporal= cola.poll();
            if (nodoTemporal.getInfo().equals(valor)){
                return nodoTemporal;
            }
            for(Node<E> hijo: nodoTemporal.getHijos()){
                cola.add(hijo);
            }
        }
        return null;
    }

    public void insertar(E valorPadre, E valorNuevo){
        Node<E> NodoPadre=BuscarNodoRecursivo(raiz,valorPadre);
        if (NodoPadre !=null){
            Node<E> nuevoNodo=new Node<E>(valorNuevo);
            NodoPadre.agregarHijo(nuevoNodo);
        }
    }

    public Node<E> EliminarNodo(E valorPadre,E valorHijo){
        Node<E> NodoPadre=BuscarNodoRecursivo(raiz,valorPadre);
        if (NodoPadre !=null){
            Node<E> NodoHijo=BuscarNodoRecursivo(NodoPadre,valorHijo);
            if (NodoHijo ==null){
                System.out.println("No se encontro el nodo a eliminar");
                return null;
            } else{
                NodoPadre.getHijos().remove(NodoHijo);
            }
        } else{
            System.out.println("No existe Nodo Padre");
            return null;
        }
        return null;
    }

    public void ActualizarNodo(E valorHijo){
        Node<E> NodoHijo=BuscarNodoRecursivo(raiz, valorHijo);
        if (NodoHijo==null){
            System.out.println("No se endcontro el nodo Padre");
        } else{
            NodoHijo.setInfo(valorHijo);
        }
    }

    //Imprimir en Amplitud
    public void ImprimirBFS (){
        Queue<Node<E>> cola=new LinkedList<>();
        cola.add(raiz);
        while(!cola.isEmpty()){
            Node<E> nodoActual=cola.poll();
            System.out.println(nodoActual.getInfo());

            for (Node<E> hijo: nodoActual.getHijos()){
                cola.add(hijo);
            }
        }
    }

    //Imprimir en Profundidad
    public void ImprimirDFSRecursivo(Node<E> Nodo){
        if (Nodo==null){
            return ;
        }
        System.out.println(Nodo.getInfo()+" ");
        for (Node<E> hijo:Nodo.getHijos()){
            ImprimirDFSRecursivo(hijo);
        }

    }

    public void ImprimirDFSPila(){
        if (raiz==null){
            return;
        }
        Stack<Node<E>> pila=new Stack<>();
        pila.push(raiz);
        while(!pila.isEmpty()){
            Node<E> NodoActual=pila.pop();

            System.out.println(NodoActual.getInfo()+" ");

            LinkedList<Node<E>> hijos=NodoActual.getHijos();

            for (int i=hijos.size()-1;i>=0;i--){
                pila.push(hijos.get(i));
            }
        }
    }

}
