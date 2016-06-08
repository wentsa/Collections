package self.chalupnik.tomas.utils;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils {
    private static final double loadFactor = 0.75;

    /**
     * If input list is null, {@link java.util.Collections#emptyList()} is returned, original list otherwise
     * @param collection input generic list
     * @return <p>
     *          <ul>
     *              <li>Original list if not null
     *              <li>{@link java.util.Collections#emptyList()} if input list is null
     *          </ul>
     *         </p>
     */
    public static <T> List<T> nullToEmpty(List<T> collection) {
        return collection == null ? Collections.<T> emptyList() : collection;
    }

    /**
     * If input set is null, {@link java.util.Collections#emptySet()} is returned, original set otherwise
     * @param collection input generic set
     * @return <p>
     *          <ul>
     *              <li>Original set if not null
     *              <li>{@link java.util.Collections#emptySet()} if input set is null
     *          </ul>
     *         </p>
     */
    public static <T> Set<T> nullToEmpty(Set<T> collection) {
        return collection == null ? Collections.<T> emptySet() : collection;
    }

    /**
     * If input array is null, new empty {@link java.lang.Object} array is returned, original array otherwise
     * @param array input generic array
     * @param cls class of objects in array
     * @return <p>
     *          <ul>
     *              <li>Original array if not null
     *              <li>new empty {@link java.lang.Object} array if input array is null
     *          </ul>
     *         </p>
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] nullToEmpty(T[] array, Class<T> cls) {
        if(array != null) {
            return array;
        }
        return (T[]) Array.newInstance(cls, 0);
    }

    /**
     * Returns optimized initial capacity for constructing HashMap or HashSet with default load factor
     * (constructor HashMap(int initialCapacity) or HashSet(int initialCapacity))
     *
     * @use Map<String, String> map = new HashMap<>(CollectionUtils.getOptimalHashCollectionInitCapacity(mapSize));
     *
     * @see https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
     * @param totalElements number of elements expected to be stored in HashMap or HashSet
     * @return initial capacity
     */
    public static int getOptimalHashCollectionInitCapacity(int totalElements) {
        return (int) (totalElements / loadFactor) + 1;
    }

    /**
     * Constructs new HashMap with optimized initial capacity
     * @param totalElements expected number of stored elements
     * @return new HashMap
     */
    public static <K, V> HashMap<K, V> newHashMap(int totalElements) {
        return new HashMap<K, V>(getOptimalHashCollectionInitCapacity(totalElements));
    }

    /**
     * Constructs new HashSet with optimized initial capacity
     * @param totalElements expected number of stored elements
     * @return new HashSet
     */
    public static <E> HashSet<E> newHashSet(int totalElements) {
        return new HashSet<E>(getOptimalHashCollectionInitCapacity(totalElements));
    }
}
