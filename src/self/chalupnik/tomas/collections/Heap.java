package self.chalupnik.tomas.collections;

public interface Heap<T> {
    T getPeek();
    T extractPeek();
    void insert(T elem);
    void deletePeek();

    int size();
    boolean isEmpty();

    void mergeWith(Heap other);

    enum HeapType {
        MAX_HEAP, MIN_HEAP
    }
}
