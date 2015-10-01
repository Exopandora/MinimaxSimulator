package de.uni_hannover.sra.minimax_simulator.ui.schematics.parts;

import static com.google.common.base.Preconditions.*;

import java.awt.Graphics2D;

import de.uni_hannover.sra.minimax_simulator.layout.Bounds;
import de.uni_hannover.sra.minimax_simulator.model.machine.part.Constant;
import de.uni_hannover.sra.minimax_simulator.ui.render.RenderEnvironment;
import javafx.scene.canvas.GraphicsContext;

public class ConstantSprite extends CircuitSprite
{
	private final Constant	_constant;

	public ConstantSprite(Constant constant)
	{
		_constant = checkNotNull(constant);
	}

	@Override
	public void paint(Graphics2D g, RenderEnvironment env)
	{
		Bounds b = _constant.getBounds();

		debugBounds(g, b);

		g.drawString(_constant.getConstantStr(), b.x, b.y + b.h);
	}

	@Override
	public void paint(GraphicsContext gc, RenderEnvironment env) {
		Bounds b = _constant.getBounds();
		debugBounds(gc, b);

		gc.fillText(_constant.getConstantStr(), b.x, b.y + b.h);
	}
}