package opg;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester {

    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue<Integer> s1 = new ArrayBlockingQueue(40);
        ArrayBlockingQueue<Integer> s2 = new ArrayBlockingQueue(40);

        s1.add(4);
        s1.add(5);
        s1.add(8);
        s1.add(12);
        s1.add(21);
        s1.add(22);
        s1.add(34);
        s1.add(35);
        Counter counter = new Counter(s1.size());

        ExecutorService es = Executors.newCachedThreadPool();
        //Create and start the four Producers (P1-P4)
        es.execute(new Producer(s1, s2));
        es.execute(new Producer(s1, s2));
        es.execute(new Producer(s1, s2));
        es.execute(new Producer(s1, s2));

        //Create and start the single Consumer Thead (P1)
        es.execute(new Consumer(s2, counter));

        es.shutdown();
        es.awaitTermination(5, TimeUnit.SECONDS);

    }

}
