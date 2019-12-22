# Group_0711
 
INTRODUCTION
------------

This App is called GameCentre, and it contains three distinct games:
1. Sliding Tiles.
2. Mine Sweeper.
3. Connect 4.
Detailed explanation will be introduced later in this file.

REQUIREMENTS
------------

This module requires the following modules:

 * Android Studio 3.2
 * API Level:28
 * SDK Platforms: Android9.0(Pie)
 * Java SE Development Kit 8u191

INSTALLATION
------------
 
1. [Install Android Studio](https://developer.android.com/studio/).
2. [Get and copy the project's URL and repository here](https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0711).
3. Follow the prompts.
4. Create an Android Virtual Device within Android Studio. Select a Pixel2 smartphone as the device to emulate, 
    specifying the device OS as Android 8.1 API 27. Create and launch the virtual device and ensure it loads correctly.
    may need to download this specific build of Android at this step.
5. Create and launch the virtual device and ensure it loads correctly.
6. Run APP.

FEATURES & EXPLANATIONS
------------

This section will introduce the main functionality of this app in general, and then go through details of patterns and 
algorithms of these three different games. The overall functionality of GameCentre will be explained first.

* **GameCentre**
    
    The GameCentre is the core of this app, as well as the most important part to contain data of users and games.
    It has following functionality:
    
    1. _Log In / Sign Up._
    
        When opening the GameCentre, clients will first see a Welcome page, and after few seconds the Log In page will
        show up. The clients can choose to log in use existing accounts, or register a new account by pressing the Sign
        Up button on the page. By signing up a new account, a User object will be created and stored in AccountManager.
        The User object will contain the userName and passWord created by clients, and AccountManager will be managing 
        all different users. The AccountManager is implemented by Singleton pattern, which grants a lot of convenience
        in saving data and getting information of Users.
    
    2. _ScoreBoard._
    
        The ScoreBoard is a factory responsible of saving and generating different score boards showing different score
        for different games and users. A ScoreBoard will be loaded and established before starting a new game, and saved
        after each game. After each game is finished, a Score object will be passed and saved into the ScoreBoard. A 
        Score object contains the number of scores the player got, the name of the player and the name of the game he 
        played. There are two different types of scoreBoards that need to be shown on the score board page: an overall
        score board of all three games of current player, and a score board of one given game containing all users have 
        played it. These two score boards will be generated in the ScoreBoard factory, according to information given
        such as userName and gameType. This factory will compare the given information to the information that each
        Score contains, and generate a sorted list of desired scores to be displayed on score board page.
    
    3. _Save/Load._
        
        GameCentre will save every game that is not finished. By going back to the main page or exiting the game, the
        game is saved automatically into User, and all Users is stored in AccountManager. If the client wants to load 
        the game back, GameCentre will go to AccountManager and find the corresponding User, and then pull out 
        previously saved game from User and put it back.
    
* **Connect 4**
    
    Connect 4 is a classic board game for two player. It has a board with 6 rows and 7 columns, and each player can 
    place stones of their own down the bottom of the board by touching the certain column of screen. The first player 
    who get 4 of their stones connected in a line will win the game. This game has these features:
    
    1. _Change Themes._
        
        Clients can choose to change the themes in the home page of this game. There are two themes provide: pokemon
        theme and the default theme. By changing the theme, the picture displayed for the stones placed by players will
        be changed. In default theme, the picture displayed for stones of player 1 will be a silver coin, and for 
        player2 will be a gold coin. In pokemon theme, the picture displayed for two players will be Pikachu and Eevee.
    
    2. _PVP/PVE._
    
        In PVP game, the client will be able to play with friends locally. Two player will place there own stone down
        the board turn by turn. However, scores will not be generated for PVP mode in case to prevent players of getting
        high scores on score board intentionally.
        
        In PVE game, the client will face a computerAI as his enemy. In this game mode, scores will be generated as a
        mark of ur personal skills.
        
    3. _Selecting Difficulty of PVE._
        
        This game provides two difficulties for player to play against: Beginner and Intermediate.
        
        In the beginner mode, the computer will only randomly place its stones down the board, so it will be very easy
        for players to win the game.
        
        In the intermediate mode, the computerAI will be used to get the best move against players. When it comes to the
        computer to place its stone, computerAI will scan the board to get available spots of placing its stone. For 
        each of the available spots, an int representing the value of placing a stone down that spot will be generated.
        The value is calculated following these rules:
        
        1. The spot worth 100 if the computer wins by putting a stone down that spot.
        2. The spot worth 99 if the computer prevents player from winning the game by putting a stone down that spot.
        3. The value of that spot will be set to an initial value of 0, and then value will be added up according the 
            those stones in two surrounding layers of that spot.
        
        After the value of each available spot is generated, computerAI will choose the spot with the highest value to
        place its stone.
        
* **Mine Sweeper**
    
    Mine Sweeper is also a classic board game. Players are required to clear all the mines buried in the board according
    to numbers displayed on each spot of the board. Touching a mine will cause the player to lose the game. There are
    two important features of this game to talk about:
    
    1. _ShowWhiteSpaces._
    
        In MineSweeper, touching an empty block (white space) will cause all empty blocks to be revealed. This is one
        important feature, and it's done by a recursive function called showWhiteSpaces. This function will be called
        when the player touch an empty block. When this function is called, it will check it surrounding blocks. If
        a block is a mine, then that block will still be buried; If a block is a number, then that block will be 
        revealed; If a block is an empty block, then that block will be revealed, and this function will be called on
        that block again.
    
    2. _Change Difficulty._
      
        The difficulty of MineSweeper can be changed by dragging a sliding bar on the main page of this game. Dragging
        the bar will change the board size of this game, as well as putting more mines down the board. The number of
        mines is currently set to be 10% of total number of blocks.
        
* **Sliding Tiles**

    Sliding tiles is a game where the players are given a board with every block filed with different numbers. The 
    player is required to rearrange the tiles so that the numbers are in the order from smallest to greatest, from 
    the upper left corner to the bottom right corner. One important thing to introduce is that our Sliding tile game
    will not generate in-solvable boards. Detailed explanation is here:
    
    * _Shuffle._
    
        When new game starts, the sliding tile game will first generate a board that is already arranged in order. Then
        the board is rearranged by randomly moving tiles to available position, by default, 50 times. In this case, 
        there will be always a solution for this board: revert every move of moved tiles.
        
Unittests
------------

There are unittests provided with this app. These tests covered most of classes and methods, except for some activity 
classes that have no logic involved. Tests are put in the test packages.
    
Group Members
-------------

* Zhiyuan Yang (Github name: CSMYang)
* Hanlai Chen
* Yiwen Zhang
* Renke Cao
* Yi Wai Chow
* Haiyi Hu

