
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Intro{
  JFrame frame;
  JTextField name;
  JButton enter;
  MyPanel panel;
  Sound s;

  public Intro(Sound s){
    frame = new JFrame("Move Label");
    name = new JTextField();
    enter = new JButton("ENTER");
    panel = new MyPanel();
    this.s=s;
  }

  public void play(){
    enter.setBackground(new Color(103,153,255));
	enter.setOpaque(true); enter.setBorderPainted(false);
    frame.setTitle("Enter your name");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.add(panel,BorderLayout.CENTER);
    frame.add(name,BorderLayout.SOUTH);
    frame.add(enter,BorderLayout.EAST);

    enter.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String userName = name.getText();
        Main.setUserName(userName);
        User user = new User(userName, 0, 0);
        ButtonMenu buttonMenu=new ButtonMenu(user, s);
        buttonMenu.play();
        frame.dispose();
      }
    });

    frame.setLocation(100,100);
    frame.setSize(800,500);
    frame.setVisible(true);
    }


    class MyPanel extends JPanel{
      ImageIcon icon;
      Image img;

      public MyPanel(){
        icon=new ImageIcon("picture/fishing.jpg");
        img=icon.getImage();
      }

      public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setFont(new Font("myFont",Font.BOLD ,50));
        g.setColor(Color.BLACK);
        g.drawString("Fishing Game", 200, 70);
        g.setFont(new Font("secondFont",Font.PLAIN,20));
        g.setColor(Color.WHITE);
        g.drawString("Enter your name to start the game",10,430);
      }
   }

}
