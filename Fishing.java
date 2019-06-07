import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fishing{
    char key;
    String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
    JFrame fishing, Message;
    JLabel text,letter;
    MyLabel bar;
    JButton button, button2;
    Sound s;
    User user;

    public Fishing (User user, MyLabel bar, char key, Sound s){
      fishing = new JFrame();
      Message= new JFrame();
      fishing.setTitle("bar");
      fishing.setLayout(new GridLayout(3,1));
      button = new JButton("Return to home");
      text = new JLabel("<html> Press "+key+" to wind your fishing rod</html>");
      this.bar = bar;
      this.s=s;
      this.user=user;
      this.key=key;
    }

    public void play(){
      text.setFont(text.getFont().deriveFont(30.0f));
      text.setVerticalAlignment(SwingConstants.BOTTOM);
      text.setHorizontalAlignment(SwingConstants.CENTER);
      fishing.add(text);

      Timer timer = new Timer();

      TimerTask task = new TimerTask(){
        public void run(){
                s.stopBgm();
                s.playSound(new File("music/fail.wav"), 1.0f, false);

                fishing.dispose();
                Message.pack();
                Message.setLocation(100,100);
                Message.setSize(800,500);

                letter = new JLabel("fail:(");
                letter.setFont(letter.getFont().deriveFont(25.0f));
                letter.setVerticalAlignment(SwingConstants.CENTER);
                letter.setHorizontalAlignment(SwingConstants.CENTER);

                button2 = new JButton("Return to home");
                Message.add(letter,BorderLayout.CENTER);
                Message.add(button2,BorderLayout.SOUTH);
                Message.setVisible(true);

                button2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Message.dispose();
                }
              });
            }
          };

        timer.schedule(task, 7000);

        bar.setBackground(new Color(212,244,250));
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


        fishing.setVisible(true);
        fishing.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
            }
            @Override
            public void keyPressed(KeyEvent ke) {
                if(ke.getKeyChar() == key)
                    bar.fill(user, timer);
            }
            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        fishing.setLocation(100,100);
        fishing.setSize(800,500);
        fishing.setVisible(true);
        fishing.requestFocus();
        ConsumerThread th = new ConsumerThread(bar);
        th.start();
    }
}

