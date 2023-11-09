package Nodos;

import Clases.Empleado;

import java.util.ArrayList;

public  class NodoEmpleado {
    private Empleado empleado;
    private  Empleado jefe;
    private ArrayList<Empleado> subordinados = new ArrayList<>();

    public NodoEmpleado(Empleado empleado, Empleado jefe, ArrayList<Empleado> subordinados) {
        this.empleado = empleado;
        this.jefe = jefe;
        this.subordinados = subordinados;
    }


    @Override
    public String toString() {
        return "NodoEmpleado{" +
                "empleado=" + empleado +
                ", jefe=" + jefe +
                ", subordinados=" + subordinados +
                '}';
    }

    //Metodo para buscar un nodo dado un empleado
//    public NodoEmpleado buscarNodoPorEmpleado(Empleado empleado){
//        if (this.empleado.equals(empleado)){
//            return  this;
//        }else {
//            for (NodoEmpleado subordinado : subordinados){
//
//            }
//        }
//
//    }

    // region GETERS Y SETERS
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    public ArrayList<Empleado> getSubordinados() {
        return subordinados;
    }

    public void setSubordinados(ArrayList<Empleado> subordinados) {
        this.subordinados = subordinados;
    }
    // endregion
}
