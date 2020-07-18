package sidescroller.entity.sprite;

import java.lang.reflect.InvocationTargetException;

public abstract class SpriteFactory{
	
	private static final String PACKAGE = "sidescroller.entity.sprite.";
	private static final String SUFFIX = "Sprite";
	
	private SpriteFactory(){
	}

	@SuppressWarnings( "unchecked")
	public static < T> T get( String entityName){
		try{
			return get( (Class< T>) Class.forName( PACKAGE + entityName + SUFFIX));
		}catch( ClassNotFoundException e){
			throw new IllegalArgumentException( "bad entity name: " + entityName, e);
		}
	}

	public static < T> T get( Class< T> type){
		try{
			return type.getConstructor().newInstance();
		}catch( InstantiationException | 
				IllegalAccessException | 
				IllegalArgumentException | 
				InvocationTargetException | 
				NoSuchMethodException | 
				SecurityException e){
			throw new IllegalArgumentException( "bad class type: "+type, e);
		}
	}
}
