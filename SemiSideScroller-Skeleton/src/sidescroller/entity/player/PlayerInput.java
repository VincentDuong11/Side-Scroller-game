package sidescroller.entity.player;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

public class PlayerInput{

	private double x, y;
	private boolean left = false, right = false, up = false, down = false;
	private boolean leftClick = false, rightClick = false, middleClick = false;
	private boolean space = false, shift = false;

	private InputAdapter adapter;

	/**
	 * create a player input using given input adapter. by default mouse movement, drag, press, and release
	 * are registered. Keyboard press and released are also registered.
	 * @param adapter - initialized instance of input adapter.
	 */
	public PlayerInput( InputAdapter adapter){
		this.adapter = adapter;
		adapter.forceFocusWhenMouseEnters();
		adapter.registerMouseMovment( this::moved, this::moved);
		adapter.registerMouseClick( this::mousePressed, this::mouseReleased);
		adapter.registerKey( this::keyPressed, this::keyReleased);
	}

	/**
	 * get the instance of input adapter used in this class.
	 * @return current instance of adapter.
	 */
	public InputAdapter getAdapter(){
		return adapter;
	}

	/**
	 * return true if any of up, down, left, right or space are pressed.
	 * @return true if a movement is registered.
	 */
	public boolean hasMoved(){
		return left || right || up || down || space;
	}

	/**
	 * has the player moved to left or right.
	 * @return if left -1, right +1 else zero.
	 */
	public int leftOrRight(){
		if( !right && !left)
			return 0;
		return right ? 1 : -1;
	}

	/**
	 * has the player moved to up or down.
	 * @return if up -1, down +1 else zero.
	 */
	public int upOrDown(){
		if( !up && !down)
			return 0;
		return down ? 1 : -1;
	}

	/**
	 * if player has moved down return one.
	 * @return if down 1 else zero.
	 */
	public int down(){
		return down ? 1 : 0;
	}

	/**
	 * @return true if space is pressed
	 */
	public boolean isSpace(){
		return space;
	}

	/**
	 * @return true if shift is pressed
	 */
	public boolean isShift(){
		return shift;
	}

	/**
	 * @return true if mouse is left clicked
	 */
	public boolean leftClicked(){
		return leftClick;
	}

	/**
	 * @return true if mouse is right clicked
	 */
	public boolean rightClicked(){
		return rightClick;
	}

	/**
	 * @return true if mouse is middle clicked
	 */
	public boolean middleClicked(){
		return middleClick;
	}

	/**
	 * @return current x position of mouse
	 */
	public double x(){
		return x;
	}

	/**
	 * @return current y position of mouse
	 */
	public double y(){
		return y;
	}

	private void mousePressed( MouseEvent e){
		// record the x and y position of the mouse
		// record if any of the mouse keys have been pressed
		this.x = e.getX();
		this.y = e.getY();
		leftClick = e.isPrimaryButtonDown();
		rightClick = e.isSecondaryButtonDown();
		middleClick = e.isMiddleButtonDown();
	}

	private void mouseReleased( MouseEvent e){
		// set all mouse keys to false
		rightClick = false;
		leftClick = false;
		middleClick = false;
	}

	private void changeKeyStatus( KeyCode key, boolean isPressed){
		//recored if a specific key was pressed
		switch( key){
			case W:
				up = isPressed;
				break;
			case A:
				left = isPressed;
				break;
			case S:
				down = isPressed;
				break;
			case D:
				right = isPressed;
				break;
			case SHIFT:
				shift = isPressed;
				break;
			case SPACE:
				space = isPressed;
				break;
			default:
				break;
		}
	}

	private void keyPressed( KeyEvent key){
		changeKeyStatus( key.getCode(), true);
	}

	private void keyReleased( KeyEvent key){
		changeKeyStatus( key.getCode(), false);
	}

	private void moved( MouseEvent e){
		// record the x and y position of the mouse
		this.x = e.getX();
		this.y = e.getY();
	}
}