package de.uni_hannover.sra.minimax_simulator.ui.schematics.parts;

import de.uni_hannover.sra.minimax_simulator.config.Config;
import de.uni_hannover.sra.minimax_simulator.model.machine.part.Pin;
import de.uni_hannover.sra.minimax_simulator.ui.layout.Bounds;
import de.uni_hannover.sra.minimax_simulator.ui.layout.Point;
import de.uni_hannover.sra.minimax_simulator.ui.schematics.render.RenderEnvironment;
import de.uni_hannover.sra.minimax_simulator.ui.schematics.render.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

/**
 * A {@link Sprite} of a component of the Minimax machine's circuit.
 *
 * @author Martin L&uuml;ck
 * @author Philipp Rohde
 */
public abstract class CircuitSprite implements Sprite {

    /**
     * A polygon that looks like an arrow head and can be drawn with
     * {@link GraphicsContext#fillPolygon(double[], double[], int)}.
     *
     * @author Philipp Rohde
     */
    private static final class ArrowHead {

        private static final double[] X_POINTS = {0, -3, 3};
        private static final double[] Y_POINTS = {0, -5, -5};
        public static final int N_POINTS = 3;

        /**
         * Prevents instance creation.
         */
        private ArrowHead() {

        }
    }

    /** The {@code Color} for debugging the schematics. */
    protected static final Color RED = new Color(1f, 0f, 0f, 0.8f);

    @Override
    public void paint(GraphicsContext gc, RenderEnvironment env) { paint(gc); }

    /**
     * Draws the {@code CircuitSprite} on a {@link GraphicsContext} of a {@link javafx.scene.canvas.Canvas}.
     *
     * @param gc
     *          the {@code GraphicsContext} the {@code CircuitSprite} will be drawn on
     */
    public abstract void paint(GraphicsContext gc);

    /**
     * Draws the arrow head at the end of a wire.<br>
     * Therefore the polygon will be translated and rotated.
     *
     * @param gc
     *          the {@code GraphicsContext} the arrow will be drawn on
     * @param point1
     *          the starting point
     * @param point2
     *          the end point
     */
    public void drawArrow(GraphicsContext gc, Point point1, Point point2) {
        double angle = Math.atan2( (double) point2.y - point1.y, (double) point2.x - point1.x);
        Affine tx = new Affine();
        tx.setToIdentity();
        tx.appendTranslation(point2.x, point2.y);
        tx.appendRotation(Math.toDegrees(angle) - 90);

        Transform oldTransform = gc.getTransform();
        gc.setTransform(tx);
        gc.fillPolygon(ArrowHead.X_POINTS, ArrowHead.Y_POINTS, ArrowHead.N_POINTS);
        gc.setTransform((Affine)oldTransform);

    }

    /**
     * Debugs {@link Bounds} on a {@code GraphicsContext}.
     *
     * @param gc
     *          the {@code GraphicsContext} the {@code Bounds} will be debugged on
     * @param bounds
     *          the {@code Bounds} to debug
     */
    public void debugBounds(GraphicsContext gc, Bounds bounds) {
        if (Config.getIsDebugSchematics()) {
            gc.setStroke(RED);
            gc.strokeRect(bounds.x, bounds.y, bounds.w, bounds.h);
            gc.setStroke(Color.BLACK);
        }
    }

    /**
     * Debugs a {@link Pin} on a {@code GraphicsContext}.
     *
     * @param gc
     *          the {@code GraphicsContext} the {@code Pin} will be debugged on
     * @param pin
     *          the {@code Pin} to debug
     */
    public void debugPin(GraphicsContext gc, Pin pin) {
        if (Config.getIsDebugSchematics()) {
            debugPosition(gc, pin.getBounds().x, pin.getBounds().y);
        }
    }

    /**
     * Debugs a point on a {@code GraphicsContext}.
     *
     * @param gc
     *          the {@code GraphicsContext} the point will be debugged on
     * @param x
     *          the x coordinate of the point to debug
     * @param y
     *          the y coordinate of the point to debug
     */
    public void debugPosition(GraphicsContext gc, int x, int y) {
        if (Config.getIsDebugSchematics()) {
            gc.setFill(RED);
            gc.fillOval(x - 2, y - 2, 5, 5);
            gc.setFill(Color.BLACK);
        }
    }
}