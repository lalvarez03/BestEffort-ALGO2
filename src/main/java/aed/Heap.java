package aed;

import java.util.ArrayList;

public abstract class Heap<T extends Comparable<T>> implements ColaDePrioridad<T> {
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
        this.guardarIndice(e,indiceActual);
        if (!this.vacia()){
            while(indiceActual>0){
                indiceActual = this.ordenarDeAbajo(indiceActual);
            }
        }
    };

    public int getTamañoLista(){
        return this.lista.size();
    }

    protected int ordenarDeAbajo(int i){
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
        if (this.esMayor(actual, padre)){
            this.lista.set(indicePadre, actual);
            this.lista.set(indiceActual, padre);
            this.guardarIndice(actual, indicePadre);
            this.guardarIndice(padre, indiceActual);
        }
        else{
            indicePadre = -1;
        }
        return indicePadre;
    }

    public T desapilar(int i){
        T raiz = null;
        T ultimo = null;
        int indice = i;
        if(this.lista.size()==1){
            raiz = this.lista.get(0);
            this.lista.remove(0);
            this.guardarIndice(raiz, -1);
        }
        else if(this.lista.size()>1){
            raiz = this.lista.get(indice);
            this.guardarIndice(raiz, -1);
            ultimo = this.lista.get(lista.size()-1);
            this.lista.set(indice, ultimo);
            this.guardarIndice(ultimo, indice);
            this.lista.remove(lista.size()-1);
            while(true){
                if(this.lista.size()>indice*2+1){
                    indice = ordenarDeArriba(indice);
                }
                if(indice*2+1>=this.lista.size()&&indice*2+2>=this.lista.size()){
                    break;
                }
            }
        }
        this.eliminarN(raiz);
        return raiz;
    };
    
    protected int ordenarDeArriba(int i){
        int indicePadre = i;
        T hijoIzq;
        T hijoDer;
        T padre = this.lista.get(indicePadre);
        T hijo=null;
        int indiceHijoMaximo=this.lista.size();
        if(this.lista.size()-1>=indicePadre*2+2){
            hijoDer=this.lista.get(indicePadre*2+2);
            hijoIzq=this.lista.get(indicePadre*2+1);
            if(this.esMayor(hijoDer, hijoIzq)){
                hijo = this.lista.get(indicePadre*2+2);
                indiceHijoMaximo = indicePadre*2+2;
            }
            else{
                hijo = this.lista.get(indicePadre*2+1);
                indiceHijoMaximo = indicePadre*2+1;
            }
        }
        else if (this.lista.size()-1==indicePadre*2+1) {
            hijo = this.lista.get(indicePadre*2+1);
            indiceHijoMaximo = indicePadre*2+1;
        }
        if (hijo!=null&&indiceHijoMaximo<this.lista.size()&&this.esMayor(hijo, padre)){
            this.lista.set(indicePadre, hijo);
            this.lista.set(indiceHijoMaximo, padre);
            this.guardarIndice(hijo, indicePadre);
            this.guardarIndice(padre, indiceHijoMaximo);
        }
        return indiceHijoMaximo;
    }

    public T getMax(){
        if(!this.vacia()){
            return this.lista.get(0);
        }
        else{
            return null;
        }
    };

    protected abstract boolean esMayor(T a, T b);
    protected abstract void guardarIndice(T a, int indice); 
    protected abstract void eliminarN(T a);


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
