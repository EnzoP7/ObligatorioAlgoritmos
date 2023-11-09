package Controladora;

import Clases.Empleado;
import Clases.Empresa;
import Clases.Sucursal;
import Clases.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Controladora {

    // region Instancias
    static Usuario usuario = new Usuario();
    public static Empresa empresa;

    // endregion

    // region LISTAS

    public static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
    public static  ArrayList<Sucursal> sucursales = new ArrayList<>();
    public static ArrayList<Empresa> listaEmpresas = new ArrayList<>();


// endregion
static Scanner scan = new Scanner(System.in);
    //#region Login

    public static void login() {
        System.out.println("Ingrese el usuario");
        String nombre = scan.next();
        System.out.println("Ingrese la contraseña");
        String pass = scan.next();

        while (!chequeoLogin(nombre, pass)) {
            System.out.println("Usuario o Contraseña incorrectos, pruebe de nuevo");
            System.out.println("Usuario:");
            nombre = scan.next();
            System.out.println("Contraseña:");
            pass = scan.next();
        }
    }

    public static Boolean chequeoLogin(String nombre, String pass) {
        if (nombre.equals(usuario.getNombre()) && pass.equals(usuario.getContrasenia()))
            return true;
        else
            return false;
    }

    //#endregion


    // region EMPRESA


    public static void altaEmpresa() {
        System.out.println("Ingrese los siguientes datos.");
        System.out.println("Ingrese el nombre");
        String nombre = scan.nextLine();


        empresa = new Empresa(nombre, sucursales);
    }


    // endregion

    // region  Sucursales

    public static void altaSucursal(){

    }

    public  static  void ingresarArbolEmpleados(){

    }



    // endregion

    // region  Empleados

    // endregion





}
