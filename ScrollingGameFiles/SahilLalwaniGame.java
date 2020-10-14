import java.awt.event.KeyEvent;
import java.awt.Color;

public class SahilLalwaniGame extends ScrollingGame {
		                           
	private final String USER_IMG = "user.gif";    // User image
	private final String EXERCISE_IMG = "exercise.gif"; // Power up image
	private final String SALAD_IMG = "salad.gif"; // Healthy object image
	private final String BURGER_IMG = "burger.gif"; // Unhealthy object image
	private final String PINEAPPLE_IMG = "pineapple.gif"; // Healthy object image
	private final String APPLE_IMG = "apple.gif"; // Healthy object image
	private final String COUCH_IMG = "couch.gif"; // Power down image
	private final String BG_IMG= "intro.png"; // Introductory image
	private final String WIN_IMG= "win.png"; // Win condition image
	private final String OVER_IMG= "over.png"; // Game over image
	private String[] level_img= {"level2.png", "level3.png", "level4.png", "level5.png", "level6.png", "level7.png"}; // Array of level change images
	private int level = 1; // keeps track of level user is playing at
	private int score= 50; // User's initial score
	private int score_next_level= 150; // Score user needs to reach next level
	private String file_name= "screenshot"; 
	private int idx_add= 1;
	private final String SPLASH = "ink.png";
	private boolean grid_status= false;
	private final int MAX_NEW = 12; //a limit so new column is not filled
	private int rand_total_odds= 40; // May be used to change the probability of randomly generating healthy and unhealthy objects in the right-most column
                                          // Increasing its value will decrease the odds of generating healthy or unhealthy objects, thus leading to more blank
                                          // spaces overall in the game
    private int rand_power_up_odds= 100; // Changes the probability of randomly generating power-ups in the game
    									 // Value increases with increase in levels
    private int rand_power_down_odds= 100; // Changes the probability of randomly generating power-downs in the game
    									 // Value decreases with increase in levels
    private final int RAND_SALAD= 0;
    private final int RAND_PINEAPPLE= 1;
    private final int RAND_APPLE= 2;
    private int rand_burger= 3;
    private final int RAND_EXERCISE= 4;
    private final int RAND_COUCH= 5;
    private int score_up= 10; // A common factor for increasing score for healthy objects
    private int score_down= 5; // A common factor for decreasing score for unhealthy objects
	private int count;
	private Location user_loc= new Location (U_ROW, 0); // Keeps track of user's location

	public SahilLalwaniGame() {
		this(H_DIM, W_DIM, U_ROW);
	}
		
	public SahilLalwaniGame(int hdim, int wdim, int urow) {
		super.init(hdim, wdim, urow);
	}
	
	/******************** Methods **********************/
	
	// update game state to reflect adding in new cells in the right-most column
	public void populateRightEdge() {
		int count=0; // Count keeps track of number of healthy objects, blank spaces and power-ups generated
		int rand_num= 0;
		int rand_num_power_up= 0;
		int rand_num_power_down= 0;
		String num_power;
        for (int i=0; i<H_DIM; i++) {
           if (i==H_DIM-1) {
           	   path_clear(count); // Re-assigns a salad image to a row in the last column if probablity generates unhealthy objects
           	   					  // or power downs in all the rows of the last column
           }
           // Generates various random numbers to check the probability of generation of healthy & unhelathy objects as well as power
           // up and power downs
           rand_num= super.rand.nextInt(rand_total_odds); 
           rand_num_power_up= super.rand.nextInt(rand_power_up_odds);
           rand_num_power_down= super.rand.nextInt(rand_power_down_odds);
           num_power= power_up_down(rand_num_power_down,rand_num_power_up,i); // Generates a power-up or power down in a cell
           if(num_power=="up" || num_power=="down"){
           	  if (num_power=="up") {
           	  	 count+=1; 
           	  }
           }
           else{
           count+= fill_elements(rand_num, i); // If power-up or power down not generated for a cell, generates a healthy/ unhealthy object or a blank space
           path_clear_overall(); // Ensures that the user always has a path to avoid hitting unhealthy objects or power downs
           }
        }
 	}
	
 	/*
 	Checks the randomly generated last column and second last column to ensure that at least one of the rows doesn't have two consecutive unhealthy objects or power downs,
 	in the two columns, to ensure that the user always has a path to avoid hitting unhealthy objects and losing score. If no such path exists, creates a healthy object and 
 	blank space in the last row of the last column to ensure this path.
 	*/
 	public void path_clear_overall() {
 		for(int i=0; i<H_DIM; i++) {
 			if(condition(i)) {	
 				return;
 			}
 			}	
 		grid.setCellImage(new Location(H_DIM-1, W_DIM-1), SALAD_IMG);
 		grid.setCellImage(new Location(H_DIM-2, W_DIM-1), null);
 	}
 	
