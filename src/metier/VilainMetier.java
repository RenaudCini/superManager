package metier;

import donnees.HerosDAO;
import donnees.VilainDAO;
import entite.Element;
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

    /**
     * Permet de récupérer un vilain par son ID de super personnage.
     * @param id [int] L'ID de super personnage du vilain.
     * @return
     */
    public Vilain getVilainById(int id) {
        VilainDAO vilainDAO = new VilainDAO();
        Vilain vilain = new Vilain();
        vilain = vilainDAO.findById(id);
        return vilain;
    }

    /**
     * Permet de créer un affichage récapitulant les informations d'un vilain.
     * @param vilain [Vilain] Une instance du vilain dont on veut le récapitulatif.
     */
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
     * Permet de créer un affichage d'une liste de vilains.
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
     * Permet d'afficher la liste des vilains n'appartenant à aucun groupe.
     */
    public void showAllFree() {
        VilainDAO vilainDAO = new VilainDAO();
        ArrayList<Vilain> listeVilains = vilainDAO.findAllFree();
        System.out.println("Liste des vilains disponibles :");
        for (int i = 0; i < listeVilains.size(); i++) {
            System.out.println("- " + listeVilains.get(i).getSuperPersonnageId() + " : " + listeVilains.get(i).getNom());
        }
    }

    /**
     * Permet de créer un vilain.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return Vilain L'instance de l'objet Vilain créée.
     */
    public void creerVilain(Scanner scan) {
        Vilain vilain = new Vilain();
        ElementMetier elementMetier = new ElementMetier();

        elementMetier.showAll();
        System.out.println("Saisissez l'id. de l'élément de la faiblesse de votre vilain :");
        int idElement = Outils.scanInteger(scan);
        Element element = new Element();
        element.setId(idElement);
        vilain.setElement(element);

        Outils.AfficherTextCreationEntite(vilain, scan);
        VilainDAO vilainDAO = new VilainDAO();
        vilainDAO.creer(vilain);
    }

    /**
     * Permet d'update une caractéristique du vilain selon l'input de l'utilisateur, et de récupérer la nouvelle valeur
     * entrée par l'utilisateur pour update en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @param vilain [Vilain] Un objet de type Vilain.
     * @param idUpdate [int] Le choix de l'utilisateur.
     */
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

