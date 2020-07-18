package sidescroller.scene;

import java.util.List;
import java.util.function.BiFunction;

import javafx.scene.canvas.Canvas;
import sidescroller.entity.GenericEntity;
import sidescroller.entity.property.Entity;
import sidescroller.entity.property.HitBox;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.BackgroundSprite;
import sidescroller.entity.sprite.LandSprite;
import sidescroller.entity.sprite.PlatformSprite;
import sidescroller.entity.sprite.SpriteFactory;
import sidescroller.entity.sprite.TreeSprite;
import sidescroller.entity.sprite.tile.BackgroundTile;
import sidescroller.entity.sprite.tile.FloraTile;
import sidescroller.entity.sprite.tile.PlatformTile;
import sidescroller.entity.sprite.tile.Tile;
import utility.Tuple;

/**
 * the purpose of this interface is to simply provide the method signatures.
 * 
 * @author Shahriar (Shawn) Emami
 * @version Mar 19, 2020
 */
public interface MapBuilderInterface{
	
	//TODO look at the class diagram for the rest of the details.
	//Initialize the lists inside of the constructor.
	//the static method only returns an instance of MapBuilder.

	/**
	 * set the canvas which this builder will use.
	 * @param canvas - instance of canvas.
	 * @return the current instance of this object.
	 */
	MapBuilderInterface setCanvas( Canvas canvas);

	/**
	 * set the number of rows and columns which will be in this application.
	 * plus the height and width of each cell.
	 * @param rowColCount - use Tuple::pair and pass max rows and counts
	 * @param dimension - use Tuple::pair and pass width and height.
	 * @return the current instance of this object.
	 */
	MapBuilderInterface setGrid( Tuple rowColCount, Tuple dimension);

	/**
	 * set the scale value for this builder. this scale will be used to 
	 * multiply all widths and heights.
	 * @param scale - a double value
	 * @return the current instance of this object.
	 */
	MapBuilderInterface setGridScale( double scale);

	/**
	 * <p>
	 * build the background sprite.<br>
	 * use {@link SpriteFactory#get(String)} and pass to it "Background". save in a {@link BackgroundSprite} variable.
	 * <br>
	 * on the return call {@link BackgroundSprite#init(double, Tuple, Tuple)} and pass scale, dimension, and Tuple.pair( 0, 0).
	 * <br>
	 * on the return call {@link BackgroundSprite#createSnapshot(Canvas, Tuple, BiFunction)} and pass canvas, rowColCount, and callback.
	 * <br>
	 * use {@link HitBox#build(double, double, double, double)} and pass 0, 0, scale * dimension.x() * rowColCount.y(), and scale * dimension.y() * rowColCount.x().
	 * <br>
	 * finally instantiate background using {@link GenericEntity#GenericEntity(Sprite, HitBox)}.
	 * <br>
	 * </p>
	 * @param callback - a lambda which takes two arguments (row and col) and returns a Tile enum of type {@link BackgroundTile}. will be used to generate background.
	 * @return the current instance of this object.
	 */
	MapBuilderInterface buildBackground( BiFunction< Integer, Integer, Tile> callback);

	/**
	 * <p>
	 * build the background sprite.<br>
	 * use {@link SpriteFactory#get(String)} and pass to it "Land". save in a {@link LandSprite} variable.
	 * <br>
	 * on the return call {@link LandSprite#init(double, Tuple, Tuple)} and pass scale, dimension, and Tuple.pair( colPos, rowPos).
	 * <br>
	 * on the return call {@link LandSprite#createSnapshot(Canvas, int, int)} and pass canvas, rowConut, and colCount.
	 * <br>
	 * use {@link HitBox#build(double, double, double, double)} and pass colPos * dimension.x() * scale, rowPos * dimension.y() * scale, scale * dimension.x() * colCount, and scale * dimension.y() * rowConut.
	 * <br>
	 * finally add the instance of {@link GenericEntity#GenericEntity(Sprite, HitBox)} to landMass list.
	 * <br>
	 * </p>
	 * @param rowPos - first row from the top which the land will start.
	 * @param colPos - first column from the left which the land will start.
	 * @param rowConut - number of rows which the land mass will cover.
	 * @param colCount - number of columns which the land mass will cover.
	 * @return the current instance of this object.
	 */
	MapBuilderInterface buildLandMass( int rowPos, int colPos, int rowConut, int colCount);

	/**
	 * <p>
	 * build the background sprite.<br>
	 * use {@link SpriteFactory#get(String)} and pass to it "Tree". save in a {@link TreeSprite} variable.
	 * <br>
	 * on the return call {@link TreeSprite#init(double, Tuple, Tuple)} and pass scale, dimension, and Tuple.pair( colPos, rowPos).
	 * <br>
	 * on the return call {@link TreeSprite#createSnapshot(Canvas, Tile)} and pass canvas and tile.
	 * <br>
	 * by default there is no hitbox.
	 * <br>
	 * finally add the instance of {@link GenericEntity#GenericEntity(Sprite, HitBox)} to other list.
	 * <br>
	 * </p>
	 * @param rowPos - first row from the top.
	 * @param colPos - first column from the left.
	 * @param tile - a tree type from enum {@link FloraTile}.
	 * @return the current instance of this object.
	 */
	MapBuilderInterface buildTree( int rowPos, int colPos, Tile tile);

	/**
	 * <p>
	 * build the background sprite.<br>
	 * use {@link SpriteFactory#get(String)} and pass to it "Platform". save in a {@link PlatformSprite} variable.
	 * <br>
	 * on the return call {@link PlatformSprite#init(double, Tuple, Tuple)} and pass scale, dimension, and Tuple.pair( colPos, rowPos).
	 * <br>
	 * on the return call {@link PlatformSprite#createSnapshot(Canvas, Tile, int)} and pass canvas, tile, and length.
	 * <br>
	 * use {@link HitBox#build(double, double, double, double)} and pass (colPos + .5) * dimension.x() * scale, rowPos * dimension.y() * scale, scale * dimension.x() * (length - 1), and scale * dimension.y() / 2.
	 * <br>
	 * finally add the instance of {@link GenericEntity#GenericEntity(Sprite, HitBox)} to other list.
	 * <br>
	 * </p>
	 * @param rowPos - first row from the top.
	 * @param colPos - first column from the left.
	 * @param length - number of columns which the platform will stretch.
	 * @param tile - a platform type from enum {@link PlatformTile}.
	 * @return
	 */
	MapBuilderInterface buildPlatform( int rowPos, int colPos, int length, Tile tile);

	/**
	 * @return the background Entity.
	 */
	Entity getBackground();

	/**
	 * pass a list which will be populated by landmass first then other objects.
	 * @param list - a list to be populated by the created entities.
	 * @return the populated list.
	 * @throws NullPointerException if list is null which means it was not initialized.
	 */
	List< Entity> getEntities( List< Entity> list);
}
