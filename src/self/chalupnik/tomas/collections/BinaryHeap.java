package self.chalupnik.tomas.collections;


public class BinaryHeap implements Heap {

    private static final double reallocMultiply = 1.5;
    private static final int defaultInitSize = 100;

    private Comparable[] heap;
    private int size = 0;
    private final HeapType heapType;

    public BinaryHeap(HeapType heapType) {
        this(defaultInitSize, heapType);
    }

    public BinaryHeap(int initialSize, HeapType heapType) {
        heap = new Comparable[initialSize];
        this.heapType = heapType;
    }

    public BinaryHeap(Comparable[] values, HeapType heapType) {
        this.heapType = heapType;
        if(values == null) {
            heap = new Comparable[defaultInitSize];
        } else {
            heap = new Comparable[(int) (values.length * reallocMultiply)];
            for(int i = 0; i < values.length; i++) {
                Comparable val = values[i];
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

    private void heapify(int i) {
        int left = left(i);
        int right = right(i);

        int largest = i;

        if(left <= size) {
            int leftCompare = heap[left].compareTo(heap[i]);
            if(heapType.equals(HeapType.MAX_HEAP) && leftCompare > 0 || heapType.equals(HeapType.MIN_HEAP) && leftCompare < 0) {
                largest = left;
            }
        }

        if(right <= size) {
            int rightCompare = heap[right].compareTo(heap[largest]);
            if(heapType.equals(HeapType.MAX_HEAP) && rightCompare > 0 || heapType.equals(HeapType.MIN_HEAP) && rightCompare < 0) {
                largest = right;
            }
        }

        if(largest != i) {
            swap(largest, i);
            heapify(largest);
        }
    }

    private void swap(int a, int b) {
        Comparable tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
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


    @Override
    public Comparable getPeek() {
        if(size > 0) {
            return heap[1];
        }
        return null;
    }

    @Override
    public Comparable extractPeek() {
        Comparable peek = getPeek();
        if(peek == null) {
            return peek;
        }
        deletePeek();
        return peek;
    }

    @Override
    public void insert(Comparable elem) {
        size++;
        if(size >= heap.length) {
            realloc();
        }
        int i = size;
        while(i > 1 && (heapType.equals(HeapType.MAX_HEAP) && heap[parent(i)].compareTo(elem) < 0 || heapType.equals(HeapType.MIN_HEAP) && heap[parent(i)].compareTo(elem) > 0)) {
            swap(i, parent(i));
            i = parent(i);
        }
        heap[i] = elem;
    }

    private void realloc() {
        Comparable[] old = heap;
        heap = new Comparable[(int) (old.length * reallocMultiply)];
        System.arraycopy(old, 0, heap, 0, old.length);
    }

    @Override
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
        if(other instanceof BinaryHeap) {
            BinaryHeap otherHeap = (BinaryHeap) other;
            if(!heapType.equals(otherHeap.heapType)) {
                throw new IllegalArgumentException("Cannot merge MAX heap with MIN heap");
            }
            if(size + otherHeap.size + 2 > heap.length) {
                Comparable[] old = heap;
                heap = new Comparable[(int) ((size + otherHeap.size) * reallocMultiply)];
                System.arraycopy(old, 0, heap, 0, old.length);
            }
            System.arraycopy(otherHeap.heap, 1, heap, size + 1, otherHeap.size);
            size += otherHeap.size;

            buildHeap();
        }
    }

    @Override
    public String toString() {
        Comparable[] src = new Comparable[size];
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
}
