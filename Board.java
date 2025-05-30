
public class Board  {


    private int rows;
    private int cols;
    
    /** The grid of pieces */
    private Player[][] grid;
    
    

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Player[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c] = null;
            }
        }
        // set each cell of the board to null (empty).
        reset();

    }
    
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = null;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    
    /**
    * Returns the Player whose piece occupies the given location, 
    * @param row int
    * @param col int
    */
    public Player getCell(int row, int col ) throws IndexOutOfBoundsException{
        if( (row < 0) || (col < 0) || (row >= rows) || (col >= cols) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid[row][col];
        }
    }
    
    //returns true if there are no more plays left
    public boolean boardFilled(){
         for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == null)
                    return false;
            }
         }
         return true;
    }

    // Returns true if move is possible given board state.  
    public boolean possibleMove(Move move) {
        return (grid[rows - 1][move.getColumn()]== null);
    }
    
    // Adds a piece to the board for a given Move
    public void addPiece(Move move) {
        //TODO: this is a test stub, you need to rewrite this.
        int c = move.getColumn();
            for(int r = 0; r < rows; r++){
                if(grid[r][c] == null){
                    grid[r][c] = move.getPlayer();
                    break;
                }
                    
            }
        }
        

    

    // if the board contains a winning position, returns the Player that wins.
    // Otherwise, returns null.  You could ignore lastMove.

    //write seperate method called colwinner...
    // then do a row winner
    //then do diagonal

    public boolean colWinner(Move lastMove){
        int a = 0;
        for(int i = 0; i < rows; i++){
            if(grid[i][lastMove.getColumn()] == lastMove.getPlayer()){
                a++;
            }
            else a = 0;
            if(a == 4){
                return true;
            }
        }
        return false; 
    }

    public boolean rowWinner(Move lastMove){
        int A = 0; 
        int row = 0;
        for(int i = rows-1; i > 0; i--){
            if(grid[i][lastMove.getColumn()] != null){
                row = i;
                break;
            }
        }
        for(int j = 0; j < cols; j++){
            if(grid[row][j] == lastMove.getPlayer()){
                A++;
            } else{
                A = 0;
            }
            if(A == 4){
                return true;
            }
        }
        return false;
    }

    public boolean diagonalWinner(Move move){
        Player p = move.getPlayer();        
        int z = 0;
        int c = move.getColumn();
        if(grid[rows - 1][move.getColumn()] != null){
            z = rows - 1;
        }
        else{
            while(z < rows){
                if(grid[z][move.getColumn()] == null){
                    break;
                }
                z++;
            }
            z--;
        }
        int y = z;
        int x = c;
        while(!(x == 0||y == 0)){
            x--;
            y--;
        }
        int bl_y = y;
        int bl_x = x;
        y = z;
        x = c;
        while(!(x == cols-1||y == rows-1)){
            x++;
            y++;
        }
        int tr_y = y;
        int tr_x = x;
        y = z;
        x = c;
        while(!(x == cols-1||y == 0)){
            x++;
            y--;
        }
        int br_y = y;
        int br_x = x;
        y = z;
        x = c;
        while(!(x == 0||y == rows-1)){
            x--;
            y++;
        }
        int tl_y = y;
        int tl_x = x;
        

        int counter = 0;
        for(int col = bl_x; col <= tr_x; col++){
            if(grid[bl_y][col] == null)
                counter = 0;
            if(grid[bl_y][col] != null)
                if(grid[bl_y][col].equals(p)){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if(counter == 4)
                    return true;
            bl_y++;
        }
        counter = 0;
        for(int col = tl_x; col <= br_x; col++){
            if(grid[tl_y][col] == null)
                counter = 0;
            if(grid[tl_y][col] != null)
                if(grid[tl_y][col].equals(p)){
                    counter++;
                }
                else{
                    counter = 0;
                }
                if(counter == 4)
                    return true;
            tl_y--;
        }
        return false;
    }

    public Player winner(Move lastMove) {
        if(colWinner(lastMove)){
            return lastMove.getPlayer();
        }
        if(rowWinner(lastMove)){
            return lastMove.getPlayer();
        }
        if(diagonalWinner(lastMove)){
            return lastMove.getPlayer();
        }
           

        return null;
    }
    }
    
    
 // end Board class
