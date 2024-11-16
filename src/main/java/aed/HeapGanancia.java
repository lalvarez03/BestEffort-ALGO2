package aed;

public class HeapGanancia extends Heap<Traslado> {
    private HeapTimestamp otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        return a.gananciaNeta > b.gananciaNeta;
    }

    @Override
    protected void heapify(Traslado[] array, int n, int i) {
        int largo = i; // Inicializar el nodo raíz como el mayor
        int izq = 2 * i + 1; // Hijo izquierdo
        int der = 2 * i + 2; // Hijo derecho

        // Si el hijo izquierdo es mayor que la raíz
        if (izq < n && esMayor(array[izq], array[largo])) {
            largo = izq;
        }

        // Si el hijo derecho es mayor que el más grande actual
        if (der < n && esMayor(array[der], array[largo])) {
            largo = der;
        }

        // Si el más grande no es la raíz
        if (largo != i) {
            Traslado temp = array[i];
            array[i] = array[largo];
            array[largo] = temp;
            heapify(array, n, largo);
        }
    }

    @Override
    protected void guardarIndice(Traslado a, int i){
        int indice = i;
        a.setIndiceGanancia(indice);
    }

    @Override
    protected void eliminarN(Traslado a){
        if(a.indiceAntiguo!=-1){
            this.otroHeap.desapilar(a.indiceAntiguo);
            a.setIndiceAntiguo(-1);
        }
    } 
    
    public void setOtroHeap(HeapTimestamp otroHeap) {
        this.otroHeap = otroHeap;
    }
}
