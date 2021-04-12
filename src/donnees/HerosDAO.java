package donnees;

import entite.Heros;
import entite.SuperPersonnage;
import metier.Outils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
