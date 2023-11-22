package edu.hw7;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class PersonDataBase {

    private List<Person> all = new Vector<>();
    public synchronized void add(Person person) {

        all.add(person);
    }
    public synchronized void delete(int id) {
        for (int y = 0; y < all.size(); y++) {
            if (all.get(y).id() == id) all.remove(y);
        }
    }
    public synchronized List<Person> findByName(String name) {
        try(var need = all.stream().filter(x -> Objects.equals(x.name(), name))){
            return need.toList();
        }
    }
    public synchronized List<Person> findByAddress(String address) {
        try(var need = all.stream().filter(x -> Objects.equals(x.address(), address))){
            return need.toList();
        }
    }
    public synchronized List<Person> findByPhone(String phone) {
        try(var need = all.stream().filter(x -> Objects.equals(x.phoneNumber(), phone))){
            return need.toList();
        }
    }
}
