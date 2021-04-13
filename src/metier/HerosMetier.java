package metier;

import donnees.HerosDAO;
import entite.Heros;

import java.util.ArrayList;
import java.util.Scanner;

public class HerosMetier {
    protected Heros heros;

    public HerosMetier() {

    }
    /*public HerosMetier(Heros heros) {
        this.heros = heros;
    }*/

    public HerosMetier setHero(Heros heros) {
        this.heros = heros;
        return this;
    }

    public Heros getHerosById(int id) {
        HerosDAO herosDAO = new HerosDAO();
        Heros heros = new Heros();
        heros = herosDAO.findById(id);
        return heros;
    }

    /**
     * Permet de créer un affichage récapitulant les informations d'un héros.
     * @param heros Heros Une instance du héros dont on veut le récapitulatif.
     */
    public void showHerosForUpdate(Heros heros) {
        System.out.println("1 - Nom : " + heros.getNom());
        System.out.println("2 - Identité secrète : " + heros.getIdentiteSecrete());
        System.out.println("3 - Commentaire : " + heros.getCommentaire());
        System.out.println("4 - Points de vie (base) : " + heros.getPdv());
        System.out.println("5 - Dégâts : " + heros.getDegats());
        System.out.println("6 - Pouvoir : " + heros.getPouvoir());
        System.out.println("7 - Dégâts du pouvoir : " + heros.getDegatsPouvoir());
        System.out.println("8 - Elément : id. => " + heros.getElement().getId() + ", nom => " + heros.getElement().getNom());
        System.out.println("9 - Organisation : id. => " + heros.getOrganisation().getOrganisationId() + ", nom => " + heros.getOrganisation().getNom());
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllForUpdate() {
        HerosDAO herosDAO = new HerosDAO();
        ArrayList<Heros> listeHeros = herosDAO.findAll();
        System.out.println("Liste des héros :");
        for (int i = 0; i < listeHeros.size(); i++) {
            System.out.println("- " + listeHeros.get(i).getSuperPersonnageId() + " : " + listeHeros.get(i).getNom());
        }
    }

    public void showAllFree() {
        HerosDAO herosDAO = new HerosDAO();
        ArrayList<Heros> listeHeros = herosDAO.findAllFree();
        System.out.println("Liste des héros disponibles :");
        for (int i = 0; i < listeHeros.size(); i++) {
            System.out.println("- " + listeHeros.get(i).getSuperPersonnageId() + " : " + listeHeros.get(i).getNom());
        }
    }

    public void updateHeros(Scanner scan, Heros heros, int idUpdate) {
        HerosDAO herosDAO = new HerosDAO();
        String value;
        switch (idUpdate) {
            case 1:
                System.out.println("Saisissez un nouveau nom pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("nom", value, heros.getSuperPersonnageId());
                break;
            case 2:
                System.out.println("Saisissez une nouvelle identité secrète pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("identite_secrete", value, heros.getSuperPersonnageId());
                break;
            case 3:
                System.out.println("Saisissez un nouveau commentaire pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("commentaire", value, heros.getSuperPersonnageId());
                break;
            case 4:
                System.out.println("Saisissez de nouveaux points de vie pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("vie_base", value, heros.getSuperPersonnageId());
                break;
            case 5:
                System.out.println("Saisissez de nouveaux dégâts pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("degats_base", value, heros.getSuperPersonnageId());
                break;
            case 6:
                System.out.println("Saisissez un nouveau pouvoir pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("pouvoir", value, heros.getSuperPersonnageId());
                break;
            case 7:
                System.out.println("Saisissez de nouveaux dégâts de pouvoir pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("degats_pouvoir", value, heros.getSuperPersonnageId());
                break;
            case 8:
                System.out.println("Saisissez un nouvel id. d'élément pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("element_id", value, heros.getSuperPersonnageId());
                break;
            case 9:
                System.out.println("Saisissez un nouvel id. d'organisation pour " + heros.getNom() + " :");
                value = scan.nextLine();
                herosDAO.update("organisation_id", value, heros.getSuperPersonnageId());
                break;
            default:
                break;
        }
    }
}

