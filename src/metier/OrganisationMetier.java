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

    /**
     * Permet de récupérer une organisation par son ID.
     * @param id [int] L'ID de l'organisation.
     * @return
     */
    public Organisation getOrganisationById(int id) {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        Organisation organisation = new Organisation();
        organisation = organisationDAO.findById(id);
        return organisation;
    }

    /**
     * Permet de créer un affichage récapitulant les informations d'une organisation.
     * @param organisation [Organisation] Une instance de l'organisation dont on veut le récapitulatif.
     */
    public void showOrganisationForUpdate(Organisation organisation) {
        System.out.println("1 - Nom : " + organisation.getNom());
        System.out.println("2 - Siège social : " + organisation.getSiegeSocial());
        System.out.println("3 - Nom du dirigeant : " + organisation.getNomDirigeant());
        System.out.println("4 - Commentaire : " + organisation.getCommentaire());
    }

    /**
     * Permet de créer un affichage d'une liste d'organisations.
     */
    public void showAllForUpdate() {
        OrganisationDAO organisationDAO = new OrganisationDAO();
        ArrayList<Organisation> listeOrganisations = organisationDAO.findAll();
        System.out.println("Liste des organisations :");
        for (int i = 0; i < listeOrganisations.size(); i++) {
            System.out.println("- " + listeOrganisations.get(i).getOrganisationId() + " : " + listeOrganisations.get(i).getNom());
        }
    }

    /**
     * Permet de créer une organisation en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return [Organisation] Un objet de type Organisation.
     */
    public void creerOrganisation(Scanner scan) {
        Organisation organisation = new Organisation();
        Outils.AfficherTextCreationEntite(organisation, scan);
        OrganisationDAO organisationDAO = new OrganisationDAO();
        organisationDAO.creer(organisation);
    }

    /**
     * Permet d'update une caractéristique d'une organisation selon l'input de l'utilisateur, et de récupérer la
     * nouvelle valeur entrée par l'utilisateur pour update en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @param organisation [Organisation] Un objet de type Organisation.
     * @param idUpdate [int] Le choix de l'utilisateur.
     */
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

