package entite;

import java.util.ArrayList;

public class Groupe {

    private Integer groupeId;
    private String nom;
    private ArrayList<Heros> listeHeros;
    private ArrayList<Vilain> listeVilains;

    public Groupe() {}

    public Groupe(int id, ArrayList<Heros> listeHeros, ArrayList<Vilain> listeVilains) {
        this.groupeId = id;
        this.listeHeros = listeHeros;
        this.listeVilains = listeVilains;
    }

    /**
     * Permet de récupérer l'ID du groupe.
     * @return [int] L'id du groupe.
     */
    public Integer getId() {
        return groupeId;
    }
    /**
     * Permet de paramétrer l'ID du groupe.
     * @param groupeId [Integer] L'ID du groupe à paramétrer.
     * @return [this] L'instance de la classe.
     */
    public Groupe setId(Integer groupeId) {
        this.groupeId = groupeId;
        return this;
    }

    /**
     * Permet de récupérer le nom du groupe.
     * @return [String] Le nom du groupe.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de paramétrer le nom du groupe.
     * @param nom [String] Le nom à paramétrer.
     * @return [this] L'instance de la classe.
     */
    public Groupe setNom(String nom) {
        this.nom = nom;
        return this;
    }

    /**
     * Permet de récupérer la liste des héros du groupe.
     * @return [ArrayList] Une ArrayList contenant des objets Heros, représentant ceux du groupe.
     */
    public ArrayList<Heros> getListeHeros() {
        return listeHeros;
    }

    /**
     * Permet de paramétrer la liste des héros du groupe.
     * @param listeHeros [ArrayList] Une ArrayList d'objets Heros.
     * @return [this] L'instance de la classe.
     */
    public Groupe setListeHeros(ArrayList<Heros> listeHeros) {
        this.listeHeros = listeHeros;
        return this;
    }

    /**
     * Permet de récupérer la liste des vilains du groupe.
     * @return [ArrayList] Une ArrayList contenant des objets Vilain, représentant ceux du groupe.
     */
    public ArrayList<Vilain> getListeVilains() {
        return listeVilains;
    }

    /**
     * Permet de paramétrer la liste des vilains du groupe.
     * @param listeVilains [ArrayList] Une ArrayList d'objets Vilain.
     * @return [this] L'instance de la classe.
     */
    public Groupe setListeVilains(ArrayList<Vilain> listeVilains) {
        this.listeVilains = listeVilains;
        return this;
    }
}