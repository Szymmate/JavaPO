import java.util.Scanner;

public class Stick {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int s,l;
        s=0;
        l=scan.nextInt();
        if(l%2==1){
            System.out.println("nie da się pociąć na 4 części by utworzyć z nich prostokąt");
        }
        for (int i=1;i<l/4;i++){
            if(i!=l/2-i){
                s++;
            }
        }
        System.out.println(s);
    }
}