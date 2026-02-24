import java.util.LinkedList;

public class Node<E> {
    public E info;
    public LinkedList<Node<E>> hijos;

    public Node(E info) {
        this.info = info;
        hijos=new LinkedList<>();
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<Node<E>> getHijos() {
        return hijos;
    }

    public void setHijos(LinkedList<Node<E>> hijos) {
        this.hijos = hijos;
    }

    public void agregarHijo(Node<E> hijo){
        this.hijos.add(hijo);
    }
}
