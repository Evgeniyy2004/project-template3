package edu.hw7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Task3 {
    private Map<Integer, Person> idBase = new HashMap<>();
    private Map<String, List<Person>> phoneBase = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    private Map<String, List<Person>> addressBase = new HashMap<>();
    private Map<String, List<Person>> nameBase = new HashMap<>();

    public void add(Person person) {
        lock.writeLock().lock();
        idBase.put(person.id(), person);
        if (person.phoneNumber() != null) {
            if (!phoneBase.containsKey(person.phoneNumber())) {
                phoneBase.put(person.phoneNumber(), new Vector<>());
            }
            phoneBase.get(person.phoneNumber()).add(person);
        }
        if (person.address() != null) {
            if (!addressBase.containsKey(person.address())) {
                addressBase.put(person.address(), new Vector<>());
            }
            addressBase.get(person.address()).add(person);
        }
        if (person.name() != null) {
            if (!nameBase.containsKey(person.name())) {
                nameBase.put(person.name(), new Vector<>());
            }
            nameBase.get(person.name()).add(person);
        }
        lock.writeLock().unlock();
    }

    public void delete(int id) {
        lock.writeLock().lock();
        if (!idBase.containsKey(id)) {
            return;
        }
        var person = idBase.get(id);
        idBase.remove(id);
        if (person.phoneNumber() != null) {
            try (var newValue = phoneBase.get(person.phoneNumber()).stream().filter(r -> r.id() != person.id())) {
                if (newValue.count() == 0) phoneBase.remove(person.phoneNumber());
                else phoneBase.put(person.phoneNumber(), newValue.toList());
            }
        }
        if (person.address() != null) {
            try (var newValue = addressBase.get(person.address()).stream().filter(r -> r.id() != person.id())) {
                if (newValue.count() == 0) addressBase.remove(person.address());
                else addressBase.put(person.address(), newValue.toList());
            }
        }
        if (person.name() != null) {
            try (var newValue = nameBase.get(person.name()).stream().filter(r -> r.id() != person.id())) {
                if (newValue.count() == 0) nameBase.remove(person.address());
                else nameBase.put(person.name(), newValue.toList());
            }
        }
        lock.writeLock().unlock();
    }

    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        List<Person> res = new Vector();
        if (addressBase.containsKey(address)) {
            try (var all = addressBase.get(address).stream().filter(r -> r.name() != null)
                .filter(r -> r.phoneNumber() != null)) {
                res = all.toList();
            }
        }
        lock.readLock().unlock();
        return res;
    }

    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        List<Person> res = new Vector<Person>();
        if (phoneBase.containsKey(phone)) {
            try (var all = phoneBase.get(phone).stream().filter(r -> r.name() != null)
                .filter(r -> r.address() != null)) {
                res = all.toList();
            }
        }
        lock.readLock().unlock();
        return res;
    }

    public List<Person> findByName(String name) {
        lock.readLock().lock();
        List<Person> res = new Vector<Person>();
        if (nameBase.containsKey(name)) {
            try (var all = nameBase.get(name).stream().filter(r -> r.address() != null)
                .filter(r -> r.phoneNumber() != null)) {
                res = all.toList();
            }
        }
        lock.readLock().unlock();
        return res;
    }
}
