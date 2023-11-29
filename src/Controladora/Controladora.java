package Controladora;

import Clases.Empleado;
import Clases.Seccion;
import Clases.Sucursal;
import Clases.Usuario;
import Nodos.Nodo;
import Nodos.NodoEmpleado;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
    static ArrayList<Empleado> listaLibres; // es la lista que tiene a los empleados sin posicion
    static NodoEmpleado nodoRaiz; // es un falso arbol para tener la estructura armada

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

    // esta funcion parte del nodoraiz y recorre para poner al empleado como sub de
    // pJefe
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

    // esto es para elegir al empleado y de quien es sub
    private static void ingresarEmpleadoArbol(Seccion laSec) {
        Empleado elEmp = null;
        try {
            int contador = 0;
            for (Empleado empleado : listaLibres) {
                if (empleado.getCargo() != "Jefe") {
                    contador++;
                    System.out.println(empleado.toString());
                }
            }
            if (contador == 0) {
                System.out.println("No hay Empleados libres!");
                return;
            }
            System.out.print("Elija al Empleado: ");
            elEmp = buscarEmpleadoLibre(scanNum.nextInt());
            if (elEmp == null) {
                System.out.println("No existe ese empleado!");
                return;
            }
            if (elEmp.getCargo() == "Jefe") {
                System.out.println("Un jefe no puede ser subordinado");
                return;
            }
            elEmp.setSeccion(laSec);
            modificarEmpleado(elEmp); // modifico para que empleado tenga la seccion en la lista
            listaLibres.remove(elEmp); // remuevo de libres porque ya esta asignado
        } catch (InputMismatchException ex) {
            clearConsole();
            System.out.println("INGRESE EL ID!!");
            return;
        }
        ArrayList<NodoEmpleado> listaNodosLibres = ArbolUtils.nodosLibres(nodoRaiz);
        // listaNodosLibres son los nodos del arbol que tiene izq y/o der libre
        String idsJefes;
        int pIdJefe;
        do {
            clearConsole();
            idsJefes = "";
            for (NodoEmpleado nodo : listaNodosLibres) {
                idsJefes += nodo.getDato().getId() + ",";
                System.out.println(nodo.getDato().toString());
            }
            int inputJefe = 0;
            try {
                System.out.print("Quien es el Jefe?: ");
                inputJefe = scanNum.nextInt();
                if (!idsJefes.contains(String.valueOf(inputJefe))) {
                    System.out.println("Ese jefe no existe!");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
            pIdJefe = inputJefe;
        } while (!idsJefes.contains(String.valueOf(pIdJefe)));
        clearConsole();
        asignarNodo(nodoRaiz, elEmp, pIdJefe);
    }

    public static void ingresarArbolEmpleados() {
        if (sucursales.size() == 0) {
            System.out.println("Ingrese una sucursal primero!");
            return;
        }

        cargarEmpleadosLibres();
        if (listaLibres.size() == 0) {
            System.out.println("No hay Empleados Libres!!");
            return;
        }

        System.out.print("Nombre de Seccion: ");
        String nombre = scan.nextLine();

        Sucursal suc = null;
        listarSucursal();
        do {
            try {
                System.out.print("Sucursal: ");
                int sucInput = scanNum.nextInt();
                suc = buscarSucursal(sucInput);
                if (suc == null) {
                    clearConsole();
                    System.out.println("Sucursal no Válida!!");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (suc == null);
        Seccion laSeccion = new Seccion(suc, nombre);
        clearConsole();

        Empleado asignado = null;
        int contador = 0;
        do {
            for (Empleado emp : listaLibres) {
                if (emp.getCargo().equals("Jefe")) {
                    contador++;
                    System.out.println(emp.toString());
                }
            }
            if (contador == 0) {
                System.out.println("No hay JEFES!");
                return;
            }
            try {
                System.out.print("Elija el jefe: ");
                asignado = buscarEmpleadoLibre(scanNum.nextInt());
                if (asignado == null) {
                    System.out.println("No existe ese JEFE");
                    clearConsole();
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (asignado == null);
        asignado.setSeccion(laSeccion);
        modificarEmpleado(asignado);
        listaLibres.remove(asignado);
        nodoRaiz = crearNodoEmp(asignado); // creo el nodobase del arbol
        clearConsole();

        int opcion = 0;
        do {
            try {
                System.out.println("1- Ingresar Empleado al Arbol");
                System.out.println("2- Salir");
                opcion = scanNum.nextInt();
                clearConsole();
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
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE UN NUMERO!!");
                return;
            }
        } while (opcion != 2);
        clearConsole();
        laSeccion.getArbol().ingresarPos(nodoRaiz, 0, true);
        // la funcion de arriba ingresa el falso arbol al arbol de la seccion dandole
        // las posiciones que correspondan
        secciones.add(laSeccion);
    }
    // endregion ARBOL EMPLEADOS

    // region ARBOL SUCURSAL
    static ArrayList<Seccion> listaSecLibres; // lista de secciones que no estan asignadas a sucursales
    static Nodo nodoSecRaiz;

    private static void cargarSeccionesLibres(int pId) {
        listaSecLibres = new ArrayList<>();
        for (Seccion sec : secciones) {
            if (sec.getPosicion() == 0 && sec.getSucursal().getId() == pId) {
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

        Seccion sec = null;
        do {
            clearConsole();
            try {
                int contador=0;
                for (Seccion seccion : listaSecLibres) {
                    System.out.println(seccion.toString());
                    contador++;
                }
                if(contador==0){
                    clearConsole();
                    System.out.println("NO HAY SECCIONES LIBRES!");
                    return;
                }
               
                System.out.println("Elija la Seccion: ");
                sec = buscarSeccionLibre(scanNum.nextInt());
                if (sec == null) {
                    System.out.println("No existe esa sección!");
                } else {
                    listaSecLibres.remove(sec);
                }
                clearConsole();
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (sec == null);

        ArrayList<Nodo> listaNodosLibres = ArbolUtils.nodosSecLibres(nodoSecRaiz);

        String idsJefes;
        int pIdJefe;
        do {
            clearConsole();
            idsJefes = "";
            for (Nodo nodo : listaNodosLibres) {
                idsJefes += nodo.getDato().getId() + ",";
                System.out.println(nodo.getDato().toString());
            }
            int inputJefe = 0;
            try {
                System.out.print("Quien es el Jefe?: ");
                inputJefe = scanNum.nextInt();
                if (!idsJefes.contains(String.valueOf(inputJefe))) {
                    System.out.println("Ese jefe no existe!");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
            pIdJefe = inputJefe;
        } while (!idsJefes.contains(String.valueOf(pIdJefe)));
        clearConsole();
        // for (Nodo nodo : listaNodosLibres) {
        // System.out.println(nodo.getDato().toString());
        // }

        // System.out.println("Quien es el Jefe?");
        asignarNodoSec(nodoSecRaiz, sec, pIdJefe);
    }

    public static void ingresarArbolSucursal() {
        if (sucursales.size() == 0) {
            System.out.println("NO HAY SUCURSALES!");
            return;
        }
        Sucursal suc = null;
        do {
            try {
                listarSucursal();
                System.out.print("Sucursal: ");
                suc = buscarSucursal(scanNum.nextInt());
                if (suc == null) {
                    clearConsole();
                    System.out.println("Sucursal no Válida!!");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (suc == null);
        clearConsole();
        cargarSeccionesLibres(suc.getId());
        if (listaSecLibres.size() == 0) {
            System.out.println("NO HAY SECCIONES LIBRES!");
            return;
        }

        Seccion asignada = null;
        do {
            clearConsole();
            try {
                for (Seccion sec : listaSecLibres) {
                    System.out.println(sec.toString());
                }
                System.out.print("Elija el jefe: ");
                asignada = buscarSeccionLibre(scanNum.nextInt());
                if (asignada == null) {
                    clearConsole();
                    System.out.println("No existe ese JEFE");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (asignada == null);

        listaSecLibres.remove(asignada);
        nodoSecRaiz = crearNodoSec(asignada);

        clearConsole();
        int opcion = 0;
        do {
            try {
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
            } catch (InputMismatchException ex) {
                System.out.println("INGRESE UN NUMERO!!");
                return;
            }
        } while (opcion != 2);
        clearConsole();
        suc.getArbol().ingresarPos(nodoSecRaiz, 0, true);
        modificarSucursal(suc);
    }
    // endregion

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
        System.out.println("----- LISTA SUCURSALES -----");
        int contador = 0;
        for (Sucursal suc : sucursales) {
            System.out.println(suc.toString());
            contador++;
        }
        if (contador == 0)
            System.out.println("NO HAY SUCURSALES!!");
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
        if (sucursales.size() == 0) {
            System.out.println("NO hay Sucursales!");
            return;
        }
        try {
            listarSucursal();
            System.out.print("Elige Sucursal: ");
            Sucursal laSuc = buscarSucursal(scanNum.nextInt());
            clearConsole();
            if (laSuc == null) {
                System.out.println("No existe esa Sucursal!!");
            } else {
                laSuc.getArbol().imprimirMayorMenor();
            }
        } catch (InputMismatchException ex) {
            clearConsole();
            System.out.println("INGRESE EL ID!!");
            return;
        }
    }
    // endregion

    // region SECCIONES
    public static void listarSeccionesPre() {
        for (Seccion sec : secciones) {
            sec.getArbol().imprimirPre();
        }
    }

    public static void listarSecciones() {
        System.out.println("----- LISTA SECCIONES -----");
        int contador = 0;
        for (Seccion sec : secciones) {
            contador++;
            System.out.println(sec.toString());
        }
        if (contador == 0) {
            System.out.println("NO HAY SECCIONES!!");
        }
    }

    public static Seccion buscarSeccionLibre(int pId) {
        for (Seccion sec : listaSecLibres) {
            if (sec.getId() == pId) {
                return sec;
            }
        }
        return null;
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

    public static Empleado buscarEmpleadoLibre(int pId) {
        for (Empleado empleado : listaLibres) {
            if (empleado.getId() == pId) {
                return empleado;
            }
        }
        return null;
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
        System.out.println("----- LISTA EMPLEADOS -----");
        int contador = 0;
        for (Empleado empleado : listaEmpleados) {
            contador++;
            System.out.println(empleado.toString());
        }
        if (contador == 0) {
            System.out.println("NO HAY EMPLEADOS!");
        }
    }

    public static void listarSeccionMayorMenor() {
        if (secciones.size() == 0) {
            System.out.println("No hay Secciones!");
            return;
        }
        listarSecciones();
        try {
            System.out.print("Elige Seccion: ");
            Seccion laSec = buscarSeccion(scanNum.nextInt());
            clearConsole();
            if (laSec == null) {
                System.out.println("No existe esa Seccion!!");
            } else {
                laSec.getArbol().imprimirMayorMenor();
            }
        } catch (InputMismatchException ex) {
            clearConsole();
            System.out.println("INGRESE EL ID!!");
            return;
        }

    }

    public static void datosEmpSub() {
        if (listaEmpleados.size() == 0) {
            System.out.println("No hay Empleados!");
            return;
        }
        Empleado unEmp = null;
        do {
            try {
                clearConsole();
                listarEmpleado();
                System.out.print("Ingrese Empleado: ");
                unEmp = buscarEmpleado(scanNum.nextInt());
                if (unEmp == null) {
                    System.out.println("No existe el Empleado");
                }
            } catch (InputMismatchException ex) {
                clearConsole();
                System.out.println("INGRESE EL ID!!");
                return;
            }
        } while (unEmp == null);

        Seccion unaSec = null;
        clearConsole();
        if (unEmp.getSeccion() == null) {
            System.out.println("El Empleado no esta asignado!");
            return;
        }
        unaSec = buscarSeccion(unEmp.getSeccion().getId());
        if (unaSec == null) {
            System.out.println("El empleado no esta asignado a ninguna Seccion!");
        } else {
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
