import java.io.*;
import java.util.*;

public class HW24QHeap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int query = scanner.nextInt();
            if (query == 1) {
                heap.add(scanner.nextInt());
            } else if (query == 2) {
                heap.remove(Integer.valueOf(scanner.nextInt()));
            } else if (query == 3) {
                System.out.println(heap.peek());
            } else {
            }
        }
        scanner.close();
    }
}
