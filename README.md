# JAVA

## Base du Langage

### 1. Variables

#### 1.1 Bases :

| Variables:    | Description:                                                 |
| ------------- | ------------------------------------------------------------ |
| Instance      | Déclarée à l'intérieur d'un classe.                          |
| ```static ``` | Variable de classe. Accessible directement par le nom de la classe. |
| Local         | Déclarée à l'intérieur d'un fonction.                        |

| Types:       | Nombre de bits: | Valeurs:                   |
| ------------ | --------------- | -------------------------- |
| ```byte```   | 8 bits          | [-128 ; 127]               |
| `short`      | 16 bits         | [-32768 ; 32767]           |
| ``` int```   | 32 bits         | [-2147483648 ; 2147483647] |
| ```long```   | 64 bits         |                            |
| ```float```  |                 |                            |
| ``` enum```  |                 | 0 , 1 , 2 ...              |
| ```String``` |                 | "Chaine de caractères"     |



 #### 1.2 Niveau d'accès aux variables :

Limiter l'accès d'une variable lors de la déclaration la classe.

| Types:      |                                                              |
| ----------- | ------------------------------------------------------------ |
| `private`   | Variable utilisable que dans la classe où elle est déclarée. |
| `protected` | Variable utilisable que dans la classe, les sous-classes et package où elle est déclarée. |
| `public`    | Variable accessible de n'importe quelle classe extérieure.   |
| `   `       | Variable utilisable que dans la classe et package où elle est déclarée. |
| `static`    | Transforme une déclaration de variable d'instance en déclaration de variable de classe. |
| ```final``` | Constante                                                    |

#### 1.3 Instancier :

```java
Voiture objVoiture = new Voiture();
```



### 2. Structures Conditionnelles

#### 2.1 If / Else :

```java
if ( expressionBooleenne1 ) {
 action1
}
else if ( expressionBooleenne2 ) {
 action2
}
else {
 action3
}
```

```java
condition ? action1 : action 3
```



#### 2.2 Switch :

```java
int i = genererUnEntier () ;
switch ( i ) {
 case 0:
 // liste d’ instructions ...
 break ;
 case 1:
 // autres instructions ...
 break ;
 default :
 // instructions differentes ...
}
```

### 3. Boucles:

#### 3.1 While :

```java
int i =0;
while (i <10) { // boucle tant que i <10
 System . out . println ( i ) ; // affiche la valeur de i
 i ++; // i=i+1
}
```

#### 3.1 For :

```java
int i ;
for ( i =0 ; i <10 ; i ++) {
 System . out . println ( i ) ;
}
```



### 4. Les Tableaux:

#### 4.1 Déclaration :

```java
int[] tableau1;
int tableau2[];
tableau3 = new int[3];
tableau4 = {0,1,2,3};
int[][] matrice2d;

```



### 5. Les Classes:

#### 5.1 Déclaration :

**EXEMPLE :**

Pour le fichier *NomDeLaClasse.java* : 

```java
[MODIFICATEUR] class NomDeLaClasse [extends NomDeLaClasseMere]{
    
}

```

#### 5.2 Modificateur :

> Limiter l'accès d'une variable lors de la déclaration la classe.

| Types:     |                                          |
| ---------- | ---------------------------------------- |
| `public`   | Utilisable par toutes les autres classes |
| `abstract` | Ne peux pas être instancié               |
| `final`    | Doit être instancié                      |

#### 5.3 Variables :

**EXEMPLE :**

Pour le fichier *NomDeLaClasse.java* : 

```java
[MODIFICATEUR] class NomDeLaClasse [extends NomDeLaClasseMere]{
    [MODIFICATEUR] [TYPES VARIABLE] nomvariable;
}

```

Pour le fichier *voiture.java* : 

```java
[MODIFICATEUR] class Voiture {
    private String nom;
    private String prenom;
    private LocalDate miseencirculation;
}

```

#### 5.3 Méthodes :

