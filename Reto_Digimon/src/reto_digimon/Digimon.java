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
public class Digimon {

    private String nomDigimon;
    private int defensa;
    private int ataque;
    private Tipo tipo;
    private Nivel nivel;
    private String nomEvoluciona;
    
    public Digimon(String nombre, int defen, int ataq, Tipo tip, Nivel niv, String nomev){
    
        nomDigimon = nombre;
        defensa = defen;
        ataque = ataq;
        tipo = tip;
        nivel = niv;
        nomEvoluciona = nomev;
    }
    
    public Digimon(){}
    
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

    public Nivel getNivel() {
        return nivel;
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
