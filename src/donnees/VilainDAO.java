package donnees;

import entite.*;
import metier.Outils;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public Vilain findById(int id) {
        Vilain vilain = new Vilain();
        ResultSet result;
        try {
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id superPersonnageId, sp.nom, sp.commentaire, sp.vie_base pdv, sp.identite_secrete identiteSecrete, sp.degats_base degats, v.faiblesse, v.degats_faiblesse degatsFaiblesse, v.malveillance, e.id elementId, e.nom elementNom FROM vilain v INNER JOIN super_personnage sp ON v.super_personnage_id = sp.id INNER JOIN element e ON sp.element_id = e.id WHERE sp.id = '" + id + "'");

            result.next();

            Outils.SetEntite(result, vilain);

            // Elément :
            Element element = new Element(result.getString("elementNom"));
            element.setId(result.getInt("elementId"));
            vilain.setElement(element);

            st.close();
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return vilain;
    }

    public ArrayList<Vilain> findAll() {
        ArrayList<Vilain> listeVilains = new ArrayList<Vilain>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id, sp.nom FROM vilain v INNER JOIN super_personnage sp ON v.super_personnage_id = sp.id ORDER BY sp.nom");

            while (result.next()) {
                Vilain vilain = new Vilain();
                vilain.setSuperPersonnageId(result.getInt("id"));
                vilain.setNom(result.getString("nom"));
                listeVilains.add(vilain);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeVilains;
    }

    public ArrayList<Vilain> findAllFree() {
        ArrayList<Vilain> listeVilains = new ArrayList<Vilain>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT sp.id, sp.nom FROM vilain v INNER JOIN super_personnage sp ON v.super_personnage_id = sp.id WHERE sp.groupe_id IS NULL ORDER BY sp.nom");

            while (result.next()) {
                Vilain vilain = new Vilain();
                vilain.setSuperPersonnageId(result.getInt("id"));
                vilain.setNom(result.getString("nom"));
                listeVilains.add(vilain);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeVilains;
    }

    public void update(String column, String value, int superPersonnageId) {
        try {
            String updateSql;
            if (column == "nom" || column == "identite_secrete" || column == "commentaire" || column == "vie_base" || column == "degats_base" || column == "element_id") {
                updateSql = "UPDATE super_personnage SET " + column + " = ? WHERE id = " + superPersonnageId;
            } else {
                updateSql = "UPDATE vilain SET " + column + " = ? WHERE super_personnage_id = " + superPersonnageId;
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
