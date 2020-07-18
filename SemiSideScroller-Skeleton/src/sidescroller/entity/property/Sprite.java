package sidescroller.entity.property;

import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import utility.Tuple;

/**
 * <p>
 * Sprite represents what to be drawn during the canvas drawing stage. this class holds information such as
 * {@link Sprite#fill}, {@link Sprite#stroke} and {@link Sprite#strokeWidth}. this class is abstract, meaning 
 * it must be inherited and draw method overridden.
 * fill and stroke values in this class are of type {@link Paint}. this allows  the user to set any color to the sprite
 * or using the {@link ImagePattern} class choose an image asset and set it as filling.
 * </p>
 * 
 * ex: sprite for PolyShape<br>
 * <code><pre>
    sprite = new Sprite(){
        {
            setFill( new ImagePattern( new Image( "file:assets/concrete/dsc_1621.png")));
        }

        public void draw( GraphicsContext gc){
            gc.setLineWidth( getWidth());
            if( getStroke() != null){
                gc.setStroke( getStroke());
                gc.strokePolygon( points[0], points[1], pointCount);
            }
            if( getFill() != null){
                gc.setFill( getFill());
                gc.fillPolygon( points[0], points[1], pointCount);
            }
        }
    };
 * </Pre></code>
 * 
 * @author Shahriar (Shawn) Emami
 * @version March 9, 2020
 */
public abstract class Sprite implements Drawable< Sprite>{

	protected Paint fill, stroke;
	protected double strokeWidth;
	protected double scale;
	protected Tuple coord;
	protected Tuple tileSize;
	
	/**
	 * a quick method to initialize variables.
	 * @param scale - a positive double, default is one.
	 * @param tileSize - a {@link Tuple} object repressing w and h of the tile.
	 * @param coord - a {@link Tuple} object repressing x and y.
	 * @return
	 */
	public Sprite init( double scale, Tuple tileSize, Tuple coord){
		setScale( scale);
		setTileSize( tileSize);
		setCoord( coord);
		return this;
	}
	
	/**
	 * set the w and h of the tile being drawn, without scaling.
	 * @param tileSize - a {@link Tuple} object repressing w and h of the tile.
	 * @return the instance of current object
	 */
	@Override
	public Sprite setTileSize( Tuple tileSize){
		this.tileSize = tileSize;
		return this;
	}
	
	/**
	 * set the x and y coordinate for the asset to be drawn on screen.
	 * @param coord - a {@link Tuple} object repressing x and y.
	 * @return the instance of current object
	 */
	@Override
	public Sprite setCoord( Tuple coord){
		this.coord = coord;
		return this;
	}
	
	/**
	 * set the scale of the asset to be drawn.
	 * @param scale - a positive double, default is one.
	 * @return the instance of current object
	 */
	@Override
	public Sprite setScale( double scale){
		this.scale = scale;
		return this;
	}
	
	/**
	 * set the {@link Paint} to be used when filling the shape
	 * @param paint - an object representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	public Sprite setFill( Paint color){
		fill = color;
		return this;
	}
	
	/**
	 * set the {@link Paint} to be used when stroking the shape
	 * @param paint - an object representing fill content like {@link Paint} or {@link ImagePattern} object
	 * @return the instance of current object
	 */
	public Sprite setStroke( Paint color){
		stroke = color;
		return this;
	}
	
	/**
	 * set the stroke width to be used when stroking the shape
	 * @param width - stroke width
	 * @return the instance of current object
	 */
	public Sprite setWidth( double width){
		this.strokeWidth = width;
		return this;
	}
	
	/**
	 * get the w and h of tile in a {@link Tuple} object.
	 * @return {@link Tuple} object.
	 */
	@Override
	public Tuple getTileSize(){
		return tileSize;
	}
	
	/**
	 * get the x and y coordinate in a {@link Tuple} object.
	 * @return {@link Tuple} object.
	 */
	public Tuple getCoord(){
		return coord;
	}
	
	/**
	 * get the scale multiplier of asset.
	 * @return scale multiplier, if not set by default is one.
	 */
	public double getScale(){
		return scale;
	}
	
	/**
	 * get the current fill {@link Color}
	 * @return {@link Paint}
	 */
	public Paint getFill(){
		return fill;
	}

	/**
	 * get the current stroke {@link Color}
	 * @return {@link Paint}
	 */
	public Paint getStroke(){
		return stroke;
	}

	/**
	 * get the current stroke width
	 * @return stroke width
	 */
	public double getWidth(){
		return strokeWidth;
	}
	
	/**
	 * this method is to be called internally for the purpose of creating
	 * a snapshot of a JavaFX GUI Node. this method should be called at the
	 * very end to simply provide the image of assets based one provided
	 * configuration. by default the background is transparent. all other
	 * configuration such as camera and buffer are extracted from node.
	 * @param node - JavaFX node to use for snapshot.
	 * @return a {@link WritableImage} holding the snapshot of node.
	 */
	protected Image createSnapshot(Node node){
		SnapshotParameters sp = new SnapshotParameters();
		sp.setFill( Color.TRANSPARENT);
		// sp.setCamera( scene.getEffectiveCamera());
		// sp.setDepthBuffer( scene.isDepthBufferInternal());
		return node.snapshot( sp, null);
	}
}
