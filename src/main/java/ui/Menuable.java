package ui;

public interface Menuable {
    int select(String header, String footer, String[] menuItems);

    String read(String message);

    String read(String message, String format);

    String[] readArray(String message);

    String[] readArray(String message, String format);

    int readInt(String message);
    int readInt(String message, int min, int max);
    int readIntPositive(String message);

}
