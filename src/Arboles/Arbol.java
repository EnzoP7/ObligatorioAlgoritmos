package Arboles;

import java.util.ArrayList;

public class Arbol {
    static class NodoEmpleado {
        Clases.Empleado dato;
        NodoEmpleado izq, der;
    }

    NodoEmpleado raiz;

    public Arbol() {
        raiz = null;
    }

    public void insertar(Clases.Empleado dato) {
        NodoEmpleado nuevo = new NodoEmpleado();
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoEmpleado anterior = null, nodo;
            nodo = raiz;
            while (nodo != null) {
                anterior = nodo;
                if (dato.getPosicion() < nodo.dato.getPosicion()) {
                    nodo = nodo.izq;
                } else {
                    nodo = nodo.der;
                }
            }
            if (dato.getPosicion() < anterior.dato.getPosicion()) {
                anterior.izq = nuevo;
            } else {
                anterior.der = nuevo;
            }
        }
    }

    private void imprimirPreOG(NodoEmpleado nodo) {
        if (nodo != null) {
            System.out.println(nodo.dato + " ");
            imprimirPreOG(nodo.izq);
            imprimirPreOG(nodo.der);
        }
    }

    private void imprimirMayorMenor(NodoEmpleado nodo) {
        if (nodo == null) {
            return;
        }

        ArrayList<NodoEmpleado> lista = new ArrayList<>();
        lista.add(raiz);

        while (!lista.isEmpty()) {
            NodoEmpleado actual = lista.remove(0);
            System.out.println(actual.dato);

            if (actual.izq != null) {
                lista.add(actual.izq);
            }
            if (actual.der != null) {
                lista.add(actual.der);
            }
        }
    }

    public void imprimirMayorMenor(){
        imprimirMayorMenor(raiz);
    }

    public void imprimirPreEmp(NodoEmpleado nodo){
        imprimirPreOG(nodo);
        System.out.println();
    }

    public void imprimirPre() {
        imprimirPreOG(raiz);
        System.out.println();
    }

    // region INGRESAR POSICIONES
    public void ingresarPos(Nodos.NodoEmpleado nodo, int lastPos, boolean dir) {
        if (nodo != null) {
            // System.out.print(nodo.getDato() + " ");
            int cantTotal = contarNodosEmp(nodo);
            int cantDir = 0;
            if (!dir) {
                cantDir = contarNodosEmp(nodo.getIzq());
                nodo.getDato().setPosicion(lastPos - (cantTotal - cantDir) );
            } else {
                cantDir = contarNodosEmp(nodo.getDer());
                nodo.getDato().setPosicion(cantTotal - cantDir + lastPos);
            }
            //lo de arriba le pone la posicion segun el "arbol" que le pasamos por nodo para
            //que al ingresar en el arbol quede en el mismo orden
            insertar(nodo.getDato());
            ingresarPos(nodo.getIzq(), nodo.getDato().getPosicion(), false);
            ingresarPos(nodo.getDer(), nodo.getDato().getPosicion(), true);
        }
    }
    // endregion

    private void imprimirIn(NodoEmpleado nodo) {
        if (nodo != null) {
            imprimirIn(nodo.izq);
            System.out.print(nodo.dato + " ");
            imprimirIn(nodo.der);
        }
    }

    public void imprimirIn() {
        imprimirIn(raiz);
        System.out.println();
    }

    private void imprimirPost(NodoEmpleado nodo) {
        if (nodo != null) {
            imprimirPost(nodo.izq);
            imprimirPost(nodo.der);
            System.out.print(nodo.dato + " ");
        }
    }

    public void imprimirPost() {
        imprimirPost(raiz);
        System.out.println();
    }

    public NodoEmpleado buscarEmp(int pNum) {
        NodoEmpleado nodo = raiz;
        while (nodo != null) {
            if (pNum == nodo.dato.getPosicion()) {
                return nodo;
            } else {
                if (pNum > nodo.dato.getPosicion()) {
                    nodo = nodo.der;
                } else {
                    nodo = nodo.izq;
                }
            }
        }
        return null;
    }

    public boolean buscar(int num) {
        NodoEmpleado nodo = raiz;
        while (nodo != null) {
            if (num == nodo.dato.getPosicion()) {
                return true;
            } else {
                if (num > nodo.dato.getPosicion()) {
                    nodo = nodo.der;
                } else {
                    nodo = nodo.izq;
                }
            }
        }
        return false;
    }

    public int sumaTotal(NodoEmpleado nodo) {
        int suma = 0;
        if (nodo != null) {
            suma += nodo.dato.getPosicion();
            suma += sumaTotal(nodo.izq) + sumaTotal(nodo.der);
        }
        return suma;
    }

    public int contarNodosEmp(Nodos.NodoEmpleado nodo) {
        if (nodo != null) {
            int contador = 1;
            if (nodo.getDer() != null) {
                contador += contarNodosEmp(nodo.getDer());
            }
            if (nodo.getIzq() != null) {
                contador += contarNodosEmp(nodo.getIzq());
            }
            return contador;
        }
        return 0;
    }

    public int altura(NodoEmpleado nodo) {
        if (nodo != null) {
            if (altura(nodo.der) >= altura(nodo.izq)) {
                return altura(nodo.der) + 1;
            } else {
                return altura(nodo.izq) + 1;
            }
        }
        return -1;
    }

    public int altura() {
        NodoEmpleado nodo = raiz;
        return altura(nodo);
    }

    public int hojas(NodoEmpleado nodo) {
        // int hojas;
        if (nodo != null) {
            if (nodo.der == null && nodo.izq == null) {
                return 1;
            } else {
                return (hojas(nodo.der) + hojas(nodo.izq));
            }
        }
        return 0;
    }

    public int contarNodos(NodoEmpleado nodo) {
        int contador = 1;
        if (nodo.der != null) {
            contador += contarNodos(nodo.der);
        }
        if (nodo.izq != null) {
            contador += contarNodos(nodo.izq);
        }
        return contador;
    }

    public int alturaTotal() {
        NodoEmpleado nodo = raiz;
        return alturaTotal(nodo);
    }

    static int contador = 0;

    public int alturaTotal(NodoEmpleado nodo) {
        if (nodo != null) {
            if (nodo.der != null && nodo.izq != null) {
                int altura = 1 + Math.max(alturaTotal(nodo.izq), alturaTotal(nodo.der));
                if (altura >= 1) {
                    return 1;
                }
                return altura;
            }
        }
        return 0;
    }

    public boolean tieneHijos(NodoEmpleado nodo) {
        if (nodo.izq == null && nodo.der == null)
            return false;
        return true;
    }

}
