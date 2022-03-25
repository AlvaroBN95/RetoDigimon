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

/**
 *
 * @author usuario
 */
public class Digimon {

    private String nomDigimon;
    private int defensa;
    private int ataque;
    private Tipo tipo;
    private Nivel nivel;
    private String nomEvoluciona;

    public Digimon(String nombre, int defen, int ataq, Tipo tip, Nivel niv, String nomev) {

        nomDigimon = nombre;
        defensa = defen;
        ataque = ataq;
        tipo = tip;
        nivel = niv;
        nomEvoluciona = nomev;
    }

    public Digimon(String nombre, int defen, int ataq, Tipo tip, Nivel niv) {

        nomDigimon = nombre;
        defensa = defen;
        ataque = ataq;
        tipo = tip;
        nivel = niv;
    }

    public Digimon() {

    }

    static public int pideNumero(String mensaje, int min) {
        int n;
        do {
            n = SLeer1.datoInt(mensaje);
            if (n < min) {
                System.err.println("El dato no es válido, introduce uno válido: ");
            }
        } while (n < min);
        return n;
    }

    public static boolean existeDigimon(String nombre) {

        Connection con = null;
        HashSet<String> nombresResult = new HashSet();
        int tamHash = 0;
        boolean existe = false;

        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NomDigimon FROM Digimon");
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

            System.err.println("\nError en existeDigimon.");
        } finally {

            ConexionBDD.desconectar(con);

        }

        return existe;
    }

    public void creaDigimon() {
        SLeer1.limpiar();
        System.out.println("Se va a crear un digimon, introduce todos los datos.");
        String nombre = SLeer1.datoString("Nombre: ");
        if (existeDigimon(nombre)) {

            System.err.println("\nEse digimon ya existe.");

        } else {

            Connection con = null;
            int defen = pideNumero("Defensa: ", 1);
            int ataq = pideNumero("Ataque: ", 1);
            pideTipo();
            pideNivel();

            nomDigimon = nombre;
            defensa = defen;
            ataque = ataq;

            try {
                con = ConexionBDD.getConexion();
                String consulta = "INSERT INTO Digimon (NomDigimon, Defensa, Ataque, Tipo, Nivel) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(consulta);

                ps.setString(1, nomDigimon);
                ps.setInt(2, defensa);
                ps.setInt(3, ataque);
                ps.setString(4, tipo.toString());
                ps.setInt(5, numNivel(nivel));
                ps.executeUpdate();

                System.out.println("\nEl digimon " + nomDigimon + " se ha creado existosamente.");

            } catch (Exception ex) {

                System.err.println("Se ha producido un error en la creación del digimon. " + ex.getMessage());

            } finally {

                ConexionBDD.desconectar(con);

            }

        }

    }

    public void verDigimons() {

        Connection con = null;
        try {
            con = ConexionBDD.getConexion();
            String consulta = "SELECT NomDigimon, Defensa, Ataque, Tipo, Nivel, NomEvoluviona FROM Digimon;";
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

            System.err.println("Se ha producido un error en la creación del digimon. " + ex.getMessage());

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

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public String getNomDigimon() {
        return nomDigimon;
    }

    public void setNomDigimon(String nomDigimon) {
        this.nomDigimon = nomDigimon;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void pideTipo() {
        int opcion = 0;
        while (opcion < 1 || opcion > 5) {
            opcion = SLeer1.datoInt("Elija el numero que corresponda con el "
                    + "tipo desado: \n1. Vacuna\t2. Virus\t3. Animal\t4. Planta\t"
                    + "5. Elemental\n");
            switch (opcion) {
                case 1:
                    setTipo(Tipo.VACUNA);
                    break;
                case 2:
                    setTipo(Tipo.VIRUS);
                    break;
                case 3:
                    setTipo(Tipo.ANIMAL);
                    break;
                case 4:
                    setTipo(Tipo.PLANTA);
                    break;
                case 5:
                    setTipo(Tipo.ELEMENTAL);
                    break;
                default:
                    System.err.println("El tipo no es correcto. Vuelvelo a intentar.");
            }
        }
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void pideNivel() {
        int opcion = 0;
        while (opcion < 1 || opcion > 3) {
            opcion = SLeer1.datoInt("Elija el nivel: \nNivel 1\tNivel 2\tNivel 3.\n");
            switch (opcion) {
                case 1:
                    setNivel(Nivel.UNO);
                    break;
                case 2:
                    setNivel(Nivel.DOS);
                    break;
                case 3:
                    setNivel(Nivel.TRES);
                    break;
                default:
                    System.err.println("El nivel no es correcto. Vuelvelo a intentar.");
            }
        }
    }

    public int numNivel(Nivel n) {
        int numero = 1;
        switch (n) {
            case UNO:
                numero = 1;
                break;
            case DOS:
                numero = 2;
                break;
            case TRES:
                numero = 3;
                break;
        }
        return numero;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public String getNomEvoluciona() {
        return nomEvoluciona;
    }

    public void setNomEvoluciona(String nomEvoluciona) {
        this.nomEvoluciona = nomEvoluciona;
    }

}
