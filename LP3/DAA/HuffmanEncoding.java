
/*
============================================================
1. QUESTION
============================================================

Implement Huffman Encoding using the Greedy Algorithm.

Perform the following operations:
1. Build Huffman Tree.
2. Generate Huffman Codes.
3. Encode the given text.
4. Decode the encoded text.

============================================================
*/


// ==========================================================
// 2. COMPLETE WORKING JAVA CODE
// ==========================================================

import java.util.*;

public class HuffmanEncoding {

    static class Node {

        char ch;
        int freq;
        int order;
        Node left, right;

        Node(char ch, int freq, int order) {
            this.ch = ch;
            this.freq = freq;
            this.order = order;
        }

        Node(int freq, Node left, Node right, int order) {
            this.freq = freq;
            this.left = left;
            this.right = right;
            this.order = order;
        }
    }

    static Node root;
    static int count = 0;

    static Map<Character, String> codes = new TreeMap<>();

    // 1. BUILD HUFFMAN TREE
    static void buildTree(String text) {

        Map<Character, Integer> freq = new TreeMap<>();

        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(
            Comparator.comparingInt((Node n) -> n.freq)
                      .thenComparingInt(n -> n.order)
        );

        count = 0;

        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            pq.add(new Node(e.getKey(), e.getValue(), count++));
        }

        if (pq.size() == 1) {

            Node only = pq.poll();
            root = new Node(only.freq, only, null, count++);

        } else {

            while (pq.size() > 1) {

                Node left = pq.poll();
                Node right = pq.poll();

                Node parent = new Node(
                    left.freq + right.freq,
                    left, right, count++
                );

                pq.add(parent);
            }

            root = pq.poll();
        }

        codes.clear();

        generateCodes(root, "");
    }


    // 2. GENERATE HUFFMAN CODES
    static void generateCodes(Node node, String code) {

        if (node == null)
            return;

        if (node.left == null && node.right == null) {

            codes.put(node.ch, code.isEmpty() ? "0" : code);
            return;
        }

        generateCodes(node.left, code + "0");

        generateCodes(node.right, code + "1");
    }


    // 3. ENCODE TEXT
    static String encode(String text) {

        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            result.append(codes.get(c));
        }

        return result.toString();
    }


    // 4. DECODE TEXT
    static String decode(String encoded) {

        StringBuilder result = new StringBuilder();

        Node current = root;

        for (char bit : encoded.toCharArray()) {

            if (bit == '0')
                current = current.left;

            else if (bit == '1')
                current = current.right;

            else
                return null;

            if (current == null)
                return null;

            if (current.left == null && current.right == null) {

                result.append(current.ch);

                current = root;
            }
        }

        if (current != root)
            return null;

        return result.toString();
    }


    // 5. DISPLAY HUFFMAN CODES
    static void displayCodes() {

        System.out.println("\nHuffman Codes:");

        for (Map.Entry<Character, String> e : codes.entrySet()) {

            if (e.getKey() == ' ')
                System.out.println("[space] : " + e.getValue());

            else
                System.out.println(e.getKey() + " : " + e.getValue());
        }
    }


    // MAIN METHOD
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        if (text.isEmpty()) {

            System.out.println("Text cannot be empty.");
            sc.close();
            return;
        }

        buildTree(text);

        int choice;

        do {

            System.out.println("\n--- HUFFMAN ENCODING ---");
            System.out.println("1. Build Huffman Tree");
            System.out.println("2. Encode Text");
            System.out.println("3. Decode Text");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    buildTree(text);

                    System.out.println("Huffman Tree built successfully.");

                    displayCodes();

                    break;


                case 2:

                    String encoded = encode(text);

                    System.out.println("Original Text: " + text);
                    System.out.println("Encoded Text: " + encoded);

                    displayCodes();

                    break;


                case 3:

                    String encodedText = encode(text);

                    String decoded = decode(encodedText);

                    System.out.println("Encoded Text: " + encodedText);
                    System.out.println("Decoded Text: " + decoded);

                    break;


                case 4:

                    System.out.println("Program terminated.");

                    break;


                default:

                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}





