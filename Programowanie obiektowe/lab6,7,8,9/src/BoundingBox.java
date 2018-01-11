public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    BoundingBox(double xx,double yx,double xn,double yn){
        xmin=xn;
        ymin=yn;
        xmax=xx;
        ymax=yx;
    }
    BoundingBox(){
        xmin=0;
        ymin=0;
        xmax=0;
        ymax=0;
    }
    public String toString(){
        StringBuilder a=new StringBuilder();
        a.append(xmin).append(" ").append(" ").append(xmax).append(" ").append(ymin).append(" ").append(ymax).append(" ");
        return a.toString();
    }
    void addPoint(double x, double y){
        if (xmin > x) {
            xmin = x;
        }
        if (ymin > y) {
            ymin = y;
        }
        if (xmax < x) {
            xmax = x;
        }
        if (ymax < y) {
            ymax = y;
        }
    }
    boolean contains(double x, double y){
        if (x<xmax&&x>xmin&&y<ymax&&y>ymin) {
            return true;
        }
        return false;
    }
    boolean contains(BoundingBox bb){
        if(bb.xmin>xmin&&bb.xmax<xmax&&bb.ymin>ymin&&bb.ymax<ymax) {
            return true;
        }
        return false;
    }
    boolean intersects(BoundingBox bb){
        if((contains(bb.ymin,bb.xmin)||contains(bb.ymax,bb.xmin)||contains(bb.ymin,bb.xmax)||contains(bb.ymax,bb.xmax))&&!contains(bb)) {
            return true;
        }
        return false;
    }
    BoundingBox add(BoundingBox bb){
        if (xmin > bb.xmin) {
            xmin = bb.xmin;
        }
        if (ymin > bb.ymin) {
            ymin = bb.ymin;
        }
        if (xmax < bb.xmax) {
            xmax = bb.xmax;
        }
        if (ymax < bb.ymax) {
            ymax = bb.ymax;
        }
        return this;
    }
    boolean isEmpty(){
        if(xmin==Double.NaN||xmax==Double.NaN&&ymin==Double.NaN&&ymax==Double.NaN) {
            return true;
        }
        else{
            return false;
        }
    }
    double getCenterX(){
        if(!isEmpty()){
            return (xmax+xmin)/2;
        }
        throw new RuntimeException("empty");
    }
    double getCenterY(){
        if(!isEmpty()){
            return (ymin+ymax)/2;
        }
        throw new RuntimeException("empty");
    }
    double distanceTo(BoundingBox bbx){
        if(isEmpty()||bbx.isEmpty()){
            throw new RuntimeException("one is empty");
        }
        return Math.sqrt((getCenterX()-bbx.getCenterX())*(getCenterX()-bbx.getCenterX())+(getCenterY()-bbx.getCenterY())*(getCenterY()-bbx.getCenterY()));
    }


}
