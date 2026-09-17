public class Test7 extends Thread {
    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running...");
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        Test7  t1 = new Test7();
        Test7 t2 = new Test7();
        t1.start();
        t2.start();
    }
}
