package Arboles;

import java.util.ArrayList;

public class ArbolSuc {
    static class Nodo {
        Clases.Seccion dato;
        Nodo izq, der;
    }

    Nodo raiz;

    public ArbolSuc() {
        raiz = null;
    }

    public void insertar(Clases.Seccion dato) {
        Nodo nuevo = new Nodo();
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo anterior = null, nodo;
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

    // MOSTRAR SECCIONES MAYOR A MENOR JERARQUIA
    private void imprimirMayorMenorRecu(Nodo nodo, ArrayList<Nodo> lista) {
        if (nodo == null) {
            System.out.println("NO TIENE SECCIONES!!");
            return;
        }
        System.out.println("\n----------------------------------------------------------------------");
        System.out.println(nodo.dato);
        System.out.println("----------------------------------------------------------------------");
        nodo.dato.getArbol().imprimirMayorMenorRecu();

        if (nodo.izq != null) {
            lista.add(nodo.izq);
        }
        if (nodo.der != null) {
            lista.add(nodo.der);
        }
        if (lista.isEmpty()) {
            return;
        }
        imprimirMayorMenorRecu(lista.remove(0),lista);
    }

    public void imprimirMayorMenorRecu() {
        ArrayList<Nodo> lista = new ArrayList<>();
        imprimirMayorMenorRecu(raiz, lista);
    }

    // private void imprimirMayorMenor(Nodo nodo) {
    //     if (nodo == null) {
    //         System.out.println("NO TIENE SECCIONES!!");
    //         return;
    //     }

    //     ArrayList<Nodo> lista = new ArrayList<>();
    //     lista.add(raiz);

    //     while (!lista.isEmpty()) {
    //         Nodo actual = lista.remove(0);
    //         System.out.println("\n----------------------------------------------------------------------");
    //         System.out.println(actual.dato);
    //         System.out.println("----------------------------------------------------------------------");
    //         actual.dato.getArbol().imprimirMayorMenorRecu();

    //         if (actual.izq != null) {
    //             lista.add(actual.izq);
    //         }
    //         if (actual.der != null) {
    //             lista.add(actual.der);
    //         }
    //     }
    // }

    // public void imprimirMayorMenor(){
    //     imprimirMayorMenor(raiz);
    // }

    // region INGRESAR POSICIONES
    public void ingresarPos(Nodos.Nodo nodo, int lastPos, boolean dir) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            int cantTotal = contarNodosSec(nodo);
            int cantDir = 0;
            if (!dir) {
                cantDir = contarNodosSec(nodo.getIzq());
                nodo.getDato().setPosicion(lastPos - (cantTotal - cantDir) );
            } else {
                cantDir = contarNodosSec(nodo.getDer());
                nodo.getDato().setPosicion(cantTotal - cantDir + lastPos);
            }
            insertar(nodo.getDato());
            ingresarPos(nodo.getIzq(), nodo.getDato().getPosicion(), false);
            ingresarPos(nodo.getDer(), nodo.getDato().getPosicion(), true);
        }
    }

    public int contarNodosSec(Nodos.Nodo nodo) {
        if (nodo != null) {
            int contador = 1;
            if (nodo.getDer() != null) {
                contador += contarNodosSec(nodo.getDer());
            }
            if (nodo.getIzq() != null) {
                contador += contarNodosSec(nodo.getIzq());
            }
            return contador;
        }
        return 0;
    }
    // endregion

    private void imprimirPre(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            imprimirPre(nodo.izq);
            imprimirPre(nodo.der);
        }
    }

    public void imprimirPre() {
        imprimirPre(raiz);
        System.out.println();
    }

    private void imprimirIn(Nodo nodo) {
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

    private void imprimirPost(Nodo nodo) {
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

    public boolean buscar(int num) {
        Nodo nodo = raiz;
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

    public int sumaTotal(Nodo nodo) {
        int suma = 0;
        if (nodo != null) {
            suma += nodo.dato.getPosicion();
            suma += sumaTotal(nodo.izq) + sumaTotal(nodo.der);
        }
        return suma;
    }

    public int altura(Nodo nodo) {
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
        Nodo nodo = raiz;
        return altura(nodo);
    }

    public int hojas(Nodo nodo) {
//        int hojas;
        if (nodo != null) {
            if (nodo.der == null && nodo.izq == null) {
                return 1;
            } else {
                return (hojas(nodo.der) + hojas(nodo.izq));
            }
        }
        return 0;
    }

    public int contarNodos(Nodo nodo) {
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
        Nodo nodo = raiz;
        return alturaTotal(nodo);
    }

    static int contador = 0;

    public int alturaTotal(Nodo nodo) {
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

    public boolean tieneHijos(Nodo nodo) {
        if (nodo.izq == null && nodo.der == null)
            return false;
        return true;
    }

}

