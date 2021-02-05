package phoneBook.service;

import phoneBook.Contact;
import storage.Storable;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ContactService {
    private final Storable<Contact> storage;

    public ContactService(@Nonnull Storable<Contact> storage) {
        this.storage = storage;
    }

    /**
     * Возвращает все сохраненные контакты
     *
     * @return сохраненные контакты
     */
    public Contact[] getAllContacts() {
        return storage.getAll();
    }

    /**
     * Добавляет контакт в книгу
     *
     * @param contact новый контакт
     * @return получилось ли добавить контакт (false возвращается, если данный контак уже есть в книге)
     * @throws IOException если возникла ошибка при сохранении измененной книги
     */
    public boolean addContact(@Nonnull Contact contact) throws IOException {
        return storage.add(contact);
    }

    /**
     * Возвращает массив контактов содержащих совпадение по {@code name}
     *
     * @param name имя для поиска
     * @return массив контактов содержащих совпадение
     */
    public Contact[] getContactsWithName(@Nonnull String name) {
        return storage.getAllWhere(contact ->
                (contact.getSurname() + " " + contact.getName() + " " + contact.getPatronymic()).toLowerCase().contains(name.toLowerCase()));
    }

    /**
     * Возвращает массив контактов содержащих совпадение по {@code date}
     *
     * @param date дата для поиска
     * @return массив контактов содержащих совпадение
     */
    public Contact[] getContactsWithDate(@Nonnull String date) {
        return storage.getAllWhere(contact ->
                (contact.getDateOfBirth()).toUpperCase().contains(date));
    }

    /**
     * Возвращает массив контактов содержащих совпадение по {@code phone}
     *
     * @param phone телефон для поиска
     * @return массив контактов содержащих совпадение
     */
    public Contact[] getContactsWithPhone(@Nonnull String phone) {
        return storage.getAllWhere(contact ->
                Arrays.stream(contact.getPhones()).anyMatch(number -> number.contains(phone)));
    }

    /**
     * Групиррует контакты по адресу
     *
     * @return сгруппированные контакты по адресу
     */
    public Map<String, List<Contact>> getGroupedByCity() {
        return storage.getGrouped(Contact::getAddress);
    }

    /**
     * Удаляет контак из книги
     *
     * @param contact контак для удаления
     * @return false если элемента нет в книге
     * @throws IOException если возникла ошибка при сохранении измененной книги
     */
    public boolean removeContact(@Nonnull Contact contact) throws IOException {
        return storage.tryRemove(contact);
    }
}
