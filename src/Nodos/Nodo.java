package Nodos;

public class Nodo {
    private int dato;
    private Nodo izq;
    private Nodo der;

    public Nodo (int dato, Nodo izq, Nodo der){
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

    public int getDato(){
        return this.dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }

    public void setDer(Nodo der){
        this.der = der;
    }

    public void setIzq(Nodo izq){
        this.izq = izq;
    }
}
