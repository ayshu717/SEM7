
/*
============================================================
1. QUESTION
============================================================

Implement Fractional Knapsack using the Greedy Algorithm.

The program should:
1. Accept the number of items.
2. Accept value and weight of each item.
3. Accept the knapsack capacity.
4. Calculate the value/weight ratio.
5. Sort items in descending order of ratio.
6. Select complete or fractional items to maximize value.
7. Display the selected items and maximum value.

============================================================
*/


// ==========================================================
// 2. COMPLETE WORKING JAVA CODE
// ==========================================================

import java.util.*;

public class FractionalKnapsack {

    static class Item {
        int value, weight, index;
        double ratio;

        Item(int value, int weight, int index) {
            this.value = value;
            this.weight = weight;
            this.index = index;
            this.ratio = (double) value / weight;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of items");
            sc.close();
            return;
        }

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter value of item " + (i + 1) + ": ");
            int value = sc.nextInt();

            System.out.print("Enter weight of item " + (i + 1) + ": ");
            int weight = sc.nextInt();

            if (value < 0 || weight <= 0) {
                System.out.println("Invalid value or weight");
                sc.close();
                return;
            }

            items[i] = new Item(value, weight, i + 1);
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Invalid capacity");
            sc.close();
            return;
        }

        // Sort by value/weight ratio in descending order
        Arrays.sort(items, (a, b) ->
            Double.compare(b.ratio, a.ratio)
        );

        double totalValue = 0;
        double remainingCapacity = capacity;

        System.out.println("\nSelected Items:");

        for (int i = 0; i < n; i++) {

            if (remainingCapacity == 0)
                break;

            if (items[i].weight <= remainingCapacity) {

                remainingCapacity -= items[i].weight;
                totalValue += items[i].value;

                System.out.println("Item " + items[i].index
                        + " -> 100% selected");

            } else {

                double fraction =
                    remainingCapacity / items[i].weight;

                totalValue += items[i].value * fraction;

                System.out.printf("Item %d -> %.2f%% selected%n",
                        items[i].index, fraction * 100);

                remainingCapacity = 0;
            }
        }

        System.out.printf("\nMaximum value = %.2f%n", totalValue);

        sc.close();
    }
}




/*

Oral explanation:

"My third practical is the Fractional Knapsack Problem using the Greedy Algorithm.

In this problem, we are given a set of items. Each item has a weight and a profit or value. We also have a knapsack with a limited capacity.

Our objective is to maximize the total profit without exceeding the capacity of the knapsack.

First, we calculate the profit-to-weight ratio for every item using the formula:
\[
Ratio = profit/weight

Then, we sort all items in descending order according to this ratio.

After sorting, we select the item with the highest ratio first. If the complete item fits inside the knapsack, we take it completely. Otherwise, we take only the required fraction of that item to fill the remaining capacity.

We continue until the knapsack becomes full or all items are considered.

This uses the Greedy approach because we select the locally best item at every step.

The time complexity is O(n log n), mainly because of sorting."

Remember:
- Algorithm: Greedy
- Important formula: Profit / Weight
- Items can be divided into fractions.
- We sort in descending order of ratio.
- Time complexity: O(n log n).




*/

