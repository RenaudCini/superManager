package entite;


import donnees.HerosDAO;
import metier.Outils;

import java.util.Scanner;

public class Heros extends SuperPersonnage {

    private String pouvoir;
    private Integer degatsPouvoir;
    private Organisation organisation;

    public Heros() {
        super();
    }

    /**
     * @param nom
     * @param identiteSecrete
     * @param organisation
     */
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

    public String getPouvoir() {

        return pouvoir;
    }

    public Heros setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
        return this;
    }

    public Integer getDegatsPouvoir() {

        return degatsPouvoir;
    }

    public Heros setDegatsPouvoir(Integer degatsPouvoir) {
        this.degatsPouvoir = degatsPouvoir;
        return this;
    }

    public Organisation getOrganisation() {

        return organisation;
    }

    public Heros setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

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
     * cree un hero
     * @param scan
     * @return
     */
    public Heros creeHero(Scanner scan) {

        // TODO: 04/04/2021  a supprimer
        System.out.println("Id de l'organisation");
        Integer idOrganisation = Outils.scanInteger(scan);
        Organisation organisation = new Organisation(idOrganisation);
        System.out.println("Element");

        String nameElement = scan.nextLine();
        Element element = new Element(nameElement);
        setElement(element);
        getElement().setId(1);
        setOrganisation(organisation);
        getOrganisation().setId(1);


        Outils.AfficherTextCreationEntite(this, scan);
        HerosDAO herosDAO = new HerosDAO();
        herosDAO.cree(this);
        return this;
    }
}