package donnees;

import entite.SuperPersonnage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VilainDAO extends SuperPersonnageDAO {
    public void cree(SuperPersonnage superPersonnage) {
        try {

            PreparedStatement prepare = bdd.prepareStatement(
                    "INSERT INTO super_personnage (nom, identite_secraite, pdv, degats, element) " +
                            "VALUES (?, ?, ?, ?)"
            );
            prepare.setString(1, superPersonnage.getNom());
            prepare.setString(2, superPersonnage.getIdentiteSecrete());
            prepare.setInt(3, superPersonnage.getPdv());
            prepare.setInt(4, superPersonnage.getDegats());

            prepare.executeUpdate();
            prepare.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
