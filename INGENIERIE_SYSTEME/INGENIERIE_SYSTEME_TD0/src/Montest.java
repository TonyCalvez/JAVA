
public class Montest implements Moninterface{

    int age;
    String nom;

    public Montest(String nom, int age){
        this.age = age;
        this.nom = nom;
    }


    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String hello() {
        return "Bonjour";
    }
}