import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Part 1/2: ");
        int part = sc.nextInt();
        
        if (part == 1) {
            Sudoku board = new Sudoku();
            board.printSolvedBoard();
        } else {
            System.out.println("Input difficulty: ");
            int diff = sc.nextInt();
            
            Sudoku board = new Sudoku(diff);
            board.printBoard();
            System.out.println();
            
            boolean solved = false;
            
            while (!solved) {
                System.out.println("Input row: ");
                int r = sc.nextInt() - 1;
                System.out.println("Input column: ");
                int c = sc.nextInt() - 1;
                System.out.println("Input number: ");
                int n = sc.nextInt();
                System.out.println();
                
                board.attemptPlace(r, c, n);
                
                if (board.isSolved()) {
                    solved = true;
                    System.out.println("You solved the sudoku! Difficulty: " + diff);
                }
                
                System.out.println();
            }
        }
        
    }
    
}