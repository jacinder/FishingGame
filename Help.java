import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Help{
	int selectHelp;
	String selected;
    Dimension dim;
	JLabel label;
    JFrame frame;
    JButton button;
    User user;
  
	
   public Help(String selected,User user){
	   frame = new JFrame("fishing game");
	   label = new JLabel();
	   button = new JButton("confirm");
	   this.selected=selected;
	   this.user=user;
   }
   
   public void play() {
      frame.setLocation(100,100);
      frame.setSize(800,500);

      selectHelp = Integer.parseInt(selected);
      
      if(selectHelp == 1){
         dim = new Dimension(200,400);
            label.setText("<html>| Salmon<br/>| Flatfish<br/>| Squid<br/>| Octopus<br/>| Minnow<br/>| Shrimp<br/>| Carp<br/>| Tuna<br/>| Mackerel<br/>| Saury</html>");
      }
      else if (selectHelp == 2){
         dim = new Dimension(200,200);
         label.setText("<html>| Fishing nod lists:<br/>| beginner<br/>| intermediate<br/>| advanced<br/></html>");
        }
      else if (selectHelp == 3){
         dim = new Dimension(700,200);
         label.setText("<html>| This program was made by Kiwoong Kim, Narin Kang, Geonha Baek, Goeun Lee, and Hyerim Lee<br/>| for Java Team Project in 2019 spring semester<br/>| This game is also supported by Prof. Ahn in Handong Global University<br/>| Any inquries, just Contact us: Handong@hanodong.edu</html>");
      }
      else if (selectHelp == 4){
         dim = new Dimension(200,200);
         label.setText("<html>Your name: "+user.getName()+"<br/>Your money: "+user.getMoney()+"<br/>Your rodLevel: "+user.getRodLevel()+"</html>");
      }
      else{
         dim = new Dimension(200,200);
            new Print("warining","\nYou entered wrong option");
      }
      
      frame.setLayout(new BorderLayout());
      frame.add(label,BorderLayout.CENTER);
      frame.add(button,BorderLayout.SOUTH);
      frame.setVisible(true);
      
      button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            frame.dispose();
         }
      });

   }
}
