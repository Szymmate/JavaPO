import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class MatrixTest {
    @Test
    public void Matrix() throws Exception {
        Matrix rezult=new Matrix(11,45);
        assertEquals(11,rezult.getrows());
        assertEquals(45,rezult.getcols());
    }
    @Test
    public void Matrix_table() throws Exception {
        Matrix m=new Matrix(new double[][] {{12,13,14},{5,67},{1,12,123,1}});
        double[][] rezult=m.asArray();
        assertEquals("1",4,rezult[0].length);
        assertEquals("2",4,rezult[1].length);
        assertEquals("3",4,rezult[2].length);
        assertEquals("4",0,rezult[0][3],0);
        assertEquals("5",0,rezult[1][2],0);
        assertEquals("6",0,rezult[1][3],0);
        assertEquals("7",1,rezult[2][3],0);


    }
    @Test
    public void asArray() throws Exception {
        double[][] expected=new double[][] {{1,3,4,5},{1,4,3434,33},{212,1234,555,674},{2,78655,86754,334}};
        Matrix result=new Matrix(expected);
        for(int i=0;i<4;i++){
            assertArrayEquals(expected[i],result.asArray()[i],0);
        }
    }

    @Test
    public void getiset() throws Exception {
        double[][] expected=new double[][] {{1,3,4,5},{1,4,3434,33},{212,1234,555,674},{2,78655,86754,334}};
        Matrix result=new Matrix(expected);
        assertEquals(4,result.get(1,1),0);
        assertEquals(334,result.get(3,3),0);
        assertEquals(1234,result.get(2,1),0);
        result.set(1,1,54);
        result.set(3,3,54);
        result.set(2,1,54);
        assertEquals(54,result.get(1,1),0);
        assertEquals(54,result.get(3,3),0);
        assertEquals(54,result.get(2,1),0);
    }

    @Test
    public void testtostring() throws Exception {
        Matrix m=new Matrix(new double[][] {{12,13,14},{5,67},{1,12,123,1}});
        assertEquals(m.toString(),"[[12.0,13.0,14.0,0.0][5.0,67.0,0.0,0.0][1.0,12.0,123.0,1.0]]");
    }

    @Test
    public void reshape() throws Exception {

    }

    @Test
    public void sub() throws Exception {
        Matrix m1=new Matrix(new double[][] {{12,13,14},{5,67},{1,12,123,1}});
        Matrix m2=new Matrix(new double[][] {{12,13,14},{5,67},{1,12,123,1}});
        assertEquals(0,m1.sub(m2).Frobenius(),0.001);
    }

    @Test
    public void mul() throws Exception {
        Matrix m1=Matrix.random(3,3);
        Matrix m2=new Matrix(new double[][]{{1,1,1},{1,1,1},{1,1,1}});
        assertEquals(m1.Frobenius(),m1.mul(m2).Frobenius(),0.001);
    }

    @Test
    public void div() throws Exception {

        Matrix m1=Matrix.random(12,12);
        assertEquals(144,m1.div(m1).Frobenius(),0.001);
    }

    @Test
    public void add() throws Exception {

        Matrix m1=Matrix.random(12,12);
        assertEquals(m1.Frobenius()*4,m1.add(m1).Frobenius(),0.001);
    }

    @Test
    public void dot() throws Exception {
    }

    @Test
    public void eye() throws Exception {
        Matrix m=Matrix.eye(32,32);
        assertEquals(32,m.Frobenius(),0.001);

    }

    @Test
    public void inv() throws Exception {
        Matrix m1=new Matrix(new double[][]{{4,0,0},{0,4,0},{0,0,4}});
        assertEquals(3,m1.multip(m1.inv()).Frobenius(),0.01);
    }

}