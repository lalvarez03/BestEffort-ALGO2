package aed;

import java.util.ArrayList;

public class BestEffort {
    private ArrayList<Integer> mayoresGanancias;
    private ArrayList<Integer> mayoresPerdidas;
    private int gananciasTotales;
    private int cantidadDespachos;
    private Heap trasladosOrdenadosXGanancias;
    private Heap trasladosOrdenadosXTimestamp;
    private Heap ciudades;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        for(int i = 0; i < cantCiudades; i++){
            ciudades.apilar(new Ciudad(i));
        }
        for(int i = 0; i < traslados.length; i++){
            trasladosOrdenadosXGanancias.apilar(traslados[i]);
            trasladosOrdenadosXTimestamp.apilar(traslados[i]);
        }
    }

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return mayoresGanancias;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return mayoresPerdidas;
    }

    public int gananciaPromedioPorTraslado(){
        return gananciasTotales/cantidadDespachos;
    }
    
}
