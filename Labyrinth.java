public class Labyrinth {
    public final int rows;
    public final int cols;
    private UF tracker;
    private int destination;
    public static final int[] UP = {-1,0};
    public static final int[] DOWN = {1,0};
    public static final int[] LEFT = {0,-1};
    public static final int[]  RIGHT = {0,1};
    private boolean[][] grid;
    
    /**
    * Constructs a random labyrinth with specified width and height.
    * @param x the width (in grid squares) of the maze.
    * @param y the height (in grid squares) of the maze.
    * @return a new Labyrinth object of the specified dimensions.
    */
    public Labyrinth(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.tracker = new UF(rows * cols);
        this.destination = rows * cols - 1;
        
        build();
    }
    
    //Constructs the labyrinth
    private void build() {
        //Allow access to the starting and ending squares.
        grid[0][0] = true;
        grid[rows-1][cols-1] = true;
        
        while(!tracker.find(0 , destination)) {
           int n = (int) (Math.random() * this.rows);
           int m = (int) (Math.random() * this.cols);
           grid[n][m] = true;
           link(n,m);
        }  
    }
    
    //Converts grid coordinates to "absolute" coordinates used by the tracker.
    private int toAbs(int row, int col) {
        return row * cols + col;
    }
    
    //Check if a given row and col are a valid coordinate in the grid.
    public boolean isValid(int row, int col) {
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }
    
    //Link a given grid square to adjacent stone (not lava) sites.
    private void link(int row, int col) {
        for (int[] direction : new int[][]{UP, DOWN, LEFT, RIGHT}) {
            int neighborRow = row + direction[0];
            int neighborCol = col + direction[1];
            if (isValid(neighborRow, neighborCol) && isStone(neighborRow, neighborCol)) {
                tracker.union(toAbs(row, col), toAbs(neighborRow, neighborCol));
            }
        }
    }
    
    //Check if a given site is stone (not lava).
    public boolean isStone(int row, int col) {
        return grid[row][col];
    }
    
    private void printGrid() {
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print(" S ");
                } else {
                    System.out.print(" _ ");
                }
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Labyrinth l = new Labyrinth(5,5);
        l.printGrid();
    }
    
    //Private Union-Find class to assist with random maze construction.
    private class UF {
        private int[] id;
        private int[] size;
        
        UF(int n){
            id = new int[n];
            size = new int[n];
            
            for(int i = 0; i < n; i++) {
                id[i] = i; 
                size[i] = 1;
            }
        }
        
        int rootOf(int i){
            while(id[i] != i){
                id[i] = id[id[i]];
                i = id[i];
            }
            
            return i;
        }
        
        void union(int p, int q){
            int pRoot = rootOf(p);
            int qRoot = rootOf(q);
            
            if(pRoot == qRoot) return;
            
            if(size[pRoot] < size[qRoot]){
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            else{
                id[qRoot] = pRoot;
                size[pRoot] += qRoot;
            }
        }
        
        boolean find(int p, int q){
            return rootOf(p) == rootOf(q);
        }
    }

}