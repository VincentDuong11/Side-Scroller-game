package sidescroller.scene;

import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.scene.canvas.Canvas;
import sidescroller.animator.AnimatorInterface;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import utility.Tuple;

/**
 * the purpose of this interface is to simply provide the method signatures.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Mar 19, 2020
 */
public interface MapSceneInterface{

	// TODO look at the class diagram for the rest of the details.
	// Initialize the lists and BooleanProperties inside of the constructor.
	// to initialize a BooleanProperties use the class SimpleBooleanProperty.

	/**
	 * @return the drawFPS instance.
	 */
	BooleanProperty drawFPSProperty();

	/**
	 * @return the value of {@link BooleanProperty#get()}
	 */
	boolean getDrawFPS();

	/**
	 * @return the drawBounds instance.
	 */
	BooleanProperty drawBoundsProperty();

	/**
	 * @return the value of {@link BooleanProperty#get()}
	 */
	boolean getDrawBounds();

	/**
	 * @return the drawGrid instance.
	 */
	BooleanProperty drawGridProperty();

	/**
	 * @return the value of {@link BooleanProperty#get()}
	 */
	boolean getDrawGrid();

	/**
	 * save date in the correct variables.
	 * @param count - number of rows and columns in the grid.
	 * @param size - width and height of each cell in grid.
	 * @param scale - a double multiplier for width and height of each grid cell.
	 * @return current instance of this class.
	 */
	MapSceneInterface setRowAndCol( Tuple count, Tuple size, double scale);

	/**
	 * @return count variable.
	 */
	Tuple getGridCount();

	/**
	 * @return size variable.
	 */
	Tuple getGridSize();

	/**
	 * @return scale variable.
	 */
	double getScale();

	/**
	 * if current animator instance is not null stop it first.
	 * then assign the newAnimator to animator.
	 * @param newAnimator - new animator to set.
	 * @return current instance of this class.
	 * @throws NullPointerException if argument is null.
	 */
	MapSceneInterface setAnimator( AnimatorInterface newAnimator);

	/**
	 * @return get background entity.
	 */
	Entity getBackground();

	/**
	 * if animator is not null start animator.
	 */
	void start();

	/**
	 * if animator is not null stop animator.
	 */
	void stop();

	/**
	 * @return static list. this list will hold all entities short of background, player, and any Entity that moves.
	 */
	List< Entity> staticShapes();

	/**
	 * @return players list.
	 */
	List< Entity> players();

	/**
	 * <p>
	 * this method creates the static entities in the game.
	 * <br>
	 * use {@link MapBuilder#createBuilder()} to get and instance of MapBuilder called mb.
	 * <br>
	 * on mb call methods {@link MapBuilder#setCanvas(Canvas)}, {@link MapBuilder#setGrid(Tuple, Tuple)}, and {@link MapBuilder#setGridScale(double)}.
	 * <br>
	 * call all or any combination of build methods in MapBuilder to create custom map, does not have to be complex. one landmass and a tree is good enough.
	 * <br>
	 * call {@link MapBuilder#getBackground()} and {@link MapBuilder#getEntities(List)} to retrieve the built entities.
	 * </p>
	 * @param canvas
	 * @return
	 */
	MapSceneInterface createScene( Canvas canvas);

	/**
	 * @param hitbox - hitbox of an entity to check it is it still in background bounds.
	 * @return true of hitbox of background containsBouns of argument.
	 */
	boolean inMap( HitBox hitbox);
}
