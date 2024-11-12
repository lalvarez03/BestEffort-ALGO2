package aed;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PuntoYYcomaTests {

    int cantCiudades;
    Traslado[] listaTraslados;
    BestEffort bestEffort;

    @BeforeEach
    void init() {
        // Configuración inicial de ciudades y traslados
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
            new Traslado(1, 0, 1, 100, 50),
            new Traslado(2, 0, 1, 200, 30), // Timestamp más antiguo
            new Traslado(3, 3, 4, 150, 40),
            new Traslado(4, 4, 3, 180, 20),
            new Traslado(5, 1, 0, 120, 10),
            new Traslado(6, 6, 2, 300, 60)
        };
        bestEffort = new BestEffort(cantCiudades, listaTraslados);
    }

    @Test
    void testDespacharMasAntiguos() {
        int[] trasladosAntiguos = bestEffort.despacharMasAntiguos(2);
        // Verificamos que los traslados devueltos sean los de menor timestamp
        assertEquals(5, trasladosAntiguos[0], "El traslado con el timestamp más antiguo debería ser el 5");
        assertEquals(4, trasladosAntiguos[1], "El segundo traslado más antiguo debería ser el 4");
    }

    @Test
    void testDespacharMasRedituables() {
        int[] trasladosRedituables = bestEffort.despacharMasRedituables(2);
        // Comprobamos los traslados con mayores ganancias netas
        assertEquals(6, trasladosRedituables[0], "El traslado con mayor ganancia debería ser el 6");
        assertEquals(2, trasladosRedituables[1], "El segundo traslado con mayor ganancia debería ser el 2");
    }

    @Test
    void testCiudadConMayorSuperavit() {
        int ciudadSuperavit = bestEffort.ciudadConMayorSuperavit();
        // Comprobamos si se calcula correctamente la ciudad con mayor superávit
        assertTrue(ciudadSuperavit >= 0, "La ciudad con mayor superávit debería tener un valor válido de superávit");
    }

    @Test
    void testCiudadesConMayorGanancia() {
        bestEffort.despacharMasRedituables(3);
        ArrayList<Integer> ciudadesConGanancia = bestEffort.ciudadesConMayorGanancia();
        assertTrue(ciudadesConGanancia.contains(6), "La ciudad con la mayor ganancia debería incluir la ciudad 6");
    }

    @Test
    void testCiudadesConMayorPerdida() {
        bestEffort.despacharMasRedituables(3);
        ArrayList<Integer> ciudadesConPerdida = bestEffort.ciudadesConMayorPerdida();
        assertTrue(ciudadesConPerdida.contains(2), "La ciudad con la mayor pérdida debería incluir la ciudad 2");
    }

    @Test
    void testGananciaPromedioPorTraslado() {
        bestEffort.despacharMasRedituables(3);
        int gananciaPromedio = bestEffort.gananciaPromedioPorTraslado();
        assertEquals(226, gananciaPromedio, "La ganancia promedio debería ser correcta después de despachar traslados");
    }

    @Test
    void testRegistrarTrasladosAdicionales() {
        Traslado[] nuevosTraslados = {
            new Traslado(7, 1, 2, 250, 70),
            new Traslado(8, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados);

        int[] trasladosAntiguos = bestEffort.despacharMasAntiguos(2);
        assertEquals(5, trasladosAntiguos[0], "El traslado original con el timestamp más antiguo debería ser el primero");
        assertEquals(4, trasladosAntiguos[1], "El segundo traslado más antiguo debería ser el siguiente en orden");
    }
}