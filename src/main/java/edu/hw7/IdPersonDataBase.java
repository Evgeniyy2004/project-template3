package edu.hw7;

import java.util.HashMap;

public class IdPersonDataBase {

    HashMap<Integer,Person> all = new HashMap<>();
    public synchronized void add(Person person) {
        all.put(person.id(), person);
    }
    public synchronized void delete(int id) {
        if (all.containsKey(id)) all.remove(id);
    }
    public synchronized Person findById(int id) {
        if (all.containsKey(id)) return all.get(id);
        return null;
    }

}
