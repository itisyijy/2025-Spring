import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class ResultPowerSum {

    /*
     * Complete the 'powerSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER X
     *  2. INTEGER N
     */

    public static int pow(int x, int N) {
        int result = 1;
        for (int i = 0; i < N; i++)
            result = result * x;
        return (result);
    }

    public static int powerSum(int x, int N) {
        return recursion(x, N, 1);
    }

    public static int recursion(int x, int N, int i) {
        int i_pow_n = pow(i, N); // i^N
        if (x == i_pow_n)
            return 1;
        if (x < i_pow_n)
            return 0;
        return recursion(x, N, i + 1) + recursion(x-i_pow_n, N, i + 1);
    }

}

public class HW16PowerSum {
    public static void main(String[] args) throws IOException {
        System.out.println(ResultPowerSum.powerSum(100, 3));

    }
}
