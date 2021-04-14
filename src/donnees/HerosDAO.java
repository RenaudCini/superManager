package donnees;

import entite.*;
import metier.Outils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class HerosDAO extends SuperPersonnageDAO {

    /**
     * Permet d'insérer un Héros en base de données.
     * @param heros [Heros] Un objet de type Héros.
     */
    public void creer(Heros heros) {
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

    /**
     * Permet de sélectionner un héros par son ID.
     * @param id [int] L'ID du héros.
     * @return [Heros] Un objet de type Heros.
     */
    public Heros findById(int id) {
        Heros heros = new Heros();
        ResultSet result;
        try {
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id as super_personnage_id, sp.nom, sp.identite_secrete, sp.commentaire, sp.vie_base, sp.degats_base, h.degats_pouvoir, e.id as element_id, e.nom as element_nom, o.id as organisation_id, o.nom as organisation_nom FROM heros h INNER JOIN super_personnage sp ON h.super_personnage_id = sp.id INNER JOIN organisation o ON h.organisation_id = o.id INNER JOIN element e ON sp.element_id = e.id WHERE sp.id = '" + id + "'");

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
            organisation.setOrganisationId(result.getInt("organisation_id"));
            organisation.setNom(result.getString("organisation_nom"));
            heros.setOrganisation(organisation);

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heros;
    }

    /**
     * Permet de récupérer une liste des héros.
     * @return [ArrayList] Une ArrayList d'objets Heros.
     */
    public ArrayList<Heros> findAll() {
        ArrayList<Heros> listeHeros = new ArrayList<Heros>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id, sp.nom FROM heros h INNER JOIN super_personnage sp ON h.super_personnage_id = sp.id ORDER BY sp.nom");

            while (result.next()) {
                    Heros heros = new Heros();
                    heros.setSuperPersonnageId(result.getInt("id"));
                    heros.setNom(result.getString("nom"));
                    listeHeros.add(heros);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeHeros;
    }

    /**
     * Permet de récupérer la liste des héros n'appartenant pas à un groupe.
     * @return [ArrayList] Une ArrayList d'objets Heros.
     */
    public ArrayList<Heros> findAllFree() {
        ArrayList<Heros> listeHeros = new ArrayList<Heros>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id, sp.nom FROM heros h INNER JOIN super_personnage sp ON h.super_personnage_id = sp.id WHERE sp.groupe_id IS NULL ORDER BY sp.nom");

            while (result.next()) {
                Heros heros = new Heros();
                heros.setSuperPersonnageId(result.getInt("id"));
                heros.setNom(result.getString("nom"));
                listeHeros.add(heros);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeHeros;
    }

    /**
     * Permet d'update un héros en base de données.
     * @param column [String] Le nom de la colonne à updater.
     * @param value [String] La nouvelle valeur à attribuer à la colonne.
     * @param superPersonnageId [int] L'ID du super personnage.
     */
    public void update(String column, String value, int superPersonnageId) {
        try {
            String updateSql;
            if (column == "nom" || column == "identite_secrete" || column == "commentaire" || column == "vie_base" || column == "degats_base" || column == "element_id") {
                updateSql = "UPDATE super_personnage SET " + column + " = ? WHERE id = " + superPersonnageId;
            } else {
                updateSql = "UPDATE heros SET " + column + " = ? WHERE super_personnage_id = " + superPersonnageId;
            }
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
