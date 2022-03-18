
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import Sleer1.SLeer1;
import java.util.logging.Logger;

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

    public static void loginAd() {
        String administrador="";
        String contrasena="";
        
        
        administrador=SLeer1.datoString("Introduzca su usuario: ");
     
      
        contrasena=SLeer1.datoString("Introduzca su contraseña: ");

    }

    public static void loginUsu() {

    }

    public static void salir() {

    }

    public static void creaUsuario() {

    }

    public static void verEquipo() {

    }

    public static void partida() {

    }

    public static void buscaUsuario() {

    }

    public static void crearDigimon() {

    }

    public static void verDigimon() {

    }

    public static void menuGeneral() {

        do {
            System.out.println("1.Iniciar como administrador: ");
            System.out.println("2.Iniciar como usuario: ");
            System.out.println("3.Salir del programa: ");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");

            switch (eleccion) {

                case 1:
                    loginAd();
                    administrador();

                    break;

                case 2:
                    loginUsu();
                    usuarioComun();

                    break;

                case 3:
                    salir();
                    break;

                default:
                    System.out.println("Escoja una opcion valida");
            }

        } while (eleccion < 1 || eleccion > 5);
    }

    public static void usuarioComun() {

        do {
            System.out.println("1.Crear usuario: ");
            System.out.println("2.Ver equipo: ");
            System.out.println("3.Iniciar partida: ");
            System.out.println("4.Salir del programa: ");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");

            switch (eleccion) {

                case 1:
                    creaUsuario();
                    menuGeneral();
                    break;

                case 2:
                    verEquipo();
                    break;

                case 3:
                    partida();
                    break;

                case 4:
                    salir();
                    break;

                default:
                    System.out.print("Escoge una opcion valida. ");
                    break;

            }
        } while (eleccion < 1 || eleccion > 5);

    }

    public static void administrador() {

        do {
            System.out.println("1.Busca un usuario: ");
            System.out.println("2.Crea un Digimon: ");
            System.out.println("3.Busca un Digimon: ");
            System.out.println("4.Salir del programa: ");
            eleccion = SLeer1.datoInt("Elige tu opcion: ");
            switch (eleccion) {

                case 1:
                    buscaUsuario();
                    break;

                case 2:
                    crearDigimon();
                    break;

                case 3:
                    verDigimon();
                    break;

                case 4:
                    salir();
                    break;

                default:
                    System.out.print("Escoge una opcion valida. ");
                    break;
            }
        } while (eleccion < 1 || eleccion > 5);

    }

    public static void main(String[] args) {
        
        menuGeneral();

    }

}
