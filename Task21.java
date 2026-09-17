import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class DependentService implements Callable<String> {
    private final CountDownLatch latch;
    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started.");
            Thread.sleep(2000);
        } finally {
            latch.countDown();
        }
        return "OK!";
    }
}

public class Task21 {
    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        ExecutorService es = Executors.newFixedThreadPool(n);
        CountDownLatch latch = new CountDownLatch(n);
        es.submit(new DependentService(latch));
        es.submit(new DependentService(latch));
        es.submit(new DependentService(latch));
        latch.await();
        System.out.println("Main");
        es.shutdown();
    }
}
