package aed;

public class HeapTimestamp extends Heap<Traslado> {
    private HeapGanancia otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        return false;
    }
    
    public void setOtroHeap(HeapGanancia otroHeap) {
        this.otroHeap = otroHeap;
    }
}
