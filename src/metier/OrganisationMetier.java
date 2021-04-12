package metier;

import donnees.OrganisationDAO;
import entite.Organisation;

import java.util.Scanner;

public class OrganisationMetier {
    protected Organisation organisation;

    public OrganisationMetier() {}

    public Organisation getOrganisationByNom(String nom) {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        Organisation organisation = new Organisation();
        organisation = organisationDAO.findByNom(nom);
        return organisation;
    }

    public void showOrganisationForUpdate(Organisation organisation) {
        System.out.println("1 - Nom : " + organisation.getNom());
        System.out.println("2 - Siège social : " + organisation.getSiegeSocial());
        System.out.println("3 - Nom du dirigeant : " + organisation.getNomDirigeant());
        System.out.println("4 - Commentaire : " + organisation.getCommentaire());
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

