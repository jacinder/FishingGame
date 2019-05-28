import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Fishing{

    private int count = 0;
    private boolean success = true;
    private int rnd;
    private String str = "";
    Timer timer = new Timer();
    Timer timer2 = new Timer(); //각각의 타이머 필요
    public Fishing(){
        count = 0;
        rnd = (int)(Math.random()*5) +1;
    }
    
    TimerTask task = new TimerTask(){
    public void run(){
    
        if(str.equals("")){ //아무것도 입력안하고 ( == 스트링이 이전과 같을경우) 시간초과일경우
            System.out.println( "\n시간 초과로 물고기가 도망갔습니다." );
            System.out.println( "아무키나 입력하여 주세요."); //이것을 하여주지 않으면 55번 라인의 값을 전달하지 않은 이상 넘어가지지 않아서, 유저에서 아무값이 받아서 그걸 처리한다. 핵심 일단, 대기 교수님에게 여줍기
            success= false; // 시간초과로 인한 실패의 경우를 변수에 저장
            timer2.cancel();
            return;
    
        }
    }
    };

    TimerTask task2 = new TimerTask(){
    public void run(){

        if (count < rnd){
            System.out.println(".");
            count++;
        }else{
            System.out.print("!");
        }

    }
    };

    public boolean getInput() throws Exception{


        timer.schedule(task, 6*1000); //입력을 위한

        System.out.println( "낚시를 시작하겠습니다!" );

        timer2.schedule(task2, 3000,500); //표시하기 위한


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        timer2.cancel();
        timer.cancel();

    if(!success){ //시간 초과에 의한 실패 when !success
        System.out.println( "\n타이밍을 놓쳐서 아쉽습니다, 다음에 다시 시도해주세요.");
    }else if(!str.equals("낚시")){ // 아무키나 입력할 경우에도 여기에 포함되지만 위에서 else 구문을 통하여서 걸렀기 때문에 이쪽은 들어가지 않음, 에입력은 하였으나 (==스트링의 변화는 있었으나), 잘못 입력한 경우
        System.out.println("\n잘못된 문자를 입력하여 낚시에 실패하였습니다.");
    }else{ //성공
        System.out.println( "\n낚시에 성공하셨습니다.");

    }
    return success;

    }
}

