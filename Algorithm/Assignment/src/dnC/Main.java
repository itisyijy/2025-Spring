package dnC;

public class Main {

  public static void main(String[] args) {
    Itm itm = new Itm();
    System.out.println("24: " + (itm.processDigits("1234") == 24 ? 24 : -1));   // Output: 24
    System.out.println("43: " + (itm.processDigits("12345") == 43 ? 43 : -1));  // Output: 43
    System.out.println("2: " + (itm.processDigits("12") == 2 ? 2 : -1));  // Output: 2
  }
}
