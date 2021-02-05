package phoneBook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    @Test
    void test_equals_contacts() {
        Contact contact1 = new Contact("name", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");
        Contact contact2 = new Contact("name", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");

        assertEquals(contact1, contact2);
    }

    @Test
    void test_not_equals_contacts() {
        Contact contact1 = new Contact("name1", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");
        Contact contact2 = new Contact("name2", "surname",
                "patronymic", "address", new String[]{"+99809"},
                "01.02.2001", "email@ex.ru");

        assertNotEquals(contact1, contact2);
    }
}