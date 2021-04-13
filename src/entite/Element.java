package entite;

public class Element {

    private Integer elementId =1;
    private String nom;

    public Element() {
    }

    public Element(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return elementId;
    }

    public Element setId(Integer elementId) {
        this.elementId = elementId;
        return this;
    }

    public String getNom() {

        return nom;
    }

    public Element setNom(String nom) {
        this.nom = nom;
        return this;
    }
}