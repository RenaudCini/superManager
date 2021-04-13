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

    /**
     * Permet de récupérer la faiblesse du vilain.
     * @return [String] La faiblesse du vilain.
     */
    public String getFaiblesse() {
        return faiblesse;
    }

    /**
     * Permet de paramétrer la faiblesse du vilain.
     * @param faiblesse [String] La faiblesse du vilain.
     * @return [this] L'instance de la classe.
     */
    public Vilain setFaiblesse(String faiblesse) {
        this.faiblesse = faiblesse;
        return this;
    }

    /**
     * Permet de récupérer les dégâts reçus à cause de la faiblesse du vilain.
     * @return [Integer] Les dégâts reçus à cause de la faiblesse du vilain.
     */
    public Integer getDegatsFaiblesse() {
        return degatsFaiblesse;
    }

    /**
     * Permet de paramétrer les dégâts reçus à cause de la faiblesse du vilain.
     * @param degatsFaiblesse [Integer] Les dégâts reçus à cause de la faiblesse du vilain.
     * @return [this] L'instance de la classe.
     */
    public Vilain setDegatsFaiblesse(Integer degatsFaiblesse) {
        this.degatsFaiblesse = degatsFaiblesse;
        return this;
    }

    /**
     * Permet de récupérer la malveillance du vilain.
     * @return [int] La malveillance du vilain.
     */
    public int getMalveillance() {
        return malveillance;
    }

    /**
     * Permet de paramétrer la malveillance du vilain.
     * @param malveillance [Integer] La malveillance du vilain.
     * @return [this] L'instance de la classe.
     */
    public Vilain setMalveillance(Integer malveillance) {
        this.malveillance = malveillance;
        return this;
    }

    /**
     * Permet de créer un affichage contenant les informations du vilain.
     * @return [String] L'affichage.
     */
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
     * @param scan [Scanner] Un objet de type Scanner.
     * @return Vilain L'instance de l'objet Vilain créée.
     */
    public Vilain creerVilain(Scanner scan) {
        ElementMetier elementMetier = new ElementMetier();
        elementMetier.showAll();
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