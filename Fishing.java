import java.util.Sanner;
import java.util.Timer;
import java.util.TimerTask;

public class Fishing{
    private int count;
    private int number;
    private int rnd;
    
    int rnd = (int)(Math.random()*5) +1;
    
    Scanner src = new Scanner(System.in);
    count = 0;
    Timer m_timer = new Timer();
    TimerTask m_task = new TimerTask(){
    
        public void run(){
            if(count < rnd){
                System.out.println(".");
                count ++;
    
            }else if (count  == rnd){
                System.out.println("!!!");
                number = src.nextInt();
                count ++;

            }else if (number == 1){
                System.out.println("Seccuess");
                m_timer.cancel();
                count ++;

            }else if (number != 1){
                System.out.println("Fail");
                m_timer.cancel();
                count ++;
            }

        }
    };

    m_timer.schedule(m_task, 1000,500);
}

