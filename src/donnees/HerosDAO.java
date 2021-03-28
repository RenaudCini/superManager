package donnees;

import entite.Heros;
import entite.SuperPersonnage;
import metier.Outils;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HerosDAO extends SuperPersonnageDAO {

    public void cree(Heros heros) {
      super.cree(heros);

        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO heros (pouvoir, degats_pouvoir, super_personnage_id, organisation_id) VALUES (?, ?, ?, ?)");

            Object[] arrayPrepare = new Object[]{heros.getPouvoir(), heros.getDegatsPouvoir(), heros.getSuperPersonnageId(),heros.getOrganisation().getId()};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
