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
        int indiceActual = 0;
        if(!lista.isEmpty()){
            indiceActual = this.lista.size();
        }
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
        T raiz = null;
        T ultimo = null;
        int indice = 0;
        if(this.lista.size()==1){
            raiz = this.lista.get(0);
            this.lista.remove(0);
        }
        else if(this.lista.size()>1){
            raiz = this.lista.get(0);
            ultimo = this.lista.get(lista.size()-1);
            this.lista.set(0, ultimo);
            this.lista.remove(lista.size()-1);
            while(true){
                indice = ordenarDeArriba(indice);
                if(indice*2+1>=this.lista.size()&&indice*2+2>=this.lista.size()){
                    break;
                }
            }
        }
        return raiz;
    };

    private int ordenarDeArriba(int i){
        int indicePadre = i;
        T hijoIzq;
        T hijoDer;
        T padre = this.lista.get(indicePadre);
        T hijo=null;
        int indiceHijoMaximo=this.lista.size();
        if(this.lista.size()-1<=indicePadre*2+2){
            hijoDer=this.lista.get(indicePadre*2+2);
            hijoIzq=this.lista.get(indicePadre*2+1);
            if(hijoDer.compareTo(hijoIzq)>=0){
                hijo = this.lista.get(indicePadre*2+2);
                indiceHijoMaximo = indicePadre*2+2;
            }
            else{
                hijo = this.lista.get(indicePadre*2+1);
                indiceHijoMaximo = indicePadre*2+1;
            }
        }
        else if (this.lista.size()-1<=indicePadre*2+1) {
            hijo = this.lista.get(indicePadre*2+1);
            indiceHijoMaximo = indicePadre*2+1;
        }
        if (hijo!=null&&indiceHijoMaximo<this.lista.size()&&padre.compareTo(hijo)<0){
            this.lista.set(indicePadre, hijo);
            this.lista.set(indiceHijoMaximo, padre);
        }
        return indiceHijoMaximo;
    }

    public T getMax(){
        return null;
    };

    public void  verLista(){
        String response = "[";
        for(int i = 0; i<this.lista.size(); i++){
            response += this.lista.get(i).toString();
            response += ", ";
        }
        response+="]";
        System.out.println(response);
    }
}
