package Clases;

import java.util.ArrayList;

public class Empresa {

    private int id;
    private String nombre;
    private ArrayList<Sucursal> sucursales = new ArrayList<>();

    public static ArrayList<Empresa> listaEmpresas = new ArrayList<>();

    private int generarId(){
        return  listaEmpresas.size() +1;

    }


    public Empresa( String nombre, ArrayList<Sucursal> sucursales) {
        this.id = generarId();
        this.nombre = nombre;
        this.sucursales = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", sucursales=" + sucursales +
                '}';
    }


//    public  static Empresa altaEmpresa(){
//        Scanner scanner = new Scanner(System.in);
//        Scanner enteros = new Scanner(System.in);
//        System.out.println("-----  INGRESO DE DATOS EMPRESA  -----");
//        System.out.println("-----------------------------------------");
//        System.out.println("Ingrese Nombre de la Empresa: ");
//        String nombre = scanner.nextLine();
//        System.out.println("Ingrese Sucursales del Empleado: ");
//        // ACA TENEMOS QUE HACER EL ALTA DE SUCURSALES , Y EN ESE ALTA DEBERIAMOS HACER LA POSIBILIDAD
//        // DE QUE PUEDA INGRESAR MUCHAS, podemos hacer un while hasta que le pinte
//        String apellido = scanner.nextLine();
//    }
    public void agregarSucursal(Sucursal sucursal){
        sucursales.add(sucursal);
    }

    // region GET Y SET
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
    // endregion
}
