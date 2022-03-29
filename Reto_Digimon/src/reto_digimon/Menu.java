package reto_digimon;

import Sleer1.SLeer1;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private static String nombreUsu;

    public static boolean login() {

        Usuario u1 = new Usuario();
        boolean logeado = false;

        String usuario;
        String contrasena;

        SLeer1.limpiar();
        usuario = SLeer1.datoString("Introduzca su usuario: ");
        contrasena = SLeer1.datoString("Introduzca su contraseña: ");

        if (!(u1.existeRegistrado(usuario, contrasena))) {

            System.out.println("\n\tUsuario y/o contraseña incorrectos.");
            System.out.println("");

        } else {

            nombreUsu = usuario;
            logeado = true;
        }

        return logeado;
    }

    public static void restablecerBD() {

        Connection con = null;

        try {

            con = ConexionBDD.getConexion();
            String consulta1 = "DELETE FROM Tiene";
            String consulta2 = "DELETE FROM Usuario";
            String consulta3 = "DELETE FROM Digimon";
            PreparedStatement ps = con.prepareStatement(consulta1);
            ps.execute();

            ps = con.prepareStatement(consulta2);
            ps.execute();

            ps = con.prepareStatement(consulta3);
            ps.execute();

            System.out.println("\nSe han borrado los datos exitosamente.");

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            ConexionBDD.desconectar(con);
        }

    }

    public static void menuGeneral() {

        Usuario u1 = new Usuario();
        int opcion;

        do {
            System.out.println("");
            System.out.println("|----------MENÚ GENERAL----------|");
            System.out.println("|1.Iniciar como administrador.   |");
            System.out.println("|2.Iniciar como usuario.         |");
            System.out.println("|3.Crear Usuario.                |");
            System.out.println("|4.Salir del Digijuego.          |");
            System.out.println("|--------------------------------|");
            opcion = SLeer1.datoInt("Elige una opcion: ");

            switch (opcion) {

                case 1:
                    if (login()) {
                        administrador();
                    }
                    break;

                case 2:
                    if (login()) {
                        usuarioComun();
                    }
                    break;

                case 3:
                    u1.creaUsuario();
                    break;

                case 4:
                    System.out.println("\nSaliendo del DigiJuego");
                    break;

                default:
                    System.err.println("\tEscoja una opcion valida");
                    System.out.println("");
                    opcion = 1;
                    break;
            }

        } while (opcion >= 1 && opcion <= 3);
    }

    public static void usuarioComun() {
        Usuario u = new Usuario();
        Tiene t1 = new Tiene();
        int opcion;

        do {
            System.out.println("\n|----------MENÚ USUARIO-----------|");
            System.out.println("|1.Jugar partida.                 |");
            System.out.println("|2.Ver todos mis Digimons         |");
            System.out.println("|3.Ver Digiequipo.                |");
            System.out.println("|4.Administrar Digiequipo.        |");
            System.out.println("|5.Mira tu perfil de usuario.     |");
            System.out.println("|6.Ver todos los usuarios creados.|");
            System.out.println("|7.Cerrar sesion.                 |");
            System.out.println("|---------------------------------|");
            opcion = SLeer1.datoInt("Elige tu opcion: ");

            switch (opcion) {

                case 1:
                    System.out.println("Jugando partida contra user48395_45... VICTORIA!!");
                    t1.asignarDigimon(nombreUsu);
                    break;

                case 2:
                    t1.verDigimonsUsuario(nombreUsu);
                    break;

                case 3:
                    t1.verEquipo(nombreUsu);
                    break;

                case 4:
                    t1.cambiarEquipo(nombreUsu);
                    break;
                
                case 5:
                    u.verUsuario(nombreUsu);
                    break;
                    
                case 6:
                    u.verUsuarios();
                    break;
                    
                case 7:
                    break;

                default:
                    System.err.print("\n\tEscoge una opcion válida.\n");
                    opcion = 1;
                    break;

            }
        } while (opcion >= 1 && opcion <= 6);

    }

    public static void administrador() {

        int opcion;
        Digimon d = new Digimon();

        do {
            System.out.println("\n|-------MENÚ ADMINISTRADOR--------|");
            System.out.println("|1.Crear un Digimon.              |");
            System.out.println("|2.Ver los Digimon.               |");
            System.out.println("|3.Establecer digievolucion.      |");
            System.out.println("|4.Modificar Digimon.             |");
            System.out.println("|5.Restablecer Base de Datos.     |");
            System.out.println("|6.Cerrar sesion.                 |");
            System.out.println("|---------------------------------|");
            opcion = SLeer1.datoInt("Elige tu opcion: ");
            switch (opcion) {

                case 1:
                    d.creaDigimon();
                    break;
                case 2:
                    d.verDigimons();
                    break;

                case 3:
                    d.establecerEvolucion();
                    break;

                case 4:
                    d.modificarDigimon();
                    break;
                    
                case 5:
                    restablecerBD();
                    break;
                    
                case 6:
                    break;

                default:
                    System.err.print("\n\tEscoge una opcion valida.\n");
                    opcion = 1;
                    break;
            }
        } while (opcion >= 1 && opcion <= 5);

    }

    public static void main(String[] args) {

        menuGeneral();

    }

}
