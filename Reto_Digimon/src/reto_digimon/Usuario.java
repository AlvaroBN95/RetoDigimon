/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import Sleer1.SLeer1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import reto_digimon.ConexionBDD;

/**
 *
 * @author usuario
 */
public class Usuario {

    private String nombreUsu;
    private String pass;
    private int pJugadas;
    private int partidasGan;
    private int cantTokens;

    public Usuario(String nombre, String contrasenya) {
        nombreUsu = nombre;
        pass = contrasenya;
        pJugadas = 0;
        partidasGan = 0;
        cantTokens = 0;

    }
    
    public Usuario (){}

    public void creaUsuario() {

        SLeer1.limpiar();
        String nombre = SLeer1.datoString("Usuario: ");
        String pass = "";
        String pass2 = "";

        if (existeUsuario(nombre)) {

            System.err.println("\nEste usuario ya existe.");

        } else {

            do {
                pass = SLeer1.datoString("Contraseña: ");
                pass2 = SLeer1.datoString("Contraseña: ");

                if (!(pass.equals(pass2))) {
                    System.err.println("\nLas contraseñas no coinciden.");
                    System.out.println();
                }
            } while (!(pass.equals(pass2)));

            nombreUsu = nombre;
            this.pass = pass;

            try {
                Connection con = ConexionBDD.getConexion();
                String consulta = "INSERT INTO Usuario (NombreUsu, Pass, PJugadas, PartidasGan, CantTokens) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(consulta);
                
                ps.setString(1, nombreUsu);
                ps.setString(2, this.pass);
                ps.setInt(3, pJugadas);
                ps.setInt(4, partidasGan);
                ps.setInt(5, cantTokens);
                ps.executeUpdate();

                System.out.println("\nEl usuario " + nombreUsu + " se ha creado existosamente.");

            } catch (Exception ex) {

                System.err.println("Se ha producido un error en la creación del usuario." + ex.getMessage());

            } finally {

                ConexionBDD.desconectar();

            }

        }

    }

    public static boolean existeUsuario(String nombre) {

        HashSet<String> nombresResult = new HashSet();
        int tamHash = 0;
        boolean existe = false;

        try {

            Connection con = ConexionBDD.getConexion();
            String consulta = ("SELECT NombreUsu FROM Usuario");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                String nombreUsuario = output.getString(1);
                nombresResult.add(nombreUsuario);
            }

            tamHash = nombresResult.size();
            nombresResult.add(nombre);

            if (tamHash == nombresResult.size()) {

                existe = true;
            }

        } catch (Exception ex) {

            System.err.println("\nError en el método buscaUsuario de la clase Menu.");
        } finally {

            ConexionBDD.desconectar();

        }

        return existe;
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombre) {
        nombreUsu = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String contrasenya) {
        pass = contrasenya;
    }

    public int getpJugadas() {
        return pJugadas;
    }

    public void setpJugadas(int p_jugadas) {
        pJugadas = p_jugadas;
    }

    public int getPartidasGan() {
        return partidasGan;
    }

    public void setPartidasGan(int p_ganadas) {
        partidasGan = p_ganadas;
    }

    public int getCantTokens() {
        return cantTokens;
    }

    public void setCantTokens(int tokens) {
        cantTokens = tokens;
    }

    public void sumaPartidaJugada() {
        pJugadas++;
    }

    public void sumaPartidaGanada() {
        partidasGan++;
    }

    public void sumaToken() {
        cantTokens++;
    }

    /*public static void  conectarU (String consulta){
       
       try{
           
        ConexionBBDD('u', consulta);
        
       } catch (Exception ex){
           
           System.err.println(ex);

       } finally {
       
           try {

                desconectar();

            } catch (Exception ex) {

                System.err.println(ex);
            }
       }
   }*/
}
