
/*
==========================================================
                    DAA PRACTICAL 1
==========================================================

QUESTION:

1. Implement Fibonacci Series using Iteration.
2. Implement Fibonacci Series using Recursion.
3. Implement Tower of Hanoi using Recursion.

==========================================================
*/

import java.util.Scanner;

public class Practical1 {

    // 1. ITERATIVE FIBONACCI
    static int fibonacciIterative(int n) {

        int a = 0, b = 1;

        if (n == 0)
            return 0;

        for (int i = 2; i <= n; i++) {

            int c = a + b;
            a = b;
            b = c;
        }

        return b;
    }


    // 2. RECURSIVE FIBONACCI
    static int fibonacciRecursive(int n) {

        if (n == 0)
            return 0;

        if (n == 1)
            return 1;

        return fibonacciRecursive(n - 1)
             + fibonacciRecursive(n - 2);
    }


    // 3. TOWER OF HANOI
    static void towerOfHanoi(int n, char source,
                             char auxiliary, char destination) {

        if (n == 1) {

            System.out.println("Move disk 1 from "
                    + source + " to " + destination);

            return;
        }

        towerOfHanoi(n - 1, source, destination, auxiliary);

        System.out.println("Move disk " + n + " from "
                + source + " to " + destination);

        towerOfHanoi(n - 1, auxiliary, source, destination);
    }


    // MAIN METHOD
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Iterative Fibonacci");
        System.out.println("2. Recursive Fibonacci");
        System.out.println("3. Tower of Hanoi");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:

                System.out.print("Enter n: ");
                int n1 = sc.nextInt();

                if (n1 < 0)
                    System.out.println("Invalid input");
                else
                    System.out.println("Fibonacci = "
                            + fibonacciIterative(n1));

                break;


            case 2:

                System.out.print("Enter n: ");
                int n2 = sc.nextInt();

                if (n2 < 0)
                    System.out.println("Invalid input");
                else
                    System.out.println("Fibonacci = "
                            + fibonacciRecursive(n2));

                break;


            case 3:

                System.out.print("Enter number of disks: ");
                int n3 = sc.nextInt();

                if (n3 <= 0)
                    System.out.println("Invalid input");
                else
                    towerOfHanoi(n3, 'A', 'B', 'C');

                break;


            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}





/*  ONE-MINUTE EXTERNAL EXPLANATION

"In this practical, I implemented Fibonacci series using both iterative and recursive approaches and solved the Tower of Hanoi problem using recursion.

In iterative Fibonacci, I used a loop and two variables to calculate the next number. In recursive Fibonacci, I used the formula F(n) = F(n-1) + F(n-2), with base conditions 0 and 1.

In Tower of Hanoi, I used three rods: source, auxiliary and destination. First, I moved n-1 disks to the auxiliary rod, then moved the largest disk to the destination, and finally moved the remaining disks to the destination.

The minimum number of moves required is 2ⁿ - 1." */





