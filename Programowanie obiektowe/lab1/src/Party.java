import java.util.Scanner;

public class Party {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int[][] tab=new int[n][2];
        for (int i=0;i<n;i++){
            tab[i][0]=scan.nextInt();
            tab[i][1]=0;
        }
        for(int i=0;i<n;i++){
            int j=i;
            tab[i][1]=1;
            while(tab[j][0]!=-1){
                j=tab[j][0];
                if(tab[j][1]!=0){
                    tab[i][1]=tab[i][1]+tab[j][1];
                    break;
                }
                tab[i][1]++;
            }
        }
        int max=tab[0][1];
        for(int i=0;i<n;i++){
            if(tab[i][1]>max){
                max=tab[i][1];
            }
        }
        System.out.println(max);
    }
}