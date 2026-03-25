public class ArbolBinario {
    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    // Insertar contacto con manejo de duplicados
    public void insertar(Contacto nuevo) {
        String nombreBase = nuevo.getNombre();
        String nombreFinal = nombreBase;
        int contador = 1;

        // Si ya existe el nombre con distinto teléfono, se agrega como Ana 1, Ana 2...
        while (buscar(nombreFinal) != null) {
            Contacto existente = buscar(nombreFinal);
            if (!existente.getTelefono().equals(nuevo.getTelefono())) {
                nombreFinal = nombreBase + " " + contador;
                contador++;
            } else {
                return;
            }
        }

        raiz = insertarRecursivo(raiz, new Contacto(nombreFinal, nuevo.getTelefono()));
        System.out.println("Insertado: " + nombreFinal + " - " + nuevo.getTelefono());
    }

    private Nodo insertarRecursivo(Nodo actual, Contacto contacto) {
        if (actual == null) {
            return new Nodo(contacto);
        }
        if (contacto.getNombre().compareToIgnoreCase(actual.contacto.getNombre()) < 0) {
            actual.izquierda = insertarRecursivo(actual.izquierda, contacto);
        } else if (contacto.getNombre().compareToIgnoreCase(actual.contacto.getNombre()) > 0) {
            actual.derecha = insertarRecursivo(actual.derecha, contacto);
        }
        return actual;
    }


    private Contacto buscar(String nombre) {

        return buscarRecursivo(raiz, nombre);
    }

    public String buscar_Nombre(String nombre){
        Contacto encontrado = buscarRecursivo(raiz, nombre);
        if(encontrado==null){
            return "No existe el nombre '"+nombre+"'.";
        }
        return "Encontrado:  "+encontrado.getNombre()+" : "+encontrado.getTelefono();
    }



    private Contacto buscarRecursivo(Nodo actual, String nombre) {
        if (actual == null) return null;
        if (nombre.equalsIgnoreCase(actual.contacto.getNombre())) return actual.contacto;
        return (nombre.compareToIgnoreCase(actual.contacto.getNombre()) < 0)
                ? buscarRecursivo(actual.izquierda, nombre)
                : buscarRecursivo(actual.derecha, nombre);

    }


    public void buscarPorTelefono(String telefono) {
        Contacto encontrado = buscarPorTelefonoRecursivo(raiz, telefono);
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado);
        } else {
            System.out.println("El numero '" + telefono+"' no existe.");
        }
    }

    private Contacto buscarPorTelefonoRecursivo(Nodo actual, String telefono) {
        if (actual == null) return null;
        if (telefono.equals(actual.contacto.getTelefono())) return actual.contacto;
        Contacto encontrado = buscarPorTelefonoRecursivo(actual.izquierda, telefono);
        if (encontrado != null) return encontrado;
        return buscarPorTelefonoRecursivo(actual.derecha, telefono);
    }


    public String eliminar(String nombre) {
        if (buscar(nombre) != null) {
            raiz = eliminarRecursivo(raiz, nombre);
            return "Eliminado: "+nombre;
        } else {
            return "No existe '" + nombre+"'";
        }
    }

    private Nodo eliminarRecursivo(Nodo actual, String nombre) {
        if (actual == null) return null;

        if (nombre.compareToIgnoreCase(actual.contacto.getNombre()) < 0) {
            actual.izquierda = eliminarRecursivo(actual.izquierda, nombre);
        } else if (nombre.compareToIgnoreCase(actual.contacto.getNombre()) > 0) {
            actual.derecha = eliminarRecursivo(actual.derecha, nombre);
        } else {
            if (actual.izquierda == null && actual.derecha == null) return null;
            else if (actual.izquierda == null) return actual.derecha;
            else if (actual.derecha == null) return actual.izquierda;
            else {
                Nodo sucesor = encontrarMinimo(actual.derecha);
                actual.contacto = sucesor.contacto;
                actual.derecha = eliminarRecursivo(actual.derecha, sucesor.contacto.getNombre());
            }
        }
        return actual;
    }

    private Nodo encontrarMinimo(Nodo actual) {
        while (actual.izquierda != null) actual = actual.izquierda;
        return actual;
    }

    // Mostrar agenda ordenada
    public void mostrarAgenda() {
        inOrden(raiz);
    }

    private void inOrden(Nodo actual) {
        if (actual != null) {
            inOrden(actual.izquierda);
            System.out.println(actual.contacto);
            inOrden(actual.derecha);
        }
    }

    public void recorridoPreorden() {
        preorden(raiz);
    }

    private void preorden(Nodo actual) {
        if (actual != null) {
            System.out.println(actual.contacto);
            preorden(actual.izquierda);
            preorden(actual.derecha);
        }
    }
    public void recorridoPosorden() {
        posOrden(raiz);
    }

    private void posOrden(Nodo actual) {
        if (actual != null) {
            posOrden(actual.izquierda);
            posOrden(actual.derecha);
            System.out.println(actual.contacto);
        }
    }


}