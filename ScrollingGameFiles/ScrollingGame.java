import java.util.Random;

abstract class ScrollingGame {
	
	// default number of vertical cells: height of grid
	protected static final int H_DIM = 5;
	
	// default number of horizoantal cells: width of game
	protected static final int W_DIM = 10;
	
	// default location of user at start
	protected static final int U_ROW = 0;
	
	protected static final int FACTOR = 3;      // you might change that this
	                           // setting or declaration when working on timing
	                           // (speed up and down the game)
	                        
	protected static Random rand = new Random();  //USE ME 
	                                          // don't instantiate me every frame
	
	protected GameGrid grid; // graphical window to display the game (the VIEW)
	                       // a 2D game made of two dimensional array of Cells
	protected int userRow;
	
	protected long msElapsed;		// game clock: number of ms since start of
	                          // game main loop---see play() method
	protected int timerClicks;	// used to space animation and key presses
	protected int timerDelay;		// to control speed of game

	
	
	//---------------- Abstract methods -----------------//
	// methods you must implement in the Base and Creative Versions
	
	// update game state to reflect adding in new cells in the right-most column
	public abstract void populateRightEdge();
	
	// updates the game state to reflect scrolling left by one column
	public abstract void scrollLeft();
	
	// handles result of key press
	public abstract void handleKeyPress();
	
	// handles results of mouse click
	public abstract void handleMouseClick();
	
	// gets the user image for intial game set up
	public abstract String getUserImg();
	
	// checks for game over conditions
	public abstract boolean isGameOver();
	
	// changes the title bar in the game window
	public abstract void updateTitle();
	
	// gives the game introduction
	public abstract void displayIntro();
	
	// handles actions upon game end
	public abstract void displayOutcome();
	
	//---------------------------------------------------//
	
	public void play() {
		
		displayIntro();
		
		/* main game loop */
		while (!isGameOver()) {
			
			grid.sleep(timerDelay); 	 // pause for some time (smooth animation)
			msElapsed += timerDelay;	 // count the total amount of time elapsed
			timerClicks++;				 // increment the timer count
		
			handleKeyPress();        // update state based on user key press
			
			handleMouseClick();      // when the game is running: 
			                         // click & read the console output 
			
			
			if (timerClicks % FACTOR == 0) {  // if it's the FACTOR timer tick
				                            // constant 3 initially
				scrollLeft();
				populateRightEdge();
			}
			
			updateTitle();
			msElapsed += timerDelay;
		}
		
		displayOutcome();
	}
	
	protected void init(int hdim, int wdim, int urow) {  
		
		/* initialize the game window

				NOTE: look at the various constructors to see what you can do!
				For example:
					grid = new GameGrid(hdim, wdim, Color.MAGENTA);
					
				You need to use the one that takes an image to implement the 
				splashscreen functionality (don't start there, but do it as your
				first extension)
		*/
		grid = new GameGrid(hdim, wdim);   
		
		/* initialize other aspects of game state */

		// animation settings
		timerClicks = 0;
		msElapsed = 0;
		timerDelay = 100;

		// store and initialize user position
		userRow = urow;
		grid.setCellImage(new Location(userRow, 0), getUserImg());
		
		updateTitle();
		
	}
	
	
}