> Une méthode est une fonction codée directement dans la classe.

**EXEMPLE :**

Pour le fichier *NomDeLaClasse.java* : 

```java
[MODIFICATEUR] class NomDeLaClasse [extends NomDeLaClasseMere]{
    [MODIFICATEUR] [TYPES VARIABLE] nomvariable;
    
    [MODIFICATEUR] [TYPES VARIABLE] nomdelamethode([PARAMETRE1]) [throws RetourException]{
        
    }
}

```

Pour le fichier *voiture.java* : 

```java
[MODIFICATEUR] class Voiture {
    private String nom;
    private String prenom;
    private LocalDate miseencirculation;
    
    public long calculAncienneté (){
        return miseencirculation.until(LocalDate.now(),ChronoUnit.YEARS);
    }
}

```

#### 5.4 Accesseurs :

> Facilite le retour ou l'affectation d'une variable privée avec une méthode dédiée.

| Types: |                                             |
| ------ | ------------------------------------------- |
| `set`  | Affecte la valeur à une variable            |
| `get`  | Retourne la valeur d'une variable           |
| `is`   | Retourne la valeur d'une variable booléenne |

**EXEMPLE :**

Pour le fichier *voiture.java* : 

```java
[MODIFICATEUR] class Voiture {
    private String nom;
    private String prenom;
    private LocalDate miseencirculation;
    
    public long calculAncienneté (){
        return miseencirculation.until(LocalDate.now(),ChronoUnit.YEARS);
    }
    
    public void setNom(String nomrentre){
    	   nom = nomrentre.toUpperCase();
    }
    
    public String getNom(){
        return nom;
    }
}

```

#### 5.5  Constructeur : 

> Le constructeur d'une classe est une méthode portant le même nom que la classe elle-même.
>
> On lui attribut des paramètres mais il ne retourne aucun type (même pas de `void`).
>
> Elle permet d'importer des paramètres directement du main.

**EXEMPLE :**

Pour le fichier *voiture.java* : 

```java
[MODIFICATEUR] class Voiture {
    private String nom;
    private String prenom;
    private LocalDate miseencirculation;
    
    public Voiture (String n, String p, LocalDate d){
        nom = n;
        prenom = p;
        miseencirculation = d;
    }
}

```

#### 5.6 Classes Abstraites : 

> Le constructeur d'une classe est une méthode portant le même nom que la classe elle-même.
>
> On lui attribut des paramètres mais il ne retourne aucun type (même pas de `void`).

**EXEMPLE :**

Pour le fichier *voiture.java* : 

```java
[MODIFICATEUR] abstract class Voiture {
    private String nom;
    private String prenom;
    private LocalDate miseencirculation;
    
    public Voiture (String n, String p, LocalDate d){
        nom = n;
        prenom = p;
        miseencirculation = d;
    }
}

```

Elle permettra de définir plus tard dans une classe fille les propriétés.

#### 5.7 Héritages (Inheritance) :

Pour savoir si deux classes peuvent être l'héritage de l'une et de l'autre, on demande si classe1 `est une sorte de` classe2.

L'héritage permet de communiquer et acquérir méthodes ou les attributs par un couplage fort d'une classe mère. Toutes modifications réalisées dans celle-ci entraine une modification sur la classe fille.

Cette relation hiérarchique est divisée en deux: une *classe mère* (`superclass`) et une *sous-classe* (`subclass`) et en Java il ne peut y avoir qu'un seul héritage.

Afin de relier la classe mère avec la classe fille, nous utilisons juste le mot `extend`.

Pour associer la variable dans une classe, nous utilisons : `this`.



#### 5.7 Délégation : 

La délégation met en oeuvre un Design Pattern (exemple : paragraphe 6).

La tâche est confiée à quelqu'un d'autre avec un couplage de la classe "mère".

Afin de relier la classe mère avec la classe mandatée, nous venons lier la classe secrétaire et la classe boss dans le main.

**EXEMPLE :**

