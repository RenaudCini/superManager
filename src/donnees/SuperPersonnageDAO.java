package donnees;

import entite.SuperPersonnage;
import metier.Outils;
import java.sql.*;

public class SuperPersonnageDAO {

    protected Connection bdd;

    public SuperPersonnageDAO() {
        bdd = DAO.getInstance();
    }

    /**
     * Permet de créer un super personnage en base de données.
     * @param superPersonnage [SuperPersonnage] Un objet de type SuperPersonnage.
     */
    public void cree(SuperPersonnage superPersonnage) {
        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO super_personnage (nom, identite_secrete, vie_base, degats_base, element_id,commentaire) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            //permet de pouvoire mettre a null la valeur commentaire lor de la demande saisi
            String commentaire = superPersonnage.getCommentaire() != null ? superPersonnage.getCommentaire() : null;
            
            //creation d'un array contenant  les variable a prepare
            Object[] arrayPrepare = new Object[]{superPersonnage.getNom(), superPersonnage.getIdentiteSecrete(), superPersonnage.getPdv(), superPersonnage.getDegats(), superPersonnage.getElement().getId(), commentaire};
            Outils.prepareRequest(prepare, arrayPrepare);
            prepare.executeUpdate();
            //recupere l'id du super hero inseret
            ResultSet InsertId = prepare.getGeneratedKeys();
            InsertId.next();

            //met la valeur du l'id dans l'instance de l'objet superPersonnage
            superPersonnage.setSuperPersonnageId(InsertId.getInt(1));
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
