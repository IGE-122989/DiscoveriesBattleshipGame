/**
 *
 */
package iscteiul.ista.battleship;

/**
 * Represents the possible directions (bearings) in the Battleship game.
 *
 * Each direction is associated with a character representation:
 * 'n' for North, 's' for South, 'e' for East, 'o' for West,
 * and 'u' for Unknown.
 */
public enum Compass {
    NORTH('n'), SOUTH('s'), EAST('e'), WEST('o'), UNKNOWN('u');

    private final char c;

    /**
     * Creates a Compass direction associated with a character.
     *
     * @param c the character representing the direction
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character associated with this direction.
     *
     * @return the character representing the direction
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the string representation of this direction.
     *
     * @return the direction as a String
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a character into the corresponding Compass direction.
     * If the character does not match any valid direction,
     * UNKNOWN is returned.
     *
     * @param ch the character representing a direction
     * @return the corresponding Compass value
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}