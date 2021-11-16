/*
* N Queens Demonstration
* 
* (c) 2021 Joel Hammer
* Friends School of Baltimore
*
*
* Compile: javac NQueens.java
* Run: java NQueens [n]
*
*
*
*/

/**
* A solution finder for the N-Queens problem of arbitrary size. Solutions
* are found using backtracking and printed to StdOut once found.
*
* @author Joel Hammer
*/
public class NQueens {
    private final int n;
    private boolean[][] grid;
    private final int[] UP = {-1,0};
    private final int[] DOWN = {1,0};
    private final int[] DL = {1,-1};
    private final int[] DR = {1,1};
    private final int[] UR = {-1,1};
    private final int[] UL = {-1,-1};
    
    /**
    * Constructs an N-Queens problem solver with specified dimensionality.
    * @param n The number of rows, columns, and queens on the chess grid.
    * @return an NQueens solver object of dimension n.
    */
    public NQueens(int n) {
        this.n = n;
        grid = new boolean[n][n];
    }
    
    /**
    * Solves the N-Queen problem and prints out all possible solutions.
    */
    public void solve() {
        findSafeColumn(0);
    }
    
    //Check if the given row and column are in the grid.
    private boolean isValid(int row, int col) {
        return (row < n && row >= 0 && col < n && col >= 0);
    }
    
    
    /*Check all directions (excluding left and right) to ensure a queen
    * placed at the given row and column isn't attacking any other queens.
    */
    private boolean isSafe(int row, int col) {
        if(!isValid(row, col)) {
            return false;
        }
        
        return isSafe(row, col, UP) && isSafe(row, col, DOWN) &&
               isSafe(row, col, DL) && isSafe(row, col, DR) &&
               isSafe(row, col, UL) && isSafe(row, col, UR);
    }
    
    //Checks the given direction for queens. Assumes row and col are already validated.
    private boolean isSafe(int row, int col, int[] direction) {
        while (isValid(row,col)) {
            if (grid[row][col]) {
                return false;
            }
            
            row += direction[0];
            col += direction[1];
        }
        
        return true;
    }
    
    //Show the queens on the grid by printing to terminal.
    private void printGrid() {
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print(" Q ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }
    
    /**
    * Uses backtracking to place a queen into a safe column on a given and
    * following rows. Takes a parameter of "0" to solve the entire grid.
    * @param row the current row onto which a queen is to be placed. Should
    * start with {@code row = 0}.
    */
    private void findSafeColumn(int row) {
        //Print the solution when found.
        if (row >= n) {
            printGrid();
            return;
        }
        
        //Try every possible column on the current row.
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                grid[row][col] = true;
                findSafeColumn(row + 1);
                grid[row][col] = false; //Backtrack.
            }
        }
    }
    
    /**
    * Solves the N-Queens problem given n from the command line.
    * @param args an array of arguments from StdIn. Must be of length 1 where
    * {@code args[0]} is the dimensionality of the N-Queens Problem. 
    */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage is: java NQueens [dimension]");
            return;
        }
        
        int n = Integer.parseInt(args[0]);
        
        NQueens q = new NQueens(n);
        q.solve();
        
    }
    
}