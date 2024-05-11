# **Magic-Maze**
This is assignment 2 from COP3503, Computer Science 2

### Overview:
The goal is to create an algorithm that allows the user to escape any magic maze given by the input files, _maze#.txt"_ (# corresponds to the numbered maze). If the maze passes, the user has successfully escaped the maze, however, if it does not pass, then they are trapped inside the magic maze! The rules are as follows...

Rules:
1. Always start in the bottom left corner of the maze
2. Valid spaces are marked with "*"
3. Invalid spaces are marked with "@"
4. The only valid directions to move are up, down, left, and right
5. The exit is marked by "X"
6. If a digit is reached, the user will be teleported to the corresponding digit somewhere else in the maze

### Driver File:
This file contains main and will send the necessary information, the maze textfile, number of rows, and number of columns, from the arguments to _"MagicMaze.java"_ and test to see if the maze passed or not.  

*NOTE: You can test only one maze at a time.

### Terminal Commands:
```
javac MagicMazeDriver.java //compile
java  MagicMazeDriver maze4.txt 15 20 //run
```
number of rows = 15
number of columns = 20

Size of mazes:
- maze1.txt: 11x15
- maze2.txt: 11x15
- maze3.txt: 11x15
- maze4.txt: 15x20
- maze5.txt: 15x20
- maze6.txt: 5x5
- maze7.txt: 5x5
- maze8.txt: 15x20
- maze9.txt: 15x20

### Output:
If a maze passed, then the user will see `maze#.txt passed!` printed to the terminal screen.  

If a maze does not pass, then the user see `maze#.txt did not pass!` printed to the terminal screen.

