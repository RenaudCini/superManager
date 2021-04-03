package metier.Heros;

import entite.Element;
import entite.Heros;
import metier.Outils;


import javax.swing.text.html.parser.Entity;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;


public class HerosMetier {
    protected Heros heros;


    public HerosMetier(Heros heros, Scanner scan) throws IllegalAccessException {

        Outils.creatEntiteByUser(heros,scan);

    }


    public HerosMetier setHero(Heros heros) {
        this.heros = heros;
        return this;
    }
}

