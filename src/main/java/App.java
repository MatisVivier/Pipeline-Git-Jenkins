package com.example;

public class App {

    private String appName;

    // Constructeur
    public App(String appName) {
        this.appName = appName;
    }

    // Méthode pour obtenir le nom de l'application
    public String getAppName() {
        return appName;
    }

    // Méthode pour calculer le carré d'un nombre
    public int square(int number) {
        return number * number;
    }

    public static void main(String[] args) {
        App app = new App("TestApp");
        System.out.println("Bienvenue dans " + app.getAppName());
        System.out.println("Le carré de 5 est : " + app.square(5));
    }
}
