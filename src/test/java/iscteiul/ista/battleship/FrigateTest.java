// Java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrigateTest {

    @Test
    @DisplayName("getSize devolve 4 (tamanho fixo da Fragata)")
    void getSize() {
        Frigate f = new Frigate(Compass.NORTH, new Position(0, 0));
        assertEquals(4, f.getSize());
    }

    @ParameterizedTest(name = "Frigate {0}: cria 4 posições VERTICAIS (mesma coluna, linhas consecutivas)")
    @EnumSource(value = Compass.class, names = {"NORTH", "SOUTH"})
    @DisplayName("Construtor cria 4 posições verticais para NORTH/SOUTH")
    void positionsForNorthSouth(Compass bearing) {
        Position origin = new Position(1, 2);
        Frigate f = new Frigate(bearing, origin);

        List<IPosition> pos = f.getPositions();
        assertEquals(4, pos.size());

        assertAll(
                () -> assertEquals(1, pos.get(0).getRow()),
                () -> assertEquals(2, pos.get(0).getColumn()),
                () -> assertEquals(2, pos.get(1).getRow()),
                () -> assertEquals(2, pos.get(1).getColumn()),
                () -> assertEquals(3, pos.get(2).getRow()),
                () -> assertEquals(2, pos.get(2).getColumn()),
                () -> assertEquals(4, pos.get(3).getRow()),
                () -> assertEquals(2, pos.get(3).getColumn())
        );

        // primeira posição é cópia lógica do origin (mesmas coords) mas instância diferente
        assertAll(
                () -> assertEquals(origin, pos.get(0)),
                () -> assertNotSame(origin, pos.get(0))
        );
    }

    @ParameterizedTest(name = "Frigate {0}: cria 4 posições HORIZONTAIS (mesma linha, colunas consecutivas)")
    @EnumSource(value = Compass.class, names = {"EAST", "WEST"})
    @DisplayName("Construtor cria 4 posições horizontais para EAST/WEST")
    void positionsForEastWest(Compass bearing) {
        Position origin = new Position(5, 6);
        Frigate f = new Frigate(bearing, origin);

        List<IPosition> pos = f.getPositions();
        assertEquals(4, pos.size());

        assertAll(
                () -> assertEquals(5, pos.get(0).getRow()),
                () -> assertEquals(6, pos.get(0).getColumn()),
                () -> assertEquals(5, pos.get(1).getRow()),
                () -> assertEquals(7, pos.get(1).getColumn()),
                () -> assertEquals(5, pos.get(2).getRow()),
                () -> assertEquals(8, pos.get(2).getColumn()),
                () -> assertEquals(5, pos.get(3).getRow()),
                () -> assertEquals(9, pos.get(3).getColumn())
        );

        assertAll(
                () -> assertEquals(origin, pos.get(0)),
                () -> assertNotSame(origin, pos.get(0))
        );
    }

    @Test
    @DisplayName("Construtor lança IllegalArgumentException se bearing == UNKNOWN")
    void constructorThrowsIllegalArgumentIfBearingIsUnknown() {
        Position pos = new Position(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new Frigate(Compass.UNKNOWN, pos));
    }

    @Test
    @DisplayName("Construtor com bearing == null lança AssertionError (Ship) ou NullPointerException (switch)")
    void constructorThrowsIfBearingIsNull() {
        Position pos = new Position(1, 1);
        Throwable t = assertThrows(Throwable.class, () -> new Frigate(null, pos));
        assertTrue(
                t instanceof AssertionError || t instanceof NullPointerException,
                () -> "Esperava AssertionError ou NullPointerException, mas foi: " + t.getClass()
        );
    }

    @Test
    @DisplayName("Alterar a posição original após o construtor não afeta posições internas (cópias independentes)")
    void originalModificationDoesNotAffectInternalPositions() {
        Position origin = new Position(3, 3);
        Frigate f = new Frigate(Compass.EAST, origin);

        origin.shoot(); // altera só o original

        List<IPosition> pos = f.getPositions();
        assertAll(
                () -> assertFalse(pos.get(0).isHit()),
                () -> assertFalse(pos.get(1).isHit()),
                () -> assertFalse(pos.get(2).isHit()),
                () -> assertFalse(pos.get(3).isHit())
        );
    }

    @Test
    @DisplayName("Alterar posição interna não afeta a posição original (independência nos dois sentidos)")
    void internalModificationDoesNotAffectOriginal() {
        Position origin = new Position(4, 4);
        Frigate f = new Frigate(Compass.SOUTH, origin);

        IPosition stored = f.getPositions().get(3);
        stored.occupy(); // altera apenas a interna

        assertAll(
                () -> assertTrue(stored.isOccupied()),
                () -> assertFalse(origin.isOccupied())
        );
    }
}
