package aed;

public class Ciudad {
    private int idCiudad;
    private double gananciaTotal;
    private double perdidatotal;
    private int idSuperavit;

    public Ciudad(int idCiudad) {
        this.idCiudad = idCiudad;
        gananciaTotal = 0;
        perdidatotal = 0;
        idSuperavit = idCiudad;
    }
    public int getIdCiudad() {
        return idCiudad;
    }
    public void agregarGanancia(double ganancia){
        this.gananciaTotal += ganancia;
    }
    public void agregarPerdidatotal(double perdida){
        this.perdidatotal += perdida;
    }
    public double getGananciaTotal() {
        return gananciaTotal;
    }
    public double getPerdidatotal() {
        return perdidatotal;
    }
    public int getidSuperavit() {
        return idSuperavit;
    }
}