/*
============================================================
3. COMPLETE LINE-BY-LINE CODE EXPLANATION
============================================================

A. IMPORT AND CLASS

import java.util.*;

- Imports utility classes from the java.util package.
- Scanner is used for taking input.
- Arrays is used for sorting the items.

public class FractionalKnapsack

- Declares the main public class.
- The filename must be FractionalKnapsack.java.


B. ITEM CLASS

static class Item

- Creates an inner class named Item.
- Each object represents one item in the knapsack.
- static allows this nested class to be used without creating
  an object of the outer class.

int value, weight, index;

- value stores the profit obtained from the item.
- weight stores the weight of the item.
- index stores the original item number.

double ratio;

- Stores the value-to-weight ratio.
- double is used because the result can contain decimals.

FORMULA:

Ratio = Value / Weight


CONSTRUCTOR:

Item(int value, int weight, int index)

- Constructor of the Item class.
- Called whenever a new Item object is created.
- Receives value, weight and original item number.

this.value = value;

- Assigns the received value to the object's value variable.

this.weight = weight;

- Assigns the received weight to the object's weight variable.

this.index = index;

- Stores the original item number.
- This is important because sorting changes item positions.

this.ratio = (double) value / weight;

- Calculates the value-to-weight ratio.
- (double) converts value into a decimal number.
- Prevents integer division.
- Stores the calculated ratio.

Example:

Value = 60
Weight = 10

Ratio = 60 / 10 = 6.0


C. MAIN METHOD

public static void main(String[] args)

- Starting point of the Java program.
- public allows JVM to access the method.
- static means no object is required.
- void means it does not return anything.
- String[] args accepts command-line arguments.

Scanner sc = new Scanner(System.in);

- Creates a Scanner object.
- System.in accepts input from the keyboard.

System.out.print("Enter number of items: ");

- Asks the user to enter the number of items.

int n = sc.nextInt();

- Reads the number of items.
- Stores it in integer variable n.


INPUT VALIDATION:

if (n <= 0)

- Checks whether the number of items is zero or negative.

System.out.println("Invalid number of items");

- Displays an error message.

sc.close();

- Closes the Scanner.

return;

- Terminates the main method.
- The program stops executing.


D. CREATING ITEM ARRAY

Item[] items = new Item[n];

- Creates an array of Item references.
- Its size is n.
- Each position will store one Item object.

Example:

If n = 3:

items[0]
items[1]
items[2]

Initially, all positions contain null.


E. TAKING ITEM DETAILS

for (int i = 0; i < n; i++)

- Loop executes once for every item.
- int i = 0 starts from index zero.
- i < n checks the loop condition.
- i++ increments i after each iteration.

System.out.print("Enter value of item " + (i + 1) + ": ");

- Displays the current item number.
- i + 1 is used because item numbering starts from 1,
  whereas array indexing starts from 0.

int value = sc.nextInt();

- Reads the item's value.

System.out.print("Enter weight of item " + (i + 1) + ": ");

- Asks the user to enter the item's weight.

int weight = sc.nextInt();

- Reads the item's weight.


VALIDATING ITEM DETAILS:

if (value < 0 || weight <= 0)

- Checks whether the value is negative.
- Checks whether the weight is zero or negative.
- || means logical OR.
- If either condition is true, the input is invalid.

System.out.println("Invalid value or weight");

- Displays an error message.

sc.close();

- Closes the Scanner.

return;

- Terminates the program.


CREATING ITEM OBJECT:

items[i] = new Item(value, weight, i + 1);

- Creates a new Item object.
- value passes the item's value.
- weight passes the item's weight.
- i + 1 passes its original item number.
- The constructor calculates the ratio.
- The object is stored in the items array.

Example:

items[0] = new Item(60, 10, 1);

Creates Item 1 with:
Value = 60
Weight = 10
Ratio = 6.0


F. TAKING KNAPSACK CAPACITY

System.out.print("Enter knapsack capacity: ");

- Asks the user to enter the maximum capacity.

int capacity = sc.nextInt();

- Reads the knapsack capacity.

Example:
capacity = 50

The knapsack can carry a maximum weight of 50.


VALIDATING CAPACITY:

if (capacity < 0)

- Checks whether capacity is negative.

System.out.println("Invalid capacity");

- Displays an error message.

sc.close();

- Closes the Scanner.

return;

- Terminates the program.


G. SORTING ITEMS BY RATIO

Arrays.sort(items, (a, b) ->
    Double.compare(b.ratio, a.ratio)
);

- Arrays.sort() sorts the Item array.
- a and b represent two Item objects being compared.
- a.ratio is the ratio of the first item.
- b.ratio is the ratio of the second item.
- Double.compare() compares two double values.
- b.ratio is placed before a.ratio to sort in descending order.

WHY DESCENDING ORDER?

- Items with higher value per unit weight are selected first.
- This is the Greedy strategy.

Example:

Before sorting:

Item 1 -> Ratio 6
Item 2 -> Ratio 5
Item 3 -> Ratio 4

After sorting:

Item 1 -> Ratio 6
Item 2 -> Ratio 5
Item 3 -> Ratio 4

The highest ratio always comes first.


H. INITIALIZING VARIABLES

double totalValue = 0;

- Stores the total profit obtained.
- Initially, no items are selected.
- Therefore, total value is zero.

double remainingCapacity = capacity;

- Stores the remaining available capacity.
- Initially, it equals the full capacity.
- double allows fractional calculations.

System.out.println("\nSelected Items:");

- Displays the heading.
- \n moves the output to a new line.


I. SELECTING ITEMS – GREEDY LOGIC

for (int i = 0; i < n; i++)

- Iterates through the sorted items.
- Starts with the item having the highest ratio.

if (remainingCapacity == 0)

- Checks whether the knapsack is full.

break;

- Stops the loop if no capacity remains.
- No more items can be selected.


CASE 1: COMPLETE ITEM FITS

if (items[i].weight <= remainingCapacity)

- Checks whether the entire item can fit.
- items[i].weight is the current item's weight.
- remainingCapacity is the available capacity.

remainingCapacity -= items[i].weight;

- Subtracts the item's weight from the remaining capacity.
- Equivalent to:
  remainingCapacity = remainingCapacity - items[i].weight;

totalValue += items[i].value;

- Adds the complete item's value to totalValue.
- Equivalent to:
  totalValue = totalValue + items[i].value;

System.out.println("Item " + items[i].index
        + " -> 100% selected");

- Displays the original item number.
- Confirms that the complete item was selected.
- index is used because sorting changes array positions.


CASE 2: COMPLETE ITEM DOES NOT FIT

else

- Executes when the item's weight is greater than
  the remaining capacity.
- Fractional Knapsack allows us to take only a fraction.

double fraction =
    remainingCapacity / items[i].weight;

- Calculates the fraction of the item that can be selected.

FORMULA:

Fraction = Remaining Capacity / Item Weight

Example:

Remaining Capacity = 20
Item Weight = 30

Fraction = 20 / 30
         = 0.6667


totalValue += items[i].value * fraction;

- Calculates the value of the selected fraction.
- Multiplies the item's value by the fraction.
- Adds the result to totalValue.

Example:

Item Value = 120
Fraction = 0.6667

Selected Value = 120 * 0.6667
               = 80


System.out.printf("Item %d -> %.2f%% selected%n",
        items[i].index, fraction * 100);

- Displays the original item number and selected percentage.
- %d prints an integer.
- %.2f prints a decimal with two digits.
- %% prints the percentage symbol.
- %n moves to the next line.
- fraction * 100 converts the fraction into a percentage.

Example:

0.6667 * 100 = 66.67%


remainingCapacity = 0;

- Sets remaining capacity to zero.
- The knapsack is now full.
- No more items can be selected.


J. DISPLAYING MAXIMUM VALUE

System.out.printf("\nMaximum value = %.2f%n", totalValue);

- Displays the final maximum value.
- printf() is used for formatted output.
- %.2f displays two digits after the decimal point.
- totalValue contains the calculated profit.

sc.close();

- Closes the Scanner.
- Ends input handling.


============================================================
4. TIME AND SPACE COMPLEXITY
============================================================

Let:

n = Number of items.

1. CREATING ITEM OBJECTS

Time Complexity: O(n)

Reason:
Each item is created once.

2. CALCULATING RATIOS

Time Complexity: O(n)

Reason:
Each item's ratio is calculated once.

3. SORTING

Time Complexity: O(n log n)

Reason:
Arrays.sort() sorts the items based on their ratios.

4. SELECTING ITEMS

Time Complexity: O(n)

Reason:
Every item is checked at most once.

5. OVERALL TIME COMPLEXITY

O(n log n)

Reason:
Sorting dominates the total execution time.

6. SPACE COMPLEXITY

O(n)

Reason:
The Item array stores n items.
Object-array sorting can also use temporary space.


============================================================
5. VIVA QUESTIONS AND ANSWERS
============================================================

Q1. What is Fractional Knapsack?

Ans: A problem in which complete or fractional parts
of items can be selected to maximize total value.

Q2. Which algorithm is used?

Ans: Greedy Algorithm.

Q3. What is the greedy strategy?

Ans: Select items in descending order of value-to-weight ratio.

Q4. What is the formula for ratio?

Ans: Ratio = Value / Weight.

Q5. Why do we sort in descending order?

Ans: To select the item giving the highest value per unit
weight first.

Q6. Can we select half of an item?

Ans: Yes. Fractional selection is allowed.

Q7. What is the difference between Fractional and 0/1 Knapsack?

Ans:

Fractional Knapsack:
- Fractions are allowed.
- Greedy algorithm gives an optimal solution.
- Usually sorted by value/weight ratio.

0/1 Knapsack:
- Fractions are not allowed.
- An item is either selected or rejected.
- Dynamic Programming is commonly used.

Q8. Why use double for ratio?

Ans: Because division can produce decimal values.

Q9. Why use Arrays.sort()?

Ans: To sort items according to their value/weight ratio.

Q10. Why use Double.compare()?

Ans: To compare two double values correctly.

Q11. Why store the original index?

Ans: To preserve original item numbers after sorting.

Q12. What is the time complexity?

Ans: O(n log n).

Q13. What happens if capacity is zero?

Ans: No items are selected and maximum value is zero.

Q14. Why is zero weight rejected?

Ans: Because value/weight would involve division by zero.

Q15. Is Greedy always optimal for Fractional Knapsack?

Ans: Yes, for the standard problem with nonnegative values
and positive weights.

Q16. What is the purpose of remainingCapacity?

Ans: It stores the available capacity after selecting items.

Q17. What is the purpose of totalValue?

Ans: It stores the total value obtained from selected items.

Q18. What does break do?

Ans: It immediately terminates the current loop.

Q19. What does fraction * 100 do?

Ans: Converts a fraction into a percentage.

Q20. Why use a constructor?

Ans: To initialize an Item object with its value, weight,
original index and ratio.

Q21. What is the purpose of static?

Ans: It allows a member to be accessed without creating
an object of the enclosing class.

Q22. What is the difference between int and double?

Ans: int stores whole numbers, whereas double stores
decimal numbers.

Q23. What is the difference between print() and printf()?

Ans: print() displays normal output.
printf() supports formatted output.

Q24. What is the purpose of the index variable?

Ans: It remembers the original item number after sorting.

Q25. What happens if all items fit into the knapsack?

Ans: All items are selected completely and their values
are added to totalValue.


============================================================
6. SAMPLE INPUT AND OUTPUT
============================================================

INPUT:

Enter number of items: 3

Enter value of item 1: 60
Enter weight of item 1: 10

Enter value of item 2: 100
Enter weight of item 2: 20

Enter value of item 3: 120
Enter weight of item 3: 30

Enter knapsack capacity: 50


CALCULATIONS:

Item 1:
Value = 60
Weight = 10
Ratio = 6

Item 2:
Value = 100
Weight = 20
Ratio = 5

Item 3:
Value = 120
Weight = 30
Ratio = 4


SELECTION:

Initially:
Remaining Capacity = 50
Total Value = 0

Item 1:
10 weight selected completely.
Remaining Capacity = 40
Total Value = 60

Item 2:
20 weight selected completely.
Remaining Capacity = 20
Total Value = 160

Item 3:
Fraction = 20 / 30
        = 0.6667

Percentage = 66.67%

Selected Value = 120 * 0.6667
               = 80

Final Total Value = 60 + 100 + 80
                  = 240


EXPECTED OUTPUT:

Selected Items:
Item 1 -> 100% selected
Item 2 -> 100% selected
Item 3 -> 66.67% selected

Maximum value = 240.00


============================================================
7. LINUX COMMANDS
============================================================

Create Java file:

nano FractionalKnapsack.java

Compile:

javac FractionalKnapsack.java

Run:

java FractionalKnapsack

============================================================
*/
