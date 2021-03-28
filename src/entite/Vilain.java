package entite;


public class Vilain extends SuperPersonnage {

    private String faiblesse;
    private Integer degatsFaiblesse;
    private Integer malveillance;

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
}