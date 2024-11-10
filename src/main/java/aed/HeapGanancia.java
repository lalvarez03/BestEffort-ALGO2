package aed;

public class HeapGanancia extends Heap<Traslado> {
    private HeapTimestamp otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        return a.gananciaNeta > b.gananciaNeta;
    }
    @Override
    protected int guardarIndice(Traslado a, int i){
        int indice=0;
        return indice;
    }

    @Override
    protected void eliminarN(Traslado a){
        
    }
    
    public void setOtroHeap(HeapTimestamp otroHeap) {
        this.otroHeap = otroHeap;
    }
}
