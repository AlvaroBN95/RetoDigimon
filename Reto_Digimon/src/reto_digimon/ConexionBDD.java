/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBDD {

    static final String DB_URL
            = "jdbc:mysql://localhost:3306/Digimon";
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";

    static final String DB_USER = "root";
    static final String DB_PASSWD = "1311";

    public ConexionBDD() {
    }

    public static void desconectar(Connection con) {

        try {

            if (con != null) {

                con.close();

            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }
    }

    public static Connection getConexion() {

        Connection con = null;
        try {

            Class.forName(DB_DRV);
            con = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);

        } catch (Exception ex) {

            System.err.println("Error: " + ex.getMessage());

        }
        return con;
    }

}
