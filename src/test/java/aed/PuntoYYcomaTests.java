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
            new Traslado(2, 0, 1, 200, 30),
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
        assertEquals(5, trasladosAntiguos[0]); //i=0 corresponde al traslado
        assertEquals(4, trasladosAntiguos[1]);
    }

    @Test
    void testDespacharMasRedituables() {
        int[] trasladosRedituables = bestEffort.despacharMasRedituables(2);
        assertEquals(6, trasladosRedituables[0]);
        assertEquals(2, trasladosRedituables[1]);
    }

    @Test
    void testCiudadConMayorSuperavitInicial() {
        int ciudadSuperavit = bestEffort.ciudadConMayorSuperavit();
        assertTrue(ciudadSuperavit >= 0);
    }

    @Test
    void testCiudadesConMayorGanancia() {
        bestEffort.despacharMasRedituables(3);
        ArrayList<Integer> ciudadesConGanancia = bestEffort.ciudadesConMayorGanancia();
        assertTrue(ciudadesConGanancia.contains(6));
    }

    @Test
    void testCiudadesConMayorPerdida() {
        bestEffort.despacharMasRedituables(3);
        ArrayList<Integer> ciudadesConPerdida = bestEffort.ciudadesConMayorPerdida();
        assertTrue(ciudadesConPerdida.contains(2));
    }

    @Test
    void testGananciaPromedioPorTraslado() {
        bestEffort.despacharMasRedituables(3);
        int gananciaPromedio = bestEffort.gananciaPromedioPorTraslado();
        assertEquals(226, gananciaPromedio);
    }

    @Test
    void testRegistrarTrasladosAdicionales() {
        Traslado[] nuevosTraslados = {
            new Traslado(7, 1, 2, 250, 70),
            new Traslado(8, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados);

        int[] trasladosAntiguos = bestEffort.despacharMasAntiguos(2);
        assertEquals(5, trasladosAntiguos[0]);
        assertEquals(4, trasladosAntiguos[1]);
    }
    @Test
    void pruebaModificacionCiudadesSuperavit() {
        Traslado[] nuevosTraslados = {
                new Traslado(7, 1, 2, 250, 70),
                new Traslado(8, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados);

        bestEffort.despacharMasRedituables(1);
        assertEquals(3, bestEffort.ciudadConMayorSuperavit());
        Traslado[] nuevosTraslados2 = {
                new Traslado(9, 1, 2, 25000, 70),
                new Traslado(10, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados2);
        bestEffort.despacharMasRedituables(1);
        assertEquals(1, bestEffort.ciudadConMayorSuperavit());
        Traslado[] nuevosTraslados3 = {
                new Traslado(11, 4, 2, 2500000, 70),
                new Traslado(12, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados3);
        bestEffort.despacharMasRedituables(1);
        assertEquals(4, bestEffort.ciudadConMayorSuperavit());


    }
    @Test
    void pruebaModificacionCiudadesGananciayPerdida() {
        Traslado[] nuevosTraslados = {
                new Traslado(7, 1, 2, 250, 70),
                new Traslado(8, 3, 5, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados);

        bestEffort.despacharMasRedituables(1);
        ArrayList<Integer> ciudades = new ArrayList<Integer>();
        ArrayList<Integer> perdidas = new ArrayList<Integer>();
        ciudades.add(3);
        perdidas.add(5);
        assertEquals(ciudades, bestEffort.ciudadesConMayorGanancia());
        assertEquals(perdidas, bestEffort.ciudadesConMayorPerdida());
        Traslado[] nuevosTraslados2 = {
                new Traslado(9, 1, 2, 250, 70),
                new Traslado(10, 6, 2, 350, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados2);
        bestEffort.despacharMasRedituables(1);
        ciudades.add(6);
        perdidas.add(2);
        assertEquals(ciudades, bestEffort.ciudadesConMayorGanancia());
        assertEquals(perdidas, bestEffort.ciudadesConMayorPerdida());
        Traslado[] nuevosTraslados3 = {
                new Traslado(12, 1, 2, 250, 70),
                new Traslado(13, 4, 5, 350000, 80)
        };
        bestEffort.registrarTraslados(nuevosTraslados3);
        bestEffort.despacharMasRedituables(1);
        ciudades.clear();
        ciudades.add(4);
        perdidas.clear();
        perdidas.add(5);
        assertEquals(ciudades, bestEffort.ciudadesConMayorGanancia());

    }

}