public class GrafoNoDirigidoNoPonderado<T> extends GrafoNoDirigido<T> {

    public GrafoNoDirigidoNoPonderado(int capacidadInicial) {
        super(capacidadInicial);
    }


    public boolean agregarArista(T origen, T destino) {
        return super.agregarArista(origen, destino, 1);
    }




}
