package self.chalupnik.tomas.collections;

import java.lang.reflect.Array;
import java.util.Comparator;

public class BinaryHeapWithComparator<T> extends BinaryHeap<T> implements Heap<T> {

    private final Comparator<T> comparator;
    private final Class<T> cls;

    public BinaryHeapWithComparator(Class<T> cls, Comparator<T> comparator) {
        this.comparator = comparator;
        this.cls = cls;
        this.heap = newTypedArray(defaultInitSize);
    }

    public BinaryHeapWithComparator(int initialSize, Class<T> cls, Comparator<T> comparator) {
        this.comparator = comparator;
        this.cls = cls;
        this.heap = newTypedArray(initialSize);
    }

    public BinaryHeapWithComparator(T[] values, Class<T> cls, Comparator<T> comparator) {
        this.comparator = comparator;
        this.cls = cls;
        buildFromValues(values);
    }

    @Override
    protected T[] newTypedArray(int size) {
        return (T[]) Array.newInstance(cls, size);
    }


    @Override
    protected int compare(Object a, Object b) {
        return comparator.compare((T) a, (T) b);
    }

    @Override
    public T getPeek() {
        return (T) super.getPeek();
    }

    @Override
    public T extractPeek() {
        return (T) super.extractPeek();
    }

    @Override
    public void insert(Object elem) {
        if(cls.isAssignableFrom(elem.getClass())) {
            super.insert((T) elem);
        }
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
        if(other instanceof BinaryHeapWithComparator) {
            BinaryHeapWithComparator otherHeap = (BinaryHeapWithComparator) other;
            super.mergeWith(otherHeap);
        }
    }
}
