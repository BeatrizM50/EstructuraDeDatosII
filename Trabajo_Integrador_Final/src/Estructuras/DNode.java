
package Estructuras;

public class DNode<E> extends Node<E> {
    private Node<E> prev;
  

    public DNode(E info) {
        super(info);
       this.prev = null;
    }

}