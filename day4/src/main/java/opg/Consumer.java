package opg;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    ArrayBlockingQueue<Integer> s2;
    int amount;
    static Counter counter;
    static long sum;
 
    Consumer(ArrayBlockingQueue<Integer> s2, Counter counter) {
        this.s2 = s2;
        this.counter = counter;
    }

    public void run() {
        Boolean b = true;
        while (b) {
            if (counter.getCounter() > 0) {
                try {
                    Integer in = s2.take();
                    counter.decrease();
                    addSum(in);
                    System.out.println("Fibonacchi number: " + in);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Sum: " + sum);
                b = false;
            }

        }

    }
    
    private synchronized void addSum(Integer in){
        sum+=in;
    }
}
