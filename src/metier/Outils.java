package metier;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import java.util.Scanner;

@SuppressWarnings("ALL")
public abstract class Outils {

    public Outils() {
        super();
    }

    /**
     * prepare les requete en enoyant un array
     *
     * @param prepare
     * @param arrayPrepare
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

    /***
     *
     * @param scan
     * @return
     */
    public static Integer scanInteger(Scanner scan) {
        Integer integer = scan.nextInt();
        scan.nextLine();
        return integer;
    }

    /**
     * renvoie une String avec la premier lettre en majuscule
     *
     * @param string
     * @return
     */
    private static String capitalize(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }


    /**
     * demande a l'utilsateeur d'entré une valeur pour chauqe attribut
     * <p>
     * Condition
     * -ID des classe format: [nomClassId]
     * -fonction to string des classe format a rescpecter: [nom =valeur,]
     *
     * @param instance
     * @param scan
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

                    if (!field.getName().contains("Id") && !elementCourant.contains("Id")) {
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
     *
     * @param instance
     * @param type
     * @param field
     * @param scan
     * @param nomAfficher
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



    public static Object SetEntite(ResultSet row,Object instance){
        Class<?> clazz = instance.getClass();
        Integer i = 0;

        for (Field field : clazz.getDeclaredFields()) {

            String type = field.getType().toString();
            System.out.println(row);


        }
return instance;
    }
}