/*
==========================================================
                 CODE EXPLANATION
==========================================================

1. ITERATIVE FIBONACCI

- Uses a for loop to calculate Fibonacci numbers.
- a = 0 and b = 1 are initial values.
- c = a + b calculates the next number.
- a = b updates the first variable.
- b = c updates the second variable.
- Loop continues until i reaches n.
- Returns b as the final answer.

IMPORTANT:
c = a + b;
a = b;
b = c;


2. RECURSIVE FIBONACCI

- Function calls itself.
- Base condition stops recursion.

Base conditions:
if (n == 0) return 0;
if (n == 1) return 1;

Recursive formula:
F(n) = F(n-1) + F(n-2)

Example:
F(4) = F(3) + F(2)
     = 2 + 1
     = 3


3. TOWER OF HANOI

Three rods:
A = Source
B = Auxiliary
C = Destination

Three steps:

Step 1:
Move n-1 disks from Source to Auxiliary.

Step 2:
Move the largest disk from Source to Destination.

Step 3:
Move n-1 disks from Auxiliary to Destination.

Base condition:
if (n == 1)

Move one disk directly and return.

Minimum moves:
T(n) = 2^n - 1


4. MAIN METHOD

- Scanner takes user input.
- Menu displays three algorithms.
- switch(choice) selects the algorithm.
- case 1 calls Iterative Fibonacci.
- case 2 calls Recursive Fibonacci.
- case 3 calls Tower of Hanoi.
- default handles invalid choices.
- break exits the switch case.
- sc.close() closes Scanner.


==========================================================
                 BASE CONCEPTS
==========================================================

1. ITERATION:
Repeating statements using loops.

2. RECURSION:
A function calling itself.

3. BASE CONDITION:
Condition that stops recursive calls.

4. STACK:
LIFO (Last In First Out) data structure.
Recursive calls are stored in the call stack.

5. TIME COMPLEXITY:
Measures how running time grows with input size.

6. SPACE COMPLEXITY:
Measures additional memory required.

7. BIG O NOTATION:
Represents an asymptotic upper bound.


==========================================================
              TIME AND SPACE COMPLEXITY
==========================================================

1. ITERATIVE FIBONACCI

Time Complexity: O(n)
Reason: Loop executes approximately n times.

Space Complexity: O(1)
Reason: Only fixed variables are used.


2. RECURSIVE FIBONACCI

Time Complexity: O(2^n)
Reason: Each call generates two recursive calls
(exponential growth).

Space Complexity: O(n)
Reason: Maximum recursive call stack depth is O(n).


3. TOWER OF HANOI

Time Complexity: O(2^n)
Reason: T(n) = 2T(n-1) + 1.

Space Complexity: O(n)
Reason: Recursive call stack depth is O(n).


==========================================================
                  VIVA QUESTIONS & ANSWERS
==========================================================

Q1. What is Fibonacci series?

Ans: A sequence where every number is the sum
of the previous two numbers.

Q2. What is the Fibonacci formula?

Ans: F(n) = F(n-1) + F(n-2).

Q3. What are the first two Fibonacci numbers?

Ans: 0 and 1.

Q4. What is the difference between iteration
and recursion?

Ans: Iteration uses loops, recursion uses
function calls.

Q5. Which Fibonacci method is faster?

Ans: Iterative Fibonacci because it takes O(n)
time instead of O(2^n).

Q6. What is a base condition?

Ans: A condition that stops recursive calls.

Q7. What happens without a base condition?

Ans: Recursion may continue until StackOverflowError.

Q8. What is Tower of Hanoi?

Ans: A puzzle in which N disks are transferred
between three rods following specific rules.

Q9. What are the three rods?

Ans: Source, Auxiliary and Destination.

Q10. What are the rules of Tower of Hanoi?

Ans:
1. Move only one disk at a time.
2. Only the top disk can be moved.
3. Bigger disk cannot be placed on smaller disk.

Q11. What is the minimum number of moves?

Ans: 2^n - 1.

Q12. How many moves are needed for 4 disks?

Ans: 2^4 - 1 = 15.

Q13. What is the base condition in Hanoi?

Ans: n == 1.

Q14. What is the time complexity of Hanoi?

Ans: O(2^n).

Q15. What is the space complexity of Hanoi?

Ans: O(n).

Q16. What is DAA?

Ans: Design and Analysis of Algorithms.

Q17. What is Big O notation?

Ans: It represents the asymptotic upper bound
of algorithm growth.

Q18. What is a stack?

Ans: A linear data structure following LIFO.


==========================================================
                 SAMPLE INPUT AND OUTPUT
==========================================================

ITERATIVE FIBONACCI:

Input:
1
7

Output:
Fibonacci = 13


RECURSIVE FIBONACCI:

Input:
2
7

Output:
Fibonacci = 13


TOWER OF HANOI:

Input:
3
3

Output:
Move disk 1 from A to C
Move disk 2 from A to B
Move disk 1 from C to B
Move disk 3 from A to C
Move disk 1 from B to A
Move disk 2 from B to C
Move disk 1 from A to C


==========================================================
                 LINUX COMMANDS
==========================================================

Create file:
nano Practical1.java

Compile:
javac Practical1.java

Run:
java Practical1

==========================================================
*/
