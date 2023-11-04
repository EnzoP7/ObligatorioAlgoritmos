package Arboles;

import Clases.Empleado;
import Nodos.NodoEmpleado;

import java.util.ArrayList;

public class ArbolEmpleados {
    private NodoEmpleado raiz;

//    public void insertar(Empleado empleado, Empleado jefe, ArrayList<Empleado> subordinados){
//        NodoEmpleado nuevo = new NodoEmpleado();
//        nuevo.setEmpleado(empleado);
//        if (jefe != null){
//            NodoEmpleado nodoJefe = buscarNodoPorEmpleado(jefe,raiz);
//            if (nodoJefe != null){
//                nuevo.setJefe(nodoJefe);
//            }
//        }
//        if (subordinados != null){
//            for (Empleado subordinado:subordinados){
//                NodoEmpleado nodoSubordinado = buscarNodoPorEmpleado(subordinado,raiz);
//                if (nodoSubordinado != null){
//                    nuevo.agregarSubordinado(nodoSubordinado);
//                    nodoSubordinado.setJefe(nuevo);
//                }
//            }
//        }
//
//        if (raiz == null){
//            raiz == nuevo;
//        }
//    }
}
