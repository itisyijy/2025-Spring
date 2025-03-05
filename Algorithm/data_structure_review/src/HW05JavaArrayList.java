import java.io.*;
import java.util.*;

// Sometimes it's better to use dynamic size arrays.
// Java's Arraylist can provide you this feature.
// Try to solve this problem using Arraylist.

// You are given n lines. In each line there are zero or more integers.
// You need to answer a few queries where you need to tell the number located in yth position of xth line.
// Take your input from System.in.

// Input Format
// The first line has an integer n.
// In each of the next n lines there will be an integer d denoting number of integers on that line
// and then there will be d space-separated integers.
// In the next line there will be an integer q denoting number of queries.
// Each query will consist of two integers x and y.

public class HW05JavaArrayList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // make array of ArrayList
        int numberOfColumn = scanner.nextInt();
        ArrayList<Integer>[] arrayLists = new ArrayList[numberOfColumn];
        for (int i = 0; i < numberOfColumn; i++) {
            int numberOfRow = scanner.nextInt();
            arrayLists[i] = new ArrayList<>(numberOfRow);
            for (int j = 0; j < numberOfRow; j++) {
                arrayLists[i].add(scanner.nextInt());
            }
        }
        // find and print (x, y) element
        int numberOfElements = scanner.nextInt();
        for (int i = 0; i < numberOfElements; i++) {
            int xCoor = scanner.nextInt() - 1;
            int yCoor = scanner.nextInt() - 1;
            if (xCoor < numberOfColumn && yCoor < arrayLists[xCoor].size()) {
                System.out.println(arrayLists[xCoor].get(yCoor));
            }
            else {
                System.out.println("ERROR!");
            }
        }
        scanner.close();
    }
}
