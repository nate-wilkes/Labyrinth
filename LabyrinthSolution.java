import java.util.ArrayList;
public class LabyrinthSolution {
	int rows;
	int columns;
	Labyrinth l;
	
	public LabyrinthSolution(int rows, int columns){
		this.rows = rows;
		this.columns = columns;
		this.l = new Labyrinth(rows, columns);
	}
	
	public void findSafeMove(int row, int column){
		if(row == rows && column == columns){
			return;
		}
	}
	
	public static void main(String[] args){
		int rows = (int)(Math.random() * 20);
		int columns =  (int)(Math.random() * 20);
		LabyrinthSolution solver = new LabyrinthSolution(rows, columns);
		
		solver.findSafeMove(0, 0);
		System.out.println(rows + "\t" + columns);
		
	}
}
