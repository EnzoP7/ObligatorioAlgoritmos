package Controladora;

import Clases.Empleado;
import Clases.Seccion;
import Clases.Sucursal;
import Clases.Usuario;
import Nodos.Nodo;
import Nodos.NodoEmpleado;

import java.util.ArrayList;
import java.util.Scanner;

import Arboles.Arbol;
import Arboles.ArbolUtils;

public class Controladora {

    // region Instancias
    static Usuario usuario = new Usuario();

    // endregion

    // region LISTAS
    public static void datosPrueba() {
        Empleado unEmp = new Empleado("Lucas", "Chambon", 52901010, 92415687, "Jefe", "22/12/22", 20000);
        listaEmpleados.add(unEmp);
        Empleado otroEmp = new Empleado("Enzo", "Pontet", 52345678, 99333444, "SUB", "22/12/23", 10000);
        listaEmpleados.add(otroEmp);
        Empleado otroEmp2 = new Empleado("Martin", "Quevedo", 52344478, 99333222, "SUB", "22/12/24", 11111);
        listaEmpleados.add(otroEmp2);
        Empleado otroEmp3 = new Empleado("Felipe", "Castellano", 52344478, 99333222, "Jefe", "22/12/24", 11111);
        listaEmpleados.add(otroEmp3);
        Empleado otroEmp4 = new Empleado("Nahuel", "Pages", 52344478, 99333222, "SUB", "22/12/24", 11111);
        listaEmpleados.add(otroEmp4);
        Empleado otroEmp5 = new Empleado("Santiago", "Andrade", 52344455, 99333111, "SUB", "22/12/24", 11111);
        listaEmpleados.add(otroEmp5);
    }

    public static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
    public static ArrayList<Sucursal> sucursales = new ArrayList<Sucursal>();
    public static ArrayList<Seccion> secciones = new ArrayList<Seccion>();

    // endregion
    static Scanner scanNum = new Scanner(System.in);
    static Scanner scan = new Scanner(System.in);
    // #region Login

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

    // #endregion

    // region ARBOL EMPLEADOS
    static ArrayList<Empleado> listaLibres;
    static NodoEmpleado nodoRaiz;

