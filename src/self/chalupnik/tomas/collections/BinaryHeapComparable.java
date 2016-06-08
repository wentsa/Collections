package self.chalupnik.tomas.collections;

public class BinaryHeapComparable<T extends Comparable> extends BinaryHeap<Comparable> implements Heap<Comparable> {

    private final HeapType heapType;

    public BinaryHeapComparable(HeapType heapType) {
        this.heap = new Comparable[defaultInitSize];
        this.heapType = heapType;
    }

    public BinaryHeapComparable(int initialSize, HeapType heapType) {
        this.heap = new Comparable[initialSize];
        this.heapType = heapType;
    }

    public BinaryHeapComparable(Comparable[] values, HeapType heapType) {
        this.heapType = heapType;
        buildFromValues(values);
    }

    @Override
    protected Comparable[] newTypedArray(int size) {
        return new Comparable[size];
    }


    @Override
    protected int compare(Comparable a, Comparable b) {
        int res = a.compareTo(b);
        if(heapType.equals(HeapType.MIN_HEAP)) {
            return -1 * res;
        }
        return res;
    }

    @Override
    public Comparable getPeek() {
        return super.getPeek();
    }

    @Override
    public Comparable extractPeek() {
        return super.extractPeek();
    }

    @Override
    public void insert(Comparable elem) {
        super.insert(elem);
    }

    @Override
    public void deletePeek() {
        super.deletePeek();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void mergeWith(Heap other) {
        if(other instanceof BinaryHeapComparable) {
            BinaryHeapComparable otherHeap = (BinaryHeapComparable) other;
            if (!heapType.equals(otherHeap.heapType)) {
                throw new IllegalArgumentException("Cannot merge MAX heap with MIN heap");
            }
            super.mergeWith(otherHeap);
        }
    }
}
