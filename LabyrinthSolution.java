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
	/ Makes safe moves to solve the maze and records the moves in the ArrayList
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

	static boolean isSafeMove(Labyrinth l, int row, int column){
		if(isSafe(l, row + 1, column))
			return true;
		else if(isSafe(l, row, column + 1))
			return true;
		else if(isSafe(l, row - 1, column))
			return true;
		else if(isSafe(l, row, column - 1))
			return true;
		
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
	
	static boolean isSafe(Labyrinth l, int row, int column){
		if(l.isValid(row, column) && l.isStone(row, column)){
			if(row < visited.length && column < visited[0].length){
				if(!visited[row][column])
					return true;
			}
		}
		return false;
	}
	
	static int[] toSolution(Labyrinth l){
		int[] solution = new int[moves.size()];
		for(int i = 0; i < moves.size(); i++){
			solution[i] = moves.get(i);
		}
		return solution;
	}
	
	public static void main(String[] args){
		int rows = Integer.parseInt(args[0]);
		int columns = Integer.parseInt(args[1]);
		Labyrinth l = new Labyrinth(rows, columns);
		visited = new boolean[l.rows][l.cols];
		moves = new ArrayList<Integer>();
		
		System.out.println(args[0]);
		System.out.println(args[1]);
		
		
		boolean test = solveMaze(l, 0, 0);
		
		System.out.println(test);
		int[] solution = toSolution(l);
		System.out.println(l.solves(solution));
		
		l.printGrid();
		for(int i = 0; i < visited.length; i++){
			for(int j =0; j < visited[0].length; j++)
				System.out.print(visited[i][j] + " ");
			System.out.println();
		}
		for(int i = 0; i < solution.length; i++)
			System.out.print(solution[i] + " ");
	}
}
