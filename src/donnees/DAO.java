package donnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class de connexion à la base de données.
 */
public class DAO {
    private static String url = "jdbc:mysql://localhost:3306/supermanager?autoReconnect=true&useSSL=false";
    private static String user = "root";
    private static String passwd = "";
    private static Connection connect;

    /**
     * Permet de se connecter à la base de données.
     *
     * @return [Connection] L'instance de connexion à la base de données.
     */
    public static Connection getInstance(){

        if(connect == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connect = DriverManager.getConnection(url,user, passwd);
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Connexion Impossible" + e.getMessage());
            }
        }
        return connect;
    }
}


