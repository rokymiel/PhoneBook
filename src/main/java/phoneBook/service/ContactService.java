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

    public Contact[] getAllContacts() {
        return storage.getAll();
    }

    public boolean addContact(@Nonnull Contact contact) throws IOException {
        return storage.add(contact);
    }

    public Contact[] getContactsWithName(@Nonnull String name) {
        return storage.getAllWhere(contact ->
                (contact.getSurname() + " " + contact.getName() + " " + contact.getPatronymic()).toLowerCase().contains(name.toLowerCase()));
    }

    public Contact[] getContactsWithDate(@Nonnull String date) {
        return storage.getAllWhere(contact ->
                (contact.getDateOfBirth()).toUpperCase().contains(date));
    }

    public Contact[] getContactsWithPhone(@Nonnull String phone) {
        return storage.getAllWhere(contact ->
                Arrays.stream(contact.getPhones()).anyMatch(number -> number.contains(phone)));
    }

    public Map<String, List<Contact>> getGroupedByCity() {
        return storage.getGrouped(Contact::getAddress);
    }

    public boolean removeContact(@Nonnull Contact contact) throws IOException {
        return storage.tryRemove(contact);
    }
}
