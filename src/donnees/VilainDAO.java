package donnees;

import entite.Vilain;
import entite.SuperPersonnage;
import metier.Outils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VilainDAO extends SuperPersonnageDAO {
    /**
     * Permet d'insérer un vilain en BDD.
     *
     * @param vilain
     */
    public void creer(Vilain vilain) {
        super.cree(vilain);

        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO vilain (faiblesse, degats_faiblesse, malveillance, super_personnage_id) VALUES (?, ?, ?, ?)");

            // Création de l'array contenant les valeurs à insérer :
            Object[] arrayPrepare = new Object[]{vilain.getFaiblesse(), vilain.getDegatsFaiblesse(),vilain.getMalveillance(), vilain.getSuperPersonnageId()};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
