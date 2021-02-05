package storage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Storable<T> {
    T[] getAll();
    boolean add(T item) throws IOException;
    boolean tryRemove(T item) throws IOException;
    T[] getAllWhere(Predicate<T> predicate);
    <K> Map<K, List<T>> getGrouped(Function<? super T, ? extends K> predicate);
}
