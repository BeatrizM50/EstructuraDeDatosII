package Estructuras;

public interface FIFO<E> {
    void add(E item);
    E peek();
    E poll();
    boolean isEmpty();
    int size();
}
