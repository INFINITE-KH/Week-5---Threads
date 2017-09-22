package opg;

public class Counter {
    
    private int counter;
    
    Counter(int counter){
        this.counter = counter;
    }
    
    public synchronized void decrease(){
        counter--;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    
}
