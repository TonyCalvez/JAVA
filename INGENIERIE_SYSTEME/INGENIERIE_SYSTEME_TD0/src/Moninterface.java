
public interface Moninterface{
    String hello();

    public static void main(String[] args) {
        Montest t = new Montest("Yoda", 500);
        System.out.println(t.nom + " dit " + t.hello());
    }



}
