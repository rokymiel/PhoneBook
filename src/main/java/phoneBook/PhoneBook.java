package phoneBook;

import phoneBook.messages.MenuMessages;
import phoneBook.service.ContactService;
import ui.Menuable;
import ui.Outputable;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Arrays;

public class PhoneBook {
    private final Outputable output;
    private final Menuable menu;
    private final ContactService contactService;

    public PhoneBook(@Nonnull ContactService contactService, @Nonnull Outputable output, @Nonnull Menuable menu) {
        this.menu = menu;
        this.output = output;
        this.contactService = contactService;

    }

    /**
     * Открывает телефонную книгу
     */
    public void open() {
        int menuItem = menu.select(MenuMessages.MENU, MenuMessages.CHOOSE_ELEMENT, MenuMessages.MAIN_MENU);
        while (menuItem != MenuMessages.MAIN_MENU.length) {
            switch (menuItem) {
                case 1:
                    showAllContacts();
                    break;
                case 2:
                    newContact();
                    break;
                case 3:
                    removeContact();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    searchByPhone();
                    break;
                case 6:
                    searchByDate();
                    break;
                case 7:
                    group();
                    break;
            }
            menuItem = menu.select(MenuMessages.MENU, MenuMessages.CHOOSE_ELEMENT, MenuMessages.MAIN_MENU);
        }
    }

    /**
     * Показывает контакты
     *
     * @param contacts массик контактов
     */
    private void showContacts(Contact[] contacts) {
        output.showArray(contacts, MenuMessages.EMPTY_CONTACTS_LIST);
    }

    /**
     * Показывает все контакты
     */
    private void showAllContacts() {
        showContacts(contactService.getAllContacts());
    }

    /**
     * Создает новый контакт
     */
    private void newContact() {
        String name = menu.read(MenuMessages.ENTER_NAME);
        String surname = menu.read(MenuMessages.ENTER_SURNAME);
        String patronymic = menu.read(MenuMessages.ENTER_PATRONYMIC);
        String address = menu.read(MenuMessages.ENTER_ADDRESS);
        String[] phones = menu.readArray(MenuMessages.ENTER_PHONES, MenuMessages.PHONE_FORMAT);
        String dateOfBirth = menu.read(MenuMessages.ENTER_DATE, MenuMessages.DATE_FORMAT);
        String email = menu.read(MenuMessages.ENTER_EMAIL, MenuMessages.EMAIL_FORMAT);
        Contact contact = new Contact(name, surname, patronymic, address, phones, dateOfBirth, email);
        try {
            contactService.addContact(contact);
        } catch (IOException e) {
            output.showAlert(MenuMessages.CHANGES_ERROR);
        }
    }

    /**
     * Удаляет контакт
     */
    private void removeContact() {
        int index = menu.select(MenuMessages.CONTACTS_LIST, MenuMessages.CHOOSE_CONTACT, Arrays.stream(contactService.getAllContacts()).map(Contact::toString).toArray(String[]::new));
        try {
            contactService.removeContact(contactService.getAllContacts()[index - 1]);
        } catch (IOException e) {
            output.showAlert(MenuMessages.CHANGES_ERROR);
        }
    }

    /**
     * Группирует контакты по городу
     */
    private void group() {
        output.showGroups(contactService.getGroupedByCity());
    }

    /**
     * Поиск по ФИО
     */
    private void searchByName() {
        String fullName = menu.read(MenuMessages.ENTER_FULL_NAME);
        showContacts(contactService.getContactsWithName(fullName));
    }

    /**
     * Поиск по телефону
     */
    private void searchByPhone() {
        String address = menu.read(MenuMessages.ENTER_ADDRESS);
        showContacts(contactService.getContactsWithPhone(address));
    }

    /**
     * Поиск по дате
     */
    private void searchByDate() {
        String date = menu.read(MenuMessages.ENTER_DATE);
        showContacts(contactService.getContactsWithDate(date));
    }

}
