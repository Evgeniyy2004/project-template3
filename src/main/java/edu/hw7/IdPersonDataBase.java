package edu.hw7;

import java.util.HashMap;

public class IdPersonDataBase {

    HashMap<Integer,Person> all = new HashMap<>();
    public synchronized void add(Person person) {
        all.put(person.id(), person);
    }
    public synchronized Person delete(int id) {
        if (all.containsKey(id)) return all.remove(id);
        else return null;
    }
    public synchronized Person findBy(int id) {
        if (all.containsKey(id)) return all.get(id);
        return null;
    }

}
