package sidescroller.entity.sprite.tile;

import utility.Tuple;

/**
 * representation of one image on source image. this image can extends multiple rows and/or columns
 * 
 * @author Shahriar (Shawn) Emami
 * @version Mar 8, 2020
 */
public interface Tile{

	/**
	 * the row and column at top left of source image, counting from zero.
	 * @return {@link Tuple} holding row and column.
	 */
	Tuple coord();

	/**
	 * the w and h of source image
	 * @return {@link Tuple} holding w and h.
	 */
	Tuple size();

	/**
	 * the number of rows and columns this tile extends on source image, counting from 1 not zero.
	 * @return {@link Tuple} holding row and column count.
	 */
	Tuple count();
}
