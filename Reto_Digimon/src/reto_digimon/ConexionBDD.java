/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBDD {

    private static Connection connection;
    private static Statement statement;
    public static ResultSet resultSet;

    static final String DB_URL
            = "jdbc:mysql://localhost:3306/digimon";
    static final String DB_DRV
            = "com.mysql.jdbc.Driver";
    static final String DB_USER = "pma";
    static final String DB_PASSWD = "alvaro";

    public ConexionBDD() {
    }

    public static void conectar(String consulta) throws Exception {

        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(consulta);
    }

    public static void desconectar() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {

            System.out.println("No se cierra y no sabemos porqué");

        }

    }

    public static Statement getStatement() {
        return statement;
    }

    public static void setStatement(Statement statement) {
        ConexionBDD.statement = statement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static void setResultSet(ResultSet resultSet) {
        ConexionBDD.resultSet = resultSet;
    }

    /*public static void consultaDigimon(String consulta) throws Exception {

        
        
        while (resultSet.next()) {
            
            //%d entero, %fn decimales, %s String
            
            System.out.printf("%s %d %d %s %s %s",
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
        }
        System.out.println();
    }
    
    public static void consultaTiene(String consulta) throws Exception {

        statement = connection.createStatement();
        resultSet = statement.executeQuery(consulta);
        
        while (resultSet.next()) {
            
            //%d entero, %fn decimales, %s String
            
            System.out.printf("%s %s %s",
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3));
        }
        System.out.println();

    }
    
    public static void consultaUsuario(String consulta) throws Exception {

        statement = connection.createStatement();
        resultSet = statement.executeQuery(consulta);
        
        while (resultSet.next()) {
            
            //%d entero, %fn decimales, %s String
            
            System.out.printf("%s %s %d %d %d",
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5));
        }
        System.out.println();
    }

    public static void ConexionBBDD(char clase, String consulta) throws Exception {

        try {

            conectar();
            
            switch(clase){
                case 'd': consultaDigimon(consulta); break;
                case 'u': consultaUsuario(consulta); break;
                case 't': consultaTiene(consulta); break;
                default: System.err.println("Error. Clase no reconocida.");
            }

        } catch (SQLException ex) {
            
            throw ex;
            
        }
    }*/
}
