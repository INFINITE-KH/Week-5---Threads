package folder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class thread {
    public static void main(String[] args) {
        
        Thread t1 = new Thread(() -> {
            //Do stuff in a separate thread!
            for (int i = 1; i < 100000001; i++) {
                System.out.println(i + ", ");
            }
        });
        t1.start(); 
    
        Thread t2 = new Thread(() ->{
            for (int j = 1; j < 6; j++) {
                System.out.println(j + ", ");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException ex) {
                    Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t2.start();
        
        Thread t3 = new Thread(() ->{
            long start = System.currentTimeMillis();
            long end = start + 10*1000;
            int k = 10;
            while(System.currentTimeMillis() < end) {
                System.out.println(k + ", ");
                k++;
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException ex) {
                    Logger.getLogger(thread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        t3.start();
    }
 
}
