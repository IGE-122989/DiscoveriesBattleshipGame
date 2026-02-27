/**
 * Represents a Frigate (Fragata) in the Discoveries Battleship Game.
 * A Frigate occupies 4 consecutive squares on the grid, either horizontally or vertically.
 * It corresponds to the traditional 4-cannon ship in the classic Battleship game.
 * There is 1 Frigate in each player's fleet.
 */
package iscteiul.ista.battleship;

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Creates a new Frigate at the specified position with the given bearing.
     * The Frigate occupies 4 consecutive squares on the grid:
     * <ul>
     *     <li>NORTH/SOUTH: occupies 4 squares vertically</li>
     *     <li>EAST/WEST: occupies 4 squares horizontally</li>
     * </ul>
     *
     * @param bearing the direction the Frigate is facing on the grid
     * @param pos the upper left starting position of the Frigate on the grid
     * @throws IllegalArgumentException if the bearing is not a valid compass direction
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Returns the size of the Frigate.
     * A Frigate always occupies exactly 4 squares on the grid.
     *
     * @return the size of the Frigate, which is always 4
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}
