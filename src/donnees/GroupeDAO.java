package donnees;

import entite.*;
import metier.Outils;

import java.sql.*;
import java.util.ArrayList;

public class GroupeDAO {

    protected Connection bdd;

    public GroupeDAO() {
        bdd = DAO.getInstance();
    }

    /**
     * Permet de récupérer les informations d'un groupe par son ID.
     *
     * @param id [int] L'ID du groupe en base de données.
     * @return [Groupe] Un objet de type Groupe.
     */
    public Groupe findById(int id) {
        Groupe groupe = new Groupe();
        ArrayList<Heros> listeHeros = new ArrayList<Heros>();
        ArrayList<Vilain> listeVilains = new ArrayList<Vilain>();
        ResultSet result;

        try {
            // Récupération du groupe :
            Statement stGroupe = this.bdd.createStatement();
            result = stGroupe.executeQuery("SELECT id, nom FROM groupe WHERE id = '" + id + "'");

            result.next();
            groupe.setId(result.getInt("id"));
            groupe.setNom(result.getString("nom"));

            stGroupe.close();

            // Récupération des héros :
            Statement stHeros = this.bdd.createStatement();
            result = stHeros.executeQuery("SELECT g.id as groupe_id, g.nom as groupe_nom, sp.id as super_personnage_id, sp.nom as super_personnage_nom, sp.vie_base, sp.degats_base, sp.element_id, e.nom as element_nom, h.pouvoir, h.degats_pouvoir, h.organisation_id FROM groupe g INNER JOIN super_personnage sp ON g.id = sp.groupe_id INNER JOIN heros h ON sp.id = h.super_personnage_id INNER JOIN element e ON sp.element_id = e.id WHERE g.id = '" + id + "'");

            while (result.next()) {
                Heros heros = new Heros();
                heros.setSuperPersonnageId(result.getInt("super_personnage_id"));
                heros.setNom(result.getString("super_personnage_nom"));
                heros.setPdv(result.getInt("vie_base"));
                heros.setDegats(result.getInt("degats_base"));

                Element element = new Element();
                element.setId(result.getInt("element_id"));
                element.setNom(result.getString("element_nom"));
                heros.setElement(element);

                heros.setPouvoir(result.getString("pouvoir"));
                heros.setDegatsPouvoir(result.getInt("degats_pouvoir"));

                listeHeros.add(heros);
            }

            stHeros.close();

            // Récupération des vilains :
            Statement stVilains = this.bdd.createStatement();
            result = stVilains.executeQuery("SELECT g.id as groupe_id, g.nom as groupe_nom, sp.id as super_personnage_id, sp.nom as super_personnage_nom, sp.vie_base, sp.degats_base, sp.element_id, e.nom as element_nom, v.faiblesse, v.degats_faiblesse, v.malveillance FROM groupe g INNER JOIN super_personnage sp ON g.id = sp.groupe_id INNER JOIN vilain v ON sp.id = v.super_personnage_id INNER JOIN element e ON sp.element_id = e.id WHERE g.id = '" + id + "'");

            while (result.next()) {
                Vilain vilain = new Vilain();
                vilain.setSuperPersonnageId(result.getInt("super_personnage_id"));
                vilain.setNom(result.getString("super_personnage_nom"));
                vilain.setPdv(result.getInt("vie_base"));
                vilain.setDegats(result.getInt("degats_base"));

                Element element = new Element();
                element.setId(result.getInt("element_id"));
                element.setNom(result.getString("element_nom"));
                vilain.setElement(element);

                vilain.setFaiblesse(result.getString("faiblesse"));
                vilain.setDegatsFaiblesse(result.getInt("degats_faiblesse"));
    vilain.setMalveillance(result.getInt("malveillance"));

                listeVilains.add(vilain);
            }

            stVilains.close();

            // Attribution des héros et vilains au groupe :
            groupe.setListeHeros(listeHeros);
            groupe.setListeVilains(listeVilains);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupe;
    }

    /**
     * Permet de récupérer tous les groupes en base de données.
     *
     * @return [ArrayList] Une arraylist de Groupes.
     */
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

    /**
     * Permet d'update l'ID du groupe d'un personnage en base de données.
     *
     * @param groupeId [int] L'ID du groupe.
     * @param superPersonnageId [int] L'ID du personnage.
     */
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

    /**
     * Permet d'update le nom d'un groupe en base de données.
     * @param value [String] Le nouveau nom du groupe.
     * @param groupeId [int] L'ID du groupe à updater.
     */
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

    /**
     * Permet de créer un groupe en base de données et d'update les héros du groupe en paramétrant leur groupe_id
     * sur l'ID du groupe.
     *
     * @param nom [String] Le nom du groupe.
     * @param listeSuper [String] Une liste des ID de super personnages à paramétrer, chaque ID séparé par une virgule.
     */
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

    public void deleteGroupe(int groupeId) {
        try {
            String updateSql = "DELETE FROM groupe WHERE id = ?";
            PreparedStatement prepare = this.bdd.prepareStatement(updateSql);
            Object[] arrayPrepare = new Object[]{groupeId};
            Outils.prepareRequest(prepare,arrayPrepare);
            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
