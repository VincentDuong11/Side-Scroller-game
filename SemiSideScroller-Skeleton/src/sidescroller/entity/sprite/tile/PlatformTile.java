package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum PlatformTile implements Tile{

	GRASS_LEFT(0,6), GRASS(0,7), GRASS_RIGHT(0,8),
	STONE_LEFT(1,6), STONE(1,7), STONE_RIGHT(1,8),
	WOOD_LEFT(2,6), WOOD(2,7), WOOD_RIGHT(2,8);
	
	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private PlatformTile( int row, int col){
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