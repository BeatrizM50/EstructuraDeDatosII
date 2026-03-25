public class Main {
    public static void main(String[] args) {
        ArbolBinario agenda = new ArbolBinario();
//--------------------------------------------------------------------------------------------------
        System.out.println("------------------------------------------------------------------------");
        agenda.insertar(new Contacto("Ana", "555-1234"));
        agenda.insertar(new Contacto("Carlos", "555-5678"));
        agenda.insertar(new Contacto("Beatriz", "555-9012"));
        agenda.insertar(new Contacto("Alejandro", "555-3456"));
        agenda.insertar(new Contacto("Ana", "555-7777"));
        agenda.insertar(new Contacto("Ana", "555-8888"));
        System.out.println("------------------------------------------------------------------------");
//--------------------------------------------------------------------------------------------------

        System.out.println(" Agenda ordenada Alfabeticamente(InOrden) : \n");
        agenda.mostrarAgenda();
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Nombre que existe ~ \n");
        System.out.println(agenda.buscar_Nombre("Ana 1"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Nombre que no existe ~ \n");
        System.out.println(agenda.buscar_Nombre("Maria"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Telefono que existe ~ \n");
        agenda.buscarPorTelefono("555-3456");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Telefono que no existe ~ \n");
        agenda.buscarPorTelefono("555-89798");
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Eliminar nombre que no existe ~ \n");
        System.out.println(agenda.eliminar("Manuel"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Eliminar nombre que existe ~ \n");
        System.out.println(agenda.eliminar("Alejandro"));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Agenda despues de eliminar 'Alejandro' ~ \n");
        agenda.mostrarAgenda();
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Recorrido PreOrden ~\n");
        agenda.recorridoPreorden();
        System.out.println("------------------------------------------------------------------------");
        System.out.println("~ Recorrido PosOrden ~\n");
        agenda.recorridoPosorden();

    }
}