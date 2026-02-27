/**
 * Represents a Barge (Barca) in the Discoveries Battleship Game.
 * A Barge is the smallest ship in the game, occupying only 1 square on the grid.
 * It corresponds to the traditional Submarine in the classic Battleship game.
 */
package iscteiul.ista.battleship;

public class Barge extends Ship {
    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Creates a new Barge at the specified position with the given bearing.
     * The Barge occupies exactly 1 position on the grid.
     *
     * @param bearing the direction the barge is facing, either horizontal or vertical
     * @param pos the upper left position of the barge on the grid
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the Barge.
     * A Barge always occupies exactly 1 square on the grid.
     *
     * @return the size of the Barge, which is always 1
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}