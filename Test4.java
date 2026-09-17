class Sub4 implements Runnable {
    @Override
    public void run() {
        for(int i=1; i<=30; i++) {
            System.out.println(i + "-1");
        }
    }
}

public class Test4 {
    public static void main(String[] args) {
        Sub4 sub = new Sub4();
        Thread thread = new Thread(sub);
        thread.start();
        for(int i=1; i<=30; i++) {
            System.out.println(i + "-2");
        }
    }
}
