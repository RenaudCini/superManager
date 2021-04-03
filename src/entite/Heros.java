package entite;


public class Heros extends SuperPersonnage {

    private String pouvoir;
    private Integer degatsPouvoir;
    private Organisation organisation;

    public Heros() {
        super();
    }

    /**
     *
     * @param nom
     * @param identiteSecrete
     * @param organisation
     */
    public Heros(String nom, String identiteSecrete, Organisation organisation) {

        super(nom, identiteSecrete);
        this.organisation = organisation;
    }

    public Heros(String nom, String identiteSecrete, Integer pdv, Integer degats, Element element, String pouvoir, Integer degatsPouvoir, Organisation organisation) {
        super(nom, identiteSecrete, pdv, degats, element);

        this.pouvoir = pouvoir;
        this.degatsPouvoir = degatsPouvoir;
        this.organisation = organisation;

    }

    public String getPouvoir() {
        return pouvoir;
    }

    public Heros setPouvoir(String pouvoir) {
        this.pouvoir = pouvoir;
        return this;
    }

    public Integer getDegatsPouvoir() {
        return degatsPouvoir;
    }

    public Heros setDegatsPouvoir(Integer degatsPouvoir) {
        this.degatsPouvoir = degatsPouvoir;
        return this;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public Heros setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }
}