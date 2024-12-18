package aed;

import java.util.ArrayList;

public abstract class Heap<T extends Comparable<T>> implements ColaDePrioridad<T> {
    private ArrayList<T> lista;

    public Heap(){
        lista = new ArrayList<T>();
    }

    public boolean vacia(){
        return lista.isEmpty();
    }

    public void apilar(T e){
        int indiceActual = this.lista.size();
        this.lista.add(e);                                          // ubico el elemento al final del heap
        this.guardarIndice(e, indiceActual);

        while (indiceActual > 0) {
            indiceActual = this.ordenarDeAbajo(indiceActual);   // hago Sift up (ordenarDeAbajo) para reordenar el heap
        }

    }                                                               // O(log(n))

    public int getTamañoLista(){
        return this.lista.size();
    }

    public T desapilar(){
        return desencolar(0);
    }

    protected int ordenarDeAbajo(int i){
        int indiceActual = i;
        int indicePadre;
        T actual = this.lista.get(indiceActual);
        T padre = this.lista.get((indiceActual - 1) / 2);
        indicePadre = (indiceActual - 1) / 2;

        if (this.esMayor(actual, padre)) {
            this.lista.set(indicePadre, actual);
            this.lista.set(indiceActual, padre);
            this.guardarIndice(actual, indicePadre);
            this.guardarIndice(padre, indiceActual);
        } else {
            indicePadre = -1;
        }
        return indicePadre;
    }

    public T desencolar(int indice){
        T raiz = null;
        T ultimo = null;
        if (this.lista.size() == 1) {
            raiz = this.lista.get(0);
            this.lista.remove(0);
            this.guardarIndice(raiz, -1);
        } else if (this.lista.size() > 1) {
            raiz = this.lista.get(indice);
            this.guardarIndice(raiz, -1);
            ultimo = this.lista.get(lista.size() - 1);
            this.lista.set(indice, ultimo);                 // reemplazo el elemento que busco eliminar por el ultimo
            this.guardarIndice(ultimo, indice);
            this.lista.remove(lista.size() - 1);            // elimino el ultimo
            while (!(indice * 2 + 1 >= this.lista.size() && indice * 2 + 2 >= this.lista.size())) {
                if (this.lista.size() > indice * 2 + 1) {
                    indice = ordenarDeArriba(indice);       // hago Sift down (ordenarDeArriba) para reordenar el heap
                }
            }
        }
        this.eliminarEnOtroHeap(raiz);
        return raiz;
    }                                                       // O(log(n))

    ;

    protected int ordenarDeArriba(int indicePadre){
        T hijoIzq;
        T hijoDer;
        T padre = this.lista.get(indicePadre);
        T hijo = null;
        int indiceHijoMaximo = this.lista.size();
        int indiceHijoDerecho = indicePadre * 2 + 2;
        int indiceHijoIzquierdo =indicePadre * 2 + 1;

        if (this.lista.size() - 1 >= indiceHijoDerecho) {
            hijoDer = this.lista.get(indiceHijoDerecho);
            hijoIzq = this.lista.get(indiceHijoIzquierdo);
            if (this.esMayor(hijoDer, hijoIzq)) {
                hijo = this.lista.get(indiceHijoDerecho);
                indiceHijoMaximo = indiceHijoDerecho;
            } else {
                hijo = this.lista.get(indiceHijoIzquierdo);
                indiceHijoMaximo = indiceHijoIzquierdo;
            }
        } else if (this.lista.size() - 1 == indiceHijoIzquierdo) {
            hijo = this.lista.get(indiceHijoIzquierdo);
            indiceHijoMaximo = indiceHijoIzquierdo;
        }
        if (hijo != null && indiceHijoMaximo < this.lista.size() && this.esMayor(hijo, padre)) {
            this.lista.set(indicePadre, hijo);
            this.lista.set(indiceHijoMaximo, padre);
            this.guardarIndice(hijo, indicePadre);
            this.guardarIndice(padre, indiceHijoMaximo);
        }
        return indiceHijoMaximo;
    }

    public T getMax(){
        if (!this.vacia()) {
            return this.lista.get(0);
        } else {
            return null;
        }
    }

    ;

    protected void heapificarYAgregar(T[] array){       // heapifico el array O(n)
        heapificarArray(array);                         
        for (int i = 0; i < array.length; i++){
            this.lista.add(array[i]);   //O(1)
            this.guardarIndice(array[i], i); //O(1)
        }

    }

    private void heapificarArray(T[] array){
        int n = array.length;
        for (int i = (n/2) -1; i >= 0; i--){
            heapify(array, n, i); //algoritmo de Floyd
        }
    }

    private void heapify(T[] array, int n, int i) {
        int largo = i; // Inicializar el nodo raíz como el mayor
        int izq = (2*i) +1; // Hijo izquierdo
        int der = (2*i) +2; // Hijo derecho

        // Si el hijo izquierdo es mayor que la raíz
        if (izq < n && esMayor(array[izq], array[largo])) {
            largo = izq;
        }

        // Si el hijo derecho es mayor que el más grande actual
        if (der < n && esMayor(array[der], array[largo])) {
            largo = der;
        }

        // Si el más grande no es la raíz
        if (largo != i) {
            T temp = array[i];
            array[i] = array[largo];
            array[largo] = temp;
            heapify(array, n, largo);
        }
    }
    
    protected abstract boolean esMayor(T a, T b);
    protected abstract void guardarIndice(T a, int indice);
    protected abstract void eliminarEnOtroHeap(T a);

}