/*Oral explanation:

"Good morning. My second practical is Huffman Encoding using the Greedy Algorithm.

Huffman Encoding is a lossless data compression technique. It assigns variable-length binary codes to characters based on their frequency. Characters that occur more frequently get shorter codes, while less frequent characters get longer codes.

First, we calculate the frequency of every character in the input text. Then we insert all characters into a Priority Queue according to their frequency.

After that, we repeatedly remove the two nodes having the minimum frequency and combine them into a new parent node. The frequency of the parent is the sum of both child frequencies. We repeat this process until only one node remains. This forms the Huffman Tree.

Next, we generate binary codes by traversing the tree. The left branch represents 0 and the right branch represents 1.

Finally, we encode the original text using these codes and decode it to get the original text back.

We use a Priority Queue, TreeMap, recursion and StringBuilder in Java.

The overall tree-building complexity is O(k log k), where k is the number of unique characters."

Remember:
- Algorithm: Greedy
- Data structure: Priority Queue and Binary Tree
- Left = 0, Right = 1
- It is lossless compression.
- Prefix-free codes prevent ambiguity.



*/
/*
============================================================
3. COMPLETE LINE-BY-LINE CODE EXPLANATION
============================================================

A. IMPORT AND CLASS

import java.util.*;

- Imports Java utility classes.
- Scanner is used for input.
- Map and TreeMap store character frequencies and codes.
- PriorityQueue stores nodes according to priority.
- Comparator defines the sorting order.

public class HuffmanEncoding

- Declares the public class.
- The filename must be HuffmanEncoding.java.


B. NODE CLASS

static class Node

- Creates an inner class called Node.
- Every Node represents an element of the Huffman Tree.
- static allows its use without creating an outer class object.

char ch;

- Stores the character, such as A, B or C.

int freq;

- Stores the frequency of the character.
- For an internal node, it stores the combined frequency.

int order;

- Stores the insertion order.
- Helps break ties when two nodes have equal frequencies.

Node left, right;

- Stores references to the left and right child nodes.

FIRST CONSTRUCTOR:

Node(char ch, int freq, int order)

- Constructor used to create a leaf node.
- Receives a character, frequency and insertion order.

this.ch = ch;

- Stores the received character in the current object.

this.freq = freq;

- Stores the character frequency.

this.order = order;

- Stores the insertion order.

Example:
new Node('A', 3, 0);

Creates a node containing A with frequency 3 and order 0.


SECOND CONSTRUCTOR:

Node(int freq, Node left, Node right, int order)

- Constructor used to create an internal or parent node.
- Does not receive a character because it combines two nodes.

this.freq = freq;

- Stores the combined frequency.

this.left = left;

- Connects the left child.

this.right = right;

- Connects the right child.

this.order = order;

- Stores the insertion order.


C. GLOBAL VARIABLES

static Node root;

- Stores the root of the Huffman Tree.
- Other static methods can access it directly.

static int count = 0;

- Maintains the insertion order of nodes.
- Starts from zero.

static Map<Character, String> codes = new TreeMap<>();

- Creates a map to store characters and their binary codes.
- Character is the key.
- String is the corresponding Huffman code.
- TreeMap keeps the keys sorted.

Example:
A -> 11
B -> 10
C -> 0


D. BUILD HUFFMAN TREE

static void buildTree(String text)

- Declares a method named buildTree.
- Receives the original input string.
- void means it does not return a value.
- static means it can be called without an object.

Map<Character, Integer> freq = new TreeMap<>();

- Creates a map to store character frequencies.
- Character is the key.
- Integer is the frequency.

Example:
Input = AAABBCCCC

A = 3
B = 2
C = 4


for (char c : text.toCharArray())

- Converts the string into a character array.
- Visits each character one by one.
- c stores the current character.

freq.put(c, freq.getOrDefault(c, 0) + 1);

- getOrDefault(c, 0) checks whether c exists.
- If it exists, returns its current frequency.
- Otherwise, returns 0.
- Adds 1 to the frequency.
- put() updates the map.

Example:
First A -> A = 1
Second A -> A = 2
Third A -> A = 3


PRIORITY QUEUE:

PriorityQueue<Node> pq = new PriorityQueue<>(...);

- Creates a PriorityQueue that stores Node objects.
- Nodes are arranged according to their priority.
- The smallest frequency node gets the highest priority.

Comparator.comparingInt((Node n) -> n.freq)

- Compares two nodes using their frequency.
- Smaller frequency comes first.
- n represents a Node object.
- n.freq accesses its frequency.

.thenComparingInt(n -> n.order)

- If two frequencies are equal, compares insertion order.
- This provides consistent tie-breaking.

count = 0;

- Resets the insertion-order counter.

for (Map.Entry<Character, Integer> e : freq.entrySet())

- Visits every character-frequency pair in the map.
- Map.Entry represents one key-value pair.
- e represents the current pair.

pq.add(new Node(e.getKey(), e.getValue(), count++));

- e.getKey() returns the character.
- e.getValue() returns its frequency.
- new Node() creates a leaf node.
- count++ assigns the current order and then increments it.
- pq.add() inserts the node into the priority queue.


SPECIAL CASE:

if (pq.size() == 1)

- Checks whether only one unique character exists.
- Example: AAAAA.

Node only = pq.poll();

- Removes the only node from the queue.
- Stores it in the variable only.

root = new Node(only.freq, only, null, count++);

- Creates a new root node.
- only becomes its left child.
- null means it has no right child.
- Assigns the insertion order.
- This special case ensures the character gets code 0.


NORMAL TREE CONSTRUCTION:

else

- Executes when there is more than one unique character.

while (pq.size() > 1)

- Repeats while more than one node exists.

Node left = pq.poll();

- Removes the smallest-frequency node.
- Assigns it as the left child.

Node right = pq.poll();

- Removes the next-smallest-frequency node.
- Assigns it as the right child.

Node parent = new Node(...);

- Creates a new parent node.

left.freq + right.freq

- Adds the frequencies of both child nodes.
- This becomes the parent's frequency.

left, right

- Connects both child nodes to the parent.

count++

- Assigns a new insertion order.

pq.add(parent);

- Inserts the newly created parent back into the queue.

The loop repeats until only one node remains.

root = pq.poll();

- Removes the final remaining node.
- This node becomes the root of the Huffman Tree.

codes.clear();

- Removes previously generated codes.
- Prevents old codes from remaining in the map.

generateCodes(root, "");

- Calls the generateCodes method.
- Starts from the root.
- Empty string means no binary path has been assigned yet.


E. GENERATE HUFFMAN CODES

static void generateCodes(Node node, String code)

- Receives the current tree node.
- code stores the binary path from the root.

if (node == null)

- Checks whether the current node is null.

return;

- Stops the recursive call if the node does not exist.

if (node.left == null && node.right == null)

- Checks whether the current node is a leaf.
- A leaf has no children.
- Each leaf represents one original character.

codes.put(node.ch, code.isEmpty() ? "0" : code);

- Stores the character and its binary code.
- node.ch gives the character.
- code.isEmpty() checks whether the code is empty.
- If empty, assigns 0.
- Otherwise, stores the generated code.
- This handles the single-character input case.

return;

- Stops processing after reaching a leaf.

generateCodes(node.left, code + "0");

- Recursively visits the left child.
- Adds 0 to the current binary code.

generateCodes(node.right, code + "1");

- Recursively visits the right child.
- Adds 1 to the current binary code.

RULE:
Left = 0
Right = 1

The path from root to leaf becomes the character's code.


F. ENCODE TEXT

static String encode(String text)

- Method that converts original text into binary codes.
- Returns a String.

StringBuilder result = new StringBuilder();

- Creates a StringBuilder to construct the encoded string.
- StringBuilder is efficient for repeated appending.

for (char c : text.toCharArray())

- Visits every character in the original text.

result.append(codes.get(c));

- codes.get(c) retrieves the Huffman code of character c.
- append() adds that code to the result.

Example:
A = 11
B = 10
C = 0

Input: ABC

Encoded: 11100

return result.toString();

- Converts StringBuilder into a normal String.
- Returns the encoded binary string.


G. DECODE TEXT

static String decode(String encoded)

- Receives the encoded binary string.
- Returns the decoded original text.

StringBuilder result = new StringBuilder();

- Creates a StringBuilder for decoded characters.

Node current = root;

- Starts traversal from the root of the Huffman Tree.

for (char bit : encoded.toCharArray())

- Reads each encoded bit one by one.

if (bit == '0')

- Checks whether the current bit is 0.

current = current.left;

- Moves to the left child.

else if (bit == '1')

- Checks whether the current bit is 1.

current = current.right;

- Moves to the right child.

else

return null;

- If the bit is neither 0 nor 1, returns null.
- null indicates invalid encoded input.

if (current == null)

return null;

- Checks whether the selected path exists.
- If it does not exist, the encoded path is invalid.

if (current.left == null && current.right == null)

- Checks whether the current node is a leaf.

result.append(current.ch);

- Adds the character represented by the leaf to the result.

current = root;

- Resets traversal to the root.
- Starts decoding the next character from the beginning.

if (current != root)

return null;

- Checks whether the encoded string ended in the middle of a code.
- If traversal has not returned to root, the final code is incomplete.

return result.toString();

- Converts the decoded StringBuilder into a String.
- Returns the original text.


H. DISPLAY HUFFMAN CODES

static void displayCodes()

- Method used to display the generated codes.
- void means it returns nothing.

System.out.println("\nHuffman Codes:");

- Prints the heading.
- \n adds a new line.

for (Map.Entry<Character, String> e : codes.entrySet())

- Visits every character-code pair in the map.

if (e.getKey() == ' ')

- Checks whether the current character is a blank space.

System.out.println("[space] : " + e.getValue());

- Displays the blank space as [space].
- getValue() returns its binary code.

else

- Executes for all other characters.

System.out.println(e.getKey() + " : " + e.getValue());

- Prints the character and its Huffman code.


I. MAIN METHOD

public static void main(String[] args)

- Main method is the starting point of the Java program.
- public allows the JVM to access it.
- static means no object is required.
- void means it returns nothing.
- String[] args accepts command-line arguments.

Scanner sc = new Scanner(System.in);

- Creates a Scanner object.
- System.in takes input from the keyboard.

System.out.print("Enter text: ");

- Displays a message asking for text.

String text = sc.nextLine();

- Reads the complete input line.
- nextLine() also accepts spaces.

if (text.isEmpty())

- Checks whether the input string is empty.

System.out.println("Text cannot be empty.");

- Displays an error message.

sc.close();

- Closes the Scanner.

return;

- Stops the main method.

buildTree(text);

- Builds the Huffman Tree before displaying the menu.
- Generates the character codes.

int choice;

- Declares a variable to store the menu selection.

do

- Starts a do-while loop.
- The menu executes at least once.

System.out.println(...);

- Displays the program title and menu options.

System.out.print("Enter choice: ");

- Asks the user to enter an option.

choice = sc.nextInt();

- Reads the integer menu choice.

switch (choice)

- Selects which operation should execute.

CASE 1:

buildTree(text);

- Builds the Huffman Tree again.

System.out.println(...);

- Displays a success message.

displayCodes();

- Displays the generated Huffman codes.

break;

- Exits case 1.


CASE 2:

String encoded = encode(text);

- Encodes the original text.
- Stores the result in encoded.

System.out.println("Original Text: " + text);

- Displays the original input.

System.out.println("Encoded Text: " + encoded);

- Displays the binary encoded string.

displayCodes();

- Displays the character-code mapping.

break;

- Exits case 2.


CASE 3:

String encodedText = encode(text);

- Encodes the original text first.
- Stores the binary result.

String decoded = decode(encodedText);

- Decodes the binary string.
- Stores the reconstructed text.

System.out.println("Encoded Text: " + encodedText);

- Displays the encoded text.

System.out.println("Decoded Text: " + decoded);

- Displays the decoded original text.

break;

- Exits case 3.


CASE 4:

System.out.println("Program terminated.");

- Displays the exit message.

break;

- Exits case 4.


DEFAULT:

System.out.println("Invalid choice.");

- Displays an error if the user enters an unsupported option.


} while (choice != 4);

- Repeats the menu until the user selects option 4.

sc.close();

- Closes the Scanner after the loop ends.


============================================================
4. TIME AND SPACE COMPLEXITY
============================================================

Let:

n = Total number of characters in the input.
k = Number of unique characters.
m = Length of encoded string.

1. FREQUENCY CALCULATION

Time Complexity: O(n log k)

Reason:
- Each character is processed once.
- TreeMap insertion/update takes O(log k).

2. BUILD HUFFMAN TREE

Time Complexity: O(k log k)

Reason:
- PriorityQueue insertion and removal take O(log k).
- There are O(k) nodes to process.

3. GENERATE HUFFMAN CODES

Time Complexity: O(k) tree traversal,
excluding the cost of constructing/copying code strings.

4. ENCODING

Time Complexity: O(m)

Reason:
- Every character's code is appended.
- m is the total number of encoded bits.

5. DECODING

Time Complexity: O(m)

Reason:
- Every encoded bit is processed once.

6. SPACE COMPLEXITY

O(k) for:
- Frequency map.
- Huffman Tree.
- PriorityQueue.
- Code map.

Including input and output strings:
O(n + k + m).


============================================================
5. VIVA QUESTIONS AND ANSWERS
============================================================

Q1. What is Huffman Encoding?
Ans: A lossless compression algorithm that assigns
variable-length binary codes based on frequency.

Q2. Which algorithmic technique is used?
Ans: Greedy Algorithm.

Q3. Why is it called Greedy?
Ans: It repeatedly selects the two minimum-frequency nodes.

Q4. What is a Huffman Tree?
Ans: A binary tree used to generate Huffman codes.

Q5. What is a leaf node?
Ans: A node having no children.

Q6. What is an internal node?
Ans: A node containing combined frequency and child references.

Q7. What is a prefix-free code?
Ans: No character's code is the prefix of another character's code.

Q8. Is Huffman Encoding lossless?
Ans: Yes, the original text can be reconstructed.

Q9. Why do we use PriorityQueue?
Ans: To efficiently retrieve the two minimum-frequency nodes.

Q10. Why do we use Comparator?
Ans: To define the ordering of PriorityQueue nodes.

Q11. What does poll() do?
Ans: Removes and returns the highest-priority element.

Q12. What does getOrDefault() do?
Ans: Returns an existing value or a default value if the key
does not exist.

Q13. Why do we use TreeMap?
Ans: To maintain keys in sorted order.

Q14. Why do we use StringBuilder?
Ans: For efficient repeated string appending.

Q15. Why is recursion used?
Ans: To traverse the left and right subtrees.

Q16. What happens if two characters have equal frequencies?
Ans: The tie-breaking rule determines which node is selected first.

Q17. Why is the left branch assigned 0?
Ans: To represent the left path in binary form.

Q18. Why is the right branch assigned 1?
Ans: To represent the right path in binary form.

Q19. What happens if the input contains only one unique character?
Ans: The program assigns it code 0.

Q20. Can Huffman Encoding compress every input?
Ans: No. Storage overhead can outweigh compression for small inputs.

Q21. What is the difference between encoding and decoding?
Ans: Encoding converts characters into binary codes.
Decoding reconstructs characters from binary codes.

Q22. What is the use of root?
Ans: It stores the root node of the Huffman Tree.

Q23. What is the use of count?
Ans: It maintains insertion order for tie-breaking.

Q24. What does entrySet() return?
Ans: It returns all key-value pairs of a Map.

Q25. What does toCharArray() do?
Ans: Converts a String into a character array.

Q26. What is the difference between add() and poll()?
Ans: add() inserts an element; poll() removes the highest-priority
element from the PriorityQueue.


============================================================
6. SAMPLE INPUT AND OUTPUT
============================================================

INPUT:

Enter text: AAABBCCCC

OPTION 1:

Enter choice: 1

Huffman Tree built successfully.

Huffman Codes:
A : 11
B : 10
C : 0

OPTION 2:

Enter choice: 2

Original Text: AAABBCCCC
Encoded Text: 11111110100000

Huffman Codes:
A : 11
B : 10
C : 0

OPTION 3:

Enter choice: 3

Encoded Text: 11111110100000
Decoded Text: AAABBCCCC

OPTION 4:

Enter choice: 4

Program terminated.


NOTE:
- These codes follow this program's tie-breaking order.
- Other valid Huffman implementations may produce different codes.
- The decoded text must still match the original text.


============================================================
7. LINUX COMMANDS
============================================================

Create Java file:

nano HuffmanEncoding.java

Compile:

javac HuffmanEncoding.java

Run:

java HuffmanEncoding

============================================================
*/
