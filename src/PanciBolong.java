import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class PanciBolong{
    public static void printHeader(){
        String accum = "\n\n";
        File input = new File("header.txt");
        try{
            Scanner readFile = new Scanner(input);
            String line;
            while (readFile.hasNextLine() && (line = readFile.nextLine())  != null){
                accum += line + "\n";
            }
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        System.out.println(accum);
    }
    public static void main (String[] args){
        printHeader();
    }
}