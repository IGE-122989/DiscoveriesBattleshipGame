// java
package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class GalleonTest {

    @Test
    @DisplayName("getSize devolve 5 (tamanho fixo do Galeão)")
    void getSize() {
        Galleon g = new Galleon(Compass.NORTH, new Position(0, 0));
        assertEquals(5, g.getSize());
    }

    @Test
    @DisplayName("NORTH: padrão (r,c)..(r,c+2),(r+1,c+1),(r+2,c+1)")
    void positionsForNorth() {
        Position origin = new Position(5, 5);
        Galleon g = new Galleon(Compass.NORTH, origin);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertAll(
                () -> assertEquals(5, pos.get(0).getRow()),
                () -> assertEquals(5, pos.get(0).getColumn()),
                () -> assertEquals(5, pos.get(1).getRow()),
                () -> assertEquals(6, pos.get(1).getColumn()),
                () -> assertEquals(5, pos.get(2).getRow()),
                () -> assertEquals(7, pos.get(2).getColumn()),
                () -> assertEquals(6, pos.get(3).getRow()),
                () -> assertEquals(6, pos.get(3).getColumn()),
                () -> assertEquals(7, pos.get(4).getRow()),
                () -> assertEquals(6, pos.get(4).getColumn())
        );

        assertAll(
                () -> assertEquals(origin, pos.get(0)),
                () -> assertNotSame(origin, pos.get(0))
        );
    }

    @Test
    @DisplayName("SOUTH: padrão (r,c),(r+1,c),(r+2,c-1),(r+2,c),(r+2,c+1)")
    void positionsForSouth() {
        Position origin = new Position(2, 2);
        Galleon g = new Galleon(Compass.SOUTH, origin);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertAll(
                () -> assertEquals(2, pos.get(0).getRow()),
                () -> assertEquals(2, pos.get(0).getColumn()),
                () -> assertEquals(3, pos.get(1).getRow()),
                () -> assertEquals(2, pos.get(1).getColumn()),
                () -> assertEquals(4, pos.get(2).getRow()),
                () -> assertEquals(1, pos.get(2).getColumn()),
                () -> assertEquals(4, pos.get(3).getRow()),
                () -> assertEquals(2, pos.get(3).getColumn()),
                () -> assertEquals(4, pos.get(4).getRow()),
                () -> assertEquals(3, pos.get(4).getColumn())
        );

        assertAll(
                () -> assertEquals(origin, pos.get(0)),
                () -> assertNotSame(origin, pos.get(0))
        );
    }

    @Test
    @DisplayName("EAST: padrão (r,c),(r+1,c-2),(r+1,c-1),(r+1,c),(r+2,c)")
    void positionsForEast() {
        Position origin = new Position(2, 3);
        Galleon g = new Galleon(Compass.EAST, origin);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertAll(
                () -> assertEquals(2, pos.get(0).getRow()),
                () -> assertEquals(3, pos.get(0).getColumn()),
                () -> assertEquals(3, pos.get(1).getRow()),
                () -> assertEquals(1, pos.get(1).getColumn()),
                () -> assertEquals(3, pos.get(2).getRow()),
                () -> assertEquals(2, pos.get(2).getColumn()),
                () -> assertEquals(3, pos.get(3).getRow()),
                () -> assertEquals(3, pos.get(3).getColumn()),
                () -> assertEquals(4, pos.get(4).getRow()),
                () -> assertEquals(3, pos.get(4).getColumn())
        );

        assertAll(
                () -> assertEquals(origin, pos.get(0)),
                () -> assertNotSame(origin, pos.get(0))
        );
    }

    @Test
    @DisplayName("WEST: padrão (r,c),(r+1,c),(r+1,c+1),(r+1,c+2),(r+2,c)")
    void positionsForWest() {
        Position origin = new Position(7, 7);
        Galleon g = new Galleon(Compass.WEST, origin);

        List<IPosition> pos = g.getPositions();
        assertEquals(5, pos.size());

        assertAll(
                () -> assertEquals(7, pos.get(0).getRow()),
                () -> assertEquals(7, pos.get(0).getColumn()),
                () -> assertEquals(8, pos.get(1).getRow()),
                () -> assertEquals(7, pos.get(1).getColumn()),
                () -> assertEquals(8, pos.get(2).getRow()),
                () -> assertEquals(8, pos.get(2).getColumn()),
                () -> assertEquals(8, pos.get(3).getRow()),
                () -> assertEquals(9, pos.get(3).getColumn()),
                () -> assertEquals(9, pos.get(4).getRow()),
                () -> assertEquals(7, pos.get(4).getColumn())
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
        assertThrows(IllegalArgumentException.class, () -> new Galleon(Compass.UNKNOWN, pos));
    }

    @Test
    @DisplayName("Construtor com bearing == null lança NullPointerException ou AssertionError")
    void constructorThrowsIfBearingIsNull() {
        Position pos = new Position(1, 1);
        Throwable t = assertThrows(Throwable.class, () -> new Galleon(null, pos));
        assertTrue(
                t instanceof NullPointerException || t instanceof AssertionError,
                () -> "Esperava NullPointerException ou AssertionError, mas foi: " + t.getClass()
        );
    }

    @Test
    @DisplayName("Alterar origem não afeta posições internas")
    void originalModificationDoesNotAffectInternalPositions() {
        Position origin = new Position(4, 4);
        Galleon g = new Galleon(Compass.NORTH, origin);

        origin.shoot();

        List<IPosition> pos = g.getPositions();
        assertAll(
                () -> assertFalse(pos.get(0).isHit()),
                () -> assertFalse(pos.get(1).isHit()),
                () -> assertFalse(pos.get(2).isHit()),
                () -> assertFalse(pos.get(3).isHit()),
                () -> assertFalse(pos.get(4).isHit())
        );
    }

    @Test
    @DisplayName("Alterar posição interna não afeta origem")
    void internalModificationDoesNotAffectOriginal() {
        Position origin = new Position(6, 6);
        Galleon g = new Galleon(Compass.SOUTH, origin);

        IPosition internal = g.getPositions().get(4);
        internal.occupy();

        assertAll(
                () -> assertTrue(internal.isOccupied()),
                () -> assertFalse(origin.isOccupied())
        );
    }
}
