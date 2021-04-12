package donnees;

import entite.Element;
import entite.Organisation;
import entite.Vilain;
import metier.Outils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


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

    public Organisation findByNom(String nom) {
        Organisation organisation = new Organisation();
        ResultSet result;
        try {
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT id as organisationId, nom, siege_social as siegeSocial, nom_dirigeant as nomDirigeant, commentaire, date_ajout as dateAjout FROM organisation WHERE nom = '" + nom + "'");

            result.next();

            Outils.SetEntite(result, organisation);

            st.close();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return organisation;
    }

    public void update(String column, String value, int organisationId) {
        try {
            String updateSql = "UPDATE organisation SET " + column + " = ? WHERE id = " + organisationId;
            PreparedStatement prepare = this.bdd.prepareStatement(updateSql);
            Object[] arrayPrepare = new Object[]{value};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
