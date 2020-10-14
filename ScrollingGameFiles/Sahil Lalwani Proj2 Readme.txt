Name: Sahil Lalwani

Progress statement for Project 02: Scrolling Game Checkpoint

1.Theme and overall vision of the game

The title of my game is ‘The Healthy Quest’. It is meant to be a game wherein you proceed through the seven levels or stages of life, as defined by Shakespeare in his famous work ‘All The World’s a Stage’, right from an infant to death after living a long life. In this game, the user is meant to collect salad and fruits (pineapple and apple) as healthy objects, and avoid colliding with burgers as unhealthy objects. There is also exercise as a power-up, which appears at regular intervals and adds a high number of points to the overall score. Similarly, there is also a couch image, repressing a person resenting exercise to merely sit on the couch and eat unhealthy food, that is a power down and reduces the score by relatively higher number of points than the burgers as unhealthy objects.

The broader idea is that the player has to collect a certain number of points to transition into the next level. The user also starts with 50 points currently. Now, the user currently needs 150 points to reach the second level. The user then needs double the number of points needed to transition into the current level in order to reach the next level. For example, the user would need at least 300 points to reach the second level, at least 600 points to reach into the third level etc. Thus, going by this geometrical series’ pattern, the user needs at least 9600 points to complete the seventh level and win the game. In addition, the user loses or the game is over if the user gets a zero or negative score in the first 2 levels. From level 3 onwards, the game is over if the user goes below the score needed to enter the level before the current level. For example, the user would lose the game in level 3 if the user falls below a score of 150, which was needed to enter level 2.

However, the game has its own set of challenges. Even though it is not meant to be a very hard game, it has a progressively high level of difficulty as you proceed to higher stages. This rise in difficulty is realized through various means. In any level, the points gained from healthy objects are 10+2*level, while the points lost from collision with unhealthy objects is -5*level. In addition, the probability of occurrence of unhealthy objects also increases as the levels increase, while the probability of occurrence of healthy objects decreases as the levels increase. The main implication of this setup is that the game is easier to play in the initial stages, with more chances to gain higher points from healthy objects, while you lose high points from highly occurring unhealthy objects as the game proceeds to be relatively harder!

Just like you get chances to redeem or boost yourself in life though, you also have an exercise power-up in the game. Along those lines, there is also a couch (meant to represent laziness and inactivity) power-down which makes you lose high number of points. Now, the points gained from the power up and power down are the same, but these points also increase as the game goes on. Based on the algorithm of my current code, you gain 15*level points from a power up, and lose the same number of points from a power down. Again, as the level increases, the frequency of occurrence of a power down increases while the frequency of occurrence of a power up decreases.

These details may seem overwhelming, and hence there is also an introductory screen that explains all the details rules and instructions. As you cross levels, the screen pauses and displays a transition screen for the next level. The transition screen displays all relevant details of the next level to the user, including the number of points needed to cross this level, the points gained or lost by healthy and unhealthy objects, point values of power up and power down etc. You need to press a ‘p’ key to move to the next level and close the level transition image, but you need to click a mouse to begin the game or close it in case of a ‘Game Over’ or ‘You won’ screen.

2. Customizations and new features added

a. More number of get (healthy) objects, and change in probability of occurrence of objects as levels increase: While the base game only had one get object, I have 3 get objects. In addition, the probability of occurrence of a get or avoid object was the same throughout the game in the base game, but the probability of occurrence of a healthy object decreases as levels increase, while the probability of occurrence of an unhealthy object increases as levels increase. 

b. Levels added: While the initial base game had no concept of a base game, I have added 7 levels to it. I have tried to add splash screens distinct for each level, and have also tried to update the title and game state to reflect the changes in levels. In addition, the levels also tie in very well with my theme of the seven stages of life.

c. Power up and power down added: Power up and power down, with varying amounts of points gained and lost from them as the game goes on. They also tie in very well with my narrative about exercising as a power up to help us propel into the next stages of a healthy life, while laziness and eating unhealthy food leads to both short-term and long-term losses.

d. Time of the game: While the base game had the option to increase/decrease the speed of the game using key presses, I have retained that feature but also added the feature of increasing the speed of the game proportionate to the increase in level.

e. Change in level freeze screen: I added separate splash or freeze screens showing the transition into the next level, and the instructions for the next level. I also designed and reworked my code such that you could exit the level transition screen and continue playing the game using the ‘p’ key.

f. There is a separate ‘Game Over’ and ‘You won!’ screen.

3. Explanation of the design for the implementation of these extra feature(s) [I shall use the symbols a,b,c,d and e to refer to the new features added above]

a. I created private instance variables to ensure abstraction in controlling probability of generation of healthy and unhealthy objects, and these were not declared as constants because they had to be varied according to a change in levels. This feature has been implemented in the populateRightEdge(), which uses fill_elements(int rand_num, int i) as a helper method. Methods path_clear(int count) and path_clear_overall() have also been used to ensure that the user always has a path to proceed without colliding with the unhealthy objects.

b. A new private instance variable called level was added, which keeps track of the level the user is at. It is also used to display the stage of human life, based on Shakespeare’s literary work. As a helper method to handleCollision(Location loc) method, next_level_conditions() also updates the relevant instance variables and game state to reflect a change in level.

c. Power up and power down are randomly created as part of populateRightEdge() method. A separate helper method is used to generate them within game cells with varying levels of probability as the levels increase. handleCollision(Location loc) handles the points gained or lost from a power up or power down respectively depending on game level.

d. grid.sleep(timerDelay) is used to change the speed of the game at various points in the code to make the game quicker and faster as the levels increase. 

e. In my game now, pause_screen helper method takes a parameter, char, to see if it just needs to pause the screen or also display the level transition screen. Using one method to handle both the functions reduced redundancy.

f. Game over and You won screens are displayed under the displayOutcome() method.

4. My favorite implementation

It is the adding of levels or stages to my game, and designing the related level transition screens. I think it gave depth and relevance to my theme and narrative, and it was enjoyable to code it.

5. The theme

All of the new features added have a special relevance to my theme or narrative. Essentially, the increase in the speed of the game as the levels increase demonstrates that life goes by quicker in the higher stages of life. The low probability of occurrence of power-up in the higher rounds show that the opportunities to exercise are rare as life moves on, because we are busy but do not attribute value to exercise. But, this game shows that there are huge value points associated to a healthy life. All seven stages life have something in common- the need to live and embrace a healthy life, preventing as long as possible the consumption of unhealthy food or avoiding unhealthy practices.

