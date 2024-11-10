package aed;

import java.util.ArrayList;

public class BestEffort {
    private ArrayList<Integer> mayoresGanancias;
    private ArrayList<Integer> mayoresPerdidas;
    private Ciudad[] ciudades;
    private int gananciasTotales;
    private int cantidadDespachos;
    private HeapGanancia trasladosOrdenadosXGanancias;
    private HeapTimestamp trasladosOrdenadosXTimestamp;
    private HeapCiudadesSuperavit ciudadesSuperavit;

    public BestEffort(int cantCiudades, Traslado[] traslados){
        ciudades = new Ciudad[cantCiudades];
        ciudadesSuperavit = new HeapCiudadesSuperavit();
        trasladosOrdenadosXGanancias = new HeapGanancia();
        trasladosOrdenadosXTimestamp = new HeapTimestamp();
        trasladosOrdenadosXTimestamp.setOtroHeap(trasladosOrdenadosXGanancias);
        trasladosOrdenadosXGanancias.setOtroHeap(trasladosOrdenadosXTimestamp);
        gananciasTotales = 0;
        cantidadDespachos = 0;
        mayoresGanancias = new ArrayList<>();
        mayoresPerdidas = new ArrayList<>();
        for(int i = 0; i < cantCiudades; i++){
            Ciudad ciudadAux = new Ciudad(i);
            ciudades[i]=ciudadAux;
            ciudadesSuperavit.apilar(ciudadAux);
        }
        for(int i = 0; i < traslados.length; i++){
            trasladosOrdenadosXGanancias.apilar(traslados[i]);
            trasladosOrdenadosXTimestamp.apilar(traslados[i]);
        }
    }

    public void registrarTraslados(Traslado[] traslados){
        for(int i = 0; i < traslados.length; i++){
            trasladosOrdenadosXGanancias.apilar(traslados[i]);
            trasladosOrdenadosXTimestamp.apilar(traslados[i]);
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            Traslado t = this.trasladosOrdenadosXGanancias.desapilar(0);
            int origen = t.origen;
            int destino = t.destino;
            ciudades[origen].agregarGanancia(t.gananciaNeta);
            this.ciudadesSuperavit.ordenarGananciaN(ciudades[origen].getIndiceHeap());
            ciudades[destino].agregarPerdida(t.gananciaNeta);
            this.ciudadesSuperavit.ordenarPerdidaN(ciudades[destino].getIndiceHeap());
            res[i]=t.id;
            this.trasladosOrdenadosXGanancias.eliminarN(t);
            if(this.mayoresGanancias.isEmpty()){
                this.mayoresGanancias.add(origen);
            }
            else{
                if(this.ciudades[this.mayoresGanancias.get(0)].getGanancia()<ciudades[origen].getGanancia()){
                    this.mayoresGanancias.clear();
                    this.mayoresGanancias.add(origen);
                }
                else if(this.ciudades[this.mayoresGanancias.get(0)].getGanancia()==ciudades[origen].getGanancia()){
                    this.mayoresGanancias.add(origen);
                }
            }
            if(this.mayoresPerdidas.isEmpty()){
                this.mayoresPerdidas.add(destino);
            }
            else{
                if(this.ciudades[this.mayoresPerdidas.get(0)].getGanancia()<ciudades[destino].getGanancia()){
                    this.mayoresPerdidas.clear();
                    this.mayoresPerdidas.add(destino);
                }
                else if(this.ciudades[this.mayoresPerdidas.get(0)].getGanancia()==ciudades[destino].getGanancia()){
                    this.mayoresPerdidas.add(destino);
                }
            }
            this.gananciasTotales+=t.gananciaNeta;
            this.cantidadDespachos+=1;
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];
        for(int i=0;i<n;i++){
            Traslado t = this.trasladosOrdenadosXTimestamp.desapilar(0);
            int origen = t.origen;
            int destino = t.destino;
            this.ciudades[origen].agregarGanancia(t.gananciaNeta);
            this.ciudadesSuperavit.ordenarGananciaN(ciudades[origen].getIndiceHeap());
            this.ciudades[destino].agregarPerdida(t.gananciaNeta);
            this.ciudadesSuperavit.ordenarPerdidaN(ciudades[destino].getIndiceHeap());
            res[i]=t.id;
            this.trasladosOrdenadosXTimestamp.eliminarN(t);
            if(this.mayoresGanancias.isEmpty()){
                this.mayoresGanancias.add(origen);
            }
            else{
                if(this.ciudades[this.mayoresGanancias.get(0)].getGanancia()<ciudades[origen].getGanancia()){
                    this.mayoresGanancias.clear();
                    this.mayoresGanancias.add(origen);
                }
                else if(this.ciudades[this.mayoresGanancias.get(0)].getGanancia()==ciudades[origen].getGanancia()){
                    this.mayoresGanancias.add(origen);
                }
            }
            if(this.mayoresPerdidas.isEmpty()){
                this.mayoresPerdidas.add(destino);
            }
            else{
                if(this.ciudades[this.mayoresPerdidas.get(0)].getGanancia()<ciudades[destino].getGanancia()){
                    this.mayoresPerdidas.clear();
                    this.mayoresPerdidas.add(destino);
                }
                else if(this.ciudades[this.mayoresPerdidas.get(0)].getGanancia()==ciudades[destino].getGanancia()){
                    this.mayoresPerdidas.add(destino);
                }
            }
            this.gananciasTotales+=t.gananciaNeta;
            this.cantidadDespachos+=1;
        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        return ciudadesSuperavit.desapilar(0).getSuperavit();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.mayoresGanancias;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return this.mayoresPerdidas;
    }

    public int gananciaPromedioPorTraslado(){
        return this.gananciasTotales/this.cantidadDespachos;
    }
    
}
