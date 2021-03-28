package entite;

import java.util.Date;

public class Organisation {

    private Integer id;
    private String nom;
    private String nom_dirigeant;
    private String adresse;
    private String commentaire;
    private Date date_dajout;

    public Organisation (String nom,Integer id) {

        this.nom = nom;
        this.id = id;
    }
    public Organisation (Integer id) {

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Organisation setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Organisation setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getNom_dirigeant() {
        return nom_dirigeant;
    }

    public Organisation setNom_dirigeant(String nom_dirigeant) {
        this.nom_dirigeant = nom_dirigeant;
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

    public Date getDate_dajout() {
        return date_dajout;
    }

    public Organisation setDate_dajout(Date date_dajout) {
        this.date_dajout = date_dajout;
        return this;
    }
}