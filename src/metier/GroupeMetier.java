package metier;

import donnees.GroupeDAO;
import entite.Groupe;
import entite.Heros;
import entite.Vilain;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupeMetier {
    protected Groupe groupe;

    public GroupeMetier() {}

    /**
     * Permet de récupérer un groupe par son ID.
     * @param id [int] L'ID du groupe.
     * @return [Groupe] Un objet de type Groupe.
     */
    public Groupe getGroupeById(int id) {
        GroupeDAO groupeDAO = new GroupeDAO();
        Groupe groupe = new Groupe();
        groupe = groupeDAO.findById(id);
        return groupe;
    }

    /**
     * Permet d'afficher les détails d'un groupe.
     * @param groupe [Groupe] Un objet de type Groupe.
     */
    public void showGroupeForUpdate(Groupe groupe) {
        System.out.println("Nom : " + groupe.getNom());

        System.out.println("Liste des héros :");
        ArrayList<Heros> listeHeros = groupe.getListeHeros();
        for (int i = 0; i < listeHeros.size(); i++) {
            System.out.println("- Id. : " + listeHeros.get(i).getSuperPersonnageId() + ", nom : " + listeHeros.get(i).getNom());
        }

        System.out.println("Liste des vilains :");
        ArrayList<Vilain> listeVilains = groupe.getListeVilains();
        for (int i = 0; i < listeVilains.size(); i++) {
            System.out.println("- Id. : " + listeVilains.get(i).getSuperPersonnageId() + ", nom : " + listeVilains.get(i).getNom());
        }
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllForUpdate() {
        GroupeDAO groupeDAO = new GroupeDAO();
        ArrayList<Groupe> listeGroupes = groupeDAO.findAll();

        System.out.println("Liste des groupes :");
        for (int i = 0; i < listeGroupes.size(); i++) {
            System.out.println("- " + listeGroupes.get(i).getId() + " : " + listeGroupes.get(i).getNom());
        }
    }

    /**
     * Permet d'update le groupe ou le groupe_id d'un personnage selon l'input de l'utilisateur, et de récupérer la
     * nouvelle valeur entrée par l'utilisateur pour update en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @param groupe [Groupe] Un objet de type Groupe.
     * @param idUpdate [int] Le choix de l'utilisateur.
     */
    public void updateGroupe(Scanner scan, Groupe groupe, int idUpdate) {
        GroupeDAO groupeDAO = new GroupeDAO();
        HerosMetier herosMetier = new HerosMetier();
        VilainMetier vilainMetier = new VilainMetier();

        switch (idUpdate) {
            case 1:
                String value1;
                System.out.println("Saisissez un nouveau nom pour le groupe " + groupe.getNom() + " :");
                value1 = scan.nextLine();
                groupeDAO.updateGroupe(value1, groupe.getId());
                break;
            case 2:
                int value2;
                herosMetier.showAllForUpdate();
                System.out.println("Saisissez l'id d'un héros à ajouter au groupe " + groupe.getNom() + " :");
                value2 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(groupe.getId(), value2);
                break;
            case 3:
                int value3;
                System.out.println("Saisissez l'id d'un héros à supprimer du groupe " + groupe.getNom() + " :");
                value3 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(0, value3);
                break;
            case 4:
                int value4;
                vilainMetier.showAllForUpdate();
                System.out.println("Saisissez l'id d'un vilain à ajouter au groupe " + groupe.getNom() + " :");
                value4 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(groupe.getId(), value4);
                break;
            case 5:
                int value5;
                System.out.println("Saisissez l'id d'un vilain à supprimer du groupe " + groupe.getNom() + " :");
                value5 = Outils.scanInteger(scan);
                groupeDAO.updatePersonnage(0, value5);
                break;
            default:
                break;
        }
    }

    /**
     * Permet de créer un groupe en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return [GroupeMetier] L'instance de l'objet.
     */
    public GroupeMetier creer(Scanner scan) {
        HerosMetier herosMetier = new HerosMetier();
        VilainMetier vilainMetier = new VilainMetier();

        System.out.println("Saisissez un nom pour le groupe :");
        String nom = scan.nextLine();

        herosMetier.showAllFree();
        System.out.println("Choisissez des héros à ajouter au groupe en écrivant leurs id. séparés par une virgule (ex. : 1,4,9) :");
        String listeHeros = scan.nextLine();

        vilainMetier.showAllFree();
        System.out.println("Choisissez des vilains à ajouter au groupe en écrivant leurs id. séparés par une virgule (ex. : 1,4,9) :");
        String listeVilains = scan.nextLine();

        String listeSuper = "";
        if ((listeHeros == null || listeHeros.isEmpty()) && (listeVilains != null && !listeVilains.isEmpty())) {
            listeSuper = listeVilains;
        } else if ((listeVilains == null || listeVilains.isEmpty()) && (listeHeros != null && !listeHeros.isEmpty())) {
            listeSuper = listeHeros;
        } else {
            listeSuper = listeHeros + "," + listeVilains;
        }

        GroupeDAO groupeDAO = new GroupeDAO();
        groupeDAO.creerGroupe(nom, listeSuper);
        return this;
    }
}

