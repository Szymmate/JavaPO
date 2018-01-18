import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    AdminUnit parent;
    String nazwisko;
    String imie;
    String forma_zatrudnienia;
    String stanowisko;
    List<AdminUnit> children=new ArrayList<>();
    public String toString(){
        StringBuilder a=new StringBuilder();
        a.append(nazwisko).append(" ");
        if(parent!=null){
            a.append("w jednostce ").append(parent.nazwisko);
        }else {
            a.append("jednostka podstawowa");
        }
        a.append(" nazwisko: ").append(nazwisko).append(" imie: ").append(imie).append(" forma_zatrudnienia: ").append(forma_zatrudnienia).append(" stanowisko: ").append(stanowisko);
        return a.toString();
    }
}