 	/*
 	Helper method to path_clear_overall(): Returns true if at least one of the rows doesn't have two consecutive unhealthy objects or power downs,
 	in the two columns.
 	*/
 	
 	public boolean condition(int i) {
 		if ((!BURGER_IMG.equals(grid.getCellImage(new Location(i, W_DIM-1))) && !COUCH_IMG.equals(grid.getCellImage(new Location(i, W_DIM-1))) && !USER_IMG.equals(grid.getCellImage(new Location(i, W_DIM-1))))) {
 			if ((!BURGER_IMG.equals(grid.getCellImage(new Location(i, W_DIM-2))) && !COUCH_IMG.equals(grid.getCellImage(new Location(i, W_DIM-2))) && !USER_IMG.equals(grid.getCellImage(new Location(i, W_DIM-2))))){
 				return true;
 		}
 		}
 		return false;
 	}
 	
 	/*
 	Helper method: updates game state to reflect randomly populating right-most column of the grid with healthy and unhealthy object images, using the random
 	number generated in populateRightEdge() method
 	*/
 	
 	public int fill_elements(int rand_num, int i) {
 	   	if (rand_num==RAND_SALAD) {
           	grid.setCellImage(new Location(i, W_DIM-1), SALAD_IMG);
           	return 1;
 	   }
 	   else if (rand_num==RAND_PINEAPPLE) {
 	   	   grid.setCellImage(new Location(i, W_DIM-1), PINEAPPLE_IMG);
 	   	   return 1;
 	   }
 	   else if (rand_num==RAND_APPLE) {
 	   	   grid.setCellImage(new Location(i, W_DIM-1), APPLE_IMG);
 	   	   return 1;
 	   }
 	   else if (condition_rand_burger(rand_num)) {
 	   	   grid.setCellImage(new Location(i, W_DIM-1), BURGER_IMG);
 	   	   return 0;
 	   }
 	   else {
 	   	   grid.setCellImage(new Location(i, W_DIM-1), null); // Generates a blank space
 	   	   return 1;
 	   }
     }
     
     /*
     Helper method to fill_elements(int rand_num, int i): Changes the probability of generating an unhealthy object (burger image) with an increase in levels.
     With an increase in levels, probability of genearation of unhealthy objects increases.
     */
     
     public boolean condition_rand_burger(int rand_num) {
           for (int i=3; i<rand_burger+1; i++) {
           	   if(rand_num==i) {
           	   	  return true;   
           	   }
           }
       return false;
     }
     
     /*
     Helper method for fill_elements(int rand_num, int i): Randomly generates power-ups and power downs in the left-most column at regular intervals. 
     Probability of occurrence of power-ups and power-downs is lower than other healthy or unhealthy objects. Returns a string to confirm if a power-up
     or power-down was generated in a specific cell, and if none, returns 'regular' to ensure that a healthy or unhealthy object or blank space is randomly generated.
     */
     public String power_up_down(int rand_num, int rand_num_one, int i) {
     	 if(level>=2) {
     	 	 if (rand_num_one==RAND_EXERCISE) {
     	 	 	 grid.setCellImage(new Location(i, W_DIM-1), EXERCISE_IMG);
     	 	 	 return "up";
     	 	 }
     	 	 else if (rand_num==RAND_COUCH) {
     	 	 	 grid.setCellImage(new Location(i, W_DIM-1), COUCH_IMG);
     	 	 	 return "down";
     	 	 }
     	 }
     	 return "regular";
     }
 	
     /*
    Takes the count of the number of healthy objects and blank spaces created in one instance of random population of the right edge as a parameter to 
    ensures that the user always has a path through the game without colliding with the avoid object, by re-assigning one of the rows in the last
    column as a healthy object.
    */
    public void path_clear(int count) {
    	if(count==0) {
 	    	for(int i=0; i<H_DIM; i++) {
 	    	   if(!BURGER_IMG.equals(grid.getCellImage(new Location(i, W_DIM-2)))|| !COUCH_IMG.equals(grid.getCellImage(new Location(i, W_DIM-2)))){
 	    	   	   super.grid.setCellImage(new Location(i, W_DIM-1), SALAD_IMG);
 	    	   	   return;
 	    	   }
 	    	}	
 	    }
 	}
	// updates the game state to reflect scrolling left by one column
	public void scrollLeft() {
		for (int i=0; i<H_DIM; i++) {
		    for (int j=1; j<W_DIM; j++) {
		    	String img= grid.getCellImage(new Location(i,j));
		        
		    	if(condition_user_image(img)) {
		    	   	grid.setCellImage(new Location(i, j-1), null);
		    	}
		    	else if (!record_collision(new Location(i,j))) {
		            grid.setCellImage(new Location(i, j-1), img);	
		        }
		        else if(record_collision(new Location(i,j))) {
		           	handleCollision(new Location(i,j));
		        }
		    }
		}
	}
	
