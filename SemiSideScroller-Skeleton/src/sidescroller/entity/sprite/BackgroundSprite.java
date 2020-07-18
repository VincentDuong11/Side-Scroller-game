package sidescroller.entity.sprite;

import java.util.function.BiFunction;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.Tile;
import utility.Tuple;

public class BackgroundSprite extends Sprite{

	private static final String TILE_MAP = "file:assets\\tiles\\Tilesv2.png";

	private Image backgoundImage;

	@Override
	public void draw( GraphicsContext gc){
		gc.drawImage( backgoundImage, coord.x() * tileSize.x() * scale, coord.y() * tileSize.y() * scale);
	}

	/**
	 * feed this method a single tile with one row and one column. this method will draw this tile
	 * over the whole canvas.
	 * @param canvas - {@link Canvas} object to draw the asset on. canvas will be cleared before use.
	 * @param tile - the tile to be drawn
	 * @param count - number of rows and columns this land mass will cover.
	 */
	public void createSnapshot( Canvas canvas, Tuple count, BiFunction< Integer, Integer, Tile> callback){
		Image image = new Image( TILE_MAP);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		
		for( int col = 0; col < count.y(); col++){
			for( int row = 0; row < count.x(); row++){
				Tile tile = callback.apply( row, col);
				gc.drawImage( image, tile.coord().x() * tileSize.x()+1, tile.coord().y() * tileSize.y(), tileSize.x()-2, tileSize.y(), 
						col * (tileSize.x() * scale), row * (tileSize.y() * scale), tileSize.x() * scale, tileSize.y() * scale);
			}
		}
		
		backgoundImage = super.createSnapshot( canvas);
	}
}
