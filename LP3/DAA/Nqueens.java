
/*
=========================================================
SECTION 1: QUESTION
=========================================================

Write a Java program to solve the N-Queens problem
using Backtracking.

The program should:
1. Accept the value of N from the user.
2. Create an N x N chessboard.
3. Place N queens on the board.
4. Ensure that no two queens attack each other.
5. Display the solution matrix.
6. Display a message if no solution exists.

=========================================================
SECTION 2: COMPLETE JAVA PROGRAM
=========================================================
*/

import java.util.Scanner;

public class Nqueens {

    static int[][] board;
    static int n;

    static boolean isSafe(int row, int col) {

        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 1)
                return false;
        }

        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {

            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    static boolean solveNQueens(int row) {

        if (row == n)
            return true;

        for (int col = 0; col < n; col++) {

            if (isSafe(row, col)) {

                board[row][col] = 1;

                if (solveNQueens(row + 1))
                    return true;

                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the value of N: ");
        n = sc.nextInt();

        if (n < 1) {
            System.out.println("Invalid N");
            sc.close();
            return;
        }

        board = new int[n][n];

        if (solveNQueens(0)) {

            System.out.println("Solution Matrix:");

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }

                System.out.println();
            }

        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}

/*

Oral explanation:

"My fifth practical is the N-Queens Problem using Backtracking.

The objective is to place N queens on an N by N chessboard such that no two queens attack each other.

In chess, a queen can attack horizontally, vertically and diagonally. Therefore, we must ensure that no two queens share the same row, column or diagonal.

In our Java program, we use a two-dimensional integer array to represent the chessboard. Zero represents an empty cell and one represents a queen.

We solve this problem row by row.

First, we try to place a queen in a column of the current row. Before placing it, we call the isSafe function to check whether the position is safe.

If the position is safe, we place the queen and recursively move to the next row.

If no valid position is available in the next row, we backtrack. This means we remove the previously placed queen and try another column.

When the row becomes equal to N, it means all queens have been successfully placed, and we have found a solution.

For example, the N=4 problem has two possible solutions.

The commonly stated time complexity is O(N!), while the board requires O(N²) space, with additional recursion space."

Remember:
- Algorithm: Backtracking
- Data structure: 2D array
- 1 = Queen, 0 = Empty
- isSafe() checks column and diagonals.
- Backtracking means undoing a choice and trying another.
- N=2 and N=3 have no solutions.


*/



/*
=========================================================
SECTION 3: LINE-BY-LINE EXPLANATION
=========================================================

A. IMPORT AND GLOBAL VARIABLES
---------------------------------------------------------

1. import java.util.Scanner;

   Imports the Scanner class to accept input from the user.

2. public class Nqueens

   Defines the class named Nqueens.

3. static int[][] board;

   Declares a 2D array to represent the chessboard.

   1 means a queen is placed.
   0 means the position is empty.

4. static int n;

   Stores the size of the chessboard.

   For example, if n = 4, the board will be 4 x 4.

---------------------------------------------------------
B. isSafe() METHOD
---------------------------------------------------------

5. static boolean isSafe(int row, int col)

   Checks whether a queen can safely be placed at
   the given row and column.

   Returns true if safe, otherwise false.

6. for (int i = 0; i < row; i++)

   Checks all the previous rows in the same column.

7. if (board[i][col] == 1)

   Checks whether a queen already exists in that column.

8. return false;

   If a queen is found, the position is unsafe.

   COLUMN CHECK:
   We only check rows above because queens are placed
   from top to bottom.

9. for (int i = row - 1, j = col - 1;
        i >= 0 && j >= 0; i--, j--)

   Checks the upper-left diagonal.

   i = row - 1 means start from the row above.
   j = col - 1 means start from the column on the left.

   i-- moves upwards.
   j-- moves left.

10. if (board[i][j] == 1)

    Checks whether a queen exists in the upper-left
    diagonal.

11. return false;

    If a queen is found, the position is unsafe.

12. for (int i = row - 1, j = col + 1;
        i >= 0 && j < n; i--, j++)

    Checks the upper-right diagonal.

    i = row - 1 means move upwards.
    j = col + 1 means move right.

    i-- moves upwards.
    j++ moves right.

13. if (board[i][j] == 1)

    Checks whether a queen exists in the upper-right
    diagonal.

14. return false;

    If a queen is found, the position is unsafe.

15. return true;

    If no queen is found in the same column or
    either upper diagonal, the position is safe.

    NOTE:
    We do not check the same row because the program
    places only one queen in each row.

---------------------------------------------------------
C. solveNQueens() METHOD
---------------------------------------------------------

16. static boolean solveNQueens(int row)

    This method solves the N-Queens problem using
    recursion and backtracking.

    It receives the current row as an argument.

17. if (row == n)

    Checks whether all rows have been completed.

    For n = 4, rows 0, 1, 2 and 3 contain queens.
    When row becomes 4, all queens are placed.

18. return true;

    Indicates that a complete solution is found.

19. for (int col = 0; col < n; col++)

    Tries every column in the current row.

    For n = 4, it checks columns 0, 1, 2 and 3.

20. if (isSafe(row, col))

    Calls isSafe() to check whether the current
    position is safe for placing a queen.

21. board[row][col] = 1;

    Places a queen at the current position.

    Stores 1 in the board.

22. if (solveNQueens(row + 1))

    Calls the same method recursively for the next row.

    This means we try to place the next queen.

23. return true;

    If the recursive call successfully places all
    remaining queens, return true.

24. board[row][col] = 0;

    This is the BACKTRACKING step.

    If the next row cannot be solved, remove the
    previously placed queen.

    Set its position back to 0 and try another column.

25. return false;

    If no column works in the current row,
    return false to the previous recursive call.

---------------------------------------------------------
D. main() METHOD
---------------------------------------------------------

26. public static void main(String[] args)

    Main method. Program execution starts here.

27. Scanner sc = new Scanner(System.in);

    Creates Scanner object to accept user input.

28. System.out.print("Enter the value of N: ");

    Displays a message asking for the board size.

29. n = sc.nextInt();

    Accepts the value of N from the user.

30. if (n < 1)

    Checks whether N is less than 1.

31. System.out.println("Invalid N");

    Displays an error message if N is invalid.

32. sc.close();

    Closes the Scanner.

33. return;

    Stops the program.

34. board = new int[n][n];

    Creates an N x N chessboard.

    All positions are initially 0.

35. if (solveNQueens(0))

    Calls the solving method starting from row 0.

    If a solution is found, the condition becomes true.

36. System.out.println("Solution Matrix:");

    Displays the solution heading.

37. for (int i = 0; i < n; i++)

    Outer loop moves through every row.

38. for (int j = 0; j < n; j++)

    Inner loop moves through every column.

39. System.out.print(board[i][j] + " ");

    Prints each position of the chessboard.

40. System.out.println();

    Moves to the next line after printing one row.

41. else

    Executes if no solution is found.

42. System.out.println("No solution exists.");

    Displays that no valid arrangement exists.

43. sc.close();

    Closes the Scanner object.

=========================================================
HOW THE PROGRAM WORKS — SIMPLE WORDING
=========================================================

1. First, we accept the value of N from the user.

2. Then, we create an N x N chessboard filled with zeros.

3. We call solveNQueens(0) to start placing queens
   from the first row.

4. Inside solveNQueens(), we try every column
   of the current row.

5. Before placing a queen, we call isSafe().

6. isSafe() checks three directions:
   - Same column.
   - Upper-left diagonal.
   - Upper-right diagonal.

7. If the position is safe, we place a queen
   by storing 1 in that position.

8. Then, we call solveNQueens(row + 1) to move
   to the next row.

9. If we successfully place all N queens,
   the method returns true.

10. If we cannot place a queen in the next row,
    we remove the previous queen by storing 0.

11. Then, we try another column.

12. This process continues until a valid solution
    is found or all possibilities are checked.

13. Finally, we print the solution matrix.

This process of placing a queen, checking the next
possibility, and removing the queen when necessary
is called BACKTRACKING.

=========================================================
SECTION 4: TIME AND SPACE COMPLEXITY
=========================================================

TIME COMPLEXITY:

Commonly expressed as O(N!).

Reason:

- We place one queen in each row.
- We try different columns for each row.
- The number of possibilities reduces because
  a column cannot be reused.
- Each safety check takes up to O(N) time.

A more detailed upper-bound analysis includes
the safety-checking cost, giving O(N × N!).

SPACE COMPLEXITY:

O(N²)

Reason:

The chessboard is a 2D array of size N x N.

The recursive function also uses O(N) stack space.

=========================================================
SECTION 5: VIVA QUESTIONS AND ANSWERS
=========================================================

Q1. What is the N-Queens problem?

Ans: It is a problem of placing N queens on an
N x N chessboard so that no two queens attack
each other.

Q2. Which technique is used?

Ans: Backtracking.

Q3. What is backtracking?

Ans: It is a technique where we make a choice,
explore it, and undo it if it does not lead
to a valid solution.

Q4. What does isSafe() do?

Ans: It checks whether a queen can be placed
at a particular position safely.

Q5. Why do we check only the upper diagonals?

Ans: Because queens are placed row by row
from top to bottom. Lower rows are empty.

Q6. Why do we check the column?

Ans: To ensure that two queens are not placed
in the same column.

Q7. What does 1 represent in the board?

Ans: A queen is placed at that position.

Q8. What does 0 represent?

Ans: An empty position.

Q9. What is the base condition?

Ans: if (row == n) return true;

    It means all queens have been placed.

Q10. What is recursion?

Ans: A method calling itself to solve a problem.

Q11. Why do we use board[row][col] = 0?

Ans: To remove a queen when a placement does not
lead to a solution. This is backtracking.

Q12. Is there a solution for N = 2 or N = 3?

Ans: No, there is no solution for N = 2 or N = 3.

Q13. What is the time complexity?

Ans: Commonly expressed as O(N!), with an
additional O(N) safety check per attempted position.

Q14. What is the space complexity?

Ans: O(N²) for the chessboard.

Q15. Why do we use a boolean return type?

Ans: To indicate whether a valid arrangement
has been found or not.

Q16. Why do we place only one queen in each row?

Ans: Because two queens in the same row
would attack each other.

=========================================================
SECTION 6: SAMPLE INPUT AND OUTPUT
=========================================================

INPUT:

Enter the value of N: 4

OUTPUT:

Solution Matrix:
0 1 0 0
0 0 0 1
1 0 0 0
0 0 1 0

EXPLANATION:

For N = 4, we need to place 4 queens
on a 4 x 4 chessboard.

Queen positions:

Queen 1: Row 0, Column 1
Queen 2: Row 1, Column 3
Queen 3: Row 2, Column 0
Queen 4: Row 3, Column 2

No two queens share the same row,
column, or diagonal.

Therefore, this is a valid solution.

ANOTHER INPUT:

Enter the value of N: 3

OUTPUT:

No solution exists.

=========================================================
SECTION 7: LINUX COMMANDS
=========================================================

1. Open terminal.

2. Create the Java file:

   nano Nqueens.java

3. Write or paste the program.

4. Save the file:

   Ctrl + O
   Enter
   Ctrl + X

5. Compile the program:

   javac Nqueens.java

6. Run the program:

   java Nqueens

7. Enter the value of N when prompted.

=========================================================
END OF PRACTICAL 5
=========================================================



*/
