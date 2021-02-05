package mocks;

import phoneBook.Contact;
import storage.Storable;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class StorageMock implements Storable<Contact> {
    public int getAllCount = 0;
    public Contact[] contacts = new Contact[0];

    @Override
    public Contact[] getAll() {
        getAllCount++;
        return contacts;
    }

    public int addCount = 0;

    @Override
    public boolean add(Contact item) throws IOException {
        addCount++;
        return true;
    }

    public int tryRemoveCount = 0;

    @Override
    public boolean tryRemove(Contact item) throws IOException {
        tryRemoveCount++;
        return true;
    }

    public int getAllWhereCount = 0;

    @Override
    public Contact[] getAllWhere(Predicate<Contact> predicate) {
        getAllWhereCount++;
        return contacts;
    }

    public int getGroupedCount = 0;

    @Override
    public <K> Map<K, List<Contact>> getGrouped(Function<? super Contact, ? extends K> predicate) {
        getGroupedCount++;
        return null;
    }
}
