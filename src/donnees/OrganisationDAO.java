package donnees;

import entite.Organisation;
import metier.Outils;

import java.lang.reflect.InvocationTargetException;
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
            PreparedStatement prepare = this.bdd.prepareStatement("SELECT id organisationId ,nom,siege_social siegeSocial, nom_dirigeant nomDirigeant,commentaire,date_ajout dateAjout FROM organisation");

            result = prepare.executeQuery();
      ;
            while (result.next()) {
                organisation = new Organisation();
                Outils.SetEntite(result, organisation);
                System.out.println(organisation.toString());
            }
            prepare.close();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return organisations;
    }



}
