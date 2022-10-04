/*
 demonstrates priority queue

Note: Name the "Project DS_PriorityQue"
 */
package ds_priorityque;

import java.io.*;

class Node {

    //Data goes here
    public int Key = 0;
    public String City = "";
    public String Fname = "";
    public String Lname = "";

    //link to next one
    public Node(int Key, String City, String Fname, String Lname) {

        this.Key = Key;
        this.City = City;
        this.Fname = Fname;
        this.Lname = Lname;
    }

    public int getKey() {
        return Key;
    }

    public void setKey(int id) {
        Key = id;
    }

}

class PriorityQ {
    // array in sorted order, from max at 0 to min at size-1

    private int maxSize;
    private Node[] queArray;
    private int nItems;
//-------------------------------------------------------------

    public PriorityQ(int s) // constructor
    {

        maxSize = s;
        queArray = new Node[maxSize];
        nItems = 0;
    }
//-------------------------------------------------------------

    public void insert(int item, String City, String Fname, String Lname) // insert item
    {
        Node newNode = new Node(item, City, Fname, Lname);
        int j;

        if (nItems == 0) // if no items,
        {
            queArray[nItems++] = newNode;         // insert at 0
        } else // if items,
        {
            for (j = nItems - 1; j >= 0; j--) // start at end,
            {
                if (item > queArray[j].Key) // if new item larger,
                {
                    queArray[j + 1] = queArray[j]; // shift upward
                } else // if smaller,
                {
                    break;                     // done shifting
                }
            }  // end for
            queArray[j + 1] = newNode;            // insert it
            nItems++;
        }  // end else (nItems > 0)
    }  // end insert()
//-------------------------------------------------------------

    public Node remove() // remove minimum item
    {
        return queArray[--nItems];
    }
//-------------------------------------------------------------

    public Node peekMin() // peek at minimum item
    {
        return queArray[nItems - 1];
    }
//-------------------------------------------------------------

    public boolean isEmpty() // true if queue is empty
    {
        return (nItems == 0);
    }
//-------------------------------------------------------------

    public boolean isFull() // true if queue is full
    {
        return (nItems == maxSize);
    }
//-------------------------------------------------------------

    public void peeknext() {
        System.out.println("Next: Key: " + queArray[nItems - 1].Key + ", First Name: " + queArray[nItems - 1].Fname + ", Last Name: " + queArray[nItems - 1].Lname + ", City Name: " + queArray[nItems - 1].City);
    }

    public void sizesout() {
        System.out.print("Que size is: " + nItems + "\n");
    }
}  // end class PriorityQ
////////////////////////////////////////////////////////////////

class Heap {

    private Node[] heapArray;
    private int maxSize;           // size of array
    private int currentSize;       // number of nodes in array
// -------------------------------------------------------------

    public Heap(int mx) // constructor
    {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];  // create array
    }
// -------------------------------------------------------------

    public boolean isEmpty() {
        return currentSize == 0;
    }
    public boolean isFull() {
        return currentSize == maxSize;
    }
    public int currentSize(){
        return currentSize;
    }
// -------------------------------------------------------------

    public boolean insert(int key, String City, String Fname, String Lname) {
        if (currentSize == maxSize) {
            return false;
        }
        Node newNode = new Node(key, City, Fname, Lname);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }  // end insert()
// -------------------------------------------------------------

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0
                && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];  // move it down
            index = parent;
            parent = (parent - 1) / 2;
        }  // end while
        heapArray[index] = bottom;
    }  // end trickleUp()
