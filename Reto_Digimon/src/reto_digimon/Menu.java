package reto_digimon;

import Sleer1.SLeer1;
import java.sql.SQLException;
import reto_digimon.ConexionBDD.*;
import reto_digimon.Tiene;
import reto_digimon.Usuario;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alvaro
 */
public class Menu {

    private static int eleccion;

    public static void login() {
        String usuario = "";
        String contrasena = "";

        SLeer1.limpiar();
        usuario = SLeer1.datoString("Introduzca su usuario: ");

        contrasena = SLeer1.datoString("Introduzca su contraseña: ");

    }

  

    public static void partida() {

    }

    

    public static void crearDigimon() {

           
      

    }

    public static void verDigimon() {
        try {
            //("SELECT * FROM Digimon");
        } catch (Exception ex) {
            System.err.println("\tError fatal");

        }
    }

    public static void menuGeneral() {

        Usuario u1 = new Usuario();
        
        do {
            System.out.println("\n----MENÚ GENERAL----");
            System.out.println("1.Iniciar como administrador: ");
            System.out.println("2.Iniciar como usuario: ");
            System.out.println("3.Crear Usuario: ");
            System.out.println("4.Salir del Digijuego");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");

            switch (eleccion) {

                case 1:
                    login();//oel
                    administrador();
                    break;

                case 2:
                    login();//joel
                    usuarioComun();

                    break;

                case 3:
                    u1.creaUsuario();//usuario
                    menuGeneral();
                    break;
                    
                case 4:
                    System.out.println("Saliendo del DigiJuego");
                    break;

                default:
                    System.err.println("\n\tEscoja una opcion valida\n");
            }

        } while (eleccion < 1 || eleccion > 4);
    }

    public static void usuarioComun() {

        
        Tiene t1 = new Tiene();
        
        do {
            System.out.println("\n----MENÚ USUARIO----");
            System.out.println("1.Ver equipo.");
            System.out.println("2.Iniciar partida.");
            System.out.println("3.Cerrar sesión.");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");
            
            switch (eleccion) {

                case 1:
                    t1.verEquipo("rapa");//tiene, hacer cambiar equipo y controlar que siempre sean 3Poner nombre del susuario
                    usuarioComun();
                    break;

                case 2:
                    partida();//usuario,
                    usuarioComun();
                    break;

                case 3:
                    menuGeneral();
                    break;

                default:
                    System.err.print("\n\tEscoge una opcion válida.\n");
                    break;

            }
        } while (eleccion < 1 || eleccion > 3);

    }

    public static void administrador() {

        do {
            System.out.println("\n----MENÚ ADMINISTRADOR----");
            System.out.println("\n\n1.Busca un usuario: ");
            System.out.println("2.Crea un Digimon: ");
            System.out.println("3.Buscar Digimons: ");
            System.out.println("4.Cerrar sesion: ");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");
            switch (eleccion) {

         
                case 1:
                    crearDigimon();//digimon
                    break;

                case 2:

                    verDigimon();//digimon, select * 
                    break;
                    
                case 4:
                      // DefinirDigievolucion(); digimon
                       break;

                case 3:
                   
                    break;

                default:
                    System.err.print("\n\tEscoge una opcion valida.\n");
                    break;
            }
        } while (eleccion < 1 || eleccion > 3);

    }

    public static void main(String[] args) {

        menuGeneral();

    }

}
