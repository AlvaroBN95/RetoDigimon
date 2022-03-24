/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static reto_digimon.ConexionBDD.*;

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

    public void verEquipo(String NombreUsuario) {
        try {

            Connection con = ConexionBDD.getConexion();
            String consulta = ("SELECT ti.NomDigimon, di.Defensa, di.Ataque, di.Tipo, di.Nivel, di.NomEvoluviona FROM Tiene ti JOIN Digimon di ON ti.Nomdigimon=di.Nomdigimon WHERE Equipo =\"Si\";");
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

            ConexionBDD.desconectar();

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

    public void asignarDigimons() {

    }

}
