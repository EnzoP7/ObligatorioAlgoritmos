package Arboles;

import java.util.ArrayList;

import Nodos.Nodo;
import Nodos.NodoEmpleado;

public class ArbolUtils {

    // region Mostrar nodos que tiene un subordinado o niguno
    static ArrayList<Nodo> listaSecLibres;

    private static void recorrerSecNodos(Nodo nodo) {
        if (nodo != null) {
            agregarNodosSec(nodo);
            recorrerSecNodos(nodo.getIzq());
            recorrerSecNodos(nodo.getDer());
        }
    }

    public static ArrayList<Nodo> nodosSecLibres(Nodo pNodo) {
        listaSecLibres = new ArrayList<>();
        recorrerSecNodos(pNodo);
        return listaSecLibres;
    }

    private static void agregarNodosSec(Nodo nodo) {
        if (nodo != null) {
            if (nodo.getDer() == null || nodo.getIzq() == null) {
                listaSecLibres.add(nodo);
            }
        }
    }
    // endregion

    // region Mostrar nodos que tiene un subordinado o niguno
    static ArrayList<NodoEmpleado> listaLibres;

    private static void recorrerNodos(NodoEmpleado nodo) {
        if (nodo != null) {
            agregarNodos(nodo);
            recorrerNodos(nodo.getIzq());
            recorrerNodos(nodo.getDer());
        }
    }

    public static ArrayList<NodoEmpleado> nodosLibres(NodoEmpleado pNodo) {
        listaLibres = new ArrayList<>();
        recorrerNodos(pNodo);
        return listaLibres;
    }

    private static void agregarNodos(NodoEmpleado nodo) {
        if (nodo != null) {
            if (nodo.getDer() == null || nodo.getIzq() == null) {
                listaLibres.add(nodo);
            }
        }
    }
    // endregion
}
