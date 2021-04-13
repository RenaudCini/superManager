package entite;

public class Element {

    private Integer elementId =1;
    private String nom;

    public Element() {
    }

    public Element(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de récupérer l'ID de l'élément.
     * @return [Integer] L'ID de l'élément.
     */
    public Integer getId() {
        return elementId;
    }

    /**
     * Permet de paramétrer l'ID de l'élément.
     * @return [this] L'instance de l'objet.
     */
    public Element setId(Integer elementId) {
        this.elementId = elementId;
        return this;
    }

    /**
     * Permet de récupérer le nom de l'élément.
     * @return [String] Le nom de l'élément.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de paramétrer le nom de l'élément.
     * @return [this] L'instance de l'objet.
     */
    public Element setNom(String nom) {
        this.nom = nom;
        return this;
    }
}