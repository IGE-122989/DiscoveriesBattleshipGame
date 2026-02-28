/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Battleship game session.
 *
 * The Game class manages the fleet, registers shots,
 * validates them, and keeps statistics about hits,
 * invalid shots, repeated shots and sunk ships.
 */
public class Game implements IGame {

    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;

    /**
     * Creates a new Game with the given fleet.
     *
     * @param fleet the fleet used in this game
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Fires a shot at the given position.
     *
     * If the shot is invalid, the invalid shot counter is increased.
     * If the shot was already fired, the repeated shot counter is increased.
     * If the shot hits a ship, the hit counter is increased and,
     * if the ship is sunk, the sink counter is also increased.
     *
     * @param pos the position to fire at
     * @return the ship that was sunk by this shot, or null otherwise
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the list of shots fired during the game.
     *
     * @return the list of shot positions
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Returns the number of repeated shots.
     *
     * @return the number of repeated shots
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Returns the number of invalid shots.
     *
     * @return the number of invalid shots
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Returns the number of successful hits.
     *
     * @return the number of hits
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Returns the number of sunk ships.
     *
     * @return the number of sunk ships
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Returns the number of ships still floating.
     *
     * @return the number of remaining ships
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE &&
                pos.getColumn() >= 0 && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Prints a board representation marking the given positions
     * with the specified character.
     *
     * @param positions the positions to mark
     * @param marker the character used to mark those positions
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Prints the board showing valid shots that have been fired.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board showing the fleet positions.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}