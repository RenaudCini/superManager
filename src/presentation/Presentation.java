package presentation;

import donnees.OrganisationDAO;
import entite.Groupe;
import entite.Heros;
import entite.Vilain;
import entite.Organisation;
import metier.Outils;

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
        System.out.println("-----------------------");
        System.out.println("|    SUPER MANAGER    |");
        System.out.println("-----------------------");

        String[] liste = {"1 - Créer", "2 - Visionner", "3 - Modifier", "4 - Combat"};
        do {
            choix = menu(liste);
            if (choix != 0) {
                switch (choix) {
                    case 1:
                        int choixCreation;
                        String[] listeCreation = {"1 - Créer un Super Héros", "2 - Créer un Super Vilain", "3 - Créer une Organisation", "4 - Créer un Groupe", "5 - Retour au menu principal"};
                        do {
                            choixCreation = menu(listeCreation);
                            if (choixCreation != 0) {
                                switch (choixCreation) {
                                    case 1:
                                        System.out.println("---- CREER UN SUPER HEROS ----");
                                        System.out.println("Saisissez les information suivantes :");

                                        Heros heros = new Heros();
                                        heros.creeHero(scan);

                                        break;
                                    case 2:
                                        System.out.println("---- CREER UN SUPER VILAIN ----");
                                        System.out.println("Saisissez les informations suivantes :");

                                        Vilain vilain = new Vilain();
                                        vilain.creerVilain(scan);

                                        break;
                                    case 3:
                                        System.out.println("---- CREER UNE ORGANISATION ----");
                                        System.out.println("Saisissez les informations suivantes :");

                                        Organisation organisation = new Organisation();
                                        organisation.creeOrganisation(scan);

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
                        OrganisationDAO organisation = new OrganisationDAO();
                        organisation.findAllByOrganisation();
                        // code block
                        break;
                    // Modification :
                    case 3:
                        int choixModification;
                        String[] listeModification = {
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
                                        // + Demander quel attribut doit être modifié dans la fonction showGroupeForUpdate :
                                        Groupe groupeUpdate = groupeMetier.getGroupeById(idGroupe);
                                        groupeMetier.showGroupeForUpdate(groupeUpdate);
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
                        break;
                    default:
                        break;
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