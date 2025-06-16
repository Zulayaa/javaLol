
public class Main {
    public static void main(String[] args) {
        List list = new List();
        list.add("First");
        list.add("Second");
        list.add("Third");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getValue(i));
        }

        System.out.println("\nDeleting Second\n...");
        list.remove("Second");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.getValue(i));
        }
    }
}
