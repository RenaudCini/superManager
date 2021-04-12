package donnees;

import entite.Element;
import entite.Heros;
import entite.Organisation;
import entite.SuperPersonnage;
import metier.Outils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HerosDAO extends SuperPersonnageDAO {

    /**
     * permet d'insere un hero en bdd
     * @param heros
     */
    public void cree(Heros heros) {
      super.cree(heros);

        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO heros (pouvoir, degats_pouvoir, super_personnage_id, organisation_id) VALUES (?, ?, ?, ?)");

            //creation d'un array contenant  les variable a prepare
            Object[] arrayPrepare = new Object[]{heros.getPouvoir(), heros.getDegatsPouvoir(), heros.getSuperPersonnageId(),heros.getOrganisation().getOrganisationId()};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Heros findByName(String name) {
        Heros heros = new Heros();
        ResultSet result;
        try {
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id as super_personnage_id, sp.nom, sp.identite_secrete, sp.commentaire, sp.vie_base, sp.degats_base, h.degats_pouvoir, e.id as element_id, e.nom as element_nom, o.id as organisation_id, o.nom as organisation_nom FROM heros h INNER JOIN super_personnage sp ON h.super_personnage_id = sp.id INNER JOIN organisation o ON h.organisation_id = o.id INNER JOIN element e ON sp.element_id = e.id WHERE sp.nom = '" + name + "'");

            result.next();

            // Caractéristiques de base :
            heros.setSuperPersonnageId(result.getInt("super_personnage_id"));
            heros.setNom(result.getString("nom"));
            heros.setIdentiteSecrete(result.getString("identite_secrete"));
            heros.setCommentaire(result.getString("commentaire"));
            heros.setPdv(result.getInt("vie_base"));
            heros.setDegats(result.getInt("degats_base"));
            heros.setDegatsPouvoir(result.getInt("degats_pouvoir"));

            // Elément :
            Element element = new Element(result.getString("element_nom"));
            element.setId(result.getInt("element_id"));
            heros.setElement(element);

            // Organisation :
            Organisation organisation = new Organisation();
            organisation.setId(result.getInt("organisation_id"));
            organisation.setNom(result.getString("organisation_nom"));
            heros.setOrganisation(organisation);

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heros;
    }

}
