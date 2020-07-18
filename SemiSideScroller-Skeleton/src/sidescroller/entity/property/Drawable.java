package sidescroller.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import utility.Tuple;

/**
 * an interface used on drawable objects.
 * 
 * @param <T> - the type of class which implements this interface.
 * @author Shahriar (Shawn) Emami
 * @version March 9, 2020
 */
public interface Drawable<T extends Sprite>{
	
	/**
	 * set the w and h of the tile being drawn, without scaling.
	 * @param tileSize - a {@link Tuple} object repressing w and h of the tile.
	 * @return the instance of current object
	 */
	T setTileSize( Tuple tileSize);
	
	/**
	 * set the x and y coordinate for the asset to be drawn on screen.
	 * @param coord - a {@link Tuple} object repressing x and y.
	 * @return the instance of current object
	 */
	T setCoord( Tuple coord);
	
	/**
	 * set the scale of the asset to be drawn.
	 * @param scale - a positive double, default is one.
	 * @return the instance of current object
	 */
	T setScale( double scale);
	
	/**
	 * set the {@link Paint} to be used when filling the shape
	 * @param paint - an object representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	T setFill( Paint paint);
	
	/**
	 * set the {@link Paint} to be used when stroking the shape
	 * @param paint - an object representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	T setStroke( Paint paint);
	
	/**
	 * set the stroke width to be used when stroking the shape
	 * @param width - stroke width
	 * @return the instance of current object
	 */
	T setWidth( double width);
	
	/**
	 * get the w and h of tile in a {@link Tuple} object.
	 * @return {@link Tuple} object.
	 */
	Tuple getTileSize();
	
	/**
	 * get the x and y coordinate in a {@link Tuple} object.
	 * @return {@link Tuple} object.
	 */
	Tuple getCoord();
	
	/**
	 * get the scale multiplier of asset.
	 * @return scale multiplier, if not set by default is one.
	 */
	double getScale();
	
	/**
	 * get the current fill {@link Color}
	 * @return {@link Paint}
	 */
	Paint getFill();

	/**
	 * get the current stroke {@link Color}
	 * @return {@link Paint}
	 */
	Paint getStroke();

	/**
	 * get the current stroke width
	 * @return stroke width
	 */
	double getWidth();
	
	/**
	 * draw the shape given the {@link GraphicsContext}
	 * @param gc - {@link GraphicsContext} object
	 */
	void draw( GraphicsContext gc);
}