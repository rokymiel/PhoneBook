import phoneBook.Contact;
import phoneBook.service.ContactService;
import phoneBook.messages.MenuMessages;
import phoneBook.PhoneBook;
import storage.JSONStorage;
import storage.Storable;
import ui.*;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static final String DATA_PATH = "contacts.txt";

    public static void main(String[] args) {
        Outputable output = new Output();
        Menuable menu = new ConsoleMenu(output);
        Optional<Storable<Contact>> storable = getStorage();
        if (storable.isPresent()) {
            ContactService service = new ContactService(storable.get());
            PhoneBook phoneBook = new PhoneBook(service, output, menu);
            phoneBook.open();
        } else {
            output.showAlert(MenuMessages.STORAGE_CREATION_ERROR);
        }
    }

    private static Optional<Storable<Contact>> getStorage() {
        try {
            Storable<Contact> storable = new JSONStorage(DATA_PATH, Contact.class);
            return Optional.of(storable);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
