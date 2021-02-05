package ui;

import ui.Outputable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Output implements Outputable {

    @Override
    public void show(String s) {
        System.out.println(s);
    }

    @Override
    public <T> void showArray(T[] items, String messageIfEmpty) {
        if (items == null || items.length == 0) {
            show(messageIfEmpty);
            return;
        }
        for (int i = 0; i < items.length; i++) {
            show(items[i].toString());
        }
    }

    @Override
    public <K, T> void showGroups(Map<K, List<T>> groups) {
        if (groups == null) return;
        Set<K> keys = groups.keySet();
        for (K key : keys) {
            show(key.toString());
            for (T items : groups.get(key)) {
                show("\t" + items.toString());
            }
        }
    }

    @Override
    public void showAlert(String message) {

    }
}
