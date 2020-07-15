import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
        public static final int CARS_COUNT = 4;
        static int count = 0;

        public static void main(String[] args) {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            CyclicBarrier cb = new CyclicBarrier(4);
            Semaphore smp = new Semaphore(2);
            final CountDownLatch cdl = new CountDownLatch(4);
            final CountDownLatch cdl1 = new CountDownLatch(4);


            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, smp, cdl, cdl1);
            }
            for (int i = 0; i < cars.length; i++) {
                new Thread(cars[i]).start();
            }

            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");


            try {
                cdl1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");



        }





}
