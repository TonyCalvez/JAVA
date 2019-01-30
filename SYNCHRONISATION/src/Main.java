import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    public static int na;
    public static int nb;

    public static Semaphore sa = new Semaphore(1, true);
    public static Semaphore sb = new Semaphore(1, true);

    public static int result;


    public static void main (String[] args){
        Thread ta = new Thread(new A());
        ta.start();

        Thread tb = new Thread(new B());
        tb.start();

        while(true){
            try{
                sa.tryAcquire(1, TimeUnit.SECONDS);
                sb.tryAcquire(1, TimeUnit.SECONDS);
                result = (nb - na);
                System.out.println("A-B ->" + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Main.na);
                System.out.println(Main.nb);

            }
        }
    }
}
