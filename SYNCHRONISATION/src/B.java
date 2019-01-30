import static java.lang.Thread.sleep;

public class B implements Runnable {

    @Override
    public void run() {
        while (true){
            boolean permit = Main.sa.tryAcquire();
            if (permit) {
                Main.nb = Main.nb + 1;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main.sb.release();
            }
//            else{
//                System.out.println("Semaphore B is not acquired.");
//            }

        }
    }
}
