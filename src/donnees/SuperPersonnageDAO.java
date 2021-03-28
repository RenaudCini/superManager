package donnees;

import entite.SuperPersonnage;
import metier.Outils;
import java.sql.*;


public class SuperPersonnageDAO {

    protected Connection bdd;

    public SuperPersonnageDAO() {

        bdd = DAO.getInstance();
    }

    public void cree(SuperPersonnage superPersonnage) {
        try {
            PreparedStatement prepare = this.bdd.prepareStatement("INSERT INTO super_personnage (nom, identite_secrete, vie_base, degats_base, element_id,commentaire) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            String commentaire = superPersonnage.getCommentaire() != null ? superPersonnage.getCommentaire() : null;
            Object[] arrayPrepare = new Object[]{superPersonnage.getNom(), superPersonnage.getIdentiteSecrete(), superPersonnage.getPdv(), superPersonnage.getDegats(), superPersonnage.getElement().getId(), commentaire};
            Outils.prepareRequest(prepare, arrayPrepare);
            prepare.executeUpdate();
            ResultSet InsertId = prepare.getGeneratedKeys();
            InsertId.next();
            Integer IdSuperPersonnage = InsertId.getInt(1);
            superPersonnage.setSuperPersonnageId(IdSuperPersonnage);
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
