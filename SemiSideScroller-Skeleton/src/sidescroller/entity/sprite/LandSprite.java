package sidescroller.entity.sprite;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.LandTile;
import sidescroller.entity.sprite.tile.Tile;

public class LandSprite extends Sprite{
	
	private static final String TILE_MAP = "file:assets\\tiles\\Tilesv2.png";

	private Image landImage;

	@Override
	public void draw( GraphicsContext gc){
		gc.drawImage( landImage, coord.x() * tileSize.x() * scale, coord.y() * tileSize.y() * scale);
	}
	
	/**
	 * this method creates the image for a land mass with top of it covered with grass tile.
	 * ideally the min row count should be three. 
	 * @param canvas - {@link Canvas} object to draw the asset on. canvas will be cleared before use.
	 * @param rowCount - number of rows this land will be drawn on.
	 * @param colCount - number of columns this land will be drawn on.
	 */
	public void createSnapshot( Canvas canvas, int rowCount, int colCount){
		Image image = new Image( TILE_MAP);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect( 0, 0, canvas.getWidth(), canvas.getHeight());
		
		for( int col = 0; col < colCount; col++){
			for( int row = 0; row < rowCount; row++){
				Tile tile = getTile( row, col, rowCount, colCount);
				gc.drawImage( image, tile.coord().x() * tileSize.x()+1, tile.coord().y() * tileSize.y(), tileSize.x()-2, tileSize.y(), 
						col * (tileSize.x() * scale), row * (tileSize.y() * scale), tileSize.x() * scale, tileSize.y() * scale);
			}
		}
		
		landImage = super.createSnapshot( canvas);
	}
	
	private Tile getTile( int row, int col, int maxRows, int maxCols){
		if(row==0){
			if(col==0)
				return LandTile.GRASS_LEFT;
			if(col==maxCols-1)
				return LandTile.GRASS_RIGHT;
			return LandTile.GRASS;
		}else if(row==1){
			if(col==0)
				return LandTile.DIRT_TOP_LEFT_CORNER;
			if(col==maxCols-1)
				return LandTile.DIRT_TOP_RIGHT_CORNER;
			return LandTile.DIRT_TOP;
		}else if(row==maxRows-1){
			if(col==0)
				return LandTile.DIRT_BOTTOM_LEFT_CORNER;
			if(col==maxCols-1)
				return LandTile.DIRT_BOTTOM_RIGHT_CORNER;
			return LandTile.DIRT_BOTTOM;
		}else{
			if(col==0)
				return LandTile.DIRT_LEFT;
			if(col==maxCols-1)
				return LandTile.DIRT_RIGHT;
			return LandTile.DIRT;
		}
	}
}
