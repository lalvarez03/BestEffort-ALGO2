package aed;

public class HeapGanancia extends Heap<Traslado> {
    private HeapTimestamp otroHeap;
    @Override
    protected boolean esMayor(Traslado a, Traslado b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        return a.gananciaNeta > b.gananciaNeta;
    }

    

    @Override
    protected void guardarIndice(Traslado a, int i){
        int indice = i;
        a.setIndiceGanancia(indice);
    }

    @Override
    protected void eliminarEnOtroHeap(Traslado a){
        if(a.indiceAntiguo!=-1){
            this.otroHeap.desapilar(a.indiceAntiguo);
            a.setIndiceAntiguo(-1);
        }
    } 
    
    public void setOtroHeap(HeapTimestamp otroHeap) {
        this.otroHeap = otroHeap;
    }
}
