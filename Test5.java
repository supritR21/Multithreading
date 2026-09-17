public class Test5 extends Thread {
    @Override
    public void run() {
        System.out.println("Running");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Test5 t1 = new Test5();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        
        try {
            Thread.sleep(100);
        } catch(Exception e) {}

        System.out.println(t1.getState());

        try {
            t1.join();
        } catch (Exception e) {}
        System.out.println(t1.getState());

    }
}
