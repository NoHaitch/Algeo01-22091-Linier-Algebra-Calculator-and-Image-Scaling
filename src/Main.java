import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in); // Create a Scanner object
        System.out.print("Masukan Angka : "); // Read user input
        String inpt = myObj.nextLine(); // Output user input
        System.out.println("Kamu milih " + inpt + "?");
        myObj.close();
    }
}