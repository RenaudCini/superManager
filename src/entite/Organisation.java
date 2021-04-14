package entite;

import donnees.OrganisationDAO;
import metier.Outils;

import java.util.Date;
import java.util.Scanner;

public class Organisation {

    private Integer organisationId;
    private String nom;
    private String nomDirigeant;
    private String siegeSocial;
    private String commentaire;
    private String dateAjout;

    public Organisation (String nom,Integer organisationId) {
        this.nom = nom;
        this.organisationId = organisationId;
    }

    public Organisation (Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Organisation () {
        this.organisationId = organisationId;
    }

    /**
     * Permet de récupérer l'id de l'organisation.
     * @return [Integer] L'id de l'organisation.
     */
    public Integer getOrganisationId() {
        return organisationId;
    }

    /**
     * Permet de paramétrer l'id de l'organisation.
     * @param organisationId [Integer] L'id de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
        return this;
    }

    /**
     * Permet de récupérer le nom de l'organisation.
     * @return [String] Le nom de l'organisation.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de paramétrer le nom de l'organisation.
     * @param nom [String] Le nom de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setNom(String nom) {
        this.nom = nom;
        return this;
    }

    /**
     * Permet de récupérer le nom du dirigeant de l'organisation.
     * @return [String] Le nom du dirigeant de l'organisation.
     */
    public String getNomDirigeant() {
        return nomDirigeant;
    }

    /**
     * Permet de paramétrer le nom du dirigeant de l'organisation.
     * @param nomDirigeant [String] Le nom du dirigeant de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setNomDirigeant(String nomDirigeant) {
        this.nomDirigeant = nomDirigeant;
        return this;
    }

    /**
     * Permet de récupérer le commentaire de l'organisation.
     * @return [String] Le commentaire  de l'organisation.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Permet de paramétrer le commentaire de l'organisation.
     * @param commentaire [String] Le commentaire de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setCommentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    /**
     * Permet de récupérer la date d'ajout de l'organisation.
     * @return [String] La date d'ajout de l'organisation.
     */
    public String getDateAjout() {
        return dateAjout;
    }

    /**
     * Permet de paramétrer la date d'ajout de l'organisation.
     * @param dateAjout [String] La date d'ajout de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
        return this;
    }

    /**
     * Permet de récupérer le siège social de l'organisation.
     * @return [String] Le siège social de l'organisation.
     */
    public String getSiegeSocial() {
        return siegeSocial;
    }

    /**
     * Permet de paramétrer le siège social de l'organisation.
     * @param siegeSocial [String] Le siège social de l'organisation.
     * @return [this] L'instance de la classe.
     */
    public Organisation setSiegeSocial(String siegeSocial) {
        this.siegeSocial = siegeSocial;
        return this;
    }

    /**
     * Permet de créer un affichage contenant les informations de l'organisation.
     * @return [String] L'affichage.
     */
    @Override
    public String toString() {
        return
                "Saisissez le nom de l'organisation :=" + nom +
                ",Saisissez le nom du dirigeant :=" + nomDirigeant +
                ",Saisissez l'adresse du siège social :=" + siegeSocial +
                ",Saisissez un commentaire (entrée pour laisser le champ vide) :=" + commentaire;

    }



}