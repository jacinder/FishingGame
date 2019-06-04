import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;//
import java.io.*;

class Main2{
	public static int money;
	public static int rodLevel;
	private static String userName;

    public static void main(String[] args){
		String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
		Random random = new Random();
		loadFile file = new loadFile();
		new Intro(fishArray);
		new Fishing(user)
	}
	public static void setUserName(String userName) {
		Main2.userName = userName;
	}
}
class loadFile{
	public loadFile(){
		String fileName= "user.txt";
		File file = new File(fileName);
		String buffer;
		int money=0;
		int rodLevel=1;
		try{
			Scanner sc = new Scanner(file);
			buffer = sc.nextLine();
			Main2.money = Integer.parseInt(buffer);
			buffer = sc.nextLine();
			Main2.rodLevel = Integer.parseInt(buffer);
			sc.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Error occurred while trying to read the file");
		//file.createNewFile();
		}
	}
}
class ButtonMenu{
    JButton b1 = new JButton("FISHING");
    JButton b2 = new JButton("SHOP");
	JButton b3 = new JButton("SAVE");
	JButton b4 = new JButton("HELP");
	JButton b5 = new JButton("EXIT");

    public ButtonMenu(User user, String[] fishArray)
	{
		JFrame menu = new JFrame("Fishing game");
		menu.setLayout(new GridLayout(5,1));
		menu.add(b1);
		menu.add(b2);
		menu.add(b3);
		menu.add(b4);
		menu.add(b5);

		menu.setLocation(100,100);
		menu.setSize(300, 400);
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				MyLabel bar = new MyLabel(20);
				//TabAndThreadEx fishingThread = new TabAndThreadEx(bar);
				//bar.setFrame(fishingThread.fishing);//게임실행되게 하기!!!!!!!!!!!!!!!!
			}
		});//b1 ActionListener
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Shopping(user);
			}
		});//b2 ActionListener
		b3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new SaveFile(user);
			}
		});//b3 ActionListener
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Ask(user);
			}
		});//b4 ActionListener
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});//b5 ActionListener
	}//ButtonThree()
}//ButtonMenu
class Shopping{
	JButton beginner = new JButton("beginner");
	JLabel beginnerPrice = new JLabel("         100000");
	JButton intermediate = new JButton("intermediate");
	JLabel intermediatePrice = new JLabel("         500000");
	JButton advanced = new JButton("advanced");
	JLabel advancedPrice = new JLabel("         1000000");
	public Shopping(User user){
		JFrame shop = new JFrame("SHOP");
		shop.setLayout(new GridLayout(1,3));

		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel1.add(beginner);
		panel1.add(beginnerPrice);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1));
		panel2.add(intermediate);
		panel2.add(intermediatePrice);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(2,1));
		panel3.add(advanced);
		panel3.add(advancedPrice);

		shop.add(panel1);
		shop.add(panel2);
		shop.add(panel3);

		shop.pack();
        shop.setVisible(true);

		beginner.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(user.getMoney()>100000){
					user.setMoney(-100000);
					user.setRodLevel(1);
					new Print("Shopping","Success to buy beginner fishing rod");
				}
				else
					new Print("Shopping","Not enough balance!");
			}
		});
		intermediate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(user.getMoney()>500000){
					user.setMoney(-500000);
					user.setRodLevel(2);
					new Print("Shopping","Success to buy intermediate fishing rod");
				}
				else
					new Print("Shopping","Not enough balance!");
			}
		});
		advanced.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(user.getMoney()>1000000){
					user.setMoney(-1000000);
					user.setRodLevel(3);
					new Print("Shopping","Success to buy advanced fishing rod");
				}
				else
					new Print("Shopping","Not enough balance!");
			}
		});
	}
}
class SaveFile{
	public SaveFile(User user){
		new Print("saveFile","Saved:)");
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
	}
}//SaveFile
class Print{
    public Print(String frameName, String message){
        Dimension dim = new Dimension(300,100);
		JFrame frame = new JFrame(frameName);
		frame.setLayout(new BorderLayout());
        frame.setLocation(300,400);
        frame.setPreferredSize(dim);

        JLabel label = new JLabel();
        label.setText("                "+message);
        frame.add(label,BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
	}//Print()
}//Print
class Help{
	public Help(String selected,User user){
		Dimension dim;
		JLabel label = new JLabel();
		JFrame frame = new JFrame("fishing game");
		JButton button = new JButton("confirm");
		frame.setLocation(300,400);

		int selectHelp = Integer.parseInt(selected);
		if(selectHelp == 1){
			dim = new Dimension(200,400);
            label.setText("<html>| Salmon<br/>| Flatfish<br/>| Squid<br/>| Octopus<br/>| Minnow<br/>| Shrimp<br/>| Carp<br/>| Tuna<br/>| Mackerel<br/>| Saury</html>");
		}else if (selectHelp == 2){
			dim = new Dimension(200,200);
			label.setText("<html>| Fishing nod lists:<br/>| beginner<br/>| intermediate<br/>| advanced<br/></html>");
        }else if (selectHelp == 3){
			dim = new Dimension(700,200);
			label.setText("<html>| This program was made by Kiwoong Kim, Narin Kang, Geonha Baek, Goeun Lee, and Hyerim Lee<br/>| for Java Team Project in 2019 spring semester<br/>| This game is also supported by Prof. Ahn in Handong Global University<br/>| Any inquries, just Contact us: Handong@hanodong.edu</html>");
		}else if (selectHelp == 4){
			dim = new Dimension(200,200);
			label.setText("<html>Your name: "+user.getName()+"<br/>Your money: "+user.getMoney()+"<br/>Your rodLevel: "+user.getRodLevel()+"</html>");
		}else{
			dim = new Dimension(200,200);
            new Print("warining","\nYou entered wrong option");
		}
		frame.setPreferredSize(dim);
		frame.setLayout(new BorderLayout());
		frame.add(label,BorderLayout.CENTER);
		frame.add(button,BorderLayout.SOUTH);
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
	Ask(User user){
		Dimension dim = new Dimension(600,200);
		JFrame frame = new JFrame("askmenu");
		JButton button = new JButton("ENTER");
		JLabel label = new JLabel();
		JTextField text = new JTextField();

		label.setText("<html>| Creator: How can I help you?<br/>| 1: Show me the fish list<br/>| 2: Show me the fishing nod list<br/>| 3: Show me the creators who made this prgoram<br/>| 4:Your current information</html>");

		frame.setLocation(300,400);
		frame.setPreferredSize(dim);

		frame.setLayout(new BorderLayout());
		frame.add(label,BorderLayout.CENTER);
		frame.add(text,BorderLayout.SOUTH);
		frame.add(button,BorderLayout.EAST);

		frame.pack();
		frame.setVisible(true);

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Help(text.getText(),user);
				frame.dispose();
			}
		});//button ActionListener
	}
}
class Intro{
    public Intro(String[] fishArray){
		JFrame frame = new JFrame("Move Label");
		JTextField name = new JTextField();
		JButton enter = new JButton("ENTER");
		MyPanel panel=new MyPanel();

        frame.setTitle("Enter your name");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel,BorderLayout.CENTER);
		frame.add(name,BorderLayout.SOUTH);
		frame.add(enter,BorderLayout.EAST);

		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String userName = name.getText();
				Main2.setUserName(userName);
				User user = new User(userName, Main2.money, Main2.rodLevel);
				//new Print("intro","In Handong University Electronic Engineering Department " + userName + "starts fishing to earn money for insufficient tuition. . . .");
				new ButtonMenu(user,fishArray);
				frame.dispose();
			}
		});
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setLocation(100,100);
        frame.setSize(800,500);
		frame.setVisible(true);
    }

    class MyPanel extends JPanel{
      ImageIcon icon=new ImageIcon("fishing.jpg");
      Image img=icon.getImage();
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


class FishingIntro{
    public FishingIntro(){
		JFrame frame = new JFrame("Fishing");
		JTextField name = new JTextField();
		//JButton enter = new JButton("ENTER");
		MyPanel panel=new MyPanel();

        frame.setTitle("Fishing Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel,BorderLayout.CENTER);
		frame.add(name,BorderLayout.SOUTH);
		//frame.add(enter,BorderLayout.EAST);

		/*enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String userName = name.getText();
				Main2.setUserName(userName);
				User user = new User(userName, Main2.money, Main2.rodLevel);
				//new Print("intro","In Handong University Electronic Engineering Department " + userName + "starts fishing to earn money for insufficient tuition. . . .");
				new ButtonMenu(user,fishArray);
				frame.dispose();
			}
		});*/
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setLocation(100,100);
        frame.setSize(800,500);
		frame.setVisible(true);
    }

    class MyPanel extends JPanel{
      ImageIcon icon=new ImageIcon("sea.jpg");
      Image img=icon.getImage();
      public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			g.setFont(new Font("myFont",Font.BOLD ,50));
			g.setColor(Color.BLACK);
			g.drawString("Fishing Game Start", 200, 70);
			g.setFont(new Font("secondFont",Font.PLAIN,20));
			g.setColor(Color.WHITE);
			g.drawString("Enter the key",10,430);
           }
	}
}


class Fishing{
	Random ran = new Random(); // 유저에게 입력받을 아스키코드를 랜덤으로 발생시키 위한 rnd 객체 선언을 위함.
	private boolean success = true; // 가장 기본적으로 물고기를 잡았는가 못잡았는가?.
	private boolean bite = false; // 입질이 왔는가? ( == 물고기가 물었는가?).
	private int rnd = ran.nextInt(94)+33; // 랜덤넘버를 위한 인트형 변수선언 [입질이 언제오는가를 위한 것인가를 매번 다르게 설정], 정해진 랜덤넘버를 max로 둠
	private int rnd2 = ran.nextInt(6)+1; // ...의 갯수를 랜덤하게 하기 위한
	private int count = 0; // 정해진 랜덤넘버까지 특정문자를 계속해서 출력하여서 물고기가 입질오는 모션을 표현
	private char str = 0; // 이따 특정 문자를 입력받아서 성공/여부를 결정하기 위해 비교할 스트링 변수.
	Timer timer = new Timer(); // 잡는 시간을 제한하기 위해서, 타이머 객체선언.
	Timer timer2 = new Timer(); // 입질이 오는 모션을 표현하기 위해서, 일정 시간 간격을 두고 특정한 문자를 출력. //
	Scanner kb = new Scanner(System.in);


	TimerTask task = new TimerTask(){ // timer 클래스에 들어갈 업무(task) 를 객체 선언해준다.

			public void run(){ // TimerTask 클래스 내에있는 run 메소드를 overriding 해준다.
					if(str == 0){ //이번과 스트링이 같다, 즉 아무것도 입력이 되지 않은 경우(못잡은 경우) 는 크게 두 가지 경우입니다 1)입질이 안 와서 입력을 못한경우 2)시간초과로인하여서 입력을 못한 경우이다.
							if(bite){ // 입질이 왔으나 시간초과로 못잡은경우
									System.out.println( "\n[Fail] The fish has gone over time. " ); // 실패한 내용 출력
									System.out.println( "Input any key for next step"); //이것을 입력하여 준 것은, 62번재줄에 입력해야할 값을 하나도 입력하지 않았기 때문입니다.
									success= false; // 물고기 못잡았기때문에 false로 저장했습니다.
									timer2.cancel(); // 낚시가 끝났으니, timer2.cancel 메소드를 통해서 . 과 ! 의 출력을 멈춥니다.
							}else{ // 첫번째 경우를 제외하면, 아무것도 입력이 되지 않았지만, 못잡은 경우는 낚시대에 입질이 오지 않은 경우 즉, 주어진 시간안에 . . . 만 출력이 된 경우르 를 뜻합니다.
									System.out.println( "[Fail] Any fish doesn't take the bite" ); // 실패한 내용 출력
									System.out.println( "Input any key for next step"); //마찬가지로,62번재줄에 입력해야할 값을 하나도 입력하지 않았기 때문입니다.
									success= false; // 마찬가지로, 실패의 경우를 변수에 저장
									timer2.cancel(); // 마찬가지로, 못잡았기 때문에 false를 저장했습니다.
									}
					}else{ //그 외에는 입력은 되어있으나, 입질이 안오고나서 입력된 경우를 말한다.
							success = false;  //여전히 실패
							System.out.println("here");
							timer2.cancel();

					} //if
			}// void run
	}; //TimerTask

	TimerTask task2 = new TimerTask(){ // TimerTask 클래스 task2로서 객체를 선언하여주고,

			public void run(){ // 마찬가지로 run 메소드를 오버라이딩 한다.

					if (count < rnd2){ // count = 0 이였고, 실행될때마다 랜덤으로 정해진 rnd의 값보다 작을 경우 실행한다,
							System.out.println("."); // 내용 출력 ' . '
							count++; // count 를 1씩 증가시켜주준다 else 구문 ( '! ') 을 출력하기 위함
							bite = false; //입질안옴
					}else{
							System.out.print((char)rnd); // 내용 출력 '!'
							bite = true; //입질옴
					}

			} //void run
	}; //TimerTask

	public boolean getInput() throws Exception{ // 에러체크와 동시에 boolean getInput() 메소드 선언

			timer.schedule(task, 8*1000); // timer.schedule의 메소드를 이용하여 task를 5초뒤에 실행한다! 그리고 바로 아래 실행

			System.out.println( "Fishing begins.." ); // 낚시시작을 알리는 내용 출력
			System.out.println( "\n Input f with Enter key as soon as you see this letter '!' "); // 낚시 방법을 알려줌

			timer2.schedule(task2, 2000,800); // timer2.schedule의 메소드를 이용하여 task를 2초뒤에 실행한다 0.8초간격으로 실행! 그리고 바로 아래 실행

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			str = kb.next().charAt(0); // 한줄 입력받을 것을 대기한다.

			if(count < rnd2){ // timer 클래스가 미리 실행되는 것을 방지하기 위해서 count가 맥스값에 차지 않았을 때, 여전히 타이밍 실패로 만들기 위함 이걸 하지 않으면, 언제 입력하든 항상 성공을 표시 됨
					// 궁금할 경우 이 라인을 제거하고, . . . 구간에 f를 입력해보면 됨

					success = false; // 실패임을 알려줌

			}else if (count == rnd2 && !bite){
					success = false;
			}
			//(timer 클래스의 핵심) task 를 5초뒤에 처리한다고 하더라도, 5초 안에 cancel()메소드 라인에 도달하게 되면 task는 실행하지 않는다.
			// 같은말로서, 위에서 입력 스트림을 통해서 입력을 받았을 경우에 바로 아래로 내려와서 밑에 메소드를 실행하게 된다, 그렇다면 5초 뒤에 실행된다 하더라도 53번 줄에 들어가서 처리할 task는 실행되지 않고 멈춘다.
			timer2.cancel(); // 시간안에 이게 실행되면, task 업무는 처리 안함 timer2.cancel() 가 멈춤!
			timer.cancel(); // timer.cancel() 메소드를 통해서 task 가 계속 출력되는 것을 방지하게 됨!



	if(!success && count <= rnd2 && !bite){ //실패이면서, count <rnd2 일때면서, 입질이 안온경우
			System.out.println( "\n[Fail] You entered too early, please press the key slowly.\n");
	}else if(!success && !bite){ // 실패이면서, 입질이 없었을때 나오는 메시지
			System.out.println("[Fail] that was so close, try again next\n");
	}else if (!success && str == rnd){
			 System.out.println( "\n[Fail] You entered too late, please press the key fast.\n");
	}else if(str != rnd){ // f 가 아닌 문자를 입력하였을 경우
			System.out.println("\n[Fail] You entered with wrong key. please try to enter with key 'f' \n");
	}else{ //성공했을 경우
			System.out.println( "\n[Success] Congratuation! You caught the fish!\n");
			success = true;
	}
	return success; //그 성공여부를 return 하여준다.

	} // getInput()
}
