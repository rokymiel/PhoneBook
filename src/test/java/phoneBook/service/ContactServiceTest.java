package phoneBook.service;

import mocks.StorageMock;
import org.junit.jupiter.api.Test;
import phoneBook.Contact;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    @Test
    void test_get_all() {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);

        Contact[] all = contactService.getAllContacts();
        assertEquals(1,storageMock.getAllCount);
        assertEquals(storageMock.getAll(), all);
    }

    @Test
    void test_remove() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);
        Contact contact = new Contact("name1", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");

        assertTrue(contactService.removeContact(contact));
        assertEquals(1,storageMock.tryRemoveCount);
    }

    @Test
    void test_add_contact() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);
        Contact contact = new Contact("name1", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");

        assertTrue(contactService.addContact(contact));
        assertEquals(1,storageMock.addCount);
    }

    @Test
    void test_get_contacts_with_name() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);

        Contact[] all = contactService.getContactsWithName("name");
        assertEquals(1,storageMock.getAllWhereCount);
        assertEquals(storageMock.getAllWhere(null), all);
    }

    @Test
    void test_get_contacts_with_date() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);

        Contact[] all = contactService.getContactsWithDate("date");
        assertEquals(1,storageMock.getAllWhereCount);
        assertEquals(storageMock.getAllWhere(null), all);
    }

    @Test
    void test_get_contacts_with_phone() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);

        Contact[] all = contactService.getContactsWithPhone("phone");
        assertEquals( 1,storageMock.getAllWhereCount);
        assertEquals(storageMock.getAllWhere(null), all);
    }

    @Test
    void test_group() throws IOException {
        StorageMock storageMock = new StorageMock();
        ContactService contactService = new ContactService(storageMock);

        Map<String, List<Contact>> all = contactService.getGroupedByCity();
        assertEquals(storageMock.getGroupedCount, 1);
        assertEquals(storageMock.getGrouped(null), all);
    }

}