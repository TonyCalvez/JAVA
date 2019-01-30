import static java.lang.Thread.sleep;

public class A implements Runnable {

    @Override
    public void run() {
        while (true){
            boolean permit = Main.sb.tryAcquire();
            if (permit) {
                Main.na = Main.na + 1;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main.sa.release();
            }
//            else{
//                System.out.println("Semaphore A is not acquired.");
//            }
        }
    }
}
