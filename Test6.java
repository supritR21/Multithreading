public class Test6 extends Thread {
    public Test6(String name) {
        super(name);
    }
    @Override
    public void run() {
        System.out.println("Thread is running...");
        for(int i=1; i<=5; i++) {
            for(int j=1; j<=5; j++) {
                System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority() + " - count: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Test6 l1 = new Test6("Low Priority Thread");
        Test6 m1 = new Test6("Medium Priority Thread");
        Test6 h1 = new Test6("High Priority Thread");

        l1.setPriority(Thread.MIN_PRIORITY);
        m1.setPriority(Thread.NORM_PRIORITY);
        h1.setPriority(Thread.MAX_PRIORITY);
        l1.start();
        m1.start();
        h1.start();
    }
}
