package entite;


import donnees.VilainDAO;
import metier.ElementMetier;
import metier.Outils;

import java.util.Scanner;

public class Vilain extends SuperPersonnage {

    private String faiblesse;
    private Integer degatsFaiblesse;
    private Integer malveillance;

    public Vilain() {
        super();
    }

    public Vilain(String nom, String identiteSecrete) {

        super(nom,identiteSecrete);
    }

    public String getFaiblesse() {
        return faiblesse;
    }

    public Vilain setFaiblesse(String faiblesse) {
        this.faiblesse = faiblesse;
        return this;
    }

    public Integer getDegatsFaiblesse() {
        return degatsFaiblesse;
    }

    public Vilain setDegatsFaiblesse(Integer degatsFaiblesse) {
        this.degatsFaiblesse = degatsFaiblesse;
        return this;
    }

    public int getMalveillance() {
        return malveillance;
    }

    public Vilain setMalveillance(Integer malveillance) {
        this.malveillance = malveillance;
        return this;
    }

    @Override
    public String toString() {
        return
                "faiblesse =" + faiblesse +
                        ",degats de la faiblesse =" + degatsFaiblesse +
                        ",malveillance =" + malveillance +
                        ",nom =" + getNom()+
                        ",identite Secrete =" + getIdentiteSecrete() +
                        ",commentaire =" + getCommentaire() +
                        ",point de vie =" + getPdv() +
                        ",degats =" + getDegats();
    }

    /**
     * Permet de créer un vilain.
     *
     * @param scan
     * @return Vilain L'instance de l'objet Vilain créée.
     */
    public Vilain creerVilain(Scanner scan) {
        ElementMetier elementMetier = new ElementMetier();
        elementMetier.showElement();
        System.out.println("Element");
        String nameElement = scan.nextLine();
        Element element = new Element(nameElement);
        setElement(element);
        getElement().setId(1);

        Outils.AfficherTextCreationEntite(this, scan);
        VilainDAO vilainDAO = new VilainDAO();
        vilainDAO.creer(this);
        return this;
    }
}