package ui;

import ui.Menuable;
import ui.Outputable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ConsoleMenu implements Menuable {
    private final Outputable output;
    private static final Scanner in = new Scanner(System.in);
    private static final String WRONG_FORMAT = "Неверный формат!";
    private static final String ENTER_CORRECT = "Введите корректное число!";
    private static final String ASK_FOR_NUM_OF_ELEMENTS = "Сколько элементов хотите ввести?";

    public ConsoleMenu(@Nonnull Outputable output) {
        this.output = output;
    }

    /**
     * Производит выбор элемента из переданного массив
     *
     * @param header    заголовок
     * @param footer    подзаголовок
     * @param menuItems массив меню
     * @return индекс выбранного элемента начиная с 1
     */
    @Override
    public int select(String header, String footer, String[] menuItems) {
        String menu = getMenuString(menuItems);
        output.show(header);
        output.show(menu);
        return readIntNumber(footer, num -> num >= 1 && num <= menuItems.length);
    }

    /**
     * Считывает строку
     *
     * @param message сообщеие
     * @return считанная строка
     */
    @Override
    public String read(String message) {
        return read(message, res -> !res.isEmpty());
    }

    /**
     * Считывает строку по переданному формату
     *
     * @param message сообщение
     * @param format  формат строки
     * @return считанная строка
     */
    @Override
    public String read(String message, String format) {
        return read(message, res -> Pattern.matches(format, res));
    }

    /**
     * Считывает массив строк
     *
     * @param message сообщение
     * @return считанный массив строк
     */
    @Override
    public String[] readArray(String message) {
        return privateReadArray(message, null);
    }

    /**
     * Считывает массив строк с переданным форматом
     *
     * @param message сообщение
     * @param format  формат строк
     * @return считанный массив строк
     */
    @Override
    public String[] readArray(String message, String format) {
        return privateReadArray(message, format);
    }

    /**
     * Считывает целочисленное число
     *
     * @param message сообщение
     * @return считанное число
     */
    @Override
    public int readInt(String message) {
        return readIntNumber(message, num -> true);
    }

    /**
     * Считывает целочисленное число
     *
     * @param message сообщение
     * @param min     минимальная граниа (вклбчена)
     * @param max     максимальная граница (включено)
     * @return считанное число
     */
    @Override
    public int readInt(String message, int min, int max) {
        return readIntNumber(message, num -> num >= min && num <= max);
    }

    /**
     * Считывает положительное число
     *
     * @param message сообщение
     * @return считанное число
     */
    @Override
    public int readIntPositive(String message) {
        return readIntNumber(message, num -> num > 0);
    }

    /**
     * Чтение числа
     *
     * @param message   сообщение для пользователя при вводе
     * @param predicate предикат для проверки введенного число
     * @return введенное пользователем чсило
     */
    private int readIntNumber(String message, Predicate<Integer> predicate) {
        while (true) {
            output.show(message);
            try {
                int n = in.nextInt();
                in.nextLine();
                if (!predicate.test(n)) {
                    output.show(ENTER_CORRECT);
                } else {
                    return n;
                }
            } catch (InputMismatchException e) {
                in.nextLine();
                output.show(ENTER_CORRECT);
            }
        }
    }

    /**
     * Считывает массив строк с переданным форматом
     *
     * @param message сообщение
     * @param format  формат строк
     * @return считанный массив строк
     */
    private String[] privateReadArray(String message, String format) {
        output.show(message);
        int arrayLength = readIntPositive(ASK_FOR_NUM_OF_ELEMENTS);
        List<String> items = new ArrayList<>();
        for (int i = 0; i < arrayLength; i++) {
            if (format == null) {
                items.add(read(""));
            } else {
                items.add(read("Введите элемент №" + (i + 1), format));
            }
        }
        return items.toArray(new String[0]);
    }

    /**
     * Получение строки с меню
     *
     * @param items массив элементов меню
     * @return строка с меню
     */
    private String getMenuString(String[] items) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.length - 1; i++) {
            stringBuilder.append("\t").append(i + 1).append(". ").append(items[i]).append("\n");
        }
        stringBuilder.append("\t").append(items.length).append(". ").append(items[items.length - 1]);
        return stringBuilder.toString();
    }

    /**
     * Считывает строку по переданной проверке
     *
     * @param message   сообщение
     * @param predicate проверка ввода
     * @return считанная строка
     */
    private String read(String message, Predicate<String> predicate) {
        output.show(message);
        String res = in.nextLine();
        while (!predicate.test(res)) {
            output.show(WRONG_FORMAT);
            res = in.nextLine();
        }
        return res;
    }
}
