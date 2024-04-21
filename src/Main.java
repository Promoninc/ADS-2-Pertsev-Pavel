import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyLinkedList<String> arr = new MyLinkedList<>();
        arr.add("Hi");
        arr.add("Hello");
        arr.add("Privet");
        arr.add("Hola");
        arr.sort();
        MyLinkedList<Integer> arrInt = new MyLinkedList<>();
        arrInt.add(5);
        arrInt.add(3);
        arrInt.add(7);
        arrInt.add(10);
        arrInt.add(26);
        arrInt.add(5, 11);
        arrInt.sort();
        for(int i : arrInt){
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }
}