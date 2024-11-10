package aed;

public class Ciudad implements Comparable<Ciudad> {
    private int ciudad;
    private int gananciaTotal;
    private int perdidaTotal;
    private int superavit;
    private int indiceHeap;

    public Ciudad(int ciudad){
        this.ciudad = ciudad;
        this.gananciaTotal = 0;
        this.perdidaTotal = 0;
        this.superavit = 0;
    }

    public int getId(){
        return this.ciudad;
    }

    public int getSuperavit(){
        return this.superavit;
    }

    public int getGanancia(){
        return this.gananciaTotal;
    }

    public int getPerdida(){
        return this.perdidaTotal;
    }

    public void setIndiceHeap(int i){
        this.indiceHeap = i;
    }

    public void agregarGanancia(int ganancia){
        this.gananciaTotal += ganancia;
    }

    public void agregarPerdida(int perdida){
        this.perdidaTotal += perdida;
    }

    @Override
    public int compareTo(Ciudad o) {
        return 0;
    }
}
