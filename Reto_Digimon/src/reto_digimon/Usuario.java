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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

    public Usuario() {
    }

    public void creaUsuario() {
        Tiene t1 = new Tiene();
        Connection con = null;
        
        SLeer1.limpiar();
        String nombre = SLeer1.datoString("Nombre de usuario (0 para volver): ");

        while (!nombre.equals("0")) {
            
            String pass = "";
            String pass2 = "";

            if (existeUsuario(nombre)) {

                System.err.println("\tEste usuario ya existe.");
                System.out.println();
                nombre = SLeer1.datoString("\nNombre de usuario (0 para salir): ");

            } else {

                do {
                    pass = SLeer1.datoString("Contraseña: ");
                    pass2 = SLeer1.datoString("Introduzca de nuevo la contraseña: ");

                    if (!(pass.equals(pass2))) {
                        System.err.println("\nLas contraseñas no coinciden.");
                        System.out.println();
                    }
                } while (!(pass.equals(pass2)));

                nombreUsu = nombre;
                this.pass = pass;

                try {
                    con = ConexionBDD.getConexion();
                    String consulta = "INSERT INTO Usuario (NombreUsu, Pass, PJugadas, PartidasGan, CantTokens) VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement ps = con.prepareStatement(consulta);

                    ps.setString(1, nombreUsu);
                    ps.setString(2, this.pass);
                    ps.setInt(3, pJugadas);
                    ps.setInt(4, partidasGan);
                    ps.setInt(5, cantTokens);
                    ps.executeUpdate();

                    System.out.println("\nEl usuario " + nombreUsu + " se ha creado existosamente.");
                    nombre = "0";
                    
                    t1.asignarDigimons(nombreUsu);

                } catch (Exception ex) {

                    System.err.println("Se ha producido un error en la creación del usuario." + ex.getMessage());

                } finally {

                    ConexionBDD.desconectar(con);

                }
            } while (!(pass.equals(pass2)));
        }

    }

    public boolean existeUsuario(String nombre) {

        HashSet<String> nombresResult = new HashSet();
        int tamHash = 0;
        boolean existe = false;
        Connection con = null;

        try {
            System.out.println("Hasta aqui bien");
            con = ConexionBDD.getConexion();
            System.out.println("hasta aqui tambien");
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

            System.err.println(ex.getMessage());
        } finally {
            
            ConexionBDD.desconectar(con);

        }

        return existe;
    }
    
    public boolean existeRegistrado(String usuario, String contrasena){
    
        HashMap<String, String> login = new HashMap<>();
        boolean existe = false;
        Connection con = null;
        
        try{
            con = ConexionBDD.getConexion();
            String consulta = ("SELECT NombreUsu, Pass FROM Usuario");
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet output = ps.executeQuery(consulta);
            
            while(output.next()){
                
                String nombreUsuario = output.getString(1);
                String passwd = output.getString(2);
                login.put(nombreUsuario, passwd);
            }
            
        }catch(Exception ex){
            
            System.err.println(ex.getMessage());
        }finally{
            
            ConexionBDD.desconectar(con);
        }
        
        Iterator<String> it = login.keySet().iterator();
        
        while(it.hasNext() && (!existe)){
            
            String clave = it.next();
            String valor = login.get(clave);
            
            if(clave.equals(usuario) && valor.equals(contrasena)){
                
                existe = true;
            }
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

}
