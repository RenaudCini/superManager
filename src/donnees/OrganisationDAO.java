package donnees;

import entite.Organisation;
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

    /**
     * Permet d'insérer une organisation en base de données.
     * @param organisation [Organisation] Un objet de type Organisation.
     */
    public void creer(Organisation organisation) {

        try {

            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO organisation (nom, siege_social, nom_dirigeant, commentaire, date_ajout) VALUES (?, ?, ?, ?, NOW())");
            String commentaire = organisation.getCommentaire() != null ? organisation.getCommentaire() : null;
            //creation d'un array contenant  les variable a prepare
            Object[] arrayPrepare = new Object[]{organisation.getNom(), organisation.getSiegeSocial(), organisation.getNomDirigeant(), commentaire};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    /**
     * Permet de récupérer une liste des organisations.
     * @return [ArrayList] Une ArrayList d'objets Organisation.
     */
    public ArrayList<Organisation> findAll() {
        ArrayList<Organisation> listeOrganisations = new ArrayList<Organisation>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT id, nom FROM organisation ORDER BY nom");

            while (result.next()) {
                Organisation organisation = new Organisation();
                organisation.setOrganisationId(result.getInt("id"));
                organisation.setNom(result.getString("nom"));
                listeOrganisations.add(organisation);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeOrganisations;
    }

    /**
     * Permet de sélectionner une organisation par son ID.
     * @param id [int] L'ID de l'organisation.
     * @return [Organisation] Un objet de type Organisation.
     */
    public Organisation findById(int id) {
        Organisation organisation = new Organisation();
        ResultSet result;
        try {
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT id as organisationId, nom, siege_social as siegeSocial, nom_dirigeant as nomDirigeant, commentaire, date_ajout as dateAjout FROM organisation WHERE id = '" + id + "'");

            result.next();

            Outils.SetEntite(result, organisation);

            st.close();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return organisation;
    }

    /**
     * Permet d'update une organisation en base de données.
     * @param column [String] Le nom de la colonne à updater.
     * @param value [String] La nouvelle valeur à attribuer à la colonne.
     * @param organisationId [int] L'ID de l'organisation.
     */
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
