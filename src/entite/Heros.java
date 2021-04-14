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
                        "Saisissez le nom du pouvoir de votre héros :=" + pouvoir +
                        ",Saisissez les dégâts du pouvoir de votre héros :=" + degatsPouvoir +
                        ",Saisissez le nom de votre héros :=" + getNom()+
                        ",Saisissez l'identité secrète :=" + getIdentiteSecrete() +
                        ",Saisissez un commentaire pour votre héros (entrée pour laisser ce champ vide) :=" + getCommentaire() +
                        ",Saisissez les points de vie de votre héros :=" + getPdv() +
                        ",Saisissez les dégâts de base de votre héros :=" + getDegats();
    }


}