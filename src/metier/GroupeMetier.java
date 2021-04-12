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

    public Groupe getGroupeByNom(String nom) {
        GroupeDAO groupeDAO = new GroupeDAO();
        Groupe groupe = new Groupe();
        groupe = groupeDAO.findByNom(nom);
        return groupe;
    }

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

        System.out.println("");
        System.out.println("1 - Modifier le nom du groupe");
        System.out.println("2 - Ajouter un héros à la liste");
        System.out.println("3 - Supprimer un héros de la liste");
        System.out.println("4 - Ajouter un vilain à la liste");
        System.out.println("5 - Supprimer un vilain de la liste");

    }

    public void updateGroupe(Scanner scan, Groupe groupe, int idUpdate) {
        GroupeDAO groupeDAO = new GroupeDAO();
        HerosMetier herosMetier = new HerosMetier();
        VilainMetier vilainMetier = new VilainMetier();
        String value;
        switch (idUpdate) {
            case 1:
                System.out.println("Saisissez un nouveau nom pour le groupe " + groupe.getNom() + " :");
                value = scan.nextLine();
                groupeDAO.updateGroupe(value, groupe.getId());
                break;
            case 2:
                System.out.println("Saisissez le nom d'un héros à ajouter au groupe " + groupe.getNom() + " :");
                value = scan.nextLine();
                // Récupération du Héros :
                Heros heros2 = herosMetier.getHerosByNom(value);
                groupeDAO.updatePersonnage(groupe.getId(), heros2.getSuperPersonnageId());
                break;
            case 3:
                System.out.println("Saisissez le nom d'un héros à supprimer du groupe " + groupe.getNom() + " :");
                value = scan.nextLine();
                // Récupération du Héros :
                Heros heros3 = herosMetier.getHerosByNom(value);
                groupeDAO.updatePersonnage(0, heros3.getSuperPersonnageId());
                break;
            case 4:
                System.out.println("Saisissez le nom d'un vilain à ajouter au groupe " + groupe.getNom() + " :");
                value = scan.nextLine();
                // Récupération du Héros :
                Vilain vilain4 = vilainMetier.getVilainByNom(value);
                groupeDAO.updatePersonnage(groupe.getId(), vilain4.getSuperPersonnageId());
                break;
            case 5:
                System.out.println("Saisissez le nom d'un vilain à supprimer du groupe " + groupe.getNom() + " :");
                value = scan.nextLine();
                // Récupération du Héros :
                Vilain vilain5 = vilainMetier.getVilainByNom(value);
                groupeDAO.updatePersonnage(0, vilain5.getSuperPersonnageId());
                break;
            default:
                break;
        }
    }
}

