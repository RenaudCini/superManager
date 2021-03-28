package metier;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

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

    private static String capitalize(String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public static void creatEntiteByUser(Object instance, Scanner scan) throws IllegalAccessException {

        Class<?> clazz = instance.getClass();
        boolean element = true;
        while (element) {
            for (Field field : clazz.getDeclaredFields()) {
                String idClass = "entite." + capitalize(field.getName().replace("Id", ""));
                if (!clazz.getName().equals(idClass)) {



                    if (field.getType().toString().equals("class java.lang.String")) {
                        System.out.println(field.getName());
                        String value = scan.nextLine();
                        value = !value.equals("") ? value : null;
                        try {
                            String setter = "set" + capitalize(field.getName());
                            instance.getClass().getMethod(setter, String.class).invoke(instance, value);
                        } catch (InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    } else if (field.getType().toString().equals("class java.lang.Integer")) {
                        System.out.println(field.getName());
                        Integer value = Outils.scanInteger(scan);
                        try {
                            String setter = "set" + capitalize(field.getName().toString());
                            instance.getClass().getMethod(setter, Integer.class).invoke(instance, value);
                        } catch (InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }

                }

            }

            if (Arrays.toString(new Class[]{clazz.getSuperclass()}).equals("[class java.lang.Object]")) {
                element = false;
            } else {
                clazz = clazz.getSuperclass();
            }
        }
    }

}

