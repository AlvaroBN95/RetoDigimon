/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import static reto_digimon.ConexionBDD.*;

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
    
    public static void  main (String args[]){
       
       try{
           
        ConexionBBDD();
        
       } catch (Exception ex){
           
           System.err.println(ex);

       } finally {
       
           try {

                desconectar();

            } catch (Exception ex) {

                System.err.println(ex);
            }
       }
   }
}
