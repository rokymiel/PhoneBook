package ui;

import java.util.List;
import java.util.Map;

public interface Outputable {
    void show(String s);

    <T> void showArray(T[] items, String messageIfEmpty);

    <K, T> void showGroups(Map<K, List<T>> groups);

    void showAlert(String message);
}
