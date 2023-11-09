package Clases;

public class Usuario {


        private String nombre = "ERNESTA ERES MUY BELLA";
        private String contrasenia = "BARA BARA BARA; BERE BERE BERE";

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getContrasenia() {
            return contrasenia;
        }

        public void setContrasenia(String contrasenia) {
            this.contrasenia = contrasenia;
        }

        public Usuario() { }

        public Usuario(String nombre, String contrasenia)
        {
            this.nombre = nombre;
            this.contrasenia = contrasenia;
        }

        @Override
        public String toString() {
            return "Usuario{" +
                    "nombre='" + nombre + '\'' +
                    ", contrasenia='" + contrasenia + '\'' +
                    '}';
        }
    }


