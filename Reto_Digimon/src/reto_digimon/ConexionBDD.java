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
        static final String DB_URL =
      "jdbc:mariadb://localhost:3306/digimon";
   static final String DB_DRV =
      "com.mariadb.jdbc.Driver";
   static final String DB_USER = "pma";
   static final String DB_PASSWD = "alvaro";

   public static void main(String[] args){

      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;

      try{
         connection=DriverManager.getConnection
            (DB_URL,DB_USER,DB_PASSWD);
         statement=connection.createStatement();
         resultSet=statement.executeQuery
            ("SELECT * FROM digimon");
         while(resultSet.next()){
            System.out.printf("%st%st%st%fn",
            resultSet.getString(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getFloat(4));
         }

      }catch(SQLException ex){
      }finally{
         try {
            resultSet.close();
            statement.close();
            connection.close();
         } catch (SQLException ex) {
         }
      }
   }
    
 
    
}
