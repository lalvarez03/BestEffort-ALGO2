package aed;

public class HeapCiudadesSuperavit extends Heap<Ciudad>{

    
    @Override
    protected boolean esMayor(Ciudad a, Ciudad b) {
        // Compara por ganancia neta. Si son iguales, compara por ID para garantizar orden único.
        if (a.getSuperavit() != b.getSuperavit()) {
            return a.getSuperavit() > b.getSuperavit();
        }
        return a.getSuperavit() > b.getSuperavit(); // Desempata usando el ID
    }

}
