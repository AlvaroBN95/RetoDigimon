/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

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
    
    public Usuario(String nombre, String contrasenya, int p_jugadas, int p_ganadas, int tokens) {
        nombreUsu = nombre;
        pass = contrasenya;
        pJugadas = p_jugadas;
        partidasGan = p_ganadas;
        cantTokens = tokens;
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

    
}
