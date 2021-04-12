package metier;

import donnees.VilainDAO;
import entite.Vilain;

import java.util.Scanner;

public class VilainMetier {
    protected Vilain vilain;

    public VilainMetier() {
    }

    public VilainMetier setVilain(Vilain vilain) {
        this.vilain = vilain;
        return this;
    }

    public Vilain getVilainByNom(String nom) {
        VilainDAO vilainDAO = new VilainDAO();
        Vilain vilain = new Vilain();
        vilain = vilainDAO.findByNom(nom);
        return vilain;
    }

    public void showVilainForUpdate(Vilain vilain) {
        System.out.println("1 - Nom : " + vilain.getNom());
        System.out.println("2 - Commentaire : " + vilain.getCommentaire());
        System.out.println("3 - Points de vie (base) : " + vilain.getPdv());
        System.out.println("4 - Dégâts : " + vilain.getDegats());
        System.out.println("5 - Faiblesse : " + vilain.getFaiblesse());
        System.out.println("6 - Dégâts reçus (faiblesse) : " + vilain.getDegatsFaiblesse());
        System.out.println("7 - Degré de malveillance : " + vilain.getMalveillance());
        System.out.println("8 - Elément : id. => " + vilain.getElement().getId() + ", nom => " + vilain.getElement().getNom());
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

