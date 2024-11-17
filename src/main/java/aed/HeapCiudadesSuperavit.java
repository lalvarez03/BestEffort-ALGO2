package aed;

public class HeapCiudadesSuperavit extends Heap<Ciudad>{
    @Override
    protected boolean esMayor(Ciudad a, Ciudad b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        if (a.getSuperavit() != b.getSuperavit()) {
            return a.getSuperavit() > b.getSuperavit();
        }
        return a.getId() < b.getId();
    }

    @Override
    protected void heapify(Ciudad[] array, int n, int i) {
            int largo = i; // Inicializar el nodo raíz como el mayor
            int izq = (2*i) +1; // Hijo izquierdo
            int der = (2*i) +2; // Hijo derecho

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
                Ciudad temp = array[i];
                array[i] = array[largo];
                array[largo] = temp;
                heapify(array, n, largo);
            }
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

    public int getIdCiudadMayorSuperavit(Ciudad c){
        int res = c.getId();
        return res;
    }
}

