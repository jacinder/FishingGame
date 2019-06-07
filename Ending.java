import java.awt.*;
import javax.swing.*;


public class Ending{
  JFrame frame;
  MyPanel panel;

  public Ending(){
        frame = new JFrame("Move Label");
        panel=new MyPanel();
    }

    public void play(){
      frame.setTitle("Ending");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.add(panel,BorderLayout.CENTER);
      frame.setLocation(100,100);
      frame.setSize(800,500);
      frame.setVisible(true);
    }

    class MyPanel extends JPanel{
        ImageIcon icon;
        Image img;

        public MyPanel(){
          icon=new ImageIcon("picture/handong.png");
          img=icon.getImage();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            g.setFont(new Font("myFont",Font.BOLD ,50));
            g.setColor(Color.BLACK);
            g.drawString("Handong University", 140, 70);
            g.setFont(new Font("secondFont",Font.PLAIN,20));
            g.setColor(Color.WHITE);
            g.drawString("To school..",10,430);
        }
    }

}