	/*
	Helper method: checks if a string, representing an image file name, is the same as the USER_IMG
	*/
	public boolean condition_user_image(String img) {
		return USER_IMG.equals(img); // This method has been primarily added to reduce redundancy
	}
	
	/*
	Returns true if a get or avoid object collides with the user object while these GET and AVOID objects are scrolling to the left
	*/
	public boolean record_collision(Location loc) {
	    return USER_IMG.equals(grid.getCellImage(new Location(loc.getRow(), loc.getCol()-1)));	
	}
	
	// returns the user image
	public String getUserImg() {
		return USER_IMG;
	}
	
	/* handleCollision()
	 * handle a collision between the user and an object in the game
	 */    
	public void handleCollision(Location loc) {
       String img= grid.getCellImage(new Location(loc.getRow(), loc.getCol())); 
       // Algorithm for changing scores meant to make loss of score greater than gain of score with increase in levels,
	   // the game much more competitive.
       if (SALAD_IMG.equals(img)|| PINEAPPLE_IMG.equals(img)|| APPLE_IMG.equals(img)) {
       	   score+=score_up+2*level; // Increases the score by common factor plus 2 times the level
       }
	   else if (BURGER_IMG.equals(img)) {
	   	   score-=score_down*level; // Decreases the score by common factor times the level  
	   }
	   // Increases or decreases the score by 15 times the level for power ups and power downs
	   else if (EXERCISE_IMG.equals(img)) {
	   	   score+=15*level;   // 
	   }
	   else if (COUCH_IMG.equals(img)) {
	   	   score-=15*level;   
	   }
	   if (score>=score_next_level) {
	   	   if(level!=7) {
	   	   	   next_level_conditions(); // Calls next_level_conditions() to update game variables to reflect new level state,
	   	   							// if the user reaches next level
	   }
	}
	}
	
	/*
	Updates the relevant variables and the title, updtaes the title and pauses the screen for a splash screen with instructions for the next 
	level, in case a user crosses a level.
	*/
	public void next_level_conditions() {
		level+=1;
	   	score_next_level= score_next_level*2;
	   	rand_power_up_odds+=10;
	   	rand_power_down_odds-=10;
	   	timerDelay-=5;
	   	rand_burger+=1;
	   	updateTitle();
	   	pause_screen('l');
	}
	
	//---------------------------------------------------//
	
	// handles actions upon mouse click in game
	public void handleMouseClick() {
		
		Location loc = grid.checkLastLocationClicked();
		
		if (loc != null)
			System.out.println("You clicked on a square " + loc);
	
	}
	
	// handles actions upon key press in game
	public void handleKeyPress() {
			
		int key = grid.checkLastKeyPressed();
		
		//use Java constant names for key presses
		//http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN
		
		// Q for quit
		if (key == KeyEvent.VK_Q)
			System.exit(0);
		
		else if (key == KeyEvent.VK_S) {
			grid.save(file_name+idx_add+".png");
		    idx_add++;
		}
		else if (key==KeyEvent.VK_UP) 
			change_user_location('u');
		
		else if (key==KeyEvent.VK_DOWN) 
			change_user_location('d');
		
		else if (key==KeyEvent.VK_LEFT) 
			change_user_location('l');
		
		else if (key==KeyEvent.VK_RIGHT) 
			change_user_location('r');
		
		else if (key==KeyEvent.VK_P) {
			pause_screen('p');
		}
		else if (key==KeyEvent.VK_D) {
			draw_grids();
		}
		
		else if (key==KeyEvent.VK_PERIOD) {
			if ((timerDelay-1)!=0)
			timerDelay-=15;
		}
		
		else if (key==KeyEvent.VK_COMMA) {
			timerDelay+=15;
		}
		
		/* To help you with step 9: 
		   use the 'T' key to help you with implementing speed up/slow down/pause
    	   this prints out a debugging message */
		else if (key == KeyEvent.VK_T)  {
			boolean interval =  (timerClicks % FACTOR == 0);
			System.out.println("timerDelay " + timerDelay + " msElapsed reset " + 
				msElapsed + " interval " + interval);
		} 
	}
	
