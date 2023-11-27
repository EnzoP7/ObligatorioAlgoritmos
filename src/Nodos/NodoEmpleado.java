package Nodos;

public  class NodoEmpleado {
    private Clases.Empleado dato;
    private NodoEmpleado izq;
    private NodoEmpleado der;

    public NodoEmpleado (Clases.Empleado dato, NodoEmpleado izq, NodoEmpleado der){
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public NodoEmpleado getIzq(){
        return this.izq;
    }

    public NodoEmpleado getDer(){
        return this.der;
    }

    public Clases.Empleado getDato(){
        return this.dato;
    }

    public void setDato(Clases.Empleado dato){
        this.dato = dato;
    }

    public void setDer(NodoEmpleado der){
        this.der = der;
    }

    public void setIzq(NodoEmpleado izq){
        this.izq = izq;
    }
    // endregion
}
