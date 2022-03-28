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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Tiene {

    private String nombreUsu;
    private String nombreDigimon;
    private Equipo equipo;

    public Tiene() {

    }

    public Tiene(String usuario, String digimon, Equipo eq) {

        nombreUsu = usuario;
        nombreDigimon = digimon;
        equipo = eq;

    }

    public void verEquipo(String nombreUsuario) {
        Connection con = null;
        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT ti.NomDigimon, di.Defensa, di.Ataque, di.Tipo, di.Nivel, di.NomEvoluviona FROM Tiene ti JOIN Digimon di ON ti.Nomdigimon=di.Nomdigimon WHERE Equipo ='" + "Si" + "' AND NombreUsu = '" + nombreUsuario + "';");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            int contador = 0;

            while (output.next()) {
                contador++;

                String NomDigimon = output.getString(1);
                int Defensa = output.getInt(2);
                int Ataque = output.getInt(3);
                String Tipo = output.getString(4);
                int Nivel = output.getInt(5);
                String NomEvoluviona = output.getString(6);

                System.out.println("\nDigimon: " + contador + "\nNombre: " + NomDigimon + "\nDefensa: " + Defensa + "\nAtaque: " + Ataque + "\nTipo: " + Tipo + "\nNivel: " + Nivel + "\nNombre evolución: " + NomEvoluviona);

            }

        } catch (Exception ex) {

            System.err.println("\nNo se puede mostrar tu equipo." + ex.getMessage());
        } finally {

            ConexionBDD.desconectar(con);

        }

    }

    public void asignarDigimons(String Usuario) {
        Connection con = null;
        try {
            con = ConexionBDD.getConexion();
            ArrayList<String> completar = new ArrayList();

            String consulta = ("Select NomDigimon FROM Digimon");
            PreparedStatement ps1 = con.prepareStatement(consulta);
            ResultSet consu = ps1.executeQuery(consulta);

            while (consu.next()) {

                String nombres = consu.getString(1);

                completar.add(nombres);

            }
            Random metodoRandomizador = new Random();

            for (int i = 0; i < 3; i++) {

                int randomizarDigimons = metodoRandomizador.nextInt(completar.size());
                String digimonAleatorio = completar.get(randomizarDigimons);

                String insertar = "INSERT INTO Tiene (NombreUsu, NomDigimon,Equipo) VALUES('" + Usuario + "','" + digimonAleatorio + "',true )";
                PreparedStatement anidar = con.prepareStatement(insertar);
                anidar.executeUpdate();

                completar.remove(randomizarDigimons);

            }

        } catch (Exception ex) {

            System.out.println("Se ha producido un error a la hora de insertar datos." + ex.getMessage());
        } finally {
            ConexionBDD.desconectar(con);

        }
    }

    static public int pideNumero(String mensaje, int min, int max) {
        int n;
        do {
            n = SLeer1.datoInt(mensaje);
            if (n < min || n > max) {
                System.err.println("El dato no es válido, introduce uno válido: ");
            }
        } while (n < min || n > max);
        return n;
    }

    public static boolean usuarioTieneDigimon(String usu, String digi) {

        Connection con = null;
        HashSet<String> tiene = new HashSet();
        int tamHash = 0;
        boolean existe = false;

        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NomDigimon FROM Tiene WHERE NombreUsu='" + usu + "';");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                String nombreDigimon = output.getString(1);
                tiene.add(nombreDigimon);
            }

            tamHash = tiene.size();
            tiene.add(digi);

            if (tamHash == tiene.size()) {

                existe = true;
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());
        } finally {

            ConexionBDD.desconectar(con);

        }

        return existe;
    }

    public void asignarDigimon(String usu) {

        Connection con = null;
        ArrayList<String> digimons = new ArrayList();
        SLeer1.limpiar();

        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NomDigimon FROM Digimon;");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                String nombrDigimon = output.getString(1);
                digimons.add(nombrDigimon);
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());
        } finally {

            ConexionBDD.desconectar(con);

        }

        Random digimonAleatorio = new Random();

        int digi = digimonAleatorio.nextInt(digimons.size());

        String nomDigi = digimons.get(digi);

        if (!usuarioTieneDigimon(usu, nomDigi)) {

            nombreUsu = usu;
            nombreDigimon = nomDigi;

            try {
                con = ConexionBDD.getConexion();
                String consulta = "INSERT INTO Tiene (NombreUsu, NomDigimon, Equipo) VALUES(?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(consulta);

                ps.setString(1, usu);
                ps.setString(2, nomDigi);
                ps.setString(3, "No");

                ps.executeUpdate();

                System.out.println("\nEl digimon " + nomDigi + " se ha añadido a tu colección.");

            } catch (Exception ex) {

                System.err.println(ex.getMessage());

            } finally {

                ConexionBDD.desconectar(con);

            }

        } else {
            asignarDigimon(usu);
        }
    }

    public void asignarDigimon1(String usu) {

        Connection con = null;
        ArrayList<String> digimons = new ArrayList();
        SLeer1.limpiar();

        boolean exito = true;

        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NomDigimon FROM Digimon;");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                String nombrDigimon = output.getString(1);
                digimons.add(nombrDigimon);
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());
        } finally {

            ConexionBDD.desconectar(con);

        }

        while (exito) {

            Random digimonAleatorio = new Random();

            int digi = digimonAleatorio.nextInt(digimons.size());

            String nomDigi = digimons.get(digi);

            if (!usuarioTieneDigimon(usu, nomDigi)) {

                nombreUsu = usu;
                nombreDigimon = nomDigi;

                try {
                    con = ConexionBDD.getConexion();
                    String consulta = "INSERT INTO Tiene (NombreUsu, NomDigimon, Equipo) VALUES(?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(consulta);

                    ps.setString(1, usu);
                    ps.setString(2, nomDigi);
                    ps.setString(3, "No");

                    ps.executeUpdate();

                    System.out.println("\nEl digimon " + nomDigi + " se ha añadido a tu colección.");
                    exito = false;

                } catch (Exception ex) {

                    System.err.println(ex.getMessage());

                } finally {

                    ConexionBDD.desconectar(con);

                }

            } else {
                digimons.remove(digi);
                if (digimons.size() == 0) {
                    System.out.println("\n\tENHORABUENA, HAS CONSEGUIDO A TODOS LOS DIGIMONS, ESE ERA EL ÚLTIMO!!");
                }
            }

            if (digimons.size() <= 0) {
                exito = false;
            }
        }
    }

    public void cambiarEquipo(String usu) {

        Connection con = null;

        ArrayList<String> usuDigis = new ArrayList();
        HashSet<String> usuEquipo = new HashSet();

        SLeer1.limpiar();

        try {

            con = ConexionBDD.getConexion();

            String consulta1 = "SELECT d.NomDigimon, d.Defensa, d.Ataque, d.Tipo, d.Nivel, d.NomEvoluviona FROM Tiene t JOIN Digimon d ON t.Nomdigimon=d.Nomdigimon WHERE t.NombreUsu = '" + usu + "';";

            PreparedStatement ps = con.prepareStatement(consulta1);
            ResultSet output1 = ps.executeQuery(consulta1);

            int contador = 0;

            while (output1.next()) {
                contador++;

                String nomDigimon = output1.getString(1);
                usuDigis.add(nomDigimon);
                int defensa = output1.getInt(2);
                int ataque = output1.getInt(3);
                String tipo = output1.getString(4);
                int nivel = output1.getInt(5);
                String nomEvoluviona = output1.getString(6);

                System.out.println("\nDigimon: " + contador + "\nNombre: " + nomDigimon + "\nDefensa: " + defensa + "\nAtaque: " + ataque + "\nTipo: " + tipo + "\nNivel: " + nivel + "\nNombre evolución: " + nomEvoluviona);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            ConexionBDD.desconectar(con);
        }

        System.out.println("Selecciona qué tres digimons quieres en tu equipo");

        while (usuEquipo.size() < 3) {
            int numDigi = pideNumero("\nIntroduce un numero de digimon: ", 1, usuDigis.size());
            usuEquipo.add(usuDigis.get(numDigi - 1));
        }
        try {
            con = ConexionBDD.getConexion();

            String consulta = "UPDATE Tiene SET Equipo = \"No\" WHERE NombreUsu = '" + usu + "' AND Equipo = \"Si\" ;";
            PreparedStatement ps = con.prepareStatement(consulta);
            ps.executeUpdate();

            for (String nomDigi : usuEquipo) {
                String consulta2 = "UPDATE Tiene SET Equipo = \"Si\" WHERE NombreUsu = '" + usu + "' AND NomDigimon = '" + nomDigi + "';";
                ps = con.prepareStatement(consulta2);
                ps.executeUpdate();
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());

        } finally {

            ConexionBDD.desconectar(con);

        }

    }

    public void verDigimonsUsuario(String usu) {
        Connection con = null;

        try {
            con = ConexionBDD.getConexion();
            String consulta
                    = "SELECT d.NomDigimon, d.Defensa, d.Ataque, d.Tipo, d.Nivel, d.NomEvoluviona FROM Tiene t JOIN Digimon d ON t.NomDigimon=d.NomDigimon WHERE t.NombreUsu='" + usu + "';";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                String NomDigimon = output.getString(1);
                int Defensa = output.getInt(2);
                int Ataque = output.getInt(3);
                String Tipo = output.getString(4);
                int Nivel = output.getInt(5);
                String NomEvoluviona = output.getString(6);

                System.out.println("\nNombre: " + NomDigimon + "\nDefensa: " + Defensa + "\nAtaque: " + Ataque + "\nTipo: " + Tipo + "\nNivel: " + Nivel + "\nNombre evolución: " + NomEvoluviona);

            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());

        } finally {

            ConexionBDD.desconectar(con);

        }
    }

    public String getNombreUsu() {
        return nombreUsu;
    }

    public void setNombreUsu(String nombreUsu) {
        this.nombreUsu = nombreUsu;
    }

    public String getNombreDigimon() {
        return nombreDigimon;
    }

    public void setNombreDigimon(String nombreDigimon) {
        this.nombreDigimon = nombreDigimon;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

}
