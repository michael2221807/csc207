### Contact Information

* Zhiyuan Yang: portal.yang@mail.utoronto.ca, (647)676-4687
* Yiwen Zhang:  yiw.zhang@mail.utoronto.ca,   (647)893-5761
* Hanlai Chen: hanlai.chen@mail.utoronto.ca,  (416)704-8366
* Renke Cao: renke.cao@mail.utoronto.ca,      (647)686-3166
* Yi Wai Chow: yiwai.chow@mail.utoronto.ca    (647)833-6434
* Haiyi Hu: haiyi.hu@mail.utoronto.ca         (778)862-4553

### Communication Tools 
A WeChat group is settled up for daily communications and discussions.

### Team Rules

1. Nobody should ever hide his problems, or try to solve problems by himself. Seek help if someone gets stuck.
2. Never be afraid of sharing ideas to other team members.
3. Talk with others instead of arguing with others.
4. No interruption when other teammates are sharing their ideas. 
5. Everyone mush have their work done on time.
6. No negative attitude when working together, because negative attitude can easily affect other team members.
7. If team decide to meet at certain time, nobody should be absent.

### Team Discussion Notes:

These notes will be updated after each group meeting:

1. _First Meeting:_
    
    Everyone is sharing their ideas on the games to be added to GameCentre. Here is a list of possible games:
    1) 2048;
    2) Connect 4;
    3) BlackJack (GamblingCentre????);
    4) Gobang;
    5) MineSweeper;
    6) Snake;
    7) Other random thoughts----
    Still waiting to decide the games to be implemented.
    
2. _Second Meeting:_

    Decisions are made on the games to be added to GameCenter: Connect 4 and MineSweeper. These two games are board
    games so we may use the board and coding structure from Sliding tiles. Now the only thing left is to choose which
    game to start with.
    
3. _Third Meeting:_
    
    We choose to start with Connect 4. Now it's time to figure out what features need to be implemented, how will the
    game work, and what classes need to be wrote. 
    Here is a summary of classes to be wrote: 
    1) Grid (The board of Connect 4)
    2) GridManager
    3) ComputerAI (The movement decider for PVE mode)
    4) UI
    Here is how the work is distributed:
    Zhiyuan Yang will be focus on ComputerAI and algorithms.
    Yiwen Zhang takes responsibility in GameActivity and ComputerAIActivity.
    Haiyi Hu will be dealing with Grid and GridManager.
    ChenHan Lai is going to add SaveGame feature to Connect 4 and write codes that make Sliding Tiles always solvable.
    Renke Cao writes UI and User for this game.
    Yi Wai Chow will be implement ScoreBoard.
    
4. _4th Meeting:_
    
    Putting code together, debugging and deciding what to do next.
    Work for second game now distributed:
    Haiyi Hu creates difference images for Connect 4.
    Yi Wai Chow will be dealing with game body and algorithms.
    Hanlai Chen adds Save feature for this game.
    Yiwen Zhang will be doing the ScoreBoard.
    Renke Cao writes the UI and setting page for this game.
    
    Some other works are being distributed like this:
    Zhiyuan Yang creates a detailed ReadMe file and Team file for the entire group, and javadoc for ScoreBoard and ComputerAI
    added.
    
5. _5th Meeting:_

    ScoreBoard re-designed. A factory called ScoreBoard created by Zhiyuan Yang instead of getting scoreBoard from
    UserScoreBoard class and GameScoreBoard class. Some bugs fixed.
    Unittests were contributed by several teammate , including Yi Wai Chow,  Hu Hai Yi, Yiwen Zhang and Hanlai Chen. The remaining part of
    javadoc is written by Hanlai Chen and Yiwen Zhang.
    The GameCentre is now in final polish stage.
