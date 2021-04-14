package presentation;

import donnees.OrganisationDAO;
import entite.*;
import entite.Groupe;
import entite.Heros;
import entite.Vilain;
import entite.Organisation;
import metier.Outils;

import metier.HerosMetier;
import metier.VilainMetier;
import metier.OrganisationMetier;
import metier.GroupeMetier;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe pr�sentation via un menu
 *
 *
 */
public class Presentation {

    private static Scanner scan;

    public Presentation() {}

    /**
     * Permet d'afficher notre menu et de gérer les input utilisateur. Il s'agit de notre front controller.
     */
    public void logiquePresentation() {
        int choix = 0;
        scan = new Scanner(System.in);
        System.out.println("-----------------------");
        System.out.println("|    SUPER MANAGER    |");
        System.out.println("-----------------------");

        String[] liste = {
                "---- MENU PRINCIPAL ----",
                "1 - Créer",
                "2 - Visionner",
                "3 - Modifier",
                "4 - Combat"
        };
        do {
            choix = menu(liste);
            if (choix != 0) {
                switch (choix) {
                    case 1:
                        int choixCreation;
                        String[] listeCreation = {
                                "---- CREER ----",
                                "1 - Créer un Super Héros",
                                "2 - Créer un Super Vilain",
                                "3 - Créer une Organisation",
                                "4 - Créer un Groupe",
                                "5 - Retour au menu principal"};
                        do {
                            choixCreation = menu(listeCreation);
                            if (choixCreation != 0) {
                                switch (choixCreation) {
                                    case 1:
                                        System.out.println("---- CREER UN SUPER HEROS ----");
                                        System.out.println("Saisissez les information suivantes :");

                                        HerosMetier herosMetier = new HerosMetier();
                                        herosMetier.creerHeros(scan);

                                        break;
                                    case 2:
                                        System.out.println("---- CREER UN SUPER VILAIN ----");
                                        System.out.println("Saisissez les informations suivantes :");

                                        VilainMetier vilainMetier = new VilainMetier();
                                        vilainMetier.creerVilain(scan);
                                        break;
                                    case 3:
                                        System.out.println("---- CREER UNE ORGANISATION ----");
                                        System.out.println("Saisissez les informations suivantes :");

                                        OrganisationMetier organisationMetier = new OrganisationMetier();
                                        organisationMetier.creerOrganisation(scan);
                                        break;
                                    case 4:
                                        System.out.println("---- CREER UN GROUPE ----");
                                        System.out.println("Saisissez les informations suivantes :");

                                        GroupeMetier groupeMetier = new GroupeMetier();
                                        groupeMetier.creer(scan);

                                        break;
                                    case 5:
                                        choixCreation = 0;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (choixCreation != 0);
                        break;
                    case 2:
                        //OrganisationDAO organisation = new OrganisationDAO();
                        //organisation.findAllByOrganisation();
                        int choixVisionnage;
                        String[] listeVisionnage = {
                                "---- VISIONNER ----",
                                "1 - Voir tous les Super Héros",
                                "2 - Voir la fiche d'un Super Héros",
                                "3 - Voir tous les Super Vilains",
                                "4 - Voir la fiche d'un Super Vilain",
                                "5 - Voir toutes les organisations",
                                "6 - Voir le détail d'une organisation",
                                "7 - Voir tous les groupes",
                                "8 - Voir le détail d'un groupe",
                                "9 - Revenir au menu principal"
                        };
                        do {
                            HerosMetier herosMetier = new HerosMetier();
                            VilainMetier vilainMetier = new VilainMetier();
                            OrganisationMetier organisationMetier = new OrganisationMetier();
                            GroupeMetier groupeMetier = new GroupeMetier();

                            choixVisionnage = menu(listeVisionnage);
                            if (choixVisionnage != 0) {
                                switch (choixVisionnage) {
                                    case 1:
                                        System.out.println("---- VOIR TOUS LES SUPER HEROS ----");
                                        // Affichage de tous les héros :
                                        herosMetier.showAllForUpdate();
                                        break;
                                    case 2:
                                        System.out.println("---- VOIR LA FICHE D'UN SUPER HEROS ----");
                                        System.out.println("Saisissez l'id. d'un super héros dont vous voulez voir la fiche :");
                                        int idHeros = Outils.scanInteger(scan);
                                        // Affichage de tous les héros :
                                        Heros heros = herosMetier.getHerosById(idHeros);
                                        herosMetier.showHerosForUpdate(heros);
                                        break;
                                    case 3:
                                        System.out.println("---- VOIR TOUS LES SUPER VILAINS ----");
                                        // Affichage de tous les héros :
                                        vilainMetier.showAllForUpdate();
                                        break;
                                    case 4:
                                        System.out.println("---- VOIR LA FICHE D'UN SUPER VILAIN ----");
                                        System.out.println("Saisissez l'id. d'un super vilain dont vous voulez voir la fiche :");
                                        int idVilain = Outils.scanInteger(scan);
                                        // Affichage de tous les héros :
                                        Vilain vilain = vilainMetier.getVilainById(idVilain);
                                        vilainMetier.showVilainForUpdate(vilain);
                                        break;
                                    case 5:
                                        System.out.println("---- VOIR TOUTES LES ORGANISATIONS ----");
                                        // Affichage de tous les héros :
                                        organisationMetier.showAllForUpdate();
                                        break;
                                    case 6:
                                        System.out.println("---- VOIR UNE ORGANISATION ----");
                                        System.out.println("Saisissez l'id. d'une organisation dont vous voulez voir le détail :");
                                        int idOrganisation = Outils.scanInteger(scan);
                                        // Affichage de tous les héros :
                                        Organisation organisation = organisationMetier.getOrganisationById(idOrganisation);
                                        organisationMetier.showOrganisationForUpdate(organisation);
                                        break;
                                    case 7:
                                        System.out.println("---- VOIR TOUS LES GROUPES ----");
                                        // Affichage de tous les héros :
                                        groupeMetier.showAllForUpdate();
                                        break;
                                    case 8:
                                        System.out.println("---- VOIR UN GROUPE ----");
                                        System.out.println("Saisissez l'id. d'un groupe dont vous voulez voir le détail :");
                                        int idGroupe = Outils.scanInteger(scan);
                                        // Affichage de tous les héros :
                                        Groupe groupe = groupeMetier.getGroupeById(idGroupe);
                                        groupeMetier.showGroupeForUpdate(groupe);
                                        break;
                                    case 9:
                                        choixVisionnage = 0;
                                        break;
                                    default:
                                        break;
                                }
                            }
                        } while (choixVisionnage != 0);
                        break;
                    // Modification :
                    case 3:
                        int choixModification;
                        String[] listeModification = {
                                "---- MODIFIER ----",
                                "1 - Modifier un Super Héros",
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

                                        // Affichage de tous les héros :
                                        HerosMetier herosMetier = new HerosMetier();
                                        herosMetier.showAllForUpdate();

                                        // Récupération de l'id. du héros à modifier :
                                        System.out.println("Saisissez l'id. du super héros à modifier :");
                                        int idHeros = Outils.scanInteger(scan);

                                        // Récupération des infos du héros à modifier et affichage :
                                        Heros heros = herosMetier.getHerosById(idHeros);
                                        herosMetier.showHerosForUpdate(heros);

                                        // Demander quel attribut doit être modifié
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateHeros = Outils.scanInteger(scan);

                                        // Update du héros :
                                        herosMetier.updateHeros(scan, heros, idUpdateHeros);
                                        break;
                                    case 2:
                                        System.out.println("---- MODIFIER UN SUPER VILAIN ----");

                                        // Affichage de tous les vilains :
                                        VilainMetier vilainMetier = new VilainMetier();
                                        vilainMetier.showAllForUpdate();

                                        // Récupération de l'id. du vilain à modifier :
                                        System.out.println("Saisissez l'id. du super vilain à modifier :");
                                        int idVilain = Outils.scanInteger(scan);

                                        // Récupération des infos du vilain à modifier et affichage :
                                        Vilain vilain = vilainMetier.getVilainById(idVilain);
                                        vilainMetier.showVilainForUpdate(vilain);

                                        // Demander quel attribut doit être modifié :
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateVilain = Outils.scanInteger(scan);

                                        // Update du vilain :
                                        vilainMetier.updateVilain(scan, vilain, idUpdateVilain);
                                        break;
                                    case 3:
                                        System.out.println("---- MODIFIER UNE ORGANISATION ----");

                                        // Affichage de toutes les organisations :
                                        OrganisationMetier organisationMetier = new OrganisationMetier();
                                        organisationMetier.showAllForUpdate();

                                        // Récupération de l'id. de l'organisation à modifier :
                                        System.out.println("Saisissez l'id. de l'organisation à modifier :");
                                        int idOrganisation = Outils.scanInteger(scan);

                                        // Récupération des infos de l'organisation à modifier et affichage :
                                        Organisation orgUpdate = organisationMetier.getOrganisationById(idOrganisation);
                                        organisationMetier.showOrganisationForUpdate(orgUpdate);

                                        // Demander quel attribut doit être modifié :
                                        System.out.println("Saisissez l'identifiant associé à l'attribut que vous voulez modifier :");
                                        int idUpdateOrganisation = Outils.scanInteger(scan);

                                        // Update de l'organisation :
                                        organisationMetier.updateOrganisation(scan, orgUpdate, idUpdateOrganisation);
                                        break;
                                    case 4:
                                        System.out.println("---- MODIFIER UN GROUPE ----");

                                        // Affichage de tous les groupes :
                                        GroupeMetier groupeMetier = new GroupeMetier();
                                        groupeMetier.showAllForUpdate();

                                        // Récupération de l'id. du groupe à modifier :
                                        System.out.println("Saisissez l'id. du groupe à modifier :");
                                        int idGroupe = Outils.scanInteger(scan);

                                        // Récupération des infos du groupe à modifier et affichage :
                                        Groupe groupeUpdate = groupeMetier.getGroupeById(idGroupe);
                                        groupeMetier.showGroupeForUpdate(groupeUpdate);

                                        // Demander quel attribut doit être modifié dans la fonction showGroupeForUpdate :
                                        System.out.println("");
                                        System.out.println("1 - Modifier le nom du groupe");
                                        System.out.println("2 - Ajouter un héros à la liste");
                                        System.out.println("3 - Supprimer un héros de la liste");
                                        System.out.println("4 - Ajouter un vilain à la liste");
                                        System.out.println("5 - Supprimer un vilain de la liste");
                                        int idUpdateGroupe = Outils.scanInteger(scan);

                                        // Update du groupe :
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
                        System.out.println("---- COMBAT ----");

                        // Affichage des groupes :
                        GroupeMetier groupeMetier = new GroupeMetier();
                        groupeMetier.showAllForUpdate();

                        // Récupération du groupe demandé par l'utilisateur :
                        System.out.println("Saisissez l'id. d'un groupe pour lancer le combat :");
                        int value =  Outils.scanInteger(scan);
                        Groupe groupe = groupeMetier.getGroupeById(value);

                        groupeMetier.combat(groupe);

                        break;
                    default:
                        break;
                }
            }
        } while (choix != 0);
    }

    /**
     * Permet d'afficher un menu depuis une liste d'éléments et de renvoyer l'input de l'utilisateur.
     *
     * @param liste [String[]] La liste des éléments.
     * @return [int] Le choix de l'utilisateur.
     */
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