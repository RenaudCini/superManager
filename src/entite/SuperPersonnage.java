package entite;


public class SuperPersonnage {

    private Integer superPersonnageId;
    private String nom;
    private String identiteSecrete;
    private String commentaire;
    private Integer pdv;
    private Integer degats;
    private Element element;


    public SuperPersonnage() {

    }

    /**
     * @param nom
     * @param identiteSecrete
     */
    public SuperPersonnage(String nom, String identiteSecrete) {
        this.nom = nom;
        this.identiteSecrete = identiteSecrete;
    }


    public SuperPersonnage(String nom, String identiteSecrete, Element element) {
        this.nom = nom;
        this.identiteSecrete = identiteSecrete;
        this.element = element;
    }

    public SuperPersonnage(String nom, String identiteSecrete, Integer pdv, Integer degats, Element element) {
        this.nom = nom;
        this.identiteSecrete = identiteSecrete;
        this.pdv = pdv;
        this.degats = degats;
        this.element = element;
    }


    public Integer getSuperPersonnageId() {
        return superPersonnageId;
    }


    public String getNom() {
        return nom;
    }

    public SuperPersonnage setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getIdentiteSecrete() {
        return identiteSecrete;
    }

    public SuperPersonnage setIdentiteSecrete(String identiteSecrete) {
        this.identiteSecrete = identiteSecrete;
        return this;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public SuperPersonnage setCommentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    public Integer getPdv() {
        return pdv;
    }

    public SuperPersonnage setPdv(Integer pdv) {
        this.pdv = pdv;
        return this;
    }

    public Integer getDegats() {
        return degats;
    }

    public SuperPersonnage setDegats(Integer degats) {
        this.degats = degats;
        return this;
    }

    public Element getElement() {
        return element;
    }

    public SuperPersonnage setSuperPersonnageId(Integer superPersonnageId) {
        this.superPersonnageId = superPersonnageId;
        return this;
    }

    public SuperPersonnage setElement(Element element) {
        this.element = element;
        return this;
    }

    // METHODES DE LA CLASSE
    public void attaquer() {
        System.out.println("Get shit on kid");
    }

}