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

#### 5.4 Accesseurs :