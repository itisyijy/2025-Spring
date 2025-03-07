import java.io.*;
import java.util.*;
import java.util.stream.IntStream;


public class HW13BalancedBrackets {
    class Result {

        /*
         * Complete the 'isBalanced' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts STRING s as parameter.
         */

        public static String isBalanced(String s) {
            // Write your code here
            char[] array = s.toCharArray();
            Stack<Character> bracketStack = new Stack<>();

            for (int i = 0; i < array.length; i++) {
                if (array[i] == '(' || array[i] == '{' || array[i] == '[')
                    bracketStack.push(array[i]);
                else if (bracketStack.isEmpty())
                    return ("NO");
                else if (array[i] == ')' && bracketStack.peek() != '(')
                    return ("NO");
                else if (array[i] == '}' && bracketStack.peek() != '{')
                    return ("NO");
                else if (array[i] == ']' && bracketStack.peek() != '[')
                    return ("NO");
                else
                    bracketStack.pop();
            }
            if (bracketStack.isEmpty())
                return ("YES");
            return ("NO");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
