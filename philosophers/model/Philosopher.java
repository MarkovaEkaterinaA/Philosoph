package philosophers.model;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int mealsToEat = 3; // Количество раз, которое философ должен поесть

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (mealsToEat > 0) {
            think(); // Философ размышляет
            eat();   // Философ ест
        }
    }

    private void think() {
        System.out.println("Философ " + id + " размышляет.");
        sleepRandom();
    }

    private void eat() {
        // Попытка взять обе вилки
        if (leftFork.pickUp()) {
            System.out.println("Философ " + id + " взял левую вилку.");
            if (rightFork.pickUp()) {
                try {
                    System.out.println("Философ " + id + " ест.");
                    sleepRandom();
                    mealsToEat--;
                } finally {
                    // Освобождаем вилки
                    rightFork.putDown();
                    System.out.println("Философ " + id + " положил правую вилку.");
                }
            }
            leftFork.putDown();
            System.out.println("Философ " + id + " положил левую вилку.");
        } else {
            System.out.println("Философ " + id + " не смог взять вилки и продолжает размышлять.");
        }
    }

    private void sleepRandom() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1000)); // Сон 500-1000 мс
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
