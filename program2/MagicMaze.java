/* Katelyn Grimes
 * Dr. Steinberg
 * COP3503 Spring 2023
 * Programming Assignment 2
 */
 
 import java.io.File;
 import java.io.IOException;
 import java.util.Scanner;
 import java.util.HashMap;
 import java.util.ArrayList;
 
 public class MagicMaze{
	 private char maze[][];
	 private boolean boardVisited[][];
	 private String mazeName;
	 private int rowNum;
	 private int columnNum;
	 private int exitRow;
	 private int exitColumn;
	 private HashMap<Integer, ArrayList<Integer>> teleportHash = new HashMap<Integer, ArrayList<Integer>>();;
	 
	 public MagicMaze(String mazeName, int rowNum, int columnNum) throws IOException{
		 this.mazeName = mazeName;
		 this.rowNum = rowNum;
		 this.columnNum = columnNum;
		 
		 maze = new char[rowNum][columnNum];
		 maze = readMaze(mazeName);
		 
		 boardVisited = new boolean[rowNum][columnNum];
	 }
	 
	 //need a method that scans the file into the char array
	 //the number of rows and columns are found from the agrs[]
	 public char[][] readMaze(String mazeName) throws IOException{
		 
		 //char newLine = '0';
		 Scanner scan = new Scanner(new File(mazeName));
		 
		 for(int r = 0; scan.hasNextLine() && r < rowNum; r++){
			char temp[] = scan.next().toCharArray();
			for(int c = 0; c < columnNum; c++){
				maze[r][c] = temp[c];
				createHash(r,c);
				if(maze[r][c] == 'X'){
					exitRow = r;
					exitColumn = c;
				}
			}
		}
		 scan.close();
		 
		 return maze;
	 }
	 
	 public void createHash(int r, int c){
		if(Character.isDigit(maze[r][c])){
			 int portKey = Character.getNumericValue(maze[r][c]);
			 
        //if the key already exists 
			if(teleportHash.containsKey(portKey)){
			  teleportHash.get(portKey).add(r);
			  teleportHash.get(portKey).add(c);
			}
			//first time key is created 
			else{
			  teleportHash.put(portKey, new ArrayList<Integer>());
			  teleportHash.get(portKey).add(r);
			  teleportHash.get(portKey).add(c);
			} 
		}
		 
	 }
	 
	 //if true, the maze was solved, else, maze was not solved
	 public boolean solveMagicMaze(){
		 //set Kenny at the bottom left corner
		 return solveMagicMazeR(rowNum-1,0);
	 }
	 
	 //recusrsive method - similar to N-Queens
	 public boolean solveMagicMazeR(int currentR, int currentC){
		 
		 //check - is this position visited?
		 if(boardVisited[currentR][currentC] == true){
			 return false;
		 }
		 
		 //check - is this the exit point?
		 if(checkExitPoint(currentR, currentC)){
			 return true;
		 }
		 
		 //check - is this a teleport?
      int key = 0;
      if(Character.isDigit(maze[currentR][currentC])){
          key = Character.getNumericValue(maze[currentR][currentC]);
 
    		 if(teleportHash.containsKey(key)){
            
            	//default values
            	int tpr = 0;
            	int tpc = 0;
    			 
    			if(teleportHash.get(key).get(0) == currentR && teleportHash.get(key).get(1) == currentC)           {
    				tpr = teleportHash.get(key).get(2);
    				tpc = teleportHash.get(key).get(3);
    			}
           		else{
    				tpr = teleportHash.get(key).get(0);
    				tpc = teleportHash.get(key).get(1);
            	}
           
           		//need to remove from the hash
    			teleportHash.remove(key);
    			 
    			if(solveMagicMazeR(tpr,tpc)){
	    			return true;
    			}
    			 
    			//else - the teleport led to a dead end, BACKTRACK
    			boardVisited[currentR][currentC] = false;
            
           		if(solveMagicMazeR(currentR,currentC)){
             		return true;
           		}		 
    	  	}
     }
		 
		 //check - is this a dead end?
		 if(maze[currentR][currentC] == '@'){
			 boardVisited[currentR][currentC] = true;
			 return false;
		 }

		//if all the check pass, then set current position to visited 
		 boardVisited[currentR][currentC] = true;
		 
		 //priority moves - up, right, down, left
		 if(currentR != 0 && solveMagicMazeR(currentR-1,currentC)){
			 return true;
		 }
		 else {
			 if(currentC != columnNum-1 && solveMagicMazeR(currentR,currentC+1)){
				 return true;
			 }
			 else{
				 if(currentR != rowNum-1 && solveMagicMazeR(currentR+1,currentC)){
					 return true;
				 }
				 else{
					 if(currentC != 0 && solveMagicMazeR(currentR,currentC-1)) {
						 return true;
					 }
				 }
			 }
		 }
		 
		 return false;
	  
	 }
	 
	 public boolean checkExitPoint(int r, int c){
		if(r == exitRow && c == exitColumn){
			return true;
		}
		return false;
	 }

	 
 }
