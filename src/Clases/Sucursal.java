package Clases;

import java.util.Scanner;

import Arboles.ArbolSuc;
import Controladora.Controladora;

public class Sucursal {
    private int id;
    private  String nombre;
    private ArbolSuc arbol;

    private int generarId(){
        return Controladora.sucursales.size() +1 ;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", arbol='" + getArbol() + "'" +
            "}";
    }


    public static Sucursal altaSucursal(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----  INGRESO DE DATOS SUCURSAL  -----");
        System.out.println("-----------------------------------------");
        System.out.print("Ingrese Nombre: ");
        String nombre = scanner.nextLine();
       
        Sucursal laSucursal = new Sucursal(nombre);

        return laSucursal;
    }

    public Sucursal(String nombre) {
        this.id = generarId();
        this.nombre = nombre;
        this.arbol = new ArbolSuc();
    }

    // region GET Y SETERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArbolSuc getArbol() {
        return arbol;
    }

    public void setArbol(ArbolSuc arbol) {
        this.arbol = arbol;
    }

    public int getId() {
        return id;
    }

    // endregion
}
