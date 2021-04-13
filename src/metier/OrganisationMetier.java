package metier;

import donnees.OrganisationDAO;
import donnees.VilainDAO;
import entite.Organisation;
import entite.Vilain;

import java.util.ArrayList;
import java.util.Scanner;

public class OrganisationMetier {
    protected Organisation organisation;

    public OrganisationMetier() {}

    public Organisation getOrganisationById(int id) {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        Organisation organisation = new Organisation();
        organisation = organisationDAO.findById(id);
        return organisation;
    }

    public void showOrganisationForUpdate(Organisation organisation) {
        System.out.println("1 - Nom : " + organisation.getNom());
        System.out.println("2 - Siège social : " + organisation.getSiegeSocial());
        System.out.println("3 - Nom du dirigeant : " + organisation.getNomDirigeant());
        System.out.println("4 - Commentaire : " + organisation.getCommentaire());
    }

    /**
     * Permet de créer un affichage d'une liste de héros, utilisée dans le cas d'un update de groupe.
     */
    public void showAllForUpdate() {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        ArrayList<Organisation> listeOrganisations = organisationDAO.findAll();
        System.out.println("Liste des organisations :");
        for (int i = 0; i < listeOrganisations.size(); i++) {
            System.out.println("- " + listeOrganisations.get(i).getOrganisationId() + " : " + listeOrganisations.get(i).getNom());
        }
    }

    public void updateOrganisation(Scanner scan, Organisation organisation, int idUpdate) {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        String value;
        switch (idUpdate) {
            case 1:
                System.out.println("Saisissez un nouveau nom pour l'organisation " + organisation.getNom() + " :");
                value = scan.nextLine();
                organisationDAO.update("nom", value, organisation.getOrganisationId());
                break;
            case 2:
                System.out.println("Saisissez un nouveau siège social pour l'organisation " + organisation.getNom() + " :");
                value = scan.nextLine();
                organisationDAO.update("siege_social", value, organisation.getOrganisationId());
                break;
            case 3:
                System.out.println("Saisissez un nouveau nom de dirigeant pour l'organisation " + organisation.getNom() + " :");
                value = scan.nextLine();
                organisationDAO.update("nom_dirigeant", value, organisation.getOrganisationId());
                break;
            case 4:
                System.out.println("Saisissez un nouveau commentaire pour l'organisation " + organisation.getNom() + " :");
                value = scan.nextLine();
                organisationDAO.update("commentaire", value, organisation.getOrganisationId());
                break;
            default:
                break;
        }
    }
}

