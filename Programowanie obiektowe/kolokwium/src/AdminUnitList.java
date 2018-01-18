import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class AdminUnitList {
    List<AdminUnit> units=new ArrayList<>();
    public void read(String filename) throws IOException {
        CSVReader a=new CSVReader("info_pracownicy.csv");
        int s=0,ic=-1;
        Map <Integer ,Integer> id=new HashMap<Integer, Integer>();
        Map <Integer ,Integer> idparent=new HashMap<Integer, Integer>();
        do {
            ic++;
            AdminUnit c = new AdminUnit();
            c.parent=null;
            if (!a.isMissing("nazwisko")) {
                c.nazwisko = a.get("nazwisko");
            }
            else {
                c.nazwisko = "nic";
            }
            if (!a.isMissing("imie")) {
                c.imie = a.get("imie");
            }
            else {
                c.imie = "nic"  ;
            }
            if (!a.isMissing("forma_zatrudnienia")) {
                c.forma_zatrudnienia = a.get("forma_zatrudnienia");
            }
            else {
                c.forma_zatrudnienia = "nic";
            }
            if (!a.isMissing("stanowisko")) {
                c.stanowisko = a.get("stanowisko");
            }
            else {
                c.stanowisko = "nic";
            }

        }while (a.next()&&a!=null);
    }
    void list(PrintStream out){
        for (AdminUnit x:units) {
            System.out.printf(x.toString(),out);
            System.out.printf("\n",out);
        }
    }
    void count(){
        int k=0;
        int m=0;
        for (AdminUnit x:units) {
            if (x.imie.charAt(x.imie.length()-1)=='a'&&x.forma_zatrudnienia.contains("o pracę")){
                k=k+1;
            }
            if (x.imie.charAt(x.imie.length()-1)!='a'&&x.forma_zatrudnienia.contains("o pracę")){
                m=m+1;
            }
            System.out.printf("kobiet jest%d",k,"mezczyzn jest", m);


        }
    }

    void specjalista(){
        for (AdminUnit x:units) {
            if (x.stanowisko.contains("specjalista")){
                System.out.printf(x.imie, x.nazwisko);
            }
        }
    }
    void not(){
        for (AdminUnit x:units) {
            if (!x.stanowisko.contains("specjalista") && !x.stanowisko.contains("sekretarka") &&!x.stanowisko.contains("stażysta") && !x.stanowisko.contains("asystent"))){
                System.out.printf(x.imie, x.nazwisko, x.stanowisko);
            }
        }
    }




