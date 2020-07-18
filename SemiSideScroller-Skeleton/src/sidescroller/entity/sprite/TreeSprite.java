package sidescroller.entity.sprite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.Tile;

public class TreeSprite extends Sprite{

	private static final String TILE_MAP = "file:assets\\tiles\\Tilesv2.png";

	private Image treeImage;

	@Override
	public void draw( GraphicsContext gc){
		gc.drawImage( treeImage, coord.x() * tileSize.x() * scale, coord.y() * tileSize.y() * scale);
	}

	/**
	 * create a tree tile from top left corner.
	 * @param canvas - {@link Canvas} object to draw the asset on. canvas will be cleared before use.
	 * @param tile - the tile to be drawn
	 */
	public void createSnapshot( Canvas canvas, Tile tile){
		Image image = new Image( TILE_MAP);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		double sourceY = tileSize.y() * tile.coord().y();
		double sourceX = tileSize.x() * tile.coord().x();
		double sourceW = tileSize.x() * (tile.count().x());
		double sourceH = tileSize.y() * (tile.count().y());
		gc.drawImage( image, sourceX, sourceY, sourceW, sourceH, 0, 0, sourceW * scale, sourceH * scale);
		treeImage = super.createSnapshot( canvas);
	}
}
