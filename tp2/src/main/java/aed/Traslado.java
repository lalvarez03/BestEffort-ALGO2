package aed;

public class Traslado implements Comparable<Traslado> {

    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    int indiceGanancia;
    int indiceAntiguo;
    boolean isTipoGanancia;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }


    @Override
    public int compareTo(Traslado o) {
        if (isTipoGanancia){
            if(timestamp > o.timestamp){
                return 1;
            }
            else if(timestamp < o.timestamp){
                return -1;
            }else if(timestamp == o.timestamp && id > o.id ){
                return 1;
            }else{
                return -1;
            }
        }else{
            if(gananciaNeta > o.gananciaNeta){
                return 1;
            }
            else if(gananciaNeta < o.gananciaNeta){
                return -1;
            }else if(gananciaNeta == o.gananciaNeta && id > o.id ){
                return 1;
            }else{
                return -1;
            }
        }
    }

    public void setIndiceAntiguo(int indiceAntiguo) {
        this.indiceAntiguo = indiceAntiguo;
    }

    public void setIndiceGanancia(int indiceGanancia) {
        this.indiceGanancia = indiceGanancia;
    }
    public void setIsTipoGanancia(boolean isTipoGanancia) {
        this.isTipoGanancia = isTipoGanancia;
    }
}
