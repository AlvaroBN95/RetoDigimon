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
    
    public void verEquipo() {

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
    
    public static void  main (String args[]){
       
       try{
           
        //ConexionBBDD('t');
        
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
