package opg;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer implements Runnable{
    
    ArrayBlockingQueue<Integer> s1;
    ArrayBlockingQueue<Integer> s2;
    
    private Integer fibber(Integer n){
        if((n == 0) || (n == 1)){
        return n;
    }else{
            return fibber(n-1) + fibber(n-2);
        }
    }
    
    public Producer(ArrayBlockingQueue<Integer> s1, ArrayBlockingQueue<Integer> s2){
        this.s1 = s1;
        this.s2 = s2;
    }

    public Producer() {
        
    }
    
    @Override
    public void run() {
        Boolean boo = true;
        while(boo){
            Integer i = s1.poll();
            if(i == null){
                boo = false;
                break;
            }else{
                Integer fibberI = fibber(i);
                try {
                    s2.put(fibberI);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    
}
