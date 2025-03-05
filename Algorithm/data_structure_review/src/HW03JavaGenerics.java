// Generic methods are a very efficient way to handle multiple datatypes using a single method.
// This problem will test your knowledge on Java Generic methods.
// Let's say you have an integer array and a string array.
// You have to write a single method printArray that can print all the elements of both arrays.
// The method should be able to accept both integer arrays or string arrays.


public class HW03JavaGenerics {
    public static <T> void printArray(T[] arr) {
        for (T t : arr) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        String[] strArray = {"Hello", "World"};

        printArray(intArray);
        printArray(strArray);
    }
}
