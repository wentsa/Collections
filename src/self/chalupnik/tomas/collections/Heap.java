package self.chalupnik.tomas.collections;

public interface Heap {
    Comparable getPeek();
    Comparable extractPeek();
    void insert(Comparable elem);
    void deletePeek();

    int size();
    boolean isEmpty();

    void mergeWith(Heap other);

    enum HeapType {
        MAX_HEAP, MIN_HEAP
    }
}
