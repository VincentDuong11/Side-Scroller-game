package sidescroller.entity.sprite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.Tile;

public class PlatformSprite extends Sprite{

	private static final String TILE_MAP = "file:assets\\tiles\\Tilesv2.png";

	private Image platformImage;

	@Override
	public void draw( GraphicsContext gc){
		gc.drawImage( platformImage, coord.x() * tileSize.x() * scale, coord.y() * tileSize.y() * scale);
	}

	/**
	 * create a platform.
	 * @param canvas - {@link Canvas} object to draw the asset on. canvas will be cleared before use.
	 * @param tile - the tile to be drawn.
	 * @param length - number of columns that platform will cover.
	 */
	public void createSnapshot( Canvas canvas, Tile tile, int length){
		Image image = new Image( TILE_MAP);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());

		double colPos;
		for( int col = 0; col < length; col++){
			if(col==0)
				colPos = tile.coord().x()-1;
			else if(col==length-1)
				colPos = tile.coord().x()+1;
			else
				colPos = tile.coord().x();
			gc.drawImage( image, colPos * tileSize.x() , tile.coord().y() * tileSize.y(), tileSize.x() , tileSize.y()-1,
					col * (tileSize.x() * scale), 0, tileSize.x() * scale, tileSize.y() * scale);
		}

		platformImage = super.createSnapshot( canvas);
	}
}
