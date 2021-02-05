package ui;

import ui.Outputable;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Output implements Outputable {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * Отображает сообщение
     *
     * @param s сообщение
     */
    @Override
    public void show(String s) {
        System.out.println(s);
    }

    /**
     * Отображает массив элементов
     *
     * @param items          массив элементов для вывода
     * @param messageIfEmpty сообщение для пустого массива
     * @param <T>            тип элементов массива
     */
    @Override
    public <T> void showArray(T[] items, String messageIfEmpty) {
        if (items == null || items.length == 0) {
            show(messageIfEmpty);
            return;
        }
        for (T item : items) {
            show(item.toString());
        }
    }

    /**
     * Отображает сгруппированные элементы
     *
     * @param groups сгруппированные элементы
     * @param <K>    Тип ключа
     * @param <T>    Тип элемента
     */
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

    /**
     * Отображает предупреждение
     *
     * @param message предупреждение
     */
    @Override
    public void showAlert(String message) {
        System.out.println(ANSI_RED + message + ANSI_RESET);
    }
}
