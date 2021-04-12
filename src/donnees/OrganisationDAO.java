package donnees;

import entite.Organisation;
import metier.Outils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrganisationDAO {


    protected Connection bdd;

    public OrganisationDAO() {

        bdd = DAO.getInstance();
    }

    public ArrayList<Organisation> findAllByOrganisation() {
        ArrayList<Organisation> organisations = new ArrayList<Organisation>();
        Organisation organisation;
        ResultSet result;
        try {
            PreparedStatement prepare = this.bdd.prepareStatement("SELECT * FROM organisation");

            result = prepare.executeQuery();
      ;
            while (result.next()) {
                organisation = new Organisation();
                Outils.SetEntite(result, organisation);
            /*    organisation.setId(result.getLong("id"));
                organisation.setNom(result.getString("nom"));
                organisation.setPrenom(result.getString("prenom"));
                organisation.setNbAbsence(result.getInt("nbabsence"));
                organisation.setNbRetard(result.getInt("nbretard"));
                organisation.add(organisation);*/
            }
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return organisations;
    }
}
