package Estructuras;

public class Pila<E> implements LIFO<E> {

    private Node<E> top;

    public Pila() {
        top = null;
    }

    @Override
    public void Push(E item) {
        Node<E> n = new Node<>(item);
        n.setNext(top);
        top = n;
    }

    @Override
    public E Top() {
        return isEmpty() ? null : top.getInfo();
    }

    @Override
    public E Pop() {
        if (isEmpty()) {
            return null;
        }
        E aux = top.getInfo();
        top = top.getNext();
        return aux;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        int contador = 0;
        Node<E> actual = top;   
        while (actual != null) {
            contador++;
            actual = actual.getNext();
        }
        return contador;
    }
}
