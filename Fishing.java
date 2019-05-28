import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Fishing{
    private int count;
    private int number;
    private int rnd;

    public Fishing(){
    	count = 0;
    	rnd = (int)(Math.random()*5) +1;
    }

    Scanner src = new Scanner(System.in);
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
                System.out.println("Succuess");
                m_timer.cancel();
                count ++;

            }else if (number != 1){
                System.out.println("Fail");
                m_timer.cancel();
                count ++;
            }

        }
    };

    // 이 줄이 에러가 나네요ㅠㅠ     m_timer.schedule(m_task(), 1000, 500);
}
