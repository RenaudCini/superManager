package donnees;

import entite.Groupe;
import entite.Heros;
import entite.Vilain;
import metier.Outils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class GroupeDAO {

    protected Connection bdd;

    public GroupeDAO() {
        bdd = DAO.getInstance();
    }

    public Groupe findById(int id) {
        Groupe groupe = new Groupe();
        ArrayList<Heros> listeHeros = new ArrayList<Heros>();
        ArrayList<Vilain> listeVilains = new ArrayList<Vilain>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT g.id as groupe_id, g.nom as groupe_nom, sp.id as super_personnage_id, sp.nom as super_personnage_nom, CASE WHEN h.super_personnage_id THEN 'heros' ELSE 'vilain' END as type FROM groupe g INNER JOIN super_personnage sp ON g.id = sp.groupe_id LEFT JOIN heros h ON sp.id = h.super_personnage_id WHERE g.id = '" + id + "'");

            while (result.next()) {
                if (result.getString("type").equals("heros")) {
                    Heros heros = new Heros();
                    heros.setSuperPersonnageId(result.getInt("super_personnage_id"));
                    heros.setNom(result.getString("super_personnage_nom"));
                    listeHeros.add(heros);
                } else {
                    Vilain vilain = new Vilain();
                    vilain.setSuperPersonnageId(result.getInt("super_personnage_id"));
                    vilain.setNom(result.getString("super_personnage_nom"));
                    listeVilains.add(vilain);
                }

                groupe.setId(result.getInt("groupe_id"));
                groupe.setNom(result.getString("groupe_nom"));
            }

            groupe.setListeHeros(listeHeros);
            groupe.setListeVilains(listeVilains);

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }

    public ArrayList<Groupe> findAll() {
        ArrayList<Groupe> listeGroupes = new ArrayList<Groupe>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT id, nom FROM groupe ORDER BY nom");

            while (result.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(result.getInt("id"));
                groupe.setNom(result.getString("nom"));
                listeGroupes.add(groupe);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeGroupes;
    }

    public void updatePersonnage(int groupeId, int superPersonnageId) {
        try {
            String updateSql = "UPDATE super_personnage SET groupe_id = ? WHERE id = " + superPersonnageId;

            PreparedStatement prepare = this.bdd.prepareStatement(updateSql);
            Object[] arrayPrepare;
            if (groupeId == 0) {
                prepare.setNull(1, Types.NULL);
            }
            else {
                prepare.setInt(1, groupeId);
            }
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroupe(String value, int groupeId) {
        try {
            String updateSql = "UPDATE groupe SET nom = ? WHERE id = " + groupeId;
            PreparedStatement prepare = this.bdd.prepareStatement(updateSql);
            Object[] arrayPrepare = new Object[]{value};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void creerGroupe(String nom, String listeSuper) {
        try {
            String createSql = "INSERT INTO groupe (nom) VALUES (?)";
            PreparedStatement prepare = this.bdd.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS);
            Object[] arrayPrepare = new Object[]{nom};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();

            ResultSet rs = prepare.getGeneratedKeys();
            int lastInsertedId =0;
            if (rs.next()) {
                lastInsertedId = rs.getInt(1);
            }

            prepare.close();

            String updateSuperPersonnages = "UPDATE super_personnage SET groupe_id = (?) WHERE id IN (" + listeSuper + ")";
            PreparedStatement prepare2 = this.bdd.prepareStatement(updateSuperPersonnages);
            Object[] arrayPrepare2 = new Object[]{lastInsertedId};
            Outils.prepareRequest(prepare2,arrayPrepare2);
            prepare2.execute();
            prepare2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
