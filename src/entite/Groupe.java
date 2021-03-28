package entite;

import java.util.ArrayList;

public class Groupe {

    private Integer id;
    private ArrayList<Heros> listeHeros;
    private ArrayList<Vilain> listeVilains;

    public Groupe(int id, ArrayList<Heros> listeHeros, ArrayList<Vilain> listeVilains) {
        this.id = id;
        this.listeHeros = listeHeros;
        this.listeVilains = listeVilains;
    }

    public Integer getId() {
        return id;
    }

    public Groupe setId(Integer id) {
        this.id = id;
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