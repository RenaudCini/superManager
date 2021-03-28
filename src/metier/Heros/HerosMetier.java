package metier.Heros;

import entite.Element;
import entite.Heros;
import metier.Outils;


import java.util.HashMap;
import java.util.Scanner;


public class HerosMetier {
    protected Heros heros;


    public HerosMetier(Heros heros, Scanner scan) throws NoSuchMethodException {

        HashMap<Integer, FormatType> mapHeroes = new HashMap<>();
        mapHeroes.put(1, new FormatType("name  du hero", "String", Heros.class.getMethod("setNom", String.class)));
        mapHeroes.put(2, new FormatType("identifiant secret du heros", "String", Heros.class.getMethod("setIdentiteSecrete", String.class)));
        mapHeroes.put(3, new FormatType("commentaire", "String", Heros.class.getMethod("setCommentaire", String.class)));
        mapHeroes.put(4, new FormatType("point de vie du hero", "Integer", Heros.class.getMethod("setPdv", Integer.class)));
        mapHeroes.put(5, new FormatType("degat  du hero", "Integer", Heros.class.getMethod("setDegats", Integer.class)));
        mapHeroes.put(6, new FormatType("pouvoir  du hero", "String", Heros.class.getMethod("setPouvoir", String.class)));
        mapHeroes.put(7, new FormatType("degatsPouvoir  du hero", "Integer", Heros.class.getMethod("setDegatsPouvoir", Integer.class)));

        System.out.println(mapHeroes);

        Outils.creatEntiteByUser(heros, mapHeroes, scan);

    }


    public HerosMetier setHero(Heros heros) {
        this.heros = heros;
        return this;
    }
}

