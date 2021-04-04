package metier.Heros;

import donnees.HerosDAO;
import entite.Element;
import entite.Heros;
import metier.Outils;


import java.util.Scanner;


public class HerosMetier {
    protected Heros heros;


    public HerosMetier(Heros heros) {
        this.heros = heros;
    }

    public HerosMetier setHero(Heros heros) {
        this.heros = heros;
        return this;
    }
}

