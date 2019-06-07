import java.awt.*;
import javax.swing.*;

public class Print{
	JFrame frame;
	JLabel label;
	String frameName,message;
	
    public Print(String frameName, String message){
    	frame = new JFrame(frameName);
    	label = new JLabel();
    	this.frameName=frameName;
    	this.message=message;
    }
    
    public void play() {
      frame.setLayout(new BorderLayout());
      frame.setLocation(100,100);
      frame.setSize(800,500);

      label.setText("                "+message);
      frame.add(label,BorderLayout.CENTER);
      frame.setVisible(true);
   }
}
