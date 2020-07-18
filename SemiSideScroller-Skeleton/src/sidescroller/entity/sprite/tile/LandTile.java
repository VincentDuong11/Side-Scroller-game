package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum LandTile implements Tile{

	GRASS_LEFT(3,0), GRASS(3,1), GRASS_RIGHT(3,2),
	DIRT_TOP_LEFT_CORNER(4,0), DIRT_TOP(4,1), DIRT_TOP_RIGHT_CORNER(4,2),
	DIRT_LEFT(5,0), DIRT(5,1), DIRT_RIGHT(5,2),
	DIRT_BOTTOM_LEFT_CORNER(6,0), DIRT_BOTTOM(6,1), DIRT_BOTTOM_RIGHT_CORNER(6,2);
	
	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private LandTile( int row, int col){
		coord = Tuple.pair( col, row);
		count = Tuple.pair( 1, 1);
		size = Tuple.pair( 16, 16);
	}

	@Override
	public Tuple coord(){
		return coord;
	}

	@Override
	public Tuple size(){
		return size;
	}

	@Override
	public Tuple count(){
		return count;
	}
}