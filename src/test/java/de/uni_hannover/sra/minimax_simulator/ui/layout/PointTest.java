package de.uni_hannover.sra.minimax_simulator.ui.layout;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the implementation of {@link Point}.
 *
 * @author Philipp Rohde
 */
public class PointTest {

    /**
     * Actually runs the test.
     */
    @Test
    public void test() {
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(0, 0);
        Point p3 = new Point(10, 20);

        assertEquals("hash of p1", calculateHash(-1, -1), p1.hashCode());
        assertEquals("hash of p2", calculateHash(0, 0), p2.hashCode());
        assertEquals("hash of p3", calculateHash(10, 20), p3.hashCode());

        assertEquals("string of p1", "[-1,-1]", p1.toString());
        assertEquals("string of p2", "[0,0]", p2.toString());
        assertEquals("string of p3", "[10,20]", p3.toString());

        assertEquals("self comparison", true, p1.equals(p1));
        assertEquals("null comparison", false, p1.equals(null));
        assertEquals("other class comparison", false, p2.equals(new Dimension(0, 0)));
        assertEquals("different x", false, p1.equals(new Point(0, -1)));
        assertEquals("different y", false, p2.equals(new Point(0, 3)));
        assertEquals("same values", true, p3.equals(new Point(10, 20)));
    }

    /**
     * Calculates the expected hash of the object with the specified x and y values.
     *
     * @param x
     *         the x-coordinate of the object to calculate the hash for
     * @param y
     *         the y-coordinate of the object to calculate the hash for
     * @return
     *         the expected hash code
     */
    private int calculateHash(int x, int y) {
        return 31 * (31 + x) + y;
    }
}
