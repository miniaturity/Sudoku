Program Overview

My program generates a unique, solvable sudoku puzzle through a recursive backtracking algorithm. It first places the top left, middle, and bottom right sudoku squares, because you do not need to check if a numbers placement is valid in its row and column in each of these squares.

Data Structures

To create the sudoku board, I used a 9x9 2D integer array. I also created a 2nd 9x9 2D integer array to store a playable sudoku. I also shuffled a 1D integer array filled with the numbers 1-9 in order to place the initial 3 squares of the sudoku.

Algorithm Design

I used a backtracking recursive algorithm to quickly generate sudokus. Before the algorithm begins, I generated the 3 diagonal squares (top left, center, bottom right), since they don't intersect each other. I did this by shuffling a 1D array of integers, containing the numbers 1-9. After the array is shuffled, the numbers are placed in order, so it creates a valid 3x3 sudoku square. To fill the rest of the squares, I used a recursive method that returns a boolean. Inside of the method, I used a nested loop to iterate through the 2D array. For each cell that is empty, I loop through the numbers 1-9, and for each number, I check if it is a valid placement according to the sudoku rules (no duplicates in the column, row, or square). If it is a valid placement, I call the method again, but inside of a conditional that returns true if the method call returns true.  If there is no valid placement, I return false. The base case for this recursive method is that the target cell is out of bounds, meaning that the method has reached the end of the board without backtracking, indicating that it has successfully created a valid sudoku board. For each recursive call, I increment x. If incrementing x makes it out of bounds, I set x to 0 and increment y.

Methods

**Sudoku.java**  
getBoard() returns the board (2D integer array)  
getSolvedBoard() returns the solved board (2D integer array)  
printBoard() prints the board

isSolved() checks if the board is solved, making sure that each cell follows the rules of sudoku. Returns a boolean.

generatePuzzle(int difficulty) generates a solvable sudoku board based on the difficulty level that the user inputted. It first fills the board with the solution, and attempts to take out a cell. It checks if taking that cell out would result in a solvable puzzle through countSolutions(int count), a recursive backtracking function that solves the sudoku. It attempts to solve the sudoku, omitting that cell. If it is able to solve it, it increments the count of solutions. If not, it returns count. Since we don't need to check for multiple solutions, we can stop at 1\. If it can't find a solution, it moves to the next cell and tries again.

attemptPlace(int x, int y, int num) attempts to place a number on the sudoku board, given by user input. It uses validateAttempt(int x, int y, int num), which returns true if the coordinates are in bounds and if the cell is empty.

generateBoard() generates the solved board. It places the diagonal squares by shuffling an array, and starts the recursive backtrack algorithm.

place(int x, int y, int\[\]\[\] board) is a recursive function that places numbers on the sudoku board. It is mostly explained in the Algorithms section.

shuffle(int\[\] nums) shuffles the given array through a basic swapping algorithm.

**MyProgram.java**  
public static void main(String\[\] args) main method for taking user input and instantiating the Sudoku class.

Testing Plan

To test my sudoku generator, I generated multiple sudokus and hand checked if they were valid. 

Challenges

One of the main challenges I faced was optimizing the algorithm. The greatest optimization I made was seeding the board with the 3 diagonal squares. This saved a lot of time because it gave the algorithm numbers to work off of, so it didn't need to keep backtracking to the beginning.

