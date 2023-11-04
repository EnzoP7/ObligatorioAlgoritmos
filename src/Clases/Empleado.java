package Clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Empleado {

    private int id;
    private String nombre;
    private String apellido;
    private int cedula;
    private int telefono;
    private String seccion;
    private String cargo;
    private String fechaIngreso;
    private int sueldo;


    public Empleado( String nombre, String apellido, int cedula, int telefono,
                     String seccion, String cargo, String fechaIngreso, int sueldo) {
        this.id = generarId();
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.telefono = telefono;
        this.seccion = seccion;
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
                ", cedula='" + cedula + '\'' +
                ", telefono='" + telefono + '\'' +
                ", seccion='" + seccion + '\'' +
                ", cargo='" + cargo + '\'' +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }

    public static ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    private int generarId(){
        return listaEmpleados.size() + 1;
    }

    public  static Empleado altaEmpleado(){
        Scanner scanner = new Scanner(System.in);
        Scanner enteros = new Scanner(System.in);
        System.out.println("-----  INGRESO DE DATOS EMPLEADO  -----");
        System.out.println("-----------------------------------------");
        System.out.println("Ingrese Nombre del Empleado: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese Apellido del Empleado: ");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese Cedula del Empleado: ");
        int cedula = enteros.nextInt();
        System.out.println("Ingrese Telefono del Empleado: ");
        int Telefono = enteros.nextInt();
        System.out.println("Ingrese Seccion del Empleado: ");
        String Seccion = scanner.nextLine();
        System.out.println("Ingrese Cargo del Empleado: ");
        String cargo = scanner.nextLine();
        System.out.println("Ingrese Fecha de Ingreso del Empleado: ");
        String fechaIng = scanner.nextLine();
        System.out.println("Ingrese Sueldo del Empleado: ");
        int Sueldo = enteros.nextInt();
        Empleado elEmpleado = new Empleado(nombre,apellido,cedula,Telefono,Seccion,cargo,fechaIng,Sueldo);
        listaEmpleados.add(elEmpleado);
        System.out.println("Empleado Ingresado Con Exito");
        return elEmpleado;


    }


    // region  GET Y SETERS
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
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
