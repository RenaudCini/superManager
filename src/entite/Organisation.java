package entite;

import java.util.Date;

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

    public String getDateAjout() {
        return dateAjout;
    }

    public Organisation setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
        return this;
    }

    public String getSiegeSocial() {
        return siegeSocial;
    }

    public Organisation setSiegeSocial(String siegeSocial) {
        this.siegeSocial = siegeSocial;
        return this;
    }


    @Override
    public String toString() {
        return
                "nom =" + nom +
                ",nom dirigeant =" + nomDirigeant +
                ",Siege sociale =" + siegeSocial +
                ",commentaire =" + commentaire;

    public Organisation creeOrganisation(Scanner scan) {

        Outils.AfficherTextCreationEntite(this, scan);
        OrganisationDAO organisationDAO = new OrganisationDAO();
        organisationDAO.cree(this);
        return this;
    }
}