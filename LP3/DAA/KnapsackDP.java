
/*
SECTION 1: QUESTION

Write a Java program to implement the 0/1 Knapsack problem using
Dynamic Programming.

The program should:
1. Accept the number of items.
2. Accept the value and weight of each item.
3. Accept the knapsack capacity.
4. Calculate the maximum possible value.
5. Display the selected items.
*/

import java.util.Scanner;

public class KnapsackDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of items must be positive.");
            sc.close();
            return;
        }

        int[] value = new int[n];
        int[] weight = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value of item " + (i + 1) + ": ");
            value[i] = sc.nextInt();

            System.out.print("Enter weight of item " + (i + 1) + ": ");
            weight[i] = sc.nextInt();

            if (value[i] < 0 || weight[i] <= 0) {
                System.out.println("Invalid value or weight.");
                sc.close();
                return;
            }
        }

        System.out.print("Enter knapsack capacity: ");
        int W = sc.nextInt();

        if (W < 0) {
            System.out.println("Capacity cannot be negative.");
            sc.close();
            return;
        }

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        dp[i - 1][w],
                        value[i - 1] + dp[i - 1][w - weight[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nMaximum value = " + dp[n][W]);

        System.out.println("Selected items:");

        int w = W;

        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.println("Item " + i);
                w = w - weight[i - 1];
            }
        }

        sc.close();
    }
}




