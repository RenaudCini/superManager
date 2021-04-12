package entite;

import java.util.Date;

public class Organisation {

    private Integer organisationId;
    private String nom;
    private String nomDirigeant;
    private  String SiegeSociale;
    private String adresse;
    private String commentaire;
    private Date dateAjout;

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

    public Integer getId() {
        return organisationId;
    }

    public Organisation setId(Integer organisationId) {
        this.organisationId = organisationId;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Organisation setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNomDirigeant() {
        return nomDirigeant;
    }

    public Organisation setNomDirigeant(String nomDirigeant) {
        this.nomDirigeant = nomDirigeant;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public Organisation setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Organisation setCommentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public Organisation setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
        return this;
    }

    @Override
    public String toString() {
        return
                "organisationId =" + organisationId +
                ",nom =" + nom +
                ",nomDirigeant =" + nomDirigeant +
                ",adresse =" + adresse +
                ",commentaire =" + commentaire +
                ",dateAjout =" + dateAjout;

    }

    public String getSiegeSociale() {
        return SiegeSociale;
    }

    public Organisation setSiegeSociale(String siegeSociale) {
        SiegeSociale = siegeSociale;
        return this;
    }
}