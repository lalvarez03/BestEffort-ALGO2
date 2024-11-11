package aed;

public class HeapCiudadesSuperavit extends Heap<Ciudad>{
    @Override
    protected boolean esMayor(Ciudad a, Ciudad b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        if (a.getSuperavit() != b.getSuperavit()) {
            return a.getSuperavit() > b.getSuperavit();
        }
        return a.getId() > b.getId();
    }
    
    @Override
    protected void guardarIndice(Ciudad a, int i){
        int indice = i;
        a.setIndiceHeap(indice);
    }
    
    @Override
    protected void eliminarN(Ciudad a){
    }

    public void ordenarGananciaN(int i){
        int indice = i;
        while(indice>0){
            indice = this.ordenarDeAbajo(indice);
        }
    }
    public void ordenarPerdidaN(int i){
        int indice = i;
        while(true){
            indice = ordenarDeArriba(indice);
            if(indice*2+1>=this.getTamañoLista()&&indice*2+2>=this.getTamañoLista()){
                break;
            }
        }
    }
}
