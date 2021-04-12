package donnees;

import entite.Organisation;
import metier.Outils;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO organisation (nom, siege_social, nom_dirigeant, commentaire, date_ajout) VALUES (?, ?, ?, ?, NOW())");
            String commentaire = organisation.getCommentaire() != null ? organisation.getCommentaire() : null;
            //creation d'un array contenant  les variable a prepare
            Object[] arrayPrepare = new Object[]{organisation.getNom(), organisation.getAdresse(), organisation.getNomDirigeant(), organisation.getCommentaire()};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



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
