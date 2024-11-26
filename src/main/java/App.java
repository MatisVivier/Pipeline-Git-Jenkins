package com.example;
 
public class App {
    private String appName;

    // Constructeur par défaut
    public App() {
        this.appName = "DefaultApp";
    }

    // Constructeur avec un paramètre
    public App(String appName) {
        this.appName = appName;
    }

    // Méthode pour retourner le nom de l'application
    public String getAppName() {
        return appName;
    }

    // Méthode pour calculer le carré d'un nombre
    public int square(int number) {
        if (number == 4) {
            throw new RuntimeException("Erreur simulée : Carré de 4 n'est pas autorisé");
        }
        return number * number;
    }

    public static void main(String[] args) {
        App app = new App("MyApp");
        System.out.println("Nom de l'application : " + app.getAppName());
        System.out.println("Carré de 4 : " + app.square(4));  // Ceci va générer une exception
    }
}
