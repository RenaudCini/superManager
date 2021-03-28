package presentation;


import donnees.HerosDAO;
import entite.Element;
import entite.Heros;
import entite.Organisation;
import metier.Heros.HerosMetier;
import metier.Outils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe pr�sentation via un menu
 *
 * @author Tophe
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
                        String[] listeCreation = {"1 Création Super Heros", "2  Création Super Vilain", "3 Création organisation", "4 Création groupe", "5 Retour au menu principale"};
                        do {
                            choixCreation = menu(listeCreation);
                            if (choixCreation != 0) {
                                switch (choixCreation) {
                                    case 1:
                                        System.out.println("---- CREE UN SUPER HERO ----");
                                        System.out.println("saisisez les information suivante");

                                        System.out.println("Id de l'organisation");
                                        Integer idOrganisation = Outils.scanInteger(scan);
                                        Organisation organisation = new Organisation(idOrganisation);

                                        System.out.println("Element");
                                        String nameElement = scan.nextLine();
                                        Element element = new Element(nameElement);
                                        Heros heros = new Heros();
                                        scan = new Scanner(System.in);
                                        try {
                                            HerosMetier hm = new HerosMetier(heros, scan);
                                            heros.setElement(element);
                                            heros.getElement().setId(1);
                                            heros.setOrganisation(organisation);
                                            heros.getOrganisation().setId(1);
                                            HerosDAO h = new HerosDAO();
                                            h.cree(heros);
                                        } catch (NoSuchMethodException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case 2:

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