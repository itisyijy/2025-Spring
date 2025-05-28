package Ternary;

public class MainTST {

  public static void main(String[] args) {
    TSTree t = new TSTree();
    Itm itm = new Itm();

    t.insert("hello");
    t.insert("world");
    t.insert("her");
    t.insert("hero");

    System.out.println(
        "Following is traversal of ternary search tree:");
    t.traverseTST();

    System.out.println(
        "\nFollowing are search results");
    System.out.println("her: " + (itm.searchTST(t, "her") ? "Found" : "Not Found"));
    System.out.println("hell: " + (itm.searchTST(t, "hell") ? "Found" : "Not Found"));

    // Another Test Case
    t = new TSTree();
    itm = new Itm();
    t.insert("cat");
    t.insert("cats");
    t.insert("up");
    t.insert("bug");

    System.out.println(
        "\nFollowing is traversal of ternary search tree:");

    t.traverseTST();
    System.out.println(
        "\nFollowing are search results for 'cats', 'bu', and 'up':");
    System.out.println("cats: " + (itm.searchTST(t, "cats") ? "Found" : "Not Found"));
    System.out.println("cat: " + (itm.searchTST(t, "cat") ? "Found" : "Not Found"));
    System.out.println("bu: " + (itm.searchTST(t, "bu") ? "Found" : "Not Found"));
    System.out.println("up: " + (itm.searchTST(t, "up") ? "Found" : "Not Found"));
    System.out.println("u: " + (itm.searchTST(t, "u") ? "Found" : "Not Found"));
  }
}
