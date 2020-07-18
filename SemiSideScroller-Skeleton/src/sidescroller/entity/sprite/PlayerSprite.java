package sidescroller.entity.sprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sidescroller.entity.property.Sprite;
import sidescroller.entity.sprite.tile.Samurai;

public class PlayerSprite extends Sprite{

	private static final Image IDE = new Image( "file:assets\\samurai\\Idle_strip4.png");
	private static final Image RUN = new Image( "file:assets\\samurai\\Run_strip4.png");
	private static final Image JUMP = new Image( "file:assets\\samurai\\Jump_strip2.png");
	private static final Image SWORD = new Image( "file:assets\\samurai\\Sword_strip6.png");

	private double playerFrame = 0;
	private Image activeImage;
	private Samurai tile;
	private boolean left;

	@Override
	public void draw( GraphicsContext gc){
		playerFrame += .1;
		if( playerFrame > tile.count().x()){
			playerFrame = 0;
		}
		if(left){
			gc.drawImage( activeImage, ((int) playerFrame) * 20.0, 0, 20, 20, coord.x()+(20 * scale)+8, coord.y(), -20 * scale, 20 * scale);
		}else{
			gc.drawImage( activeImage, ((int) playerFrame) * 20.0, 0, 20, 20, coord.x(), coord.y(), 20 * scale, 20 * scale);
		}
	}
	
	/**
	 * determine if player animation should be facing left or right.
	 * @param isLeft - true if left, false if right.
	 */
	public void setLeft( boolean isLeft){
		left = isLeft;
	}

	/**
	 * Using Samurai enum determine what animation to use for player sprite.
	 * @param tile - {@link Samurai} object.
	 */
	public void setTile( Samurai tile){
		if(tile==this.tile)
			return;
		switch (tile) {
			case IDLE:
				activeImage = IDE;
				break;
			case JUMP:
				activeImage = JUMP;
				break;
			case RUN:
				activeImage = RUN;
				break;
			case SWORD:
				activeImage = SWORD;
				break;
			default:
				throw new IllegalArgumentException( "Tile \"" + tile + "\" is not supported");
		}
		this.tile = tile;
		playerFrame = 0;
	}
}
