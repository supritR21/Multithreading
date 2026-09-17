public class Test8 extends Thread {
    @Override
    public void run() {
        while(true) {
            System.out.println("Hello World!");
        }
    }
    public static void main(String[] args) {
        Test8 t1 = new Test8();
        t1.setDaemon(true);
        Test8 t2 = new Test8();
        t2.start();
        t1.start();
        System.out.println("Main done");
    }
}
