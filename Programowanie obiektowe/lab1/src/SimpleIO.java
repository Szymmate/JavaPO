import java.util.Scanner;
import java.util.Locale;

public class SimpleIO {
    public static void main(String[] args)
    {
        System.out.print("...");
        System.out.println("...");
        System.out.printf("String %s int %d double %d","asd",2,23432);

        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f",s,i,d);
    }
}