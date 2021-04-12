package donnees;

import entite.Organisation;
import metier.Outils;
import java.sql.*;

public class OrganisationDAO {

    protected Connection bdd;

    public OrganisationDAO() {

        bdd = DAO.getInstance();
    }

    /**
     * permet d'insérer une organisation en bdd
     * @param organisation
     */
    public void cree(Organisation organisation) {

        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO organisation (nom, siege_social, nom_dirigeant, commentaire, date_ajout) VALUES (?, ?, ?, ?, ?)");

            //creation d'un array contenant  les variable a prepare
            Object[] arrayPrepare = new Object[]{organisation.getNom(), organisation.getAdresse(), organisation.getNomDirigeant(), organisation.getCommentaire(), organisation.getDateAjout()};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
