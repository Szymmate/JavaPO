import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AdminUnitListTest {
    @Test
    public void read() throws Exception {
        AdminUnitList a=new AdminUnitList();
        a.read("admin-units.csv");
        a.fixMissingValues();
        //a.list(System.out,90,100);
        //a.selectByName("województwo [^m]*",true).list(System.out);
        //a.getNeighbors(a.selectByName("województwo śląskie",false).units.get(0),0.13513513513).list(System.out);
        //a.sortInplaceByName().list(System.out,0,100);
        //a.sortInplaceByArea().list(System.out,0,100);
        //a.sortInplaceByPopulation().list(System.out,0,100);
        //a.filter(b->b.name.startsWith("K")).sortInplaceByArea().list(System.out);
//        a.filter(b->b.adminLevel==6&&b.parent.name.equals("województwo małopolskie")).sortInplaceByArea().list(System.out);
//        a.filter(b->b.adminLevel==6&&b.parent.name.equals("województwo małopolskie"),5).sortInplaceByArea().list(System.out);
//        a.filter(b->b.adminLevel==6&&b.parent.name.equals("województwo małopolskie"),2,3).sortInplaceByArea().list(System.out);
    }

}