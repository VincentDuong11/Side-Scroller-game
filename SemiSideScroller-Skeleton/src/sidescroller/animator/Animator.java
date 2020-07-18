package sidescroller.animator;

import java.util.Iterator;
import java.util.function.Consumer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sidescroller.entity.player.Player;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;

public class Animator extends AbstractAnimator {

	private Color background = Color.ANTIQUEWHITE;
	private Consumer<Entity> draw;
	private Iterator<Entity> iterator;

	public void handle(GraphicsContext gc, long now) {
		updateEntities();
		clearAndFill(gc, background);
		drawEntities(gc);
	}

	public void drawEntities(GraphicsContext gc) {
		draw = e -> {
			if (e != null && e.isDrawable()) {
				e.getDrawable().draw(gc);
				if (map.getDrawBounds() && e.hasHitbox()) {
					e.getHitBox().getDrawable().draw(gc);
				}
			}
		};

		draw.accept(map.getBackground());
		for (Entity e : map.staticShapes()) {
			draw.accept(e);
		}
		for (Entity e : map.players()) {
			draw.accept(e);
		}

	}

	public void updateEntities() {
		for(Entity player : map.players()) {
			player.update();
		}
		for(Entity player : map.staticShapes()) {
			player.update();
		}
		if(map.getDrawBounds()) {
			for(Entity player : map.players()) {
				player.getHitBox();
				player.getDrawable().setStroke(Color.RED);
			}
		}
		for(Entity staticShape : map.staticShapes()) {
			proccessEntityList(map.players().iterator(), staticShape.getHitBox());
		}
	}

	public void proccessEntityList(Iterator<Entity> iterator, HitBox shapeHitBox) {

		while (iterator.hasNext()) {

			Entity entity = iterator.next();
			HitBox bounds = entity.getHitBox();

			if (!map.inMap(bounds)) {
				updateEntity(entity, iterator);
			} else if (shapeHitBox != null && bounds.intersectBounds(shapeHitBox)) {
				if (map.getDrawBounds()) {
					bounds.getDrawable().setStroke(Color.BLUEVIOLET);;
				}
				updateEntity(entity, iterator);
			}

		}

	}

	public void updateEntity(Entity entity, Iterator<Entity> iterator) {
//		 player;
		if(entity instanceof Player) {
			((Player) entity).stepBack();
		}
	}
}
