package co.chatsdk.core.base;

import java.util.ArrayList;
import java.util.List;

import co.chatsdk.core.NM;
import co.chatsdk.core.dao.User;
import co.chatsdk.core.handlers.ContactHandler;
import co.chatsdk.core.types.ConnectionType;
import io.reactivex.Completable;

/**
 * Created by benjaminsmiley-andrews on 24/05/2017.
 */

public class BaseContactHandler implements ContactHandler {

    @Override
    public List<User> contacts() {
        if(NM.currentUser() != null) {
            return NM.currentUser().getContacts();
        }
        return new ArrayList<>();
    }

    @Override
    public List<User> contactsWithType(ConnectionType type) {
        if(NM.currentUser() != null) {
            return NM.currentUser().getContacts(type);
        }
        return new ArrayList<>();
    }

    @Override
    public Completable addContact(User user, ConnectionType type) {
        if(NM.currentUser() != null) {
            NM.currentUser().addContact(user, type);
        }
        return Completable.complete();
    }

    @Override
    public Completable deleteContact(User user, ConnectionType type) {
        if(NM.currentUser() != null) {
            NM.currentUser().deleteContact(user, type);
        }
        return Completable.complete();
    }

    @Override
    public Completable addContacts(ArrayList<User> users, ConnectionType type) {
        ArrayList<Completable> completables = new ArrayList<>();
        for(User user : users) {
            completables.add(addContact(user, type));
        }
        return Completable.concat(completables);
    }

    @Override
    public Completable deleteContacts(ArrayList<User> users, ConnectionType type) {
        ArrayList<Completable> completables = new ArrayList<>();
        for(User user : users) {
            completables.add(addContact(user, type));
        }
        return Completable.concat(completables);
    }

}