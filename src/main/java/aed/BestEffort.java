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
        mayoresGanancias = new ArrayList<>();
        mayoresPerdidas = new ArrayList<>();
        gananciasTotales = 0;
        cantidadDespachos = 0;
        for(int i = 0; i < cantCiudades; i++){                          // realizo C veces
            Ciudad ciudadAux = new Ciudad(i);
            ciudades[i]=ciudadAux;                                      // O(1)
            mayoresGanancias.add(ciudadAux.getId());
            mayoresPerdidas.add(ciudadAux.getId());
        }
        ciudadesSuperavit.heapificarYAgregar(ciudades);                 // agrego las ciudades a la cola de prioridad O(C) y hago Sift down desde el ultimo elemento hasta el primero (heapify) O(C)
        trasladosOrdenadosXGanancias.heapificarYAgregar(traslados);     // agrego los traslados a las colas de prioridad O(T) y hago Sift down desde el ultimo elemento hasta el primero (heapify) O(T)
        trasladosOrdenadosXTimestamp.heapificarYAgregar(traslados);     // agrego los traslados a las colas de prioridad O(T) y hago Sift down desde el ultimo elemento hasta el primero (heapify) O(T)

    }                                                                   // entonces queda O(T + C)

    public void registrarTraslados(Traslado[] traslados){
        for(int i = 0; i < traslados.length; i++){                  // realiza |traslado| veces
            trasladosOrdenadosXGanancias.apilar(traslados[i]);      // O(log(T)), El arbol está balanceado y se hace una operacion x nivel
            trasladosOrdenadosXTimestamp.apilar(traslados[i]);      // O(log(T)), El arbol está balanceado y se hace una operacion x nivel
        }                                                           // entonces queda O(|traslado|log(T))
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        for(int i=0;i<n;i++){                                                           // realiza n veces
            Traslado t = this.trasladosOrdenadosXGanancias.desapilar();              // desapilo el primer traslado en la cola de prioridad de Ganancias, y como el arbol está balanceado: O(log(T))
            int origen = t.origen;
            int destino = t.destino;
            ciudades[origen].agregarGanancia(t.gananciaNeta);                           // O(1)
            this.ciudadesSuperavit.ordenarGananciaN(ciudades[origen].getIndiceHeap());  // reordeno la cola de prioridad de superavit haciendo Sift up en origen O(log(C))
            ciudades[destino].agregarPerdida(t.gananciaNeta);                           // O(1)
            this.ciudadesSuperavit.ordenarPerdidaN(ciudades[destino].getIndiceHeap());  // reordeno la cola de prioridad de superavit haciendo Sift down en destino O(log(C))
            res[i]=t.id;
            this.trasladosOrdenadosXGanancias.eliminarEnOtroHeap(t);                             // sabiendo el indice puedo desapilar el traslado de la cola de prioridad de Timestamp O(log(T))
            this.actualizarCiudades(origen, destino, t);                                // O(1)
        }                                                                               // entonces queda O(n(log(T) + log(C)))
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];
        for(int i=0;i<n;i++){                                                           // realiza n veces
            Traslado t = this.trasladosOrdenadosXTimestamp.desapilar();              // desapilo el primer traslado en la cola de prioridad de Timestamp O(log(T)) puesto a que el arbol está balanceado
            int origen = t.origen;
            int destino = t.destino;
            this.ciudades[origen].agregarGanancia(t.gananciaNeta);                      // O(1)
            this.ciudadesSuperavit.ordenarGananciaN(ciudades[origen].getIndiceHeap());  // reordeno la cola de prioridad de superavit haciendo Sift up en origen O(log(C))
            this.ciudades[destino].agregarPerdida(t.gananciaNeta);                      // O(1)
            this.ciudadesSuperavit.ordenarPerdidaN(ciudades[destino].getIndiceHeap());  // reordeno la cola de prioridad de superavit haciendo Sift down en destino O(log(C))
            res[i]=t.id;
            this.trasladosOrdenadosXTimestamp.eliminarEnOtroHeap(t);                             // sabiendo el indice puedo desapilar el traslado de la cola de prioridad de Ganancia, y como el arbol está balanceado: O(log(t))
            this.actualizarCiudades(origen, destino, t);                                // O(1)
        }                                                                               // entonces queda O(n(log(T) + log(C)))
        return res;
    }

    private void actualizarCiudades(int origen, int destino, Traslado t){               // al ser en su totalidad operaciones elementales queda O(1)
        if(this.mayoresGanancias.isEmpty()||this.mayoresGanancias.contains(origen)){
            this.mayoresGanancias.clear();
            this.mayoresGanancias.add(origen);
        }
        else{
            int mayorGanancia = this.mayoresGanancias.get(0);
            if(this.ciudades[mayorGanancia].getGanancia()<ciudades[origen].getGanancia()){
                this.mayoresGanancias.clear();
                this.mayoresGanancias.add(origen);
            }
            else if(this.ciudades[mayorGanancia].getGanancia()==ciudades[origen].getGanancia()&&!this.mayoresGanancias.contains(origen)){
                this.mayoresGanancias.add(origen);
            }
        }
        if(this.mayoresPerdidas.isEmpty()||this.mayoresPerdidas.contains(destino)){
            this.mayoresPerdidas.clear();
            this.mayoresPerdidas.add(destino);
        }
        else{
            int mayorPerdida = this.mayoresPerdidas.get(0);
            if(this.ciudades[mayorPerdida].getPerdida()<ciudades[destino].getPerdida()){
                this.mayoresPerdidas.clear();
                this.mayoresPerdidas.add(destino);
            }
            else if(this.ciudades[mayorPerdida].getPerdida()==ciudades[destino].getPerdida()&&!this.mayoresPerdidas.contains(destino)){
                this.mayoresPerdidas.add(destino);
            }
        }
        this.gananciasTotales+=t.gananciaNeta;
        this.cantidadDespachos+=1;
    }

    public int ciudadConMayorSuperavit(){                               // O(1)
        Ciudad c = this.ciudadesSuperavit.getMax();
        int res = this.ciudadesSuperavit.getIdCiudadMayorSuperavit(c);
        return res;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){               // O(1)
        return this.mayoresGanancias;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){                // O(1)
        return this.mayoresPerdidas;
    }

    public int gananciaPromedioPorTraslado(){                           // O(1)
        return this.gananciasTotales/this.cantidadDespachos;
    }
    
}
