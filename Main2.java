import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
class Main2{
    public static void main(String[] args){
		String userName;
		String fileName= "user.txt";
		Scanner keyboard = new Scanner(System.in);
		Random random = new Random();

		//물고기 배열
		String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
      	//이야기의 시작
		System.out.print("What is your name? >> ");
		userName = keyboard.next();
		//파일 불러오기
		File file = new File(fileName);
		String buffer;
		int money=0;
		int rodLevel=1;
		try{
		Scanner sc = new Scanner(file);
		buffer = sc.nextLine(); //돈
		money = Integer.parseInt(buffer);
		buffer = sc.nextLine(); //로드레벨
		rodLevel = Integer.parseInt(buffer);
		sc.close();
		}//try문 닫는 괄호
		catch(FileNotFoundException e){
		System.out.println("Error occurred while trying to read the file");
		//file.createNewFile();
		}
		User user = new User(userName, money, rodLevel);
		System.out.println("In Handong University Electronic Engineering Department " + userName + "starts fishing to earn money for insufficient tuition. . . .");
		new ButtonThree(user,fishArray);
	}
}
class ButtonThree{
    JButton b1 = new JButton("FISHING");
    JButton b2 = new JButton("SHOP");
	JButton b3 = new JButton("SAVE");
	JButton b4 = new JButton("HELP");
	JButton b5 = new JButton("EXIT");
    
    public ButtonThree(User user, String[] fishArray)
	{
		JFrame menu = new JFrame("Fishing game"); //제목 설정		
		menu.setLayout(new GridLayout(5,1)); //레이아웃 설정
		menu.add(b1);
		menu.add(b2);
		menu.add(b3);
		menu.add(b4);
		menu.add(b5);
		
		menu.setSize(300, 400); //프레임 크기 지정
		menu.setVisible(true); //프레임 보이도록 설정
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X버튼 눌렀을 때 닫히도록 설정
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Example("                 Fishing!!");
			}
		});//b1 ActionListener
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Example("                shop");
				user.setMoney(20000);
			}
		});//b2 ActionListener
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Example("              Saved:)");
				String fileName= "user.txt";
				PrintWriter outputStream= null;
				try{
				outputStream= new PrintWriter(fileName);
				}
				catch(FileNotFoundException f){
				System.out.println("Error opening the file"+ fileName);
				System.exit(0);
				}
				Scanner keyboard= new Scanner(System.in);
				outputStream.println(user.getMoney());
				outputStream.println(user.getRodLevel());
				outputStream.close();

				System.out.println("File's saved");
			}
		});//b3 ActionListener
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Ask();
			}
		});//b4 ActionListener
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});//b5 ActionListener
	}//ButtonThree()
}//ButtonThree
class Example{
    public Example(String message){
        Dimension dim = new Dimension(300,100);
        JFrame frame = new JFrame("fishing game");
        frame.setLocation(300,400);
        frame.setPreferredSize(dim);

        JLabel label = new JLabel();
        label.setText(message);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
    }//Example()
}//Example
class Help{
	public Help(String selected){
		Dimension dim = new Dimension(600,200);
		JLabel label = new JLabel();
		JFrame frame = new JFrame("fishing game");
		JButton button = new JButton("confirm");
		frame.setLocation(300,400);
		frame.setPreferredSize(dim);
		
		int selectHelp = Integer.parseInt(selected);
		if(selectHelp == 1){
            label.setText("<html>Salmon<br/>Flatfish<br/>Squid<br/>Octopus<br/>Minnow<br/>Shrimp<br/>Carp<br/>Tuna<br/>Mackerel<br/>Saury</html>");
		}else if (selectHelp == 2){
			label.setText("<html>Fishing nod lists:<br/>초급<br/>중급<br/>고급<br/></html>");
        }else if (selectHelp == 3){
			label.setText("<html>This program was made by Kiwoong Kim, Narin Kang, Geonha Baek, Goeun Lee, and Hyerim Lee for Java Team Project in 2019 spring semester<br/>This game is also supported by Prof. Ahn in Handong Global University<br/>Any inquries, just Contact us: Handong@hanodong.edu</html>");
        }else{
            System.out.println("\nYou entered wrong option");
		}
		frame.setLayout(new GridLayout(2,1));
		frame.add(label);
		frame.add(button);
		frame.pack();
		frame.setVisible(true);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.dispose();
			}
		});
		
	}
}
class Ask{
	Ask(){
		Dimension dim = new Dimension(600,200);
		JFrame frame = new JFrame("askmenu");
		JButton button = new JButton("ENTER");
		JLabel label = new JLabel();
		JTextField text = new JTextField();

		label.setText("<html>Creator: How can I help you?<br/>1: Show me the fish list<br/>2: Show me the fishing nod list<br/>3: Show me the creators who made this prgoram<br/></html>");
		
		frame.setLocation(300,400);
		frame.setPreferredSize(dim);

		frame.setLayout(new GridLayout(3,1));
		frame.add(label);
		frame.add(text);
		frame.add(button);

		frame.pack();
		frame.setVisible(true);

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Help(text.getText());
				frame.dispose();
			}
		});//button ActionListener
	}
}