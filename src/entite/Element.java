package entite;

public class Element {

    private Integer id =1;
    private String nom;

    public Element(String nom) {
        this.nom = nom;
    }

    public Integer getId() {
        return id;
    }

    public Element setId(Integer id) {
        this.id = id;
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