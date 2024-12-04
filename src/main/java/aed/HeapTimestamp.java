package aed;

public class HeapTimestamp extends Heap<Traslado>{
    private HeapGanancia otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        return a.timestamp < b.timestamp;
    }

    

    @Override
    protected void guardarIndice(Traslado a, int i){
        int indice = i;
        a.setIndiceAntiguo(indice);
    }

    @Override
    protected void eliminarEnOtroHeap(Traslado a){
        if(a.indiceGanancia!=-1){
            this.otroHeap.desencolar(a.indiceGanancia);
            a.setIndiceGanancia(-1);
        }
    }    

    public void setOtroHeap(HeapGanancia otroHeap) {
        this.otroHeap = otroHeap;
    }
}
