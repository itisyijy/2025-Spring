package bruteforce;

public class Itm {

  public int maxSubarraySum(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++) {
      int tmp = 0;
      for (int j = i; j < arr.length; j++) {
        tmp += arr[j];
        max = Math.max(max, tmp);
      }
    }
    return max;
  }
}
