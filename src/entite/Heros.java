package entite;

import donnees.HerosDAO;
import metier.ElementMetier;
import metier.Outils;
import java.util.Scanner;

public class Heros extends SuperPersonnage {

    private String pouvoir;
    private Integer degatsPouvoir;
    private Organisation organisation;

    public Heros() {
        super();
    }

    public Heros(String nom, String identiteSecrete, Organisation organisation) {

        super(nom, identiteSecrete);
        this.organisation = organisation;
    }

    public Heros(String nom, String identiteSecrete, Integer pdv, Integer degats, Element element, String pouvoir, Integer degatsPouvoir, Organisation organisation) {
        super(nom, identiteSecrete, pdv, degats, element);

        this.pouvoir = pouvoir;
        this.degatsPouvoir = degatsPouvoir;
        this.organisation = organisation;
    }

    /**
     * Permet de récupérer le pouvoir du héros.
     * @return [String] Le pouvoir du héros.
     */
    public String getPouvoir() {
        return pouvoir;
    }

    /**
     * Permet de paramétrer le pouvoir du héros.
     * @param pouvoir [String] Le pouvoir du héros.
     * @return [this] L'instance de la classe.
     */
    public Heros setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
        return this;
    }

    /**
     * Permet de récupérer les dégâts du pouvoir du héros.
     * @return [Integer] Les dégâts du pouvoir du héros.
     */
    public Integer getDegatsPouvoir() {

        return degatsPouvoir;
    }

    /**
     * Permet de paramétrer les dégâts du pouvoir du héros.
     * @param degatsPouvoir [Integer] Les dégâts du pouvoir du héros.
     * @return [this] L'instance de la classe.
     */
    public Heros setDegatsPouvoir(Integer degatsPouvoir) {
        this.degatsPouvoir = degatsPouvoir;
        return this;
    }

    /**
     * Permet de récupérer l'organisation du héros.
     * @return [Organisation] Un objet de type Organisation.
     */
    public Organisation getOrganisation() {
        return organisation;
    }

    /**
     * Permet de paramétrer l'organisation du héros.
     * @param organisation [Organisation] Un objet de type Organisation.
     * @return [this] L'instance de la classe.
     */
    public Heros setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    /**
     * Permet de créer un affichage contenant les informations du héros.
     * @return [String] L'affichage.
     */
    @Override
    public String toString() {
        return
                        "pouvoir =" + pouvoir +
                        ",degats du Pouvoir =" + degatsPouvoir +
                        ",nom =" + getNom()+
                        ",identite Secrete =" + getIdentiteSecrete() +
                        ",commentaire =" + getCommentaire() +
                        ",point de vie =" + getPdv() +
                        ",degats =" + getDegats();
    }

    /**
     * Permet de créer un héros en base de données.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return [this] L'instance de la classe.
     */
    public Heros creeHero(Scanner scan) {

        ElementMetier elementMetier = new ElementMetier();

        // TODO: 04/04/2021  a supprimer
        System.out.println("Id de l'organisation");
        Integer idOrganisation = Outils.scanInteger(scan);
        Organisation organisation = new Organisation(idOrganisation);
        elementMetier.showAll();
        System.out.println("Element");

        String nameElement = scan.nextLine();
        Element element = new Element(nameElement);
        setElement(element);
        getElement().setId(1);
        setOrganisation(organisation);
        getOrganisation().setOrganisationId(1);

        Outils.AfficherTextCreationEntite(this, scan);
        HerosDAO herosDAO = new HerosDAO();
        herosDAO.cree(this);
        return this;
    }
}