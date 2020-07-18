package sidescroller.entity.sprite.tile;

import utility.Tuple;

public enum ItemTile implements Tile{

	BOX(1,0), SIGN(2,0),
	COIN_RIGHT(4,8), COIN_SIDE(5,8), COIN_LEFT(6,8);
	
	private Tuple coord;
	private Tuple count;
	private Tuple size;

	private ItemTile( int row, int col){
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