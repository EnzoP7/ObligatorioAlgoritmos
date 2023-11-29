package Clases;

import Arboles.Arbol;
import Controladora.Controladora;

public class Seccion {
    private int id;
    private Sucursal sucursal;
    private int posicion;
    private String nombre;
    private Arbol arbol;

    public Seccion(Sucursal sucursal, String nombre) {
        this.id = generarId();
        this.sucursal = sucursal;
        this.posicion=0;
        this.nombre = nombre;
        this.arbol = new Arbol();
    }

    //#region GETS && SETS
    public int getId() {
        return this.id;
    }

    private int generarId(){
        return Controladora.secciones.size() +1 ;
    }

    public Sucursal getSucursal() {
        return this.sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getPosicion() {
        return this.posicion	;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Arbol getArbol() {
        return this.arbol;
    }

    public void setArbol(Arbol arbol) {
        this.arbol = arbol;
    }
    //#endregion GETS && SETS

    @Override
    public String toString() {
        return "Seccion{" +
            " id='" + getId() + "'" +
            ", sucursal='" + getSucursal().getId() + "'" +
            ", posicion='" + getPosicion() + "'" +
            ", nombre='" + getNombre() + "'" +
            "}";
    }

}
