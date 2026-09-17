class Counter {
    private int count = 0;
    public void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}

public class Test9 extends Thread {
    private Counter counter;
    public Test9(Counter counter) {
        this.counter = counter;
    }
    @Override
    public void run() {
        for(int i=1; i<=1000; i++) {
            counter.increment();
        }
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        Test9 t1 = new Test9(counter);
        Test9 t2 = new Test9(counter);

        t1.start();
        t2.start();

        // try {
        //     t1.join();
        //     t2.join();
        // } catch (Exception e) {}

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        System.out.println(counter.getCount());
    }
}
