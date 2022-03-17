/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reto_digimon;

import java.util.Scanner;

/**
 *
 * @author Alvaro
 */
public class Menu {

    public static void main(String[] args) {

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion;

        System.out.println("1, Iniciar sesion");
        System.out.println("2, Dar de alta un usuario");
        System.out.println("3, Crear un Digimon");
        System.out.println("4, Definir Digievolucion");

        System.out.println("Introduce tu eleccion");
        opcion = sn.nextInt();

        switch (opcion) {

            case 1:
                Digimon();
                break;

            default:
                System.out.print("repetir");
                break;
        }

    }

}
