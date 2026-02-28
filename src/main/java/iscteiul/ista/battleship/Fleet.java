/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships belonging to a player in the Battleship game.
 *
 * A Fleet is responsible for managing ship placement, verifying
 * collisions and board limits, and providing information about
 * the current state of the ships.
 */
public class Fleet implements IFleet {

    /**
     * Prints all ships in the given list.
     *
     * @param ships the list of ships to be printed
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Creates an empty fleet.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns all ships in the fleet.
     *
     * @return the list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Attempts to add a ship to the fleet.
     * A ship is only added if:
     * - the fleet size limit is not exceeded
     * - the ship is inside the board boundaries
     * - there is no collision risk with existing ships
     *
     * @param s the ship to be added
     * @return true if the ship was successfully added, false otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns all ships belonging to a given category.
     *
     * @param category the ship category
     * @return a list of ships matching the given category
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Returns all ships that are still floating (not sunk).
     *
     * @return a list of ships still floating
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Returns the ship occupying a given position.
     *
     * @param pos the position to check
     * @return the ship occupying the position, or null if none exists
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 &&
                s.getTopMostPos() >= 0 && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Prints the overall state of the fleet.
     * Includes all ships, floating ships, and ships grouped by category.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all ships belonging to a specific category.
     *
     * @param category the category of ships to print
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships that are still floating.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }
}