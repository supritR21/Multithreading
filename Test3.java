class World extends Thread {
    @Override
    public void run() {
        for(int i=1; i<100; i++) {
            System.out.println(i + "-1");

        }
    }
}

public class Test3 {
    public static void main(String[] args) {
        World world = new World();
        world.start();
        for(int i=1; i<100; i++) {
            System.out.println(i + "-2");
        }
        
    }
}
