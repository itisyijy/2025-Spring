package dnC;

public class Itm {

  /**
   * 문자열 형태의 숫자를 문제 조건에 따라 재귀적으로 처리하는 함수 1. 자릿수가 1이면 → 그대로 반환 2. 자릿수가 2이면 → 두 숫자의 곱 반환 3. 자릿수가 짝수이면
   * → 절반으로 나눠서 각각 재귀 처리한 뒤 곱함 4. 자릿수가 홀수이면 → 중간 숫자 기준으로 양쪽 나눠 재귀 처리 후, 중간 숫자를 더함
   */
  public int processDigits(String str) {
    int len = str.length();

    // 자릿수가 1자리일 경우 → 숫자 그대로 반환
    if (len == 1) {
      return str.charAt(0) - '0';
    }

    // 자릿수가 2자리일 경우 → 두 숫자의 곱 반환
    if (len == 2) {
      return (str.charAt(0) - '0') * (str.charAt(1) - '0');
    }

    // 중앙 인덱스를 기준으로 나눔
    int mid = len / 2;
    String left = str.substring(0, mid);  // 왼쪽 절반

    if (len % 2 == 0) {
      // 자릿수가 짝수일 경우 → 좌우 절반으로 나누어 곱함
      String right = str.substring(mid);  // 오른쪽 절반
      return processDigits(left) * processDigits(right);
    } else {
      // 자릿수가 홀수일 경우 → 가운데 숫자 제외하고 좌우로 나눠서 곱한 뒤, 가운데 숫자를 더함
      String right = str.substring(mid + 1);      // 가운데 이후 오른쪽 절반
      int center = str.charAt(mid) - '0';       // 가운데 숫자
      return processDigits(left) * processDigits(right) + center;
    }
  }
}
