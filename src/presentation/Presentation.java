package presentation;

import donnees.OrganisationDAO;
import entite.Groupe;
import entite.Heros;
import entite.Vilain;
import entite.Organisation;

import metier.HerosMetier;
import metier.Outils;
import metier.VilainMetier;
import metier.OrganisationMetier;
import metier.GroupeMetier;


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
                        OrganisationDAO organisation = new OrganisationDAO();
                        organisation.findAllByOrganisation();
                        // code block
                        break;
                    // Modification :
                    case 3:
                        int choixModification;
                        String[] listeModification = {
                                "1 - Modifier un Super Heros",
                                "2 - Modifier un Super Vilain",
                                "3 - Modifier une Organisation",
                                "4 - Modifier un Groupe",
                                "5 - Retour au menu principal"
                        };
                        do {
                            choixModification = menu(listeModification);
                            if (choixModification != 0) {
                                switch (choixModification) {
                                    case 1:
                                        System.out.println("---- MODIFIER UN SUPER HEROS ----");

                                        HerosMetier herosMetier = new HerosMetier();
                                        herosMetier.showAllForUpdate();

                                        System.out.println("Saisissez l'id. du super héros à modifier :");

                                        // Récupérer le nom
                                        int idHeros = Outils.scanInteger(scan);

                                        // Appeler HerosMetier qui va créer une entité Heros contenant toutes les infos du héros dont on a saisi le nom
                                        // Montrer les attributs du héros associés à des ID
                                        Heros heros = herosMetier.getHerosById(idHeros);
                                        herosMetier.showHerosForUpdate(heros);

                                        // Demander quel attribut doit être modifié
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateHeros = Outils.scanInteger(scan);

                                        // Récupérer la nouvelle valeur
                                        // L'enregistrer en BDD
                                        herosMetier.updateHeros(scan, heros, idUpdateHeros);
                                        break;
                                    case 2:
                                        System.out.println("---- MODIFIER UN SUPER VILAIN ----");

                                        VilainMetier vilainMetier = new VilainMetier();
                                        vilainMetier.showAllForUpdate();

                                        System.out.println("Saisissez l'id. du super vilain à modifier :");

                                        // Récupérer le nom
                                        int idVilain = Outils.scanInteger(scan);

                                        // Appeler HerosMetier qui va créer une entité Heros contenant toutes les infos du héros dont on a saisi le nom
                                        // Montrer les attributs du héros associés à des ID
                                        Vilain vilain = vilainMetier.getVilainById(idVilain);
                                        vilainMetier.showVilainForUpdate(vilain);

                                        // Demander quel attribut doit être modifié
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateVilain = Outils.scanInteger(scan);

                                        // Récupérer la nouvelle valeur
                                        // L'enregistrer en BDD
                                        vilainMetier.updateVilain(scan, vilain, idUpdateVilain);
                                        break;
                                    case 3:
                                        System.out.println("---- MODIFIER UNE ORGANISATION ----");

                                        OrganisationMetier organisationMetier = new OrganisationMetier();
                                        organisationMetier.showAllForUpdate();

                                        System.out.println("Saisissez l'id. de l'organisation à modifier :");

                                        // Récupérer le nom
                                        int idOrganisation = Outils.scanInteger(scan);

                                        // Appeler HerosMetier qui va créer une entité Heros contenant toutes les infos du héros dont on a saisi le nom
                                        // Montrer les attributs du héros associés à des ID
                                        Organisation orgUpdate = organisationMetier.getOrganisationById(idOrganisation);
                                        organisationMetier.showOrganisationForUpdate(orgUpdate);

                                        // Demander quel attribut doit être modifié
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateOrganisation = Outils.scanInteger(scan);

                                        // Récupérer la nouvelle valeur
                                        // L'enregistrer en BDD
                                        organisationMetier.updateOrganisation(scan, orgUpdate, idUpdateOrganisation);
                                        break;
                                    case 4:
                                        System.out.println("---- MODIFIER UN GROUPE ----");

                                        GroupeMetier groupeMetier = new GroupeMetier();
                                        groupeMetier.showAllForUpdate();

                                        System.out.println("Saisissez l'id. du groupe à modifier :");

                                        int idGroupe = Outils.scanInteger(scan);

                                        Groupe groupeUpdate = groupeMetier.getGroupeById(idGroupe);
                                        groupeMetier.showGroupeForUpdate(groupeUpdate);

                                        int idUpdateGroupe = Outils.scanInteger(scan);

                                        groupeMetier.updateGroupe(scan, groupeUpdate, idUpdateGroupe);
                                        break;
                                    case 5:
                                        choixModification = 0;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (choixModification != 0);
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
                choix = Outils.scanInteger(scan);
                state = false;
            } catch (Exception e) {
                System.out.println("Vous devez saisir un entier");
            }
            //scan.nextLine();
        } while (state);
        return choix;
    }
}