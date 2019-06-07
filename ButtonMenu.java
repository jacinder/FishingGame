import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class ButtonMenu{
	int rnd;
    char key;
    JButton b1,b2,b3,b4,b5;
    JFrame menu;
    User user;
    Sound s;
    Random ran;
    MyLabel bar;
   

    public ButtonMenu(User user, Sound s){
      b1 = new JButton("FISHING");
      b2 = new JButton("SHOP");
      b3 = new JButton("SAVE");
      b4 = new JButton("HELP");
      b5 = new JButton("EXIT");
      menu = new JFrame("Fishing game");
      this.user = user;
      this.s=s;
    }

    public void play(){
      menu.setLayout(new GridLayout(5,1));

      b1.setBackground(new Color(212,244,250));
      menu.add(b1);
		  b2.setBackground(new Color(228,247,186));
		  menu.add(b2);
		  b3.setBackground(new Color(250,244,192));
		  menu.add(b3);
		  b4.setBackground(new Color(255,217,250));
		  menu.add(b4);
		  b5.setBackground(new Color(232,217,255));
		  menu.add(b5);

		  b1.setOpaque(true); b1.setBorderPainted(false);
		  b2.setOpaque(true); b2.setBorderPainted(false);
		  b3.setOpaque(true); b3.setBorderPainted(false);
		  b4.setOpaque(true); b4.setBorderPainted(false);
		  b5.setOpaque(true); b5.setBorderPainted(false);

      menu.setLocation(100,100);
      menu.setSize(800,500);
      menu.setVisible(true);
      menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      b1.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
              ran = new Random();
              rnd = ran.nextInt(94)+33;
              key = (char)(rnd);
              bar = new MyLabel(20);
              Fishing fishingThread = new Fishing(user,bar,key,s);
              fishingThread.play();
              bar.setFrame(fishingThread.fishing);
          }
      });

      b2.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            Shopping shopping = new Shopping(user, menu);
            shopping.play();
         }
      });

      b3.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            SaveFile saveFile = new SaveFile(user);
            saveFile.play();
         }
      });

      b4.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            Ask ask = new Ask(user);
            ask.play();
         }
      });

      b5.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            System.exit(0);
         }
      });

   }
 }
