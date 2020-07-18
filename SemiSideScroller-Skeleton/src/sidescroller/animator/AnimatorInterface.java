package sidescroller.animator;

import java.util.Iterator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.scene.MapSceneInterface;

public interface AnimatorInterface{

	void setCanvas( Canvas canvas);

	void setMapScene( MapSceneInterface map);

	void clearAndFill( GraphicsContext gc, Color background);

	void handle( GraphicsContext gc, long now);

	void drawEntities( GraphicsContext gc);

	void updateEntities();

	void proccessEntityList( Iterator< Entity> iterator, HitBox shapeHitBox);

	void updateEntity( Entity entity, Iterator< Entity> iterator);
	
	void start();
	
	void stop();
}
