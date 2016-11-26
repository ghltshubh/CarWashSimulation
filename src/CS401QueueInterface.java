public interface CS401QueueInterface<E> {
    void add(E element);

    E remove();

    E peek();

    boolean is_empty();

    boolean is_full();
}