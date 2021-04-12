package presentation;

import entite.Heros;
import entite.Vilain;
import java.util.Scanner;

/**
 * Classe pr�sentation via un menu
 *
 *
 */
public class Presentation {

    private static Scanner scan;

    public Presentation() {

    }

    public void logiquePresentation() {
        int choix = 0;
        scan = new Scanner(System.in);

        String[] liste = {"1 Création", "2 Visionner", "3 Modifer", "4 Combat"};
        do {
            choix = menu(liste);
            if (choix != 0) {
                switch (choix) {
                    case 1:
                        int choixCreation;
                        String[] listeCreation = {"1 Création Super Heros", "2 Création Super Vilain", "3 Création organisation", "4 Création groupe", "5 Retour au menu principale"};
                        do {
                            choixCreation = menu(listeCreation);
                            if (choixCreation != 0) {
                                switch (choixCreation) {
                                    case 1:
                                        System.out.println("---- CREER UN SUPER HEROS ----");
                                        System.out.println("Saisissez les informations suivantes");

                                        Heros heros = new Heros();
                                        heros.creeHero(scan);

                                        break;
                                    case 2:
                                        System.out.println("---- CREER UN SUPER VILAIN ----");
                                        System.out.println("Saisissez les informations suivantes");

                                        Vilain vilain = new Vilain();
                                        vilain.creerVilain(scan);

                                        break;
                                    case 4:

                                        break;
                                    case 5:

                                        choixCreation = 0;
                                        break;
                                    default:

                                }
                            }
                        } while (choixCreation != 0);
                        break;
                    case 2:
                        // code block
                        break;
                    case 3:
                        // code block
                        break;
                    case 4:
                        // code block
                        break;

                    default:
                        // code block
                }
            }
        } while (choix != 0);
    }


    private int menu(String[] liste) {
        int choix = 0;
        boolean state = true;
        do {

            for (String i : liste) {
                System.out.println(i);
            }
            try {
                choix = scan.nextInt();
                state = false;
            } catch (Exception e) {
                System.out.println("Vous devez saisir un entier");
            }
            //scan.nextLine();
        } while (state);
        return choix;
    }
}