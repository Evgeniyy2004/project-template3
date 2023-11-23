package edu.hw7;

import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public class PhonePersonDataBase {

    HashMap<String,Person> all = new HashMap<>();
    public synchronized void add(Person person) {
        all.put(person.phoneNumber(), person);
    }
    public synchronized Person delete(@NotNull String number) {
        if (all.containsKey(number)) return all.remove(number);
        else return null;
    }
    public synchronized Person findBy(@NotNull String number) {
        if (all.containsKey(number)) return all.get(number);
        return null;
    }

}
