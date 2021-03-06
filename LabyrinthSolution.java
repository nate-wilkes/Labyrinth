import java.util.ArrayList;
public class LabyrinthSolution{
	/**
	/ ArrayList that stores all the moves needed to complete the maze
	*/
	static ArrayList<Integer> moves;
	
	/**
	/ Array that keeps track of which squares have been visited
	*/
	static boolean[][] visited;
	
	/**
	/ Method that the user calls to solve the method
	/ @param l the maze that the user wants to solve
	/ @return an int array that contains the list of moves
	*/
	public static int[] solve(Labyrinth l){
		visited = new boolean[l.rows][l.cols];
		moves = new ArrayList<Integer>();
		
		boolean solved = solveMaze(l, 0, 0);
		return toSolution(l);
	}
	
	/**
	/ Makes moves to solve the maze and records the moves in the ArrayList
	/ @param l the maze that will be solved
	/ @param row the curremy y coordinate
	/ @param column the current x coordinate
	/ @return if the maze is solved or not
	*/
	static boolean solveMaze(Labyrinth l, int row, int column){
		visited[row][column] = true;
		boolean solved = false;		
		if(l.rows - 1 == row && l.cols - 1 == column)
			return true;
		else if(isSafeMove(l, row, column)){
			if(isSafe(l, row + 1, column)){
				moves.add(1);
				solved = solveMaze(l, row + 1, column);
			}
			if(!solved && isSafe(l, row, column + 1)){
				moves.add(3);
				solved = solveMaze(l, row, column + 1);
			}
			if(!solved && isSafe(l, row - 1, column)){
				moves.add(0);
				solved = solveMaze(l, row - 1, column);
			}
			if(!solved && isSafe(l, row, column - 1)){
				moves.add(2);
				solved = solveMaze(l, row, column - 1);
			}
		}
		return solved;
	}
 
	/**
	/ Determines whether or not there is a safe move available, if there isn't it begins backtracking
	/ @param l the maze that is being solved
	/ @param row the current y coordinate
	/ @param column the current x coordinate
	/ @return whether or not a safe move is available
	*/
	static boolean isSafeMove(Labyrinth l, int row, int column){
		//checks to see if a safe move is possible
		if(isSafe(l, row + 1, column))
			return true;
		else if(isSafe(l, row, column + 1))
			return true;
		else if(isSafe(l, row - 1, column))
			return true;
		else if(isSafe(l, row, column - 1))
			return true;
		
		//backtracks
		if(moves.get(moves.size() - 1) == 0){
			moves.remove(moves.size() - 1);
			return isSafeMove(l, row + 1, column);
		}
		else if(moves.get(moves.size()- 1) == 1){
			moves.remove(moves.size() - 1);
			return isSafeMove(l, row - 1, column);
		}
		else if(moves.get(moves.size() - 1) == 2){
			moves.remove(moves.size() - 1);
			return isSafeMove(l, row, column + 1);
		}
		else{
			moves.remove(moves.size() - 1);
			return isSafeMove(l, row, column - 1);
		}
	}

	
	/**
	/ Checks to see if a move is safe
	/ @param l the labyrinth that is being solved
	/ @param row the y coordinate that is being checked
	/ @param column the x coordinate that is being checked
	/ @return whether or not the move is safe/legal
	*/
	static boolean isSafe(Labyrinth l, int row, int column){
		if(l.isValid(row, column) && l.isStone(row, column)){
			if(row < visited.length && column < visited[0].length){
				if(!visited[row][column])
					return true;
			}
		}
		return false;
	}
	
	/**
	/ Converts the ArrayList into an to send to the Labyrinth method
	/ @param l the labyrinth that has been solved and contains the ArrayList with the moves
	/ @return the converted array
	*/
	static int[] toSolution(Labyrinth l){
		int[] solution = new int[moves.size()];
		for(int i = 0; i < moves.size(); i++){
			solution[i] = moves.get(i);
		}
		return solution;
	}
	
	public static void main(String[] args){
		Labyrinth l = new Labyrinth(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		
		System.out.println(args[0] + " " + args[1]);
		
		int[] solution = solve(l);
		
		System.out.println(l.solves(solution));
		
		for(int i = 0; i < solution.length; i++)
			System.out.print(solution[i] + " ");
		
		l.printGrid();
	}
}