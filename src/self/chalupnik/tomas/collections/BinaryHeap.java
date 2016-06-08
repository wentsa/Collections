package self.chalupnik.tomas.collections;

public abstract class BinaryHeap<T> {
    protected T[] heap;
    protected int size = 0;

    private static final double reallocMultiply = 1.5;
    protected static final int defaultInitSize = 100;

    protected abstract T[] newTypedArray(int size);
    protected abstract int compare(T a, T b);

    protected void buildFromValues(T[] values) {
        if(values == null) {
            heap = newTypedArray(defaultInitSize);
        } else {
            heap = newTypedArray((int) (values.length * reallocMultiply));
            for(int i = 0; i < values.length; i++) {
                T val = values[i];
                if(val == null) {
                    throw new IllegalArgumentException("Values cannot be null");
                }
                heap[1 + size++] = val;
            }
            buildHeap();
        }
    }

    private void buildHeap() {
        for(int i = size / 2; i >= 1; i--) {
            heapify(i);
        }
    }

    private int parent(int i) {
        return i/2;
    }

    private int left(int i) {
        return 2* i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void heapify(int i) {
        int left = left(i);
        int right = right(i);

        int largest = i;

        if(left <= size) {
            int leftCompare = compare(heap[left], heap[i]);
            if(leftCompare > 0) {
                largest = left;
            }
        }

        if(right <= size) {
            int rightCompare = compare(heap[right], heap[largest]);
            if(rightCompare > 0) {
                largest = right;
            }
        }

        if(largest != i) {
            swap(largest, i);
            heapify(largest);
        }
    }

    private void swap(int a, int b) {
        T tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    public T getPeek() {
        if(size > 0) {
            return heap[1];
        }
        return null;
    }

    public T extractPeek() {
        T peek = getPeek();
        if(peek == null) {
            return peek;
        }
        deletePeek();
        return peek;
    }

    public void deletePeek() {
        if(size == 1) {
            heap[1] = null;
            size = 0;
        } else if(size > 1) {
            swap(1, size);
            heap[size--] = null;
            heapify(1);
        }
    }

    private void realloc() {
        T[] old = heap;
        heap = newTypedArray((int) (old.length * reallocMultiply));
        System.arraycopy(old, 0, heap, 0, old.length);
    }

    public void insert(T elem) {
        size++;
        if (size >= heap.length) {
            realloc();
        }
        int i = size;
        while (i > 1 && (compare(heap[parent(i)], elem) < 0)) {
            swap(i, parent(i));
            i = parent(i);
        }
        heap[i] = elem;
    }

    @Override
    public String toString() {
        T[] src = newTypedArray(size);
        System.arraycopy(heap, 1, src, 0, size);

        int depth = (int) (Math.log(src.length) / Math.log(2));
        int taken = 0;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= depth; i++) {
            int toTake = 1 << i;
            for(int k = 0; k < toTake; k++) {
                if(taken >= src.length) {
                    break;
                }
                sb.append(src[taken++]);
                if(k != toTake - 1) {
                    sb.append(",");
                }
            }
            sb.append("\n");
        }
        String res = sb.toString();
        if(res.charAt(res.length() - 1) == '\n') {
            res = res.substring(0, res.length() - 1);
        }
        return res;
    }

    public void mergeWith(BinaryHeap other) {
        if(size + other.size + 2 > heap.length) {
            T[] old = heap;
            heap = newTypedArray((int) ((size + other.size) * reallocMultiply));
            System.arraycopy(old, 0, heap, 0, old.length);
        }
        System.arraycopy(other.heap, 1, heap, size + 1, other.size);
        size += other.size;

        buildHeap();
    }
}