Pour le fichier *Boss.java* : 

```java
public class Boss {

        private Secretary secretary;

        public Boss(Secretary secretary){
            this.secretary = secretary;
        }

        public String work() {
            return this.secretary.work();
        }
}

```

Pour le fichier *Secretary.java* : 

```java
public class Secretary {
    public String work() {
        return "This is my work !!!!";
    }
}

```

Pour le fichier *Main.java* : 

```java
public class Main {

    public static void main(String args[]) {
        Secretary s = new Secretary();
        Boss b = new Boss(s);
        System.out.println(b.work());
    }
}

```



#### 5.8 Composition :

La composition vient intégrer des méthodes supplémentaires des classes mères et `encapsule` un ensemble de méthodes.



### 6 Design Pattern

Source: https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm

Genérer un `package com.TonyCalvez.EncryptDecrypt;`

#### 6.1 Réaliser une interface :

Créer un fichier :  *Strategy.java*

````java
public interface Strategy {
    public String doEncrypt(String word); //fonctionnalité n°1
}
````



#### 6.2 Associer des classes à notre interface :

Ensuite, nous pouvons dériver 1,2,3 ... n fois cette fonctionnalité dans une classe diffèrente à chaque fois.

**EXEMPLE :**

Créer un fichier :  *Ceasar.java*

```java
public class Ceasar implements Strategy {
    private int shift;

    public Ceasar(int shift) {
        this.shift = shift;
    }

    @Override
    public String doEncrypt(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            int charShift = 97;
            if (Character.isUpperCase(c))
                charShift = 65;
            char ch = (char) (((int) c + shift - charShift) % 26 + charShift);
            sb.append(ch);
        }
        return sb.toString();
    }
}
```



Créer un fichier :  *Substitution.java*

```java
public class Substitution implements Strategy {
    private String keySeed;

    public Substitution(String keySeed) {
        this.keySeed = keySeed;
    }

    @Override
    public String doEncrypt(String word) {
        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char m = word.charAt(i);
            char k = keySeed.charAt(i % keySeed.length());;
            int mValue = (int) m - ((Character.isUpperCase(m)) ? 65 : 97);
            int kValue = (int) k - ((Character.isUpperCase(k)) ? 65 : 97);
            int cValue = ((mValue + kValue) % 26 + 26) % 26;;
            sb.append((char) (cValue + 97));
        }
        return sb.toString();
    }
}
```



#### 6.3 Réaliser une classe context :

Créer un fichier :  *Context.java*

```java
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    
    public void setStrategy(Strategy s){
        this.strategy= s;
    }

    public String executeStrategyEncrypt(String word){
        return strategy.doEncrypt(word);
    }
}
```



#### 6.4 Executer avec le Main :

Créer un fichier :  *Main.java*

```java
public class Main {
    public static void main(String[] args) {
        Context context = new Context(new Ceasar(13));
        context.setStrategy(new Ceasar(21));
        context.executeStrategyEncrypt("MOT");
        System.out.println(context.executeStrategyEncrypt("MOT"));
        System.out.println(context.executeStrategyDecrypt(context.executeStrategyEncrypt("MOT")));
    }
}
```



### 7 Tester avec Junit

#### 7.1 Génerer la classe automatiquement

1- Cliquer sur votre classe puis <kbd>Alt</kbd>+<kbd>Enter</kbd>.

2- Cliquer dans la boite de dialogue : `Create Test`.

3- Tout cocher

4- Une nouvelle classe pour le test est genérée.

#### 7.2 Tester son programme

Par exemple dans :

```java
class CeasarTest {
    Ceasar c;
    @org.junit.jupiter.api.Test
    void doDecrypt() {
        assertEquals("abcde", this.c.doDecrypt("cdefg"));
    }
}
```

Nous savons que notre code, nous retournera  `abcde` si nous entrons `cdefg`. A partir de la fonction `assertEquals()`, nous pouvons tester la fonction.

