package Estructuras;

public class Cola<E> implements FIFO<E> {

    private Node<E> first;
    private Node<E> last;

    public Cola() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(E item) {
        Node<E> n = new Node<>(item);
        if (isEmpty()) {
            last = n;
            first = n;
        } else {
            last.setNext(n);
            last = n;
        }
    }

    @Override
    public E peek() {
        return isEmpty() ? null : first.getInfo();
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E item = first.getInfo();
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        int c = 0;
        Node<E> actual = first;
        while (actual != null) {
            c++;
            actual = actual.getNext();
        }
        return c;
    }
    

}
