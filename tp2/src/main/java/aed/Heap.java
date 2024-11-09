package aed;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> implements ColaDePrioridad<T> {
    private ArrayList<T> lista; 

    public Heap(){
        this.lista = new ArrayList();
    }

    public boolean vacia(){
        return lista.isEmpty();
    };

    public void apilar(T e){
        int indiceActual = this.lista.size()-1;
        this.lista.add(e);
        if (!this.vacia()){
            while(indiceActual>0){
                indiceActual = this.ordenarDeAbajo(indiceActual);
            }
        }
    };

    private int ordenarDeAbajo(int i){
        int indiceActual = i;
        int indicePadre;
        T actual = this.lista.get(indiceActual);
        T padre = this.lista.get((indiceActual-1)/2);
        if(indiceActual%2!=0){
            indicePadre = (indiceActual-1)/2;
        }
        else{
            indicePadre = (indiceActual-2)/2;
        }
        if (padre.compareTo(actual)<0){
            this.lista.set(indicePadre, actual);
            this.lista.set(indiceActual, padre);
        }
        else{
            indicePadre = -1;
        }
        return indicePadre;
    }
    public T desapilarMax(){
        return null;
    };

    private int ordenarDeArriba(int i){
        return 0;
    }
    
    public T getMax(){
        return null;
    };
}
