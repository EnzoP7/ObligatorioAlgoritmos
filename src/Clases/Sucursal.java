package Clases;

import Arboles.ArbolEmpleados;

import java.util.ArrayList;

public class Sucursal {
    private int id;
    private  String nombre;
    private ArbolEmpleados arbolEmpleados;

    public ArrayList<Sucursal> listaSucursales = new ArrayList<>();
    private int generarId(){
        return listaSucursales.size() + 1;
    }

    public Sucursal(String nombre, ArbolEmpleados arbolEmpleados) {
        this.id = generarId();
        this.nombre = nombre;
        this.arbolEmpleados = new ArbolEmpleados();
    }



    // region GET Y SETERS
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArbolEmpleados getArbolEmpleados() {
        return arbolEmpleados;
    }

    public void setArbolEmpleados(ArbolEmpleados arbolEmpleados) {
        this.arbolEmpleados = arbolEmpleados;
    }

    public int getId() {
        return id;
    }

    // endregion
}