// -------------------------------------------------------------

    public Node remove() // delete item with max key
    {                           // (assumes non-empty list)
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }  // end remove()
// -------------------------------------------------------------

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];       // save root
        while (index < currentSize / 2) // while node has at
        {                               //    least one child,
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            // find larger child
            if (rightChild < currentSize
                    && // (rightChild exists?)
                    heapArray[leftChild].getKey()
                            < heapArray[rightChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            // top >= largerChild?
            if (top.getKey() >= heapArray[largerChild].getKey()) {
                break;
            }
            // shift child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;            // go down
        }  // end while
        heapArray[index] = top;            // root to index
    }  // end trickleDown()
// -------------------------------------------------------------

    public boolean change(int index, int Key, String City, String Fname, String Lname) {
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey(); // remember old
        heapArray[index].setKey(Key);
        heapArray[index].Fname = Fname;
        heapArray[index].Lname = Lname;
        heapArray[index].City = City;

        // change to new
        if (oldValue < Key) // if raised,
        {
            trickleUp(index);                // trickle it up
        } else // if lowered,
        {
            trickleDown(index);              // trickle it down
        }
        return true;
    }  // end change()
// -------------------------------------------------------------

    public void displayHeap() {
        System.out.print("heapArray (USE THE KEY BELOW TO FIND WHERE THEY SIT!): \n");
        // array format
        int counter = 0;
        for (int m = 0; m < currentSize; m++) {
            counter++;
            if (heapArray[m] != null) {
                System.out.println(counter + ". City: " + heapArray[m].City + ", " + "FirstName: " + heapArray[m].Fname + ", " + "LastName: " + heapArray[m].Lname + ", " + "KeyVal: " + heapArray[m].Key + "| "
                        + "Index of heap: " + m);
            } else {
                System.out.print("-- ");
            }
        }
        System.out.println();
        // heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;                          // current item
        System.out.println("KEY BELOW <--- Top is priority then left to right");
        String dots = "...............................";
        System.out.println(dots + dots);      // dotted top line

        while (currentSize > 0) // for each heap item
        {
            if (column == 0) // first item in row?
            {
                for (int k = 0; k < nBlanks; k++) // preceding blanks
                {
                    System.out.print(' ');
                }
            }
            // display item
            System.out.print(heapArray[j].getKey());

            if (++j == currentSize) // done?
            {
                break;
            }

            if (++column == itemsPerRow) // end of row?
            {
                nBlanks /= 2;                 // half the blanks
                itemsPerRow *= 2;             // twice the items
                column = 0;                   // start over on
                System.out.println();         //    new row
            } else // next item on row
            {
                for (int k = 0; k < nBlanks * 2 - 2; k++) {
                    System.out.print(' ');     // interim blanks
                }
            }
        }  // end for
        System.out.println("\n" + dots + dots); // dotted bottom line
    }  // end displayHeap()
    // -------------------------------------------------------------
    public void peekHeap(){
        System.out.println("City: " + heapArray[0].City + ", " + "FirstName: " + heapArray[0].Fname + ", " + "LastName: " + heapArray[0].Lname + ", " + "KeyVal: " + heapArray[0].Key + "| "
                + "Index of heap: " + 0);
    }
}

public class DS_PriorityQue {

    static PriorityQ thePQ = new PriorityQ(5);
    static Heap theHeap = new Heap(31);

    static void ADDVals() {
        thePQ.insert(30, "Kalispell", "John1", "Boinsh1");
        thePQ.insert(50, "Bozeman", "John2", "Boinsh2");
        thePQ.insert(10, "Butte", "John3", "Boinsh3");
        thePQ.insert(40, "Missoula", "John4", "Boinsh4");
        thePQ.insert(20, "Whitefish", "John5", "Boinsh5");
    }

    static void ADDVals2() {
        theHeap.insert(30, "Kalispell", "John1", "Boinsh1");
        theHeap.insert(50, "Bozeman", "John2", "Boinsh2");
        theHeap.insert(10, "Butte", "John3", "Boinsh3");
        theHeap.insert(40, "Missoula", "John4", "Boinsh4");
        theHeap.insert(20, "Whitefish", "John5", "Boinsh5");
    }

    public static void main(String[] args) {

        System.out.println("------------------------------------------------");
        ADDVals();
        //NOTE: The data stored like a stack.  In the array,
        //the order is 50, 40, 30, 20, 10
        //Remove - removes them from the end of the array
        System.out.println("Priority que example: ");
        int counter = 0;
        thePQ.sizesout();
        System.out.println("The status of the que being full: " + thePQ.isFull());

        while (!thePQ.isEmpty()) {
            counter++;
            Node item = thePQ.remove();

            System.out.println(counter + ". City: " + item.City + ", " + "First Name: " + item.Fname + ", " + "Last Name: " + item.Lname + ", " + "Key Value: " + item.Key + "|");  // 10, 20, 30, 40, 50
        }  // end while

        System.out.println("\nALL Items removed during sout \n");
        thePQ.sizesout();
        System.out.println("The status of the que being empty: " + thePQ.isEmpty());

        ADDVals();
        System.out.println("\n\n Readding data...\n");
        System.out.println("Peek Next Example: ");
        thePQ.peeknext();
        System.out.println("Deleting next and then reprinting peek: ");
        thePQ.remove();
        thePQ.peeknext();
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        System.out.println("------------------------------------------------");
        //------------------------------------------------------------------------------------------------------------------------------------------------
        System.out.println("Priority que HEAP example: ");

        int value, value2;
        // make a Heap; max size 31
        boolean success;
        System.out.println("Status of heap being empty: " + theHeap.isEmpty() + "\n\n Adding Values to Heap....\n");
        System.out.println("Status of heap being full: " + theHeap.isFull());
        ADDVals2();
        theHeap.displayHeap();
        System.out.println("\n\n\nRemoving Root...\n");
        theHeap.remove();
        theHeap.displayHeap();
        System.out.println("Changing root to Candyland man with value of 1 for key: \n");
        theHeap.change(0, 1, "Candyland", "Random", "Man");
        theHeap.displayHeap();
        System.out.println("Peaking Top of heap:");
        theHeap.peekHeap();

        System.out.println("Current size is: "+ theHeap.currentSize());



    } // TODO code application logic here

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
//-------------------------------------------------------------

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }
//-------------------------------------------------------------

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
//----
}
