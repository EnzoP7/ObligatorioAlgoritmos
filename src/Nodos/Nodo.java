package Nodos;

import Clases.Seccion;

public class Nodo {
    private Seccion dato;
    private Nodo izq;
    private Nodo der;

    public Nodo (Seccion dato, Nodo izq, Nodo der){
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public Nodo getIzq(){
        return this.izq;
    }

    public Nodo getDer(){
        return this.der;
    }

    public Seccion getDato(){
        return this.dato;
    }

    public void setDato(Seccion dato){
        this.dato = dato;
    }

    public void setDer(Nodo der){
        this.der = der;
    }

    public void setIzq(Nodo izq){
        this.izq = izq;
    }
}
