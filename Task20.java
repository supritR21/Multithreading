class SharedObj {
    private volatile boolean flag = false;

    public void setFlagTrue() {
        System.out.println("Writer thread made the flag true! ");
        flag = true;
    }
    public void printIfFlagTrue() {
        while(!flag) {}
        System.out.println("Flag is true! ");
    }
}

public class Task20 {
    public static void main(String[] args) throws InterruptedException{
        SharedObj so = new SharedObj();
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            so.setFlagTrue();
        });

        Thread readerThread = new Thread(() -> so.printIfFlagTrue());
        writerThread.start();
        readerThread.start();
    }
}
