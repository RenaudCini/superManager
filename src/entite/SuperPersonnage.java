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

    /**
     * Permet de récupérer l'ID du super personnage.
     * @return [Integer] L'ID du super personnage.
     */
    public Integer getSuperPersonnageId() {
        return superPersonnageId;
    }

    /**
     * Permet de paramétrer l'ID du super personnage.
     * @param superPersonnageId [Integer] L'ID du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setSuperPersonnageId(Integer superPersonnageId) {
        this.superPersonnageId = superPersonnageId;
        return this;
    }

    /**
     * Permet de récupérer le nom du super personnage.
     * @return [String] Le nom du super personnage.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de paramétrer le nom du super personnage.
     * @param nom [String] Le nom du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setNom(String nom) {
        this.nom = nom;
        return this;
    }

    /**
     * Permet de récupérer l'identité secrète du super personnage.
     * @return [String] L'identité secrète du super personnage.
     */
    public String getIdentiteSecrete() {
        return identiteSecrete;
    }

    /**
     * Permet de paramétrer l'identité secrète du super personnage.
     * @param identiteSecrete [String] L'identité secrète du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setIdentiteSecrete(String identiteSecrete) {
        this.identiteSecrete = identiteSecrete;
        return this;
    }

    /**
     * Permet de récupérer le commentaire du super personnage.
     * @return [String] Le commentaire du super personnage.
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Permet de paramétrer le commentaire du super personnage.
     * @param commentaire [String] Le commentaire du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setCommentaire(String commentaire) {
        this.commentaire = commentaire;
        return this;
    }

    /**
     * Permet de récupérer les points de vie du super personnage.
     * @return [Integer] Les points de vie du super personnage.
     */
    public Integer getPdv() {
        return pdv;
    }

    /**
     * Permet de paramétrer les points de vie du super personnage.
     * @param pdv [Integer] Les points de vie du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setPdv(Integer pdv) {
        this.pdv = pdv;
        return this;
    }

    /**
     * Permet de récupérer les dégâts du super personnage.
     * @return [Integer] Les dégâts du super personnage.
     */
    public Integer getDegats() {
        return degats;
    }

    /**
     * Permet de paramétrer les dégâts du super personnage.
     * @param degats [Integer] Les dégâts du super personnage.
     * @return [this] L'instance de la classe.
     */
    public SuperPersonnage setDegats(Integer degats) {
        this.degats = degats;
        return this;
    }

    /**
     * Permet de récupérer l'élément du super personnage.
     * @return [Element] Un objet de type Element.
     */
    public Element getElement() {
        return element;
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