import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.*;

class Main{
   public static int money;
   public static int rodLevel;
   private static String userName;

   public static void main(String[] args){
      String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
      Random random = new Random();
      loadFile file = new loadFile();
      Sound s = new Sound();
      s.playSound(new File("UTSS.wav"), 1.0f, true);
      new Intro(fishArray, s);
   }
   public static void setUserName(String userName) {
      Main.userName = userName;
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
         Main.money = Integer.parseInt(buffer);
         buffer = sc.nextLine();
         Main.rodLevel = Integer.parseInt(buffer);
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

    public ButtonMenu(User user, String[] fishArray, Sound s)
   {
      JFrame menu = new JFrame("Fishing game");
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
              Random ran = new Random();
              int rnd = ran.nextInt(94)+33;
              char key = (char)(rnd);
              MyLabel bar = new MyLabel(20);
              Fishing fishingThread = new Fishing(user,fishArray,bar,key,rnd,s);
              bar.setFrame(fishingThread.fishing);
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
   JLabel beginnerPrice = new JLabel("                 100000");
   JButton intermediate = new JButton("intermediate");
   JLabel intermediatePrice = new JLabel("                 500000");
   JButton advanced = new JButton("advanced");
   JLabel advancedPrice = new JLabel("                 1000000");
   JButton tuition = new JButton("Tuition");
   JLabel tuitionPrice = new JLabel("                 3000000");
   public Shopping(User user){
      JFrame shop = new JFrame("SHOP");
      shop.setLocation(100,100);
      shop.setSize(800,500);
      shop.setLayout(new GridLayout(1,4));

      beginner.setBackground(new Color(92,209,229));
      beginner.setOpaque(true); beginner.setBorderPainted(false);
      intermediate.setBackground(new Color(103,153,255));
      intermediate.setOpaque(true); intermediate.setBorderPainted(false);
      advanced.setBackground(new Color(107,102,255));
      advanced.setOpaque(true); advanced.setBorderPainted(false);
      tuition.setBackground(new Color(200,200,255));
      tuition.setOpaque(true); tuition.setBorderPainted(false);



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
      JPanel panel4 = new JPanel();
      panel4.setLayout(new GridLayout(2,1));
      panel4.add(tuition);
      panel4.add(tuitionPrice);

      shop.add(panel1);
      shop.add(panel2);
      shop.add(panel3);
      shop.add(panel4);

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
       tuition.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               if(user.getMoney()>3000000){
                   user.setMoney(-3000000);

                   new Print("Shopping","Success to tuition for Uni");
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
      JFrame frame = new JFrame(frameName);
      frame.setLayout(new BorderLayout());
      frame.setLocation(100,100);
      frame.setSize(800,500);

      JLabel label = new JLabel();
      label.setText("                "+message);
      frame.add(label,BorderLayout.CENTER);
      frame.setVisible(true);
   }//Print()
}//Print
class Help{
   public Help(String selected,User user){
      Dimension dim;
      JLabel label = new JLabel();
      JFrame frame = new JFrame("fishing game");
      JButton button = new JButton("confirm");
      frame.setLocation(100,100);
      frame.setSize(800,500);

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
class Ask{
   Ask(User user){
      JFrame frame = new JFrame("askmenu");
      JButton button = new JButton("ENTER");
      JLabel label = new JLabel();
      JTextField text = new JTextField();

      label.setText("<html>| Creator: How can I help you?<br/>| 1: Show me the fish list<br/>| 2: Show me the fishing nod list<br/>| 3: Show me the creators who made this prgoram<br/>| 4:Your current information</html>");

      frame.setLocation(100,100);
      frame.setSize(800,500);

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
    public Intro(String[] fishArray, Sound s){
      JFrame frame = new JFrame("Move Label");
      JTextField name = new JTextField();
      JButton enter = new JButton("ENTER");
      MyPanel panel=new MyPanel();

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
            User user = new User(userName, Main.money, Main.rodLevel);
            //new Print("intro","In Handong University Electronic Engineering Department " + userName + "starts fishing to earn money for insufficient tuition. . . .");
            new ButtonMenu(user,fishArray, s);
            frame.dispose();
         }
      });
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
  //private Fishing fishing = new Fishing();
    public FishingIntro(User user){
      JFrame frame = new JFrame("Fishing");
      JTextField name = new JTextField();
      JButton enter = new JButton("ENTER");

      frame.setLocation(100,100);
      frame.setSize(800,500);
      frame.setLayout(null);

      MyPanel panel = new MyPanel();
      frame.add(panel);
      frame.setVisible(true);
      panel.setBounds(0,0,800,500);
      MyPanel2 panel2 = new MyPanel2();
      panel2.setBounds(350,300,100,100);
      //frame.add(panel2);
      frame.setVisible(true);
    }
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

class Fishing{
    JFrame fishing = new JFrame();
    MyLabel bar;
    JLabel letter = new JLabel("        click");
    JButton button = new JButton("Return to home");
    char key;

    Fishing (User user, String [] fishArray, MyLabel bar, char key, int rnd, Sound s){

        this.bar = bar;
        fishing.setTitle("bar");
        //this.setDefaultCloseOperation(TabAndThreadEx.EXIT_ON_CLOSE);
        fishing.setLayout(new GridLayout(3,1));

        JLabel text = new JLabel("<html> Press "+key+" to wind your fishing rod</html>");
        text.setFont(text.getFont().deriveFont(30.0f));
        text.setVerticalAlignment(SwingConstants.BOTTOM);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        fishing.add(text);
        Timer timer = new Timer();
        JFrame Message= new JFrame();
        TimerTask task = new TimerTask(){
            public void run(){
                s.stopBgm();
                s.playSound(new File("fail.wav"), 1.0f, false);
                //fail message
                fishing.dispose();
                Message.pack();
                Message.setLocation(100,100);
                Message.setSize(800,500);
                //Message.setLayout();
                JLabel letter2 = new JLabel("fail:(");
                letter2.setFont(letter2.getFont().deriveFont(25.0f));
                letter2.setVerticalAlignment(SwingConstants.CENTER);
                letter2.setHorizontalAlignment(SwingConstants.CENTER);
                JButton button = new JButton("Return to home");
                Message.add(letter2,BorderLayout.CENTER);
                Message.add(button,BorderLayout.SOUTH);
                Message.setVisible(true);
                button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Message.dispose();
                }
              });
            }
        };

        timer.schedule(task, 10000);


        bar.setBackground(Color.ORANGE);
        bar.setOpaque(true);
        bar.setLocation(20, 50);
        bar.setSize(300,20);
        fishing.add(bar);
        fishing.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fishing.dispose();
            }
        });

