package philosophers.model;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final ReentrantLock lock = new ReentrantLock();

    // Метод для захвата вилки
    public boolean pickUp() {
        return lock.tryLock(); // Возвращает true, если вилка захвачена
    }

    // Метод для освобождения вилки
    public void putDown() {
        lock.unlock();
    }
}
