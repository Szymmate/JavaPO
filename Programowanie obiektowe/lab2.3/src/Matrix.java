import java.util.Random;

public class Matrix {
    private double[]data;
    private int rows;
    private int cols;
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d)
    {
        rows=d.length;
        cols=d[0].length;
        for (int i=0;i<rows;i++){
            if(d[i].length>cols){
                cols=d[i].length;
            }
        }
        data=new double[rows*cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(j<d[i].length){
                    data[cols*i+j]=d[i][j];
                }else {
                    data[cols*i+j]=0;
                }
            }
        }
    }
    double[][] asArray(){
        double[][] a=new double[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                a[i][j]=data[cols*i+j];
            }
        }
        return a;
    }
    void print(){
        for(int i=0;i<rows;i++){
            System.out.printf("%f",data[i*cols]);
            for(int j=1;j<cols;j++){
                System.out.printf(" %f",data[i*cols+j]);
            }
            System.out.printf("\n");
        }
    }
    double get(int r,int c){
        return data[r*cols+c];
    }
    int getrows(){
        return rows;
    }
    int getcols(){
        return cols;
    }
    void set (int r,int c, double value){
        data[r*cols+c]=value;
    }
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<rows;i++){
            buf.append("[");
            for(int j=0;j<cols;j++){
                buf.append(data[i*cols+j]);
                buf.append(',');
            }
            buf.deleteCharAt(buf.length()-1);
            buf.append(']');
        }
        buf.append(']');
        return buf.toString();
    }
    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        rows=newRows;
        cols=newCols;
    }
    int[] shape(){
        int tab[]=new int[2];
        tab[0]=rows;
        tab[1]=cols;
        return tab;
    }
    Matrix add(Matrix m)
    {
        if(m.getcols()!=cols||m.getrows()!=rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be added to %d x %d",rows,cols,m.getrows(),m.getcols()));
        Matrix W=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                W.set(i,j,data[i*cols+j]+m.get(i,j));
            }
        }
        return W;
    }Matrix sub(Matrix m)
    {
        if(m.getcols()!=cols||m.getrows()!=rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be substracted to %d x %d",rows,cols,m.getrows(),m.getcols()));
        Matrix W=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                W.set(i,j,data[i*cols+j]-m.get(i,j));
            }
        }
        return W;
    }
    Matrix mul(Matrix m)
    {
        if(m.getcols()!=cols||m.getrows()!=rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplied by %d x %d",rows,cols,m.getrows(),m.getcols()));
        Matrix W=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                W.set(i,j,data[i*cols+j]*m.get(i,j));
            }
        }
        return W;
    }
    Matrix div(Matrix m)
    {
        if(m.getcols()!=cols||m.getrows()!=rows)
            throw new RuntimeException(String.format("%d x %d matrix can't be divided by %d x %d",rows,cols,m.getrows(),m.getcols()));
        Matrix W=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                W.set(i,j,data[i*cols+j]/m.get(i,j));
            }
        }
        return W;
    }
    double Frobenius(){
        double s=0;
        for(int i=0;i<data.length;i++){
            s=s+data[i]*data[i];
        }
        return s;
    }
    Matrix multip(Matrix m){
        Matrix W=new Matrix(rows,m.getcols());
        if(cols!=m.getrows()){
            throw new RuntimeException(String.format("%d x %d matrix can't be multiplayed by %d x %d",rows,cols,m.getrows(),m.getcols()));
        }
        for(int i=0;i<rows;i++){
            for(int j=0;j<m.getcols();j++){
                W.set(i,j,0);
                for(int k=0;k<cols;k++){
                    W.set(i,j,W.get(i,j)+data[i*cols+k]*m.get(k,j));
                }
            }
        }
        return W;
    }
    public static Matrix random(int rows,int cols){
        Matrix a=new Matrix(rows,cols);
        Random r = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                a.set(i,j,100*r.nextDouble());
            }
        }
        return a;
    }public static Matrix eye(int rows,int cols){
        Matrix a=new Matrix(rows,cols);
        Random r = new Random();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                a.set(i,j,0);
            }
        }
        for(int i=0;i<rows;i++){
            a.set(i,i,1);
        }
        return a;
    }
    void addRows(int i1,int i2,double multip){
        for(int j=0;j<cols;j++){
            set(i1,j,get(i1,j)+multip*get(i2,j));
        }
    }
    void swapRows(int i1,int i2){
        for(int j=0;j<cols;j++){
            double pom=get(i1,j);
            set(i1,j,get(i2,j));
            set(i2,j,pom);
        }
    }
    Matrix inv(){
        if(rows!=cols){
            throw new RuntimeException(String.format("It's not square"));
        }
        Matrix b=eye(rows,cols);
        Matrix a=new Matrix(rows,cols);
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                a.set(i,j,get(i,j));
            }
        }
        int i,j,k;
        for(i=0; i<a.rows; i++) {
            // Find pivot row
            double max = Math.abs(a.get(i,i));
            int pivot = i;
            for (k = i; k < a.rows; k++) {
                if (max < Math.abs(a.get(k,i))) {
                    max = Math.abs(a.get(k,i));
                    pivot = k;
                }
            }
            if (i != pivot) a.swapRows(i, pivot);
            if (i != pivot) b.swapRows(i, pivot);
            if (a.get(i,i) != 1.0) {
                double divby = a.get(i,i);
                if (Math.abs(divby)> 0) {
                    for (j = 0; j < a.rows; j++) {
                        b.set(i,j,b.get(i,j)/divby);
                        a.set(i,j,a.get(i,j)/divby);
                    }
                } else {
                    throw new RuntimeException(String.format("division by zero"));
                }
            }
            for (j = 0; j < a.rows; j++) {
                if (j != i) {
                    if (a.get(j,i) != 0) {
                        double mulby = a.get(j,i);
                        for (k = 0; k < a.rows; k++) {
                            a.set(i,k,a.get(i,k)-a.get(i,k)*mulby);
                            b.set(i,k,b.get(i,k)-b.get(i,k)*mulby);
                        }
                    }
                }
            }
        }

        return b;
    }
}