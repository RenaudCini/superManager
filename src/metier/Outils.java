package metier;

import metier.Heros.FormatType;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Outils {

    public static void prepareRequest(PreparedStatement prepare, Object[] arrayPrepare) throws SQLException {
        for (int i = 0; i < arrayPrepare.length; i++) {
            if (arrayPrepare[i] instanceof Integer) {
                prepare.setInt(i + 1, (Integer) arrayPrepare[i]);

            } else if (arrayPrepare[i] instanceof String) {
                prepare.setString(i + 1, (String) arrayPrepare[i]);
            } else {
                prepare.setString(i + 1, null);
            }
        }
    }

    public static Integer scanInteger(Scanner scan) {
        Integer integer = scan.nextInt();
        scan.nextLine();
        return integer;
    }

    public static void creatEntiteByUser(Object instance, HashMap<Integer, FormatType> map, Scanner scan) {
        map.forEach((k, v) -> {
            System.out.println(v.text);
            String type = Arrays.toString(v.setter.getParameterTypes());
            System.out.println(type);
            try {
                if (type.equals("[class java.lang.String]")) {
                    String value = scan.nextLine();
                    value = !value.equals("") ? value : null;
                    v.setter.invoke(instance, value);
                } else if (type.equals("[class java.lang.Integer]")) {
                    Integer value = Outils.scanInteger(scan);
                    v.setter.invoke(instance, value);
                }

            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });

    }

}

