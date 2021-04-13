package donnees;

import entite.Element;
import entite.Organisation;
import metier.Outils;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class ElementDAO {

    protected Connection bdd;

    public ElementDAO() {

        bdd = DAO.getInstance();
    }

    public ArrayList<Element> findAll() {
        ArrayList<Element> listeElement = new ArrayList<Element>();
        ResultSet result;
        try {
            // Récupération des informations du groupe :
            Statement st = this.bdd.createStatement();
            result = st.executeQuery("SELECT id, nom FROM element ORDER BY nom");

            while (result.next()) {
                Element element = new Element();
                element.setId(result.getInt("id"));
                element.setNom(result.getString("nom"));
                listeElement.add(element);
            }

            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeElement;
    }
}