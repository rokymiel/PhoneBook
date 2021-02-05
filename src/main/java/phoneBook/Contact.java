package phoneBook;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;


public class Contact {
    @Nonnull
    private final String name;
    @Nonnull
    private final String surname;
    @Nonnull
    private final String patronymic;
    @Nonnull
    private final String address;
    @Nonnull
    private final String[] phones;
    @Nonnull
    @SerializedName("date_of_birth")
    private final String dateOfBirth;
    @Nonnull
    private final String email;

    public Contact(@Nonnull String name, @Nonnull String surname,
                   @Nonnull String patronymic, @Nonnull String address,
                   @Nonnull String[] phones, @Nonnull String dateOfBirth,
                   @Nonnull String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.address = address;
        this.phones = phones;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getAddress() {
        return address;
    }

    public String[] getPhones() {
        return phones;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return surname + ' ' +
                name + ' ' +
                patronymic + " | " +
                address + " | " +
                phonesToString(phones) + " | " +
                dateOfBirth + " | " +
                email;
    }

    private String phonesToString(String[] phones) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < phones.length; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(phones[i]);

        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(getName(), contact.getName()) &&
                Objects.equals(getSurname(), contact.getSurname()) &&
                Objects.equals(getPatronymic(), contact.getPatronymic()) &&
                Objects.equals(getAddress(), contact.getAddress()) &&
                Arrays.equals(getPhones(), contact.getPhones()) &&
                Objects.equals(getDateOfBirth(), contact.getDateOfBirth()) &&
                Objects.equals(getEmail(), contact.getEmail());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getSurname(), getPatronymic(), getAddress(), getDateOfBirth(), getEmail());
        result = 31 * result + Arrays.hashCode(getPhones());
        return result;
    }
}
