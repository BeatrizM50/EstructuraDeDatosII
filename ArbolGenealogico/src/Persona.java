public class Persona {


        private String nombre;
        String fecha;
        String genero;


        public Persona(String nombre, String fecha, String genero) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.genero = genero;
        }




        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }


        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }




    }


