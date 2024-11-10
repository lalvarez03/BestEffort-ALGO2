package aed;

public class HeapGanancia extends Heap<Traslado> {
    private HeapTimestamp otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        if (a.gananciaNeta != b.gananciaNeta) {
            return a.gananciaNeta > b.gananciaNeta;
        }
        return a.id > b.id; // Desempata usando el ID
    }
    
    public void setOtroHeap(HeapTimestamp otroHeap) {
        this.otroHeap = otroHeap;
    }
}