	/*
	Helper method: Pauses/resumes the screen when the user presses 'p' key
	*/
	public void pause_screen(char condition) {
		boolean pause_state= true;
		if(condition=='l') {
	    		grid.setSplash(level_img[level-2]); // Generates level change image and pauses screen if condition is 'l'
	    											// or level up. Screen remains paused until user presses 'p' key.
	    	}
	    while (pause_state) {
	    	grid.sleep(timerDelay);
	        msElapsed+=timerDelay;
	        if (grid.checkLastKeyPressed()==KeyEvent.VK_P) {
	            pause_state= false;	
	            grid.setSplash(null); // Else pauses the screen until user presses key 'p'
	        }
	    }
	}
	
	/*
	Helper method: Draws/removes the grid when the user presses 'd' key
	*/
	public void draw_grids() {
	    if (!grid_status) {
	        grid.setLineColor(Color.red);
	        grid_status= true;
	      }
	    else{
	        grid.setLineColor(grid.DEFAULT_COLOR);
	        grid_status= false;
	    }
	    }
	
	/*
    Helper method: Updates the game state to reflect a change in user object location when the user presses up, down, left or right keys,
    and also accounts for any collisions with GET/AVOID objects corresponding to this change in user location by updating the user score 
    or the number of avoid objects 
	*/
	public void change_user_location(char command) {
		 grid.setCellImage(user_loc, null);
		 int row= user_loc.getRow();
		 int column= user_loc.getCol();
	     if (command=='u') {
	     	if (row!=0) { 
	     	user_loc= new Location(row-1, column);
	     }
	     }
	     
	     else if (command=='d') {
	     	if (row!=H_DIM-1) {
	     	user_loc= new Location(row+1, column);
	     }
	     }
	     
	     else if (command=='l') {
	     	if (column!=0) { 
	     	user_loc= new Location(row, column-1);
	     }
	     }
	     
	     else if (command=='r') {
	     	if (column!=W_DIM-1) { 
	     	user_loc= new Location(row, column+1);
	     }
	     }
	     handleCollision(user_loc);
	     grid.setCellImage(user_loc, USER_IMG);
	}
	
	// return the "score" of the game 
	private int getScore() {
		return score;  
	}
	
	// return the level of the game
	private int getLevel() {
		return level;  
	}
	
    // return the current stage of life, as decided by the current level, to update the title. Level 0 corresponds to game over.
	private String getStage() {
	    if(level==1) {
	       return "Infant";	
	    }
	    else if(level==2) {
	       return "Schoolboy";	
	    }
	    else if(level==3) {
	       return "Lover";	
	    }
	    else if(level==4) {
	       return "Soldier";	
	    }
	    else if(level==5) {
	       return "Justice";	
	    }
	    else if(level==6) {
	       return "Old man";	
	    }
	    else if (level==7) {
	       return "Aged old man";	
	    }
	    else {
	       return "You have lived a long, healthy life!";	
	    }
	}
	
	// update the title bar of the game window 
	public void updateTitle() {
		grid.setTitle("Score:  " + getScore()+ "		Level:	"+getLevel()+ "		Stage: 		"+ getStage());
	}
	
	// return true if the game is finished, false otherwise
	//      used by play() to terminate the main game loop 
	public boolean isGameOver() {
		if(level<3 && score<=0) {
			score=0;
			updateTitle();
		    return true;
		}
		else if(level>=3) {
			if(score<(score_next_level)/4) {
				return true;	
			}
		}
		if(score>=9600) {
		   return true;
		}
		return false;
	}
	
	// displays the intro screen
	public void displayIntro(){
		grid.setSplash(BG_IMG);
		boolean pause_screen= true;
		while (pause_screen) {
			grid.sleep(timerDelay); // pause for some time (smooth animation)
			if (grid.checkLastLocationClicked()!=null) {
			    pause_screen= false;
			    grid.setSplash(null);
			}
		}
	}
	
	// displays the game over screen or win screen, depending on outcome
	public void displayOutcome() {
		if(score>=9600) {
		grid.setSplash(WIN_IMG);
		}
		else{
		grid.setSplash(OVER_IMG);	
		}
		boolean pause_screen= true;
		while (pause_screen) {
			grid.sleep(timerDelay); // pause for some time (smooth animation)
			if (grid.checkLastLocationClicked()!=null) {
			    pause_screen= false;
			    System.exit(0);
			}
		}
	}
}