    private static void cargarEmpleadosLibres() {
        listaLibres = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getPosicion() == 0) {
                listaLibres.add(empleado);
            }
        }
    }

    private static NodoEmpleado crearNodoEmp(Empleado elEmp) {
        NodoEmpleado nuevoNodo = new NodoEmpleado(elEmp, null, null);
        return nuevoNodo;
    }

    private static void asignarNodo(NodoEmpleado nodo, Empleado elEmp, int pJefe) {
        if (nodo != null) {
            if (nodo.getDato().getId() == pJefe) {
                if (nodo.getIzq() == null) {
                    nodo.setIzq(crearNodoEmp(elEmp));
                } else {
                    nodo.setDer(crearNodoEmp(elEmp));
                }
                return;
            }
            asignarNodo(nodo.getIzq(), elEmp, pJefe);
            asignarNodo(nodo.getDer(), elEmp, pJefe);
        }
    }

    private static void ingresarEmpleadoArbol(Seccion laSec) {
        for (Empleado empleado : listaLibres) {
            if (empleado.getCargo() != "Jefe") {
                System.out.println(empleado.toString());
            }
        }
        System.out.println("Elija al Empleado");
        Empleado elEmp = buscarEmpleado(scanNum.nextInt());
        elEmp.setSeccion(laSec);
        modificarEmpleado(elEmp);
        listaLibres.remove(elEmp);
        ArrayList<NodoEmpleado> listaNodosLibres = ArbolUtils.nodosLibres(nodoRaiz);

        for (NodoEmpleado nodo : listaNodosLibres) {
            System.out.println(nodo.getDato().toString());
        }

        System.out.println("Quien es el Jefe?");
        asignarNodo(nodoRaiz, elEmp, scanNum.nextInt());
    }

    public static void ingresarArbolEmpleados() {
        cargarEmpleadosLibres();

        System.out.print("Nombre de Seccion: ");
        String nombre = scan.nextLine();

        Sucursal suc;
        listarSucursal();
        do {
            System.out.print("Sucursal: ");
            suc = buscarSucursal(scanNum.nextInt());
            if (suc == null) {
                clearConsole();
                System.out.println("Sucursal no Válida!!");
            }
        } while (suc == null);
        Seccion laSeccion = new Seccion(suc, nombre);

        Empleado asignado = null;
        do {
            for (Empleado emp : listaLibres) {
                if (emp.getCargo().equals("Jefe")) {
                    System.out.println(emp.toString());
                }
            }
            System.out.println("Elija el jefe: ");
            asignado = buscarEmpleado(scanNum.nextInt());
            if (asignado == null) {
                System.out.println("No existe ese JEFE");
                clearConsole();
            }
        } while (asignado == null);
        asignado.setSeccion(laSeccion);
        modificarEmpleado(asignado);
        listaLibres.remove(asignado);
        nodoRaiz = crearNodoEmp(asignado);

        int opcion = 0;
        do {
            System.out.println("1- Ingresar Empleado al Arbol");
            System.out.println("2- Salir");
            opcion = scanNum.nextInt();
            switch (opcion) {
                case 1:
                    ingresarEmpleadoArbol(laSeccion);
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opcion Incorrecta!");
                    break;
            }
        } while (opcion != 2);
        clearConsole();
        laSeccion.getArbol().ingresarPos(nodoRaiz, 0, true);
        secciones.add(laSeccion);
    }
    // endregion ARBOL EMPLEADOS

    //region ARBOL SUCURSAL
    static ArrayList<Seccion> listaSecLibres;
    static Nodo nodoSecRaiz;

    private static void cargarSeccionesLibres(int pId) {
        listaSecLibres = new ArrayList<>();
        for (Seccion sec : secciones) {
            if (sec.getPosicion() == 0 && sec.getSucursal().getId()==pId) {
                listaSecLibres.add(sec);
            }
        }
    }

    private static Nodo crearNodoSec(Seccion sec) {
        Nodo nuevoNodo = new Nodo(sec, null, null);
        return nuevoNodo;
    }

    private static void asignarNodoSec(Nodo nodo, Seccion sec, int pJefe) {
        if (nodo != null) {
            if (nodo.getDato().getId() == pJefe) {
                if (nodo.getIzq() == null) {
                    nodo.setIzq(crearNodoSec(sec));
                } else {
                    nodo.setDer(crearNodoSec(sec));
                }
                return;
            }
            asignarNodoSec(nodo.getIzq(), sec, pJefe);
            asignarNodoSec(nodo.getDer(), sec, pJefe);
        }
    }

    private static void ingresarSeccionArbol() {
        for (Seccion sec : listaSecLibres) {
            System.out.println(sec.toString());
        }
        System.out.println("Elija la Seccion: ");
        Seccion sec = buscarSeccion(scanNum.nextInt());
        listaSecLibres.remove(sec);

        ArrayList<Nodo> listaNodosLibres = ArbolUtils.nodosSecLibres(nodoSecRaiz);

        for (Nodo nodo : listaNodosLibres) {
            System.out.println(nodo.getDato().toString());
        }

        System.out.println("Quien es el Jefe?");
        asignarNodoSec(nodoSecRaiz, sec, scanNum.nextInt());
    }

    public static void ingresarArbolSucursal() {
        Sucursal suc;
        listarSucursal();
        do {
            System.out.print("Sucursal: ");
            suc = buscarSucursal(scanNum.nextInt());
            if (suc == null) {
                clearConsole();
                System.out.println("Sucursal no Válida!!");
            }
        } while (suc == null);

        cargarSeccionesLibres(suc.getId());

        Seccion asignada = null;
        do {
            for (Seccion sec : listaSecLibres) {
                System.out.println(sec.toString());
            }
            System.out.println("Elija el jefe: ");
            asignada = buscarSeccion(scanNum.nextInt());
            if (asignada == null) {
                System.out.println("No existe ese JEFE");
                clearConsole();
            }
        } while (asignada == null);

        listaSecLibres.remove(asignada);
        nodoSecRaiz = crearNodoSec(asignada);

        int opcion = 0;
        do {
            System.out.println("1- Ingresar Sección al Arbol");
            System.out.println("2- Salir");
            opcion = scanNum.nextInt();
            switch (opcion) {
                case 1:
                    ingresarSeccionArbol();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opcion Incorrecta!");
                    break;
            }
        } while (opcion != 2);
        clearConsole();
        suc.getArbol().ingresarPos(nodoSecRaiz, 0, true);
        modificarSucursal(suc);
    }
    //endregion

    // region Sucursales
    public static boolean altaSucursal() {
        Sucursal unaSuc = Sucursal.altaSucursal();
        if (unaSuc != null) {
            sucursales.add(unaSuc);
            return true;
        } else {
            return false;
        }
    }

    public static void modificarSucursal(Sucursal pSuc) {
        for (Sucursal suc : sucursales) {
            if (suc.getId() == pSuc.getId()) {
                suc = pSuc;
            }
        }
    }

    public static void listarSucursal() {
        for (Sucursal suc : sucursales) {
            System.out.println(suc.toString());
        }
    }

    public static Sucursal buscarSucursal(int pId) {
        for (Sucursal suc : sucursales) {
            if (suc.getId() == pId) {
                return suc;
            }
        }
        return null;
    }

    public static void listarSucursalMayorMenor() {
        listarSucursal();
        System.out.print("Elige Sucursal: ");
        Sucursal laSuc=buscarSucursal(scanNum.nextInt());
        if(laSuc==null){
            System.out.println("No existe esa Sucursal!!");
        }

        laSuc.getArbol().imprimirMayorMenor();
    }
    // endregion

    // region SECCIONES
    public static void listarSeccionesPre() {
        for (Seccion sec : secciones) {
            sec.getArbol().imprimirPre();
        }
    }

    public static void listarSecciones() {
        for (Seccion sec : secciones) {
            System.out.println(sec.toString());
        }
    }

    public static Seccion buscarSeccion(int pId) {
        for (Seccion sec : secciones) {
            if (sec.getId() == pId) {
                return sec;
            }
        }
        return null;
    }
    // endregion

    // region Empleados
    public static boolean altaEmpleado() {
        Empleado unEmpleado = Empleado.altaEmpleado();
        if (unEmpleado != null) {
            listaEmpleados.add(unEmpleado);
            return true;
        } else {
            return false;
        }
    }

    public static void modificarEmpleado(Empleado unEmp) {
        for (Empleado emp : listaEmpleados) {
            if (emp.getId() == unEmp.getId()) {
                emp = unEmp;
            }
        }
    }

    public static Empleado buscarEmpleado(int pId) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getId() == pId) {
                return empleado;
            }
        }
        return null;
    }

    public static void listarEmpleado() {
        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado.toString());
        }
    }

    public static void listarSeccionMayorMenor() {
        listarSecciones();
        System.out.print("Elige Seccion: ");
        Seccion laSec=buscarSeccion(scanNum.nextInt());
        if(laSec==null){
            System.out.println("No existe esa Seccion!!");
        }

        laSec.getArbol().imprimirMayorMenor();
    }

    public static void datosEmpSub() {
        listarEmpleado();

        Empleado unEmp = null;
        do {
            System.out.print("Ingrese Empleado: ");
            unEmp = buscarEmpleado(scanNum.nextInt());
            if (unEmp == null) {
                System.out.println("No existe el Empleado");
            }
        } while (unEmp == null);

        Seccion unaSec = null;
        unaSec = buscarSeccion(unEmp.getSeccion().getId());
        if (unaSec == null) {
            System.out.println("No existe la seccion");
        }else{
            unaSec.getArbol().imprimirPreEmp(unaSec.getArbol().buscarEmp(unEmp.getPosicion()));
        }
    }
    // endregion

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            // Manejo de excepciones
        }
    }

}
