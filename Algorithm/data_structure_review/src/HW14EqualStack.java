import java.io.*;
import java.util.*;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class HW14EqualStack {
    class Result {

        /*
         * Complete the 'equalStacks' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY h1
         *  2. INTEGER_ARRAY h2
         *  3. INTEGER_ARRAY h3
         */

        public static Stack<Integer> pushList(List<Integer> h) {
            int n = h.size();
            Stack<Integer> s = new Stack<>();
            for (int i = n - 1; i >= 0; i--)
                s.push(h.get(i));
            return (s);
        }

        public static int total(List<Integer> h) {
            int total = 0;
            for (int i = 0; i < h.size(); i++)
                total += h.get(i);
            return (total);
        }

        public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
            // Write your code here
            int h1Sum = total(h1);
            int h2Sum = total(h2);
            int h3Sum = total(h3);

            Stack<Integer> h1Stack = pushList(h1);
            Stack<Integer> h2Stack = pushList(h2);
            Stack<Integer> h3Stack = pushList(h3);

            while (h1Sum != h2Sum || h2Sum != h3Sum) {
                if (h1Sum >= h2Sum && h1Sum >= h3Sum) {
                    h1Sum -= h1Stack.pop();
                } else if (h2Sum >= h1Sum && h2Sum >= h3Sum) {
                    h2Sum -= h2Stack.pop();
                } else if (h3Sum >= h1Sum && h3Sum >= h2Sum) {
                    h3Sum -= h3Stack.pop();
                }
            }
            return h1Sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
