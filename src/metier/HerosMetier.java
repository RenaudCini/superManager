package metier;

import donnees.HerosDAO;
import entite.Heros;

public class HerosMetier {
    protected Heros heros;

    public HerosMetier() {

    }
    /*public HerosMetier(Heros heros) {
        this.heros = heros;
    }*/

    public HerosMetier setHero(Heros heros) {
        this.heros = heros;
        return this;
    }

    public void showHerosForUpdate(String name) {
        HerosDAO herosDAO = new HerosDAO();
        Heros heros = new Heros();
        heros = herosDAO.findByName(name);
        System.out.println("1 - Nom : " + heros.getNom());
        System.out.println("2 - Identité secrète : " + heros.getIdentiteSecrete());
        System.out.println("3 - Commentaire : " + heros.getCommentaire());
        System.out.println("4 - Points de vie (base) : " + heros.getPdv());
        System.out.println("5 - Dégâts : " + heros.getDegats());
        System.out.println("6 - Pouvoir : " + heros.getPouvoir());
        System.out.println("7 - Dégâts du pouvoir : " + heros.getDegatsPouvoir());
        System.out.println("8 - Elément : id. => " + heros.getElement().getId() + ", nom => " + heros.getElement().getNom());
        System.out.println("9 - Organisation : id. => " + heros.getOrganisation().getId() + ", nom => " + heros.getOrganisation().getNom());
    }
}

