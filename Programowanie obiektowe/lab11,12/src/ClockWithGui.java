import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    ClockWithGui(){
        setOpaque(false);
        new ClockThread().start();
    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);
        AffineTransform saveAT = g2d.getTransform();
        g2d.translate(-10,0);
        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            if(i<=5){
                g2d.translate(10,0);
            }
            if(i==6){
                g2d.translate(5,5);
            }
            Point2D src = new Point2D.Float(0,-220);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
            if(i<=5){
                g2d.translate(-10,0);
            }
            if(i==6){
                g2d.translate(-5,-5);
            }
        }
        g2d.setTransform(saveAT);
        for(int i=1;i<61;i++){
            g2d.rotate(2*Math.PI/60);
            if(i%5==0)
                g2d.drawLine(0,-190,0,-210);
            else
                g2d.drawLine(0,-200,0,-210);

        }
        g2d.rotate(time.getHour()%12*2*Math.PI/12+time.getMinute()*2*Math.PI/60/12);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT);
        g2d.rotate(time.getMinute()*2*Math.PI/60+time.getSecond()*2*Math.PI/60/60);
        g2d.drawLine(0,0,0,-150);
        g2d.setTransform(saveAT);
        g2d.rotate(time.getSecond()*2*Math.PI/60);
        g2d.drawLine(0,0,0,-200);
        g2d.setTransform(saveAT);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}