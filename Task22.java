import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class SubSystem implements Runnable {
    private String name;
    private int intializationTime;
    private CyclicBarrier barrier;

    public SubSystem(String name, int intializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.intializationTime = intializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(intializationTime);
            System.out.println(name + " initialization complete.");
            barrier.await();
            //System.out.println("Finally");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class Task22 {
    public static void main(String[] args) {
        int numberOfSubSystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubSystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All subsystems are up and running. System startup complete.");
            }
        });

        Thread webServerThread = new Thread(new SubSystem("Web Server", 2000, barrier));
        Thread databaseThread = new Thread(new SubSystem("Database", 4000, barrier));
        Thread cacheThread = new Thread(new SubSystem("Cache", 3000, barrier));
        Thread messagingServiceThread = new Thread(new SubSystem("Messaging Service", 3500, barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}
