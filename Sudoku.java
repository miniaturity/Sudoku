public class Sudoku {
    protected int[][] solvedBoard;
    protected int[][] board = new int[9][9];
    
    public Sudoku() {
        this.solvedBoard = generateBoard();
    }
    
    public Sudoku(int difficulty) {
        this.solvedBoard = generateBoard();
        generatePuzzle(difficulty);
    }
    
    public int[][] getBoard() { return board; }
    public int[][] getSolvedBoard() { return solvedBoard; }
    
    public void printBoard() {
        System.out.println("+ - - - + - - - + - - - +");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] == 0 ? "-" : board[i][j]);
                System.out.print(" ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            if ((i + 1) % 3 == 0) { 
                System.out.println();
                System.out.println("+ - - - + - - - + - - - +"); 
            } else System.out.println();
        }
    }
    
    public void printSolvedBoard()  {
        System.out.println("+ - - - + - - - + - - - +");
        for (int i = 0; i < solvedBoard.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < solvedBoard[i].length; j++) {
                System.out.print(solvedBoard[i][j] == 0 ? "-" : solvedBoard[i][j]);
                System.out.print(" ");
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            if ((i + 1) % 3 == 0) { 
                System.out.println();
                System.out.println("+ - - - + - - - + - - - +"); 
            } else System.out.println();
        }
    }

    
    public boolean isSolved() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != solvedBoard[r][c]) return false;
            }
        }
        return true;
    }
    
    private void generatePuzzle(int difficulty) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                board[i][j] = solvedBoard[i][j];

        int toRemove = 21 + (difficulty * 5); 

        int[] positions = new int[81];
        for (int i = 0; i < 81; i++) positions[i] = i;
        shuffle(positions);

        int removed = 0;
        for (int k = 0; k < 81; k++) {
            if (removed == toRemove) break;

            int x = positions[k] / 9;
            int y = positions[k] % 9;

            int backup = board[x][y];
            board[x][y] = 0;

            if (countSolutions(0) == 1) {
                removed++;
            } else {
                board[x][y] = backup;
            }
        }
    }

    private int countSolutions(int count) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (validPlacement(x, y, num, board)) {
                            board[x][y] = num;
                            count = countSolutions(count);
                            board[x][y] = 0;
                            if (count > 1) return count; 
                        }
                    }
                    return count; 
                }
            }
        }
        return count + 1; // base case (board is filled)
    }
    
    public void attemptPlace(int x, int y, int num) {
        if (!validAttempt(x, y, num)) {
            System.out.println("Invalid placement.");
        } else {
            board[x][y] = num;
            printBoard();
        }
    }
    
    private boolean validAttempt(int x, int y, int num) {
        boolean inBounds = x >= 0 && x <= 8 && y >= 0 && y <= 8;
        boolean validNum = num >= 1 && num <= 9;
        if (!inBounds) return false;
        boolean isEmpty = board[x][y] == 0;
        
        return inBounds && validNum && isEmpty;
    }
    
    private int[][] generateBoard() {
        int[][] newBoard = new int[9][9];
        
        // fill diagonal squares first ( they dont require validatoin)
        for (int i = 0; i < 3; i++) {
            int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            shuffle(nums);
            
            for (int j = 0; j < 9; j++) {
                int r = (i * 3) + (j / 3);
                int c = (j % 3) + (i * 3);
                
                newBoard[r][c] = nums[j];
            }
        }
        
        place(0, 0, newBoard);
        
        return newBoard;
    }
    
    private void shuffle(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int n = (int) (Math.random() * nums.length);
            int temp = nums[i];
            nums[i] = nums[n];
            nums[n] = temp;
        }
    }
    
    private boolean place(int x, int y, int[][] board) {
        if (x > 8) return true;
        
        int nextX = y == 8 ? x + 1 : x;
        int nextY = y == 8 ? 0 : y + 1;
        
        if (board[x][y] != 0) return place(nextX, nextY, board);
        
        for (int i = 1; i <= 9; i++) {
            if (validPlacement(x, y, i, board)) {
                board[x][y] = i;
                if (place(nextX, nextY, board)) {
                    return true;
                } else {
                    board[x][y] = 0;
                }
            }
        }
        
        return false;
    }
    
    
    private boolean validPlacement(int x, int y, int num, int[][] board) {
        for (int r = 0; r < 9; r++) {
            if (board[x][r] == num) return false;
        }
        
        for (int c = 0; c < 9; c++) {
            if (board[c][y] == num) return false;
        }
        
        // finding the first row/col index of the square we're in
        int sqRow = (x / 3) * 3;
        int sqCol = (y / 3) * 3;
        for (int r = sqRow; r < sqRow + 3; r++) {
            for (int c = sqCol; c < sqCol + 3; c++) {
                if (board[r][c] == num) return false;
            }
        }
        
        return true;
    }
    
    
    
    
}