package phoneBook.messages;

public final class MenuMessages {
    private MenuMessages() {}
    public static final String MENU ="Меню:";
    public static final String CHOOSE_ELEMENT = "Выберите пункт меню";
    public static final String All_CONTACTS = "Посмотреть все контакты";
    public static final String NEW_CONTACT = "Добавить новый контакт";
    public static final String REMOVE_CONTACT = "Удалить контакт";
    public static final String SEARCH_BY_NAME = "Поиск по ФИО";
    public static final String SEARCH_BY_ADDRESS = "Поиск по адресу";
    public static final String SEARCH_BY_DATE = "Поиск по дате";
    public static final String GROUP_BY_ADDRESS = "Сгруппировать по адресу";
    public static final String EXIT = "Выйти";

    public static final String[] MAIN_MENU = new String[]{
            All_CONTACTS,
            NEW_CONTACT,
            REMOVE_CONTACT,
            SEARCH_BY_NAME,
            SEARCH_BY_ADDRESS,
            SEARCH_BY_DATE,
            GROUP_BY_ADDRESS,
            EXIT
    };
    public static final String EMPTY_CONTACTS_LIST = "Контактов нет";
    public static final String CONTACTS_LIST = "Контактов:";
    public static final String ENTER_FULL_NAME = "Введите ФИО";
    public static final String CHOOSE_CONTACT = "Выберите номер контакта";
    public static final String ENTER_NAME = "Введите Имя";
    public static final String ENTER_SURNAME = "Введите Фамилию";
    public static final String ENTER_PATRONYMIC = "Введите Отчество";
    public static final String ENTER_ADDRESS = "Введите адрес";
    public static final String ENTER_PHONES = "Введите номера телефонов в формате: +79999999999";
    public static final String ENTER_DATE = "Введите дату рождения в формате: дд.мм.гггг";
    public static final String ENTER_EMAIL = "Введите почту";
    public static final String INPUT_ERROR = "Ввод некорректный. Повторите попытку";

    public static final String DATE_FORMAT = "^(1[0-2]|0[1-9]).(3[01]|[12][0-9]|0[1-9]).[0-9]{4}$";
    public static final String EMAIL_FORMAT = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$";
    public static final String PHONE_FORMAT = "^\\+7[0-9]{10}$";

    public static final String STORAGE_CREATION_ERROR = "Произошла ошибка загрузки книги контактов";
    public static final String CHANGES_ERROR = "Не удалость сохранить ихменения";

}
