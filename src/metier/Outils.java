package metier;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Scanner;

@SuppressWarnings("ALL")
public abstract class Outils {


    public Outils() {
        HashMap<String, Integer> people = new HashMap<String, Integer>();
        // Add keys and values (Name, Age)
        people.put("John", 32);
        people.put("Steve", 30);
        people.put("Angie", 33);
    }

    /**
     * Permet de préparer les requêtes en envoyant un array.
     * @param prepare [PreparedStatement] L'instance PreparedStatement de la requête.
     * @param arrayPrepare [Object[]] L'array des paramètres à insérer dans la requête préparée.
     * @throws SQLException
     */
    public static void prepareRequest(PreparedStatement prepare, Object[] arrayPrepare) throws SQLException {
        //on boucle sur l'array ou ce toruve les variable a preparé
        for (int i = 0; i < arrayPrepare.length; i++) {
            //si c'est integer
            if (arrayPrepare[i] instanceof Integer) {
                prepare.setInt(i + 1, (Integer) arrayPrepare[i]);
                //si c'est String
            } else if (arrayPrepare[i] instanceof String) {
                prepare.setString(i + 1, (String) arrayPrepare[i]);
            } else {
                prepare.setString(i + 1, null);
            }
        }
    }

    /**
     * Permet de scanner un int en consommant le retour à la ligne suivant.
     * @param scan [Scanner] Un objet de type Scanner.
     * @return [Integer] L'entier scanné.
     */
    public static Integer scanInteger(Scanner scan) {
        Integer integer = scan.nextInt();
        scan.nextLine();
        return integer;
    }

    /**
     * Permet de transformer la première lettre d'une chaîne de caractères en majuscule.
     *
     * @param string [String] La chaîne à transformer.
     * @return [String] La chaîne transformée.
     */
    private static String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }


    /**
     * Permet de demander à l'utilisateur d'entrer une valeur pour chaque attribut d'une class, automatisant ainsi
     * la création d'une entité.
     *
     * Conditions :
     * - Les classes doivent être en camelCase, ex. : "nomClassId" ;
     * - Les classes doivent avoir une fonction toString avec le format "nom = valeur," à respecter.
     *
     * @param instance [Object] L'instance de l'entité.
     * @param scan [Scanner] Un objet de type Scanner.
     */

    public static Object AfficherTextCreationEntite(Object instance, Scanner scan) {

        Class<?> clazz = instance.getClass();
        //on splite  en array a chaque virgule le resultat de la fonction to string virgule
        String[] split = instance.toString().split(",");
        String attributDemander = null;
        String elementCourant = null;
        Integer i = 0;

        boolean element = true;
        while (element) {
            //boucle sur tous les attributs de la classe
            for (Field field : clazz.getDeclaredFields()) {
                // si i est inferieur au nombre d'attribut qu'il y a dans la fonction to string
                if (i < split.length) {
                    attributDemander = null;
                    String type = field.getType().toString();
                    // test si la valeur n'est pas egale a id de l'entité
                    elementCourant = split[i];

                    if (!field.getName().contains("Id") && !field.getName().contains("date") && !elementCourant.contains("Id")) {
                        attributDemander = elementCourant.replace("=null", "");
                        i = testTypeVariable(instance, type, field, scan, attributDemander, i);
                    }
                }
            }
            //il n'y a pas de classe super
            if (Arrays.toString(new Class[]{clazz.getSuperclass()}).equals("[class java.lang.Object]")) {
                element = false;
            } else {
                //on lui redefini la class par sa super classe
                clazz = clazz.getSuperclass();
            }
        }
        return instance;
    }

    /**
     * Permet de tester le type des variables d'une class.
     * @param instance
     * @param type
     * @param field
     * @param scan
     * @param attributDemander
     * @param i
     * @return
     */
    public static Integer testTypeVariable(Object instance, String type, Field field, Scanner scan, String attributDemander, Integer i) {

        Object value = null;

        String setter = "set" + capitalize(field.getName());
        Class<?> typeSetter = String.class;

        //si la valeur demander est une string
        if (type.equals("class java.lang.String")) {
            System.out.println(attributDemander);
            value = new String(scan.nextLine());
            value = !value.equals("") ? value : null;
            typeSetter = String.class;

            //si la valeur demander est une Integer
        } else if (type.equals("class java.lang.Integer")) {
            System.out.println(attributDemander);
            value = new Integer(Outils.scanInteger(scan));
            typeSetter = Integer.class;

            //si la valeur demander est une Object
        } else if (type.toString().contains("entite.")) {
            return i;
            /*  str = str.replaceAll("\\s", "");*/
        } else {
            return i;
        }
        i++;
        //appel le setter assaocier a valeur demandé
        try {
            instance.getClass().getMethod(setter, typeSetter).invoke(instance, value);

        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Permet d'automatiser la création d'une entité.
     * @param result
     * @param instance
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws SQLException
     * @throws InvocationTargetException
     */
    public static Object SetEntite(ResultSet result, Object instance) throws NoSuchMethodException, IllegalAccessException, SQLException, InvocationTargetException {
        Class<?> clazz = instance.getClass();
        Integer i = 0;
        boolean element = true;
        while (element) {
            for (Field field : clazz.getDeclaredFields()) {

                getTypeAttribut(instance, field, result);

            }
            if (Arrays.toString(new Class[]{clazz.getSuperclass()}).equals("[class java.lang.Object]")) {
                element = false;
            } else {
                //on lui redefini la class par sa super classe
                clazz = clazz.getSuperclass();
            }
        }
        return instance;
    }

    /**
     * Permet de récupérer le type d'un attribut de classe.
     * @param instance
     * @param field
     * @param result
     * @throws NoSuchMethodException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void getTypeAttribut(Object instance, Field field, ResultSet result) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Class<?> typeSetter = String.class;
        String setter = "set" + capitalize(field.getName());
        String nameAttribut = field.getName();
        String type = field.getType().toString();
        //System.out.println(field.getName());

        if (type.equals("class java.lang.Integer")) {
            try {
                typeSetter = Integer.class;
                instance.getClass().getMethod(setter, typeSetter).invoke(instance, result.getInt(nameAttribut));

            } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException throwables) {
                throwables.printStackTrace();
            }
        } else if (type.equals("class java.lang.String")) {
            try {
                typeSetter = String.class;
                instance.getClass().getMethod(setter, typeSetter).invoke(instance, result.getString(nameAttribut));
                result.getString(nameAttribut);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        /*else if (type.equals("class java.util.Date")){
            try {
                typeSetter = Date.class;
                instance.getClass().getMethod(setter, typeSetter).invoke(instance, result.getDate(nameAttribut));
                result.getDate(nameAttribut);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }*/
    }

}

