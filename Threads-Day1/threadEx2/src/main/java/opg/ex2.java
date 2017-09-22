package opg;

public class ex2 {

    private int n = 0;

    public synchronized int next() {
        n++;
        n++;
        return n;
    }
    
    public static void main(String[] args) {
        ex2 e = new ex2();
        
        Thread t1 = new Thread(() -> {
         
        System.out.println(e.next());
    });
        
        Thread t2 = new Thread(() -> {
            System.out.println(e.next());
    });
        Thread t3 = new Thread(() -> {
         
        System.out.println(e.next());
    });
        
        Thread t4 = new Thread(() -> {
            System.out.println(e.next());
    });
        Thread t5 = new Thread(() -> {
         
        System.out.println(e.next());
    });
        
        Thread t6 = new Thread(() -> {
            System.out.println(e.next());
    });
    
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    
    }
    
    
}
