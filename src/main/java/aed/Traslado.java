package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    private int indiceHeapGanancia;
    private int indiceHeapAntiguedad;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }
    public int getIndiceHeapGanancia() {
        return indiceHeapGanancia;
    }
    public void setIndiceHeapGanancia(int indiceHeapGanancia) {
        this.indiceHeapGanancia = indiceHeapGanancia;
    }
    public int getIndiceHeapAntiguedad() {
        return indiceHeapAntiguedad;
    }
    public void setIndiceHeapAntiguedad(int indiceHeapAntiguedad) {
        this.indiceHeapAntiguedad = indiceHeapAntiguedad;
    }
}
