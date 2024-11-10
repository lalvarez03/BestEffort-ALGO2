package aed;

public class HeapCiudadesSuperavit extends Heap<Ciudad>{
    @Override
    protected boolean esMayor(Ciudad a, Ciudad b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        if (a.superavit != b.superavit) {
            return a.superavit > b.superavit;
        }
        return a.ciudad > b.ciudad; // Desempata usando el ID
    }
    
    @Override
    protected int guardarIndice(Ciudad a, int i){
        int indice=0;
        return indice;
    }

    @Override
    protected void eliminarN(Ciudad a){
    }
}