/*
Oral explanation:

"My fourth practical is the 0/1 Knapsack Problem using Dynamic Programming.

Similar to Fractional Knapsack, we are given items with weights and profits, along with a fixed knapsack capacity.

But the main difference is that in 0/1 Knapsack, we cannot divide an item. We can either take the complete item or leave it.

To solve this problem, we use Dynamic Programming.

We create a two-dimensional DP table where rows represent items and columns represent different knapsack capacities.

For every item, we have two choices:

First, exclude the item, which means we keep the previous maximum profit.

Second, include the item, if its weight is less than or equal to the current capacity. In that case, we add its profit to the best possible profit for the remaining capacity.

We select the maximum value between these two choices.

The recurrence relation is:
\[
DP[i][w] = \max(DP[i-1][w],\ value_i + DP[i-1][w-weight_i])
\]

If the item cannot fit, we simply exclude it.

After filling the table, we can also trace it backwards to identify which items were selected.

The time and space complexity are both O(nW), where n is the number of items and W is the knapsack capacity."

Remember:
- Algorithm: Dynamic Programming
- Two choices: Include or Exclude
- Items cannot be divided.
- Uses a 2D DP table.
- Time complexity: O(nW).
- Fractional Knapsack and 0/1 Knapsack are different.



*/
/*
=========================================================
SECTION 3: LINE-BY-LINE EXPLANATION
=========================================================

1. import java.util.Scanner;

   Imports Scanner class to accept input from the user.

2. public class KnapsackDP

   Defines the class named KnapsackDP.

3. public static void main(String[] args)

   Main method. Program execution starts here.

4. Scanner sc = new Scanner(System.in);

   Creates Scanner object to read user input.

5. int n = sc.nextInt();

   Accepts the number of items from the user.

6. if (n <= 0)

   Checks whether the number of items is zero or negative.
   If invalid, displays a message and stops the program.

7. int[] value = new int[n];

   Creates an array to store the values of all items.

8. int[] weight = new int[n];

   Creates an array to store the weights of all items.

9. for (int i = 0; i < n; i++)

   Loop runs n times to accept details of every item.

10. value[i] = sc.nextInt();

    Accepts the value of the current item.

11. weight[i] = sc.nextInt();

    Accepts the weight of the current item.

12. if (value[i] < 0 || weight[i] <= 0)

    Checks whether the entered value or weight is invalid.
    Value cannot be negative and weight must be positive.

13. int W = sc.nextInt();

    Accepts the maximum capacity of the knapsack.

14. if (W < 0)

    Checks whether the capacity is negative.
    If negative, displays an error and stops.

15. int[][] dp = new int[n + 1][W + 1];

    Creates a 2D DP table.

    Rows represent the number of items considered.
    Columns represent the available knapsack capacity.

    dp[i][w] means:
    Maximum value possible using the first i items
    with a capacity of w.

    We use n+1 rows and W+1 columns because
    we also consider zero items and zero capacity.

16. for (int i = 1; i <= n; i++)

    Outer loop selects items one by one.

    It starts from 1 because row 0 represents
    the situation where no items are available.

17. for (int w = 0; w <= W; w++)

    Inner loop checks every possible capacity
    from 0 to the maximum capacity W.

18. if (weight[i - 1] <= w)

    Checks whether the current item can fit
    inside the available capacity.

    We use i-1 because array indexing starts from 0,
    but the DP rows start from 1.

19. dp[i - 1][w]

    This represents the value when we EXCLUDE
    the current item.

20. value[i - 1] + dp[i - 1][w - weight[i - 1]]

    This represents the value when we INCLUDE
    the current item.

    We add the item's value to the best value
    possible with the remaining capacity.

21. Math.max(...)

    Compares both choices:
    - Include the current item.
    - Exclude the current item.

    Stores whichever gives the maximum value.

22. else

    Executes when the current item cannot fit.

23. dp[i][w] = dp[i - 1][w];

    Copies the previous row's value because
    the current item cannot be included.

24. System.out.println("Maximum value = " + dp[n][W]);

    Displays the final answer.

    dp[n][W] contains the maximum value possible
    using all items and the given capacity.

25. int w = W;

    Starts the backtracking process from the
    maximum knapsack capacity.

26. for (int i = n; i > 0; i--)

    Moves backwards through the DP table
    to identify which items were selected.

27. if (dp[i][w] != dp[i - 1][w])

    If the current row's value is different from
    the previous row's value, it means the current
    item was included in this optimal solution.

28. System.out.println("Item " + i);

    Displays the selected item's number.

29. w = w - weight[i - 1];

    Reduces the remaining capacity by the
    weight of the selected item.

30. sc.close();

    Closes the Scanner object.

=========================================================
HOW THE PROGRAM WORKS — SIMPLE WORDING
=========================================================

First, we accept the number of items from the user.

Then, we create two arrays:
- One array stores item values.
- Another array stores item weights.

Next, we accept the value and weight of every item.

After that, we accept the maximum capacity of the
knapsack.

Now, we create a 2D DP table to store the maximum
value possible for different numbers of items and
different capacities.

Then, we process items one by one.

For every item, we check two possibilities:

1. Include the item:
   Add its value and calculate the best value
   possible with the remaining capacity.

2. Exclude the item:
   Keep the previous maximum value without
   selecting the current item.

We compare both possibilities using Math.max()
and store the maximum value in the DP table.

After completing the table, dp[n][W] gives
the maximum possible value.

Finally, we move backwards through the DP table
to identify and display the selected items.

This completes the 0/1 Knapsack problem using
Dynamic Programming.

=========================================================
SECTION 4: TIME AND SPACE COMPLEXITY
=========================================================

TIME COMPLEXITY:

O(n × W)

Reason:
- Outer loop runs n times.
- Inner loop runs W+1 times.
- Each DP cell takes constant time to calculate.

SPACE COMPLEXITY:

O(n × W)

Reason:
We create a 2D DP table of size
(n+1) × (W+1).

The value and weight arrays require O(n) space,
but the DP table dominates the space complexity.

=========================================================
SECTION 5: VIVA QUESTIONS AND ANSWERS
=========================================================

Q1. What is the 0/1 Knapsack problem?

Ans: It is an optimization problem in which we select
items to maximize total value without exceeding
the given capacity.

Q2. Why is it called 0/1 Knapsack?

Ans: Because an item can either be selected (1)
or not selected (0). We cannot take a fraction
of an item.

Q3. Which technique is used here?

Ans: Dynamic Programming.

Q4. What is Dynamic Programming?

Ans: It is a technique that solves a problem by
breaking it into smaller overlapping subproblems
and storing their results.

Q5. What is the purpose of the DP table?

Ans: It stores the maximum value possible for
different item counts and capacities.

Q6. What does dp[i][w] represent?

Ans: Maximum value using the first i items
with capacity w.

Q7. Why do we use Math.max()?

Ans: To choose the better result between including
and excluding the current item.

Q8. What is the recurrence relation?

Ans:

If weight[i-1] <= w:

dp[i][w] = max(
    dp[i-1][w],
    value[i-1] + dp[i-1][w-weight[i-1]]
)

Otherwise:

dp[i][w] = dp[i-1][w]

Q9. What is the time complexity?

Ans: O(n × W).

Q10. What is the space complexity?

Ans: O(n × W).

Q11. Can we select a fraction of an item?

Ans: No. Fractional selection is allowed in
Fractional Knapsack, not 0/1 Knapsack.

Q12. What is the difference between Greedy
and Dynamic Programming?

Ans: Greedy makes a locally best choice at each step.
Dynamic Programming compares subproblem results
and stores them to find an optimal solution.

Q13. Why do we use n+1 rows?

Ans: To represent the case where zero items are
available, along with all n items.

Q14. Why do we use W+1 columns?

Ans: To represent capacities from 0 to W.

Q15. What is backtracking used for here?

Ans: It helps identify which items were selected
to achieve the maximum value.

=========================================================
SECTION 6: SAMPLE INPUT AND OUTPUT
=========================================================

INPUT:

Enter number of items: 3

Enter value of item 1: 40
Enter weight of item 1: 20

Enter value of item 2: 60
Enter weight of item 2: 20

Enter value of item 3: 80
Enter weight of item 3: 40

Enter knapsack capacity: 50

OUTPUT:

Maximum value = 100
Selected items:
Item 2
Item 1

EXPLANATION:

Item 1: Value = 40, Weight = 20
Item 2: Value = 60, Weight = 20
Item 3: Value = 80, Weight = 40

Capacity = 50

We select:
Item 1 + Item 2

Total weight = 20 + 20 = 40
Total value = 40 + 60 = 100

Item 3 cannot be combined with either item
because the total weight would exceed 50.

Therefore, maximum value = 100.

=========================================================
SECTION 7: LINUX COMMANDS
=========================================================

1. Open terminal.

2. Create the Java file:

   nano KnapsackDP.java

3. Write or paste the program.

4. Save the file:
   Ctrl + O
   Enter
   Ctrl + X

5. Compile the program:

   javac KnapsackDP.java

6. Run the program:

   java KnapsackDP

7. If compilation is successful, the program
   accepts input and displays the maximum value
   and selected items.

=========================================================
END OF PRACTICAL 4
=========================================================
*/
