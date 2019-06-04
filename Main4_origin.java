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

class Main4{
   public static int money;
   public static int rodLevel;
   private static String userName;

    public static void main(String[] args){
      String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
      Random random = new Random();
      loadFile file = new loadFile();
      new Intro(fishArray);
   }
   public static void setUserName(String userName) {
      Main4.userName = userName;
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
         Main4.money = Integer.parseInt(buffer);
         buffer = sc.nextLine();
         Main4.rodLevel = Integer.parseInt(buffer);
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
            new FishingIntro(user);
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
            Main4.setUserName(userName);
            User user = new User(userName, Main4.money, Main4.rodLevel);
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

  private String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
  private int randomInt =0;
  private double fishWeight =0.0;
  private int fishPrice =0;
  private Fish fish = new Fish(fishArray[randomInt], fishWeight, fishPrice);
  private Fishing fishing = new Fishing();
    public FishingIntro(User user){
      JFrame frame = new JFrame("Fishing");
      JTextField name = new JTextField();
      JButton enter = new JButton("ENTER");

      frame.setSize(800,500);
      frame.setLayout(null);

      MyPanel panel = new MyPanel();
      frame.add(panel); //처음 게임시작 창 띄우기
      frame.setVisible(true);
      panel.setBounds(0,0,800,500);
      MyPanel2 panel2 = new MyPanel2();
      panel2.setBounds(350,300,100,100);
      //frame.add(panel2);
      frame.setVisible(true);



      /*frame.add(panel2);
      frame.add(panel);
      frame.setVisible(true);*/

      /**/


      //frame.add(panel2);
      //frame.add(panel);

      //frame.setVisible(true);

      /*try
        {
            if(!fishing.getInput()){//낚시 실패하면
                System.out.println("The fish you just missed: " + fishArray[randomInt]);
                System.out.printf("Weight: %.2fkg\n", fishWeight);
                System.out.println("Price: " + fishPrice + " Won");

            }else{ //낚시 성공시
                System.out.println("The fish you just caught: " + fishArray[randomInt]);
                System.out.printf("Wegiht: %.2fkg\n", fishWeight);
                System.out.println("Price: " + fishPrice + " Won");
                user.setMoney(fishPrice);
            }

        }
            catch(Exception e)
        {
            System.out.println(e);
        }
            System.out.println( "The fishing is over!\n" );*/


      //----랜덤일때
    }
    /*public void fishwait(){
      try {
        Thread.sleep(5000);
      }
      catch(InterruptedException e){
        System.out.println(e.getMessage()); //sleep 메소드가 발생하는 InterruptedException
      }
    }*/


    class MyPanel extends JPanel{
      ImageIcon icon=new ImageIcon("sea.jpg");
      Image img=icon.getImage();

      public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
         //g.drawImage(img,0,0,350,450, null);
         g.setFont(new Font("myFont",Font.BOLD ,50));
         g.setColor(Color.BLACK);
         g.drawString("Let's start", 200, 70);
         g.setFont(new Font("secondFont",Font.PLAIN,20));
         g.setColor(Color.WHITE);
         g.drawString("Enter the key",10,100);
      }
   }

   class MyPanel2 extends JPanel{
     ImageIcon icon=new ImageIcon("fishicon2.png");
     Image img=icon.getImage();

     public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0, 100,100, null);
     }
  }

}

/*class waitfish{

  try {
    Thread.sleep(5000);
  }
  catch(InterruptedException e){
    System.out.println(e.getMessage()); //sleep 메소드가 발생하는 InterruptedException
  }
}*/

class Fish{
	private String fishName;
	private int fishPrice;
	private double fishWeight;

	Fish(String fishName, double fishWeight, int fishPrice){
      this.fishName = fishName;
      this.fishWeight = fishWeight;
      this.fishPrice = fishPrice;
   }
   public int getfishPrice(){
      return fishPrice;
   }
}

/*class FishingSetting{
  /*private String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
  private int randomInt =0;
  private double fishWeight =0.0;
  private int fishPrice =0;
  private Fish fish = new Fish(fishArray[randomInt], fishWeight, fishPrice);
  private Fishing fishing = new Fishing();

  FishingSetting(User user){
    randomInt=random.nextInt(fishArray.length -1);
    fishWeight=10 * random.nextDouble() + 1;
    fishPrice=(int)(fishWeight * 10000 * user.getRodLevel());
  }*/
  /*try
    {
        if(!fishing.getInput()){//낚시 실패하면
            System.out.println("The fish you just missed: " + fishArray[randomInt]);
            System.out.printf("Weight: %.2fkg\n", fishWeight);
            System.out.println("Price: " + fishPrice + " Won");

        }else{ //낚시 성공시
            System.out.println("The fish you just caught: " + fishArray[randomInt]);
            System.out.printf("Wegiht: %.2fkg\n", fishWeight);
            System.out.println("Price: " + fishPrice + " Won");
            user.setMoney(fishPrice);
        }

    }
        catch(Exception e)
    {
        System.out.println(e);
    }
        System.out.println( "The fishing is over!\n" );
}*//*

public class Fishing{
    Random ran = new Random(); // 유저에게 입력받을 아스키코드를 랜덤으로 발생시키 위한 rnd 객체 선언을 위함.
    private int rnd2 = ran.nextInt(10); // ...의 갯수를 랜덤하게 하기 위한
    Timer timer = new Timer(); // 잡는 시간을 제한하기 위해서, 타이머 객체선언.


    TimerTask task = new TimerTask(){
        public void run(){

            } //if
        }// void run
    }; //TimerTask



    public void getInput() throws Exception{ // 에러체크와 동시에 boolean getInput() 메소드 선언

        timer.schedule(task, rnd2*1000);


    } // getInput()
} //Fishing*/
