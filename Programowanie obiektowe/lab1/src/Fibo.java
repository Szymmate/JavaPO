import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int tab[] = new int[n];
        if (n < 1) {
            return;
        }
        if (n == 1) {
            tab[0] = 1;
            System.out.println(tab[0]);
            return;
        }
        tab[1] = 1;
        for (int i = 2; i < n; i++) {
            tab[i] = tab[i - 1] + tab[i - 2];
        }
        for (int i = 0; i < n; i++) {
            System.out.println(tab[i]);
        }
    }
}