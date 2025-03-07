import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class HW25Hashset {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] pair_left = new String[t];
        String[] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < t; i++) {
            if (pair_left[i].compareTo(pair_right[i]) > 0)
                set.add(pair_left[i] + " " + pair_right[i]);
            else
                set.add(pair_right[i] + " " + pair_left[i]);
            System.out.println(set.size());
        }
    }
}
