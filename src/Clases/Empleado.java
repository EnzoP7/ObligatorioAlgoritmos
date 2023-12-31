package Clases;

import Controladora.Controladora;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Empleado {

    private int id;
    private int posicion;
    private String nombre;
    private String apellido;
    private int cedula;
    private int telefono;
    private Seccion seccion;
    private String cargo;
    private String fechaIngreso;
    private int sueldo;

    public Empleado(String nombre, String apellido, int cedula, int telefono,
            String cargo, String fechaIngreso, int sueldo) {
        this.id = generarId();
        this.posicion = 0;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.seccion = null;
        this.cargo = cargo;
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", posicion=" + posicion +
                '}';
    }

    private int generarId() {
        return Controladora.listaEmpleados.size() + 1;
    }

    public static Empleado altaEmpleado() {
        Scanner scanner = new Scanner(System.in);
        Scanner enteros = new Scanner(System.in);
        System.out.println("-----  INGRESO DE DATOS EMPLEADO  ----- \n");

        try {
            System.out.print("Ingrese Nombre del Empleado: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese Apellido del Empleado: ");
            String apellido = scanner.nextLine();
            System.out.print("Ingrese Cedula del Empleado: ");
            int cedula = enteros.nextInt();
            System.out.print("Ingrese Telefono del Empleado: ");
            int Telefono = enteros.nextInt();
            System.out.print("Ingrese Cargo del Empleado: ");
            String cargo = scanner.nextLine();
            System.out.print("Ingrese Fecha de Ingreso del Empleado: ");
            String fechaIng = scanner.nextLine();
            System.out.print("Ingrese Sueldo del Empleado: ");
            int Sueldo = enteros.nextInt();

            Empleado elEmpleado = new Empleado(nombre, apellido, cedula, Telefono, cargo, fechaIng, Sueldo);

            return elEmpleado;
        } catch (InputMismatchException ex) {
            System.out.println("Ingrese numeros donde se deba!!");
        }
        return null;
    }

    // region GET Y SETERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }
    // endregion

}
