*/Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
Вилки лежат на столе между каждой парой ближайших философов.
Каждый философ может либо есть, либо размышлять.
Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
*/

package philosophers.main;

import philosophers.model.Fork;
import philosophers.model.Philosopher;

public class DiningTable {
    public static void main(String[] args) {
        int numberOfPhilosophers = 5;

        // Создаём вилки
        Fork[] forks = new Fork[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new Fork();
        }

        // Создаём философов
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % numberOfPhilosophers];
            philosophers[i] = new Philosopher(i + 1, leftFork, rightFork);
        }

        // Запускаем философов
        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        // Ожидаем завершения работы всех философов
        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все философы завершили свою трапезу.");
    }
}
