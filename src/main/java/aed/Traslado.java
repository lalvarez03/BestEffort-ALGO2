package aed;

public class Traslado implements Comparable<Traslado> {

    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    int indiceGanancia;
    int indiceAntiguo;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }


    @Override
    public int compareTo(Traslado o) {
        return 0;
    }

    public void setIndiceAntiguo(int indiceAntiguo) {
        this.indiceAntiguo = indiceAntiguo;
    }

    public void setIndiceGanancia(int indiceGanancia) {
        this.indiceGanancia = indiceGanancia;
    }
}
