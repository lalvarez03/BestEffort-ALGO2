package aed;

public class Ciudad {
    int ciudad;
    int gananciaTotal;
    int perdidaTotal;
    int superavit;
    int indiceHeap;

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
}
