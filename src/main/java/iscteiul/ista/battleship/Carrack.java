/**
 * Represents a Carrack (Nau) in the Discoveries Battleship Game.
 * A Carrack occupies 3 consecutive squares on the grid, either horizontally or vertically.
 * It corresponds to the traditional 3-cannon ship in the classic Battleship game.
 * There are 2 Carracks in each player's fleet.
 */
package iscteiul.ista.battleship;

public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Creates a new Carrack at the specified position with the given bearing.
     * The Carrack occupies 3 consecutive squares on the grid:
     * <ul>
     *     <li>NORTH/SOUTH: occupies 3 squares vertically</li>
     *     <li>EAST/WEST: occupies 3 squares horizontally</li>
     * </ul>
     *
     * @param bearing the direction the Carrack is facing on the grid
     * @param pos the upper left starting position of the Carrack on the grid
     * @throws IllegalArgumentException if the bearing is not a valid compass direction
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the size of the Carrack.
     * A Carrack always occupies exactly 3 squares on the grid.
     *
     * @return the size of the Carrack, which is always 3
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
