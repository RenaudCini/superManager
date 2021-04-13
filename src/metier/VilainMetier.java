package metier;

import donnees.HerosDAO;
import donnees.VilainDAO;
import entite.Heros;
import entite.Vilain;

import java.util.ArrayList;
import java.util.Scanner;

public class VilainMetier {
    protected Vilain vilain;

    public VilainMetier() {
    }

    public VilainMetier setVilain(Vilain vilain) {
        this.vilain = vilain;
        return this;
    }

    public Vilain getVilainById(int id) {
        VilainDAO vilainDAO = new VilainDAO();
        Vilain vilain = new Vilain();
        vilain = vilainDAO.findById(id);
        return vilain;
    }

    public void showVilainForUpdate(Vilain vilain) {
        System.out.println("Fiche du vilain : " + vilain.getNom());
        System.out.println("1 - Nom : " + vilain.getNom());
        System.out.println("2 - Commentaire : " + vilain.getCommentaire());
        System.out.println("3 - Points de vie (base) : " + vilain.getPdv());
        System.out.println("4 - Dégâts : " + vilain.getDegats());
        System.out.println("5 - Faiblesse : " + vilain.getFaiblesse());
        System.out.println("6 - Dégâts reçus (faiblesse) : " + vilain.getDegatsFaiblesse());
        System.out.println("7 - Degré de malveillance : " + vilain.getMalveillance());
        System.out.println("8 - Elément : id. => " + vilain.getElement().getId() + ", nom => " + vilain.getElement().getNom());
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllForUpdate() {
        VilainDAO vilainDAO = new VilainDAO();
        ArrayList<Vilain> listeVilains = vilainDAO.findAll();
        System.out.println("Liste des vilains :");
        for (int i = 0; i < listeVilains.size(); i++) {
            System.out.println("- " + listeVilains.get(i).getSuperPersonnageId() + " : " + listeVilains.get(i).getNom());
        }
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllFree() {
        VilainDAO vilainDAO = new VilainDAO();
        ArrayList<Vilain> listeVilains = vilainDAO.findAllFree();
        System.out.println("Liste des vilains disponibles :");
        for (int i = 0; i < listeVilains.size(); i++) {
            System.out.println("- " + listeVilains.get(i).getSuperPersonnageId() + " : " + listeVilains.get(i).getNom());
        }
    }

    public void updateVilain(Scanner scan, Vilain vilain, int idUpdate) {
        VilainDAO vilainDAO = new VilainDAO();
        String value;
        switch (idUpdate) {
            case 1:
                System.out.println("Saisissez un nouveau nom pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("nom", value, vilain.getSuperPersonnageId());
                break;
            case 2:
                System.out.println("Saisissez un nouveau commentaire pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("commentaire", value, vilain.getSuperPersonnageId());
                break;
            case 3:
                System.out.println("Saisissez de nouveaux points de vie pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("vie_base", value, vilain.getSuperPersonnageId());
                break;
            case 4:
                System.out.println("Saisissez de nouveaux dégâts pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("degats_base", value, vilain.getSuperPersonnageId());
                break;
            case 5:
                System.out.println("Saisissez une nouvelle faiblesse pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("faiblesse", value, vilain.getSuperPersonnageId());
                break;
            case 7:
                System.out.println("Saisissez de nouveaux dégâts de faiblesse pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("degats_faiblesse", value, vilain.getSuperPersonnageId());
                break;
            case 8:
                System.out.println("Saisissez un nouvel id. d'élément pour " + vilain.getNom() + " :");
                value = scan.nextLine();
                vilainDAO.update("element_id", value, vilain.getSuperPersonnageId());
                break;
            default:
                break;
        }
    }
}

