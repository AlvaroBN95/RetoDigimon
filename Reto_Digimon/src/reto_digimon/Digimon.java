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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    public void establecerEvolucion() {

        Connection con = null;
        HashMap<String, Digimon> nombreDigi = new HashMap<String, Digimon>();
        Digimon d1 = new Digimon();
        Digimon d2 = new Digimon();
        String auxiliar = "0";

        try {

            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NomDigimon, Nivel, Tipo, Ataque, Defensa FROM Digimon");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);

            while (output.next()) {

                Tipo tipo = Tipo.valueOf((output.getString(3)).toUpperCase());
                Nivel nivel = intToNivel(output.getInt(2));
                d1 = new Digimon(output.getString(1), output.getInt(5), output.getInt(4), tipo, nivel);

                nombreDigi.put(d1.getNomDigimon(), d1);
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());

        } finally {

            ConexionBDD.desconectar(con);

        }

        verDigimons();

        System.out.println();
        System.out.println("-----NORMAS DE LAS DIGIEVOLUCIONES-----");
        System.out.println("1.- Los Digimons de nivel 3 NO pueden Digievolucionar más.");
        System.out.println("2.- La Digievolución debe ser tan solo un nivel superior.");
        System.out.println("3.- La Digievolución debe ser del mismo tipo.");

        SLeer1.limpiar();

        d1 = pideDigimon("Elige al que quieras establecer su Digievolución(escribe el nombre tal cual)(0 para salir): ", nombreDigi, 3, Tipo.VACUNA);
        auxiliar = d1.getNomDigimon();
        while (!auxiliar.equals("0")) {

            do {
                System.out.println();

                d2 = pideDigimon("Ahora elige la Digievolución(nombre tal cual): ", nombreDigi, numNivel(d1.getNivel()), Tipo.VACUNA);

                if (!(d1.getTipo().equals(d2.getTipo()))) {
                    System.out.println();
                    System.err.println("\tEl tipo de los dos Digimons seleccionados no es igual.");
                    d1 = pideDigimon("Elige al que quieras establecer su Digievolución(escribe el nombre tal cual)(0 para salir): ", nombreDigi, 3, d2.getTipo());
                }

            } while (!(d1.getTipo().equals(d2.getTipo())));
            auxiliar = "0";
        }

        try {

            con = ConexionBDD.getConexion();
            String consulta2 = ("UPDATE Digimon SET NomEvoluviona = '" + d2.getNomDigimon() + "' WHERE NomDigimon = '" + d1.getNomDigimon() + "';");
            PreparedStatement ps = con.prepareStatement(consulta2);
            ps.executeUpdate(consulta2);

            if (!d1.getNomDigimon().equals("0")) {
                System.out.println("\nDigievolución establecida con éxito.");
            } else {
                System.out.println("\nVolviendo al menú...");
            }

        } catch (Exception ex) {

            System.err.println(ex.getMessage());

        } finally {

            ConexionBDD.desconectar(con);

        }

    }

    public Digimon pideDigimon(String frase, HashMap<String, Digimon> nombreDigi, int nivel, Tipo tipos) {

        String nomElegido = "";
        Digimon digi = null;

        switch (nivel) {
            case 1:
            case 2:

                do {
                    nomElegido = SLeer1.datoString(frase);
                    digi = nombreDigi.get(nomElegido);

                    if (digi == null) {

                        System.err.println("\tEl nombre elegido no existe.");

                    } else if (!(digi.getNivel().equals(intToNivel(nivel + 1)))) {

                        System.err.println("\tEl digimon debe de ser UN único nivel superior.");

                    }

                } while ((digi == null) || (!(digi.getNivel().equals(intToNivel(nivel + 1)))));

                break;

            case 3:

                do {
                    nomElegido = SLeer1.datoString(frase);

                    if (nomElegido.equals("0")) {
                        Digimon d = new Digimon("0", 0, 0, tipos, intToNivel(nivel + 1));
                        digi = d;
                    } else {

                        digi = nombreDigi.get(nomElegido);
                    }

                    if (digi == null) {

                        System.err.println("\tEl nombre elegido no existe.");

                    } else if (digi.getNivel().equals(intToNivel(nivel))) {

                        System.err.println("\tEl digimon NO debe ser nivel 3.");

                    }

                } while ((digi == null) || digi.getNivel().equals(intToNivel(nivel)));
                break;
        }

        return digi;
    }

    public boolean existeDigimon(String nombre) {

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

            System.err.println(ex.getMessage());
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

                System.err.println(ex.getMessage());

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

    public void modificarDigimon() {
        Connection con = null;

        try {

            SLeer1.limpiar();
            con = ConexionBDD.getConexion();
            int opcion = 0;
            verDigimons();

            nomDigimon = SLeer1.datoString("Escoja el digimon a modificar: ");
            if (!existeDigimon(nomDigimon)) {
                System.err.println("El Digimon no esta en la tabla. ");

            } else {

                do {

                    System.out.println("\n");
                    System.out.println("1.Defensa.");
                    System.out.println("2.Ataque.");
                    System.out.println("3.Elegir Digimon.");
                    System.out.println("4.Salir de la modificacion");

                    opcion = SLeer1.datoInt("Escoja el atributo a modificar. ");

                    switch (opcion) {

                        case 1:
                            int defensa = SLeer1.datoInt("Actualice su defensa: ");
                            SLeer1.limpiar();
                            String consulta = "UPDATE Digimon SET defensa= ' " + defensa + "' WHERE nomDigimon='" + nomDigimon + "'";
                            PreparedStatement ps = con.prepareStatement(consulta);
                            ps.executeUpdate(consulta);
                            break;

                        case 2:
                            int ataque = SLeer1.datoInt("Actualice su ataque: ");
                            SLeer1.limpiar();
                            String consulta1 = "UPDATE Digimon SET ataque = ' " + ataque + "' WHERE nomDigimon='" + nomDigimon + "'";
                            PreparedStatement ps1 = con.prepareStatement(consulta1);
                            ps1.executeUpdate(consulta1);
                            break;

                        case 3:
                            modificarDigimon();
                            break;

                        case 4:
                            break;

                        default:
                            System.err.println("Escoja una opcion valida: ");
                            opcion = 1;
                            break;
                    }
                } while (opcion >= 1 && opcion <= 3);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
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

    public Nivel intToNivel(int n) {
        Nivel nivel = Nivel.UNO;
        switch (n) {
            case 1:
                nivel = Nivel.UNO;
                break;
            case 2:
                nivel = Nivel.DOS;
                break;
            case 3:
                nivel = Nivel.TRES;
                break;
        }
        return nivel;
    }

    public String tipoToString(Tipo t) {
        String tipo = "";
        switch (t) {
            case VACUNA:
                tipo = "vacuna";
                break;
            case VIRUS:
                tipo = "virus";
                break;
            case ANIMAL:
                tipo = "animal";
                break;
            case PLANTA:
                tipo = "planta";
                break;
            case ELEMENTAL:
                tipo = "elemental";
                break;
        }
        return tipo;
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
