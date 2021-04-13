package metier;

import donnees.ElementDAO;
import entite.Element;

import java.util.ArrayList;

public class ElementMetier {

    protected Element element;

    public ElementMetier() {}

    public void showElement() {
        ElementDAO elementDAO = new ElementDAO();
        ArrayList<Element> listeElement = elementDAO.findAll();
        System.out.println("Liste des elements : ");
        for(int i = 0 ; i < listeElement.size(); i++) {
            System.out.println(listeElement.get(i).getId() + " : " + listeElement.get(i).getNom() );
        }
    }

}