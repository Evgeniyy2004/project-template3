package edu.hw7;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SystemOfBases {
    private final IdPersonDataBase byId = new IdPersonDataBase();
    private final PhonePersonDataBase byPhone = new PhonePersonDataBase();
    private final NamePersonDataBase byName = new NamePersonDataBase();
    private final AddressPersonDataBase byAddress = new AddressPersonDataBase();

    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void add(Person person, BasesTypes type) {
        lock.writeLock().lock();
        if (type == BasesTypes.ID) {
            byId.add(person);
        } else if (type == BasesTypes.ADDRESS) {
            byAddress.add(person);
        } else if (type == BasesTypes.NAME) {
            byName.add(person);
        } else {
            byPhone.add(person);
        }
        lock.writeLock().unlock();
    }

    public void deleteById(int id) {
        lock.writeLock().lock();
        byId.delete(id);
        lock.writeLock().unlock();
    }

    public void deleteByNumber(String phone) {
        lock.writeLock().lock();
        byPhone.delete(phone);
        lock.writeLock().unlock();
    }

    public void deleteByName(String name) {
        lock.writeLock().lock();
        byName.delete(name);
        lock.writeLock().unlock();
    }

    public void deleteByAddress(String address) {
        lock.writeLock().lock();
        byAddress.delete(address);
        lock.writeLock().unlock();
    }

    public Person findById(int id) {
        lock.readLock().lock();
        var t = byId.findBy(id);
        if (t != null) {
            if (byAddress.findBy(t.address()) == null) {
                t = null;
            } else if (byName.findBy(t.name()) == null) {
                t = null;
            } else {
                t = byPhone.findBy(t.phoneNumber());
            }
        }
        lock.readLock().unlock();
        return t;

    }

    public Person findByName(String name) {
        lock.readLock().lock();
        var t = byName.findBy(name);
        if (t != null) {
            if (byAddress.findBy(t.address()) == null) {
                t = null;
            } else if (byId.findBy(t.id()) == null) {
                t = null;
            } else {
                t = byPhone.findBy(t.phoneNumber());
            }
        }
        lock.readLock().unlock();
        return t;
    }

    public Person findByNumber(String number) {
        lock.readLock().lock();
        var t = byPhone.findBy(number);
        if (t != null) {
            if (byAddress.findBy(t.address()) == null) {
                t = null;
            } else if (byId.findBy(t.id()) == null) {
                t = null;
            } else {
                t = byName.findBy(t.name());
            }
        }
        lock.readLock().unlock();
        return t;
    }

    public Person findByAddress(String address) {
        lock.readLock().lock();
        var t = byAddress.findBy(address);
        if (t != null) {
            if (byPhone.findBy(t.phoneNumber()) == null) {
                t = null;
            } else if (byId.findBy(t.id()) == null) {
                t = null;
            } else t = byName.findBy(t.name());
        }
        lock.readLock().unlock();
        return t;
    }
}
