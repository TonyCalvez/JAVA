import java.util.Scanner;
import java.util.InputMismatchException;

public class MyClass {
    public static void main(String args[]){

        double[] tab = new double[50];

        for(int i = 0; i < tab.length; i++){
            tab[i] = tab.length - i - 1;
        }
        for (int i = tab.length -1; i >= 0;i--)
            tab[tab.length - i -1] = i;

        for(int i = 0; i < tab.length; i++)
            System.out.println(i + "\t --> " + tab[i]);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your mark :");

        double mark = 0;
        try {
            mark = sc.nextDouble();
        } catch (InputMismatchException ex) {
            System.out.println("Erreur");
            System.exit(1);
        } finally {
            System.out.println("Mark : " + mark);
        }

        for (int i=0 ;   i<tab.length ; i++){

            try {
                tab[i] = mark / i;
            } catch (ArithmeticException ex){
                System.out.println("Erreur");
                System.exit(1);
            } finally {
                System.out.println(tab[i]);
            }
        }

    }
}

