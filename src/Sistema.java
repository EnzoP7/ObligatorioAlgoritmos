import java.util.InputMismatchException;
import java.util.Scanner;

import Controladora.Controladora;

public class Sistema {
    static Scanner inputNumber = new Scanner(System.in);
    static Scanner inputText = new Scanner(System.in);

    public static void main(String[] args) {
        Controladora.datosPrueba();
        int opcion = 0;
        Controladora.clearConsole();
        do {
            System.out.println("-----  MENU  ----- \n");

            System.out.println("1 | Ingresar Sucursal");
            System.out.println("2 | Ingresar Empleado");
            System.out.println("3 | Ingresar Sección");
            System.out.println("4 | Ingresar ARBOL Sucursal");
            System.out.println("5 | Listar Empleados");
            System.out.println("6 | Listar Sucursales");
            System.out.println("7 | Listar Secciones");
            System.out.println("8 | Datos Empleado y Subs");
            System.out.println("9 | Jerarquía Sección");
            System.out.println("10 | Jerarquía Sucursal");
            System.out.println("11 | Limpiar Consola");
            System.out.println("-1 | SALIR");
            try {
                System.out.print("Ingrese que quiere hacer: ");
                opcion = inputNumber.nextInt();
                Controladora.clearConsole();
                switch (opcion) {
                    case 1:
                        if (Controladora.altaSucursal()) {
                            Controladora.clearConsole();
                            System.out.println("Se agrego!!");
                        } else {
                            Controladora.clearConsole();
                            System.out.println("Algo salio mal!");
                        }
                        break;
                    case 2:
                        if (Controladora.altaEmpleado()) {
                            Controladora.clearConsole();
                            System.out.println("Se agrego!!");
                        } else {
                            Controladora.clearConsole();
                            System.out.println("Algo salio mal!");
                        }
                        break;
                    case 3:
                        Controladora.ingresarArbolEmpleados();
                        break;
                    case 4:
                        Controladora.ingresarArbolSucursal();
                        break;
                    case 5:
                        Controladora.listarEmpleado();
                        break;
                    case 6:
                        Controladora.listarSucursal();
                        break;
                    case 7:
                        Controladora.listarSecciones();
                        break;
                    case 8:
                        Controladora.datosEmpSub();
                        break;
                    case 9:
                        Controladora.listarSeccionMayorMenor();
                        break;
                    case 10:
                        Controladora.listarSucursalMayorMenor();
                        break;
                    case 11:
                        Controladora.clearConsole();
                        break;
                    case -1:
                        
                        break;
                    default:
                        System.out.println("OPCION INCORRECTA!");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("INGRESE UN NUMERO!!");
                return;
            }
        } while (opcion != -1);
    }

}
