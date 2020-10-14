public class GameLauncher{ 
 /**
  * Only CHANGE boolean values constants BASE/DEMO (l.26-27)
  *     - to view the demo: DEMO = true;
  *     - to play your games DEMO = false;
  *
  *     - for running the Base Game: BASE = true;
  *     - for running your Creative Version: BASE = false;
  * 
  * DO NOT ADD CODE TO THIS CLASS
  * 
  * The Scrolling Game is described the Project Handout. 
  *  
  *
  * @author Elodie Fourquet, Sandra Jackson 
  * @date February, 2019
  */
  
   private static final boolean DEMO = false;
   private static final boolean BASE = false;
   
   private static ScrollingGame game;
  
  
   public static void main(String[] args) {
      if (DEMO) {   
         // Construct a sized version of the DEMO Game
         game = new DemoGame(5, 10, 0);
         System.out.println("Running the demo: DEMO=" + DEMO);
         	 
      } else if (BASE) {
      	  // Construct sized version of the base game you've written
          game = new BaseGame(5, 10, 0);
          System.out.println("Running student version of the base game: DEMO=" + DEMO + " BASE=" + BASE);
      } else {
          // Construct sized version of the creative game you've written
          game = new SahilLalwaniGame(5, 10, 0);
      }

      game.play();

   }

}