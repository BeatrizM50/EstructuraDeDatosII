package Estructuras;

public interface LIFO<E> {
    void Push(E item);
    E Top();
    E Pop();
    boolean isEmpty();
     int size();
}
