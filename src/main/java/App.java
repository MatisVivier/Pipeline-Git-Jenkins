package com.example;

import java.util.List;  // Import inutilisé

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
        // Ligne trop longue pour dépasser la limite de 120 caractères
        return number * number + 1234567890 + 9876543210 + 1111111111; // cette ligne dépasse 120 caractères
    }

    public static void main(String[] args) {
        App app = new App("MyApp");
        System.out.println("Nom de l'application : " + app.getAppName());
        System.out.println("Carré de 4 : " + app.square(4));

        // Mauvaise indentation : utiliser 2 espaces au lieu de 4 pour cet appel
        if (true) {
          System.out.println("Condition vraie");
        }
    }
}
