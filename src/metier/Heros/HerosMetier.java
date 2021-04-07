package metier.Heros;

import entite.Heros;

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

