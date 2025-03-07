import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class ResultFibo {

    /*
     * Complete the 'isFibo' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts LONG_INTEGER n as parameter.
     */

    public static long fibo(long first, long second, long check) {
        long third = first + second;
        if (third < check){
            third = fibo(second, third, check);
        }
        return third;
    }

    public static String isFibo(long n) {
        // Write your code here
        if (n == fibo(0, 1, n))
            return ("IsFibo");
        return ("IsNotFibo");
    }

}

public class HW15Fibo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());

                String result = ResultFibo.isFibo(n);

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
