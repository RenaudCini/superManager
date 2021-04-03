package entite;

import java.util.ArrayList;

public class Groupe {

    private Integer groupeId;
    private ArrayList<Heros> listeHeros;
    private ArrayList<Vilain> listeVilains;

    public Groupe(int id, ArrayList<Heros> listeHeros, ArrayList<Vilain> listeVilains) {
        this.groupeId = id;
        this.listeHeros = listeHeros;
        this.listeVilains = listeVilains;
    }

    public Integer getId() {
        return groupeId;
    }

    public Groupe setId(Integer groupeId) {
        this.groupeId = groupeId;
        return this;
    }

    public ArrayList<Heros> getListeHeros() {
        return listeHeros;
    }

    public Groupe setListeHeros(ArrayList<Heros> listeHeros) {
        this.listeHeros = listeHeros;
        return this;
    }

    public ArrayList<Vilain> getListeVilains() {
        return listeVilains;
    }

    public Groupe setListeVilains(ArrayList<Vilain> listeVilains) {
        this.listeVilains = listeVilains;
        return this;
    }
}