        //fishing.pack();
        fishing.setVisible(true);
        fishing.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyChar() == key)
                    bar.fill(user, fishArray, rnd, timer);
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        //fishing.setLocationRelativeTo(null);
        fishing.setLocation(100,100);
        fishing.setSize(800,500);
        fishing.setVisible(true);
        fishing.requestFocus();
        ConsumerThread th = new ConsumerThread(bar);
        th.start();
    }
}

class MyLabel extends JLabel{
    int barSize=0;
    int maxBarSize;
    JFrame fishingFrame;
    JButton button;
    JFrame Message = new JFrame();
    MyLabel(int maxBarSize){
        this.maxBarSize=maxBarSize;
    }
    public void setFrame(JFrame frame){
        this.fishingFrame = frame;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        int width =(int)(((double)(this.getWidth()))/maxBarSize*barSize);
        if(width==0) return;
        g.fillRect(0,0,width,this.getHeight());
    }
    synchronized void fill(User user, String[] fishArray, int rnd, Timer timer){

        if(barSize==maxBarSize){
          fishingFrame.dispose();

            try{
                timer.cancel();
                user.setMoney (rnd*10000);
                Message.pack();
                Message.setLocation(100,100);
                Message.setSize(800,500);
                JLabel letter = new JLabel("Success:D \n The fish you just caught: " + fishArray[rnd%10]);
                letter.setFont(letter.getFont().deriveFont(25.0f));
                letter.setVerticalAlignment(SwingConstants.CENTER);
                letter.setHorizontalAlignment(SwingConstants.CENTER);
                button = new JButton("Return to home");
                Message.add(letter,BorderLayout.CENTER);
                Message.add(button,BorderLayout.SOUTH);
                Message.setVisible(true);
              }
            catch(Exception e){
              System.out.println(e);
            }
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Message.dispose();
                }
            });
          }
        barSize++;
        this.repaint();
        this.notify();
    }
    synchronized void consume(){
        if(barSize==0){
            try{
                this.wait();
            }
            catch(Exception e){
                return;
            }
        }
        barSize--;
        this.repaint();
        this.notify();
    }
}

class ConsumerThread extends Thread{
    MyLabel con;
    ConsumerThread(MyLabel con){
        this.con=con;
    }
    public void run(){
        while(true){
            try{
                sleep(500);
                con.consume();
            }
            catch(Exception e){
                return;
            }
        }
    }
}
