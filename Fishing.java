import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Fishing{
    private boolean success = true; // 가장 기본적으로 물고기를 잡았는가 못잡았는가?.
    private boolean bite = false; // 입질이 왔는가? ( == 물고기가 물었는가?).
    private int rnd = (int)(Math.random()*5) +1; // 랜덤넘버를 위한 인트형 변수선언 [입질이 언제오는가를 위한 것인가를 매번 다르게 설정], 정해진 랜덤넘버를 max로 둠
    private int count = 0; // 정해진 랜덤넘버까지 특정문자를 계속해서 출력하여서 물고기가 입질오는 모션을 표현
    private String str = ""; // 이따 특정 문자를 입력받아서 성공/여부를 결정하기 위해 비교할 스트링 변수.
    Timer timer = new Timer(); // 잡는 시간을 제한하기 위해서, 타이머 객체선언.
    Timer timer2 = new Timer(); // 입질이 오는 모션을 표현하기 위해서, 일정 시간 간격을 두고 특정한 문자를 출력. //
    
    
    TimerTask task = new TimerTask(){ // timer 클래스에 들어갈 업무(task) 를 객체 선언해준다.
    
        public void run(){ // TimerTask 클래스 내에있는 run 메소드를 overriding 해준다.
            if(str.equals("")&&!bite){ //이번과 스트링이 같다, 즉 아무것도 입력이 되지 않은 경우는 두 가지 경우입니다 1)입질이 안 와서 입력을 못한경우 2)시간초과로인하여서 입력을 못한 경우이다. 하지만 이 경우는, bite가 true 이기 때문에, 입질은 왔으나 시간초과로서 입력을 못한경우에 해당된다.
    
                System.out.println( "\n[실패] 시간 초과로 물고기가 도망갔습니다." ); // 실패한 내용 출력
                System.out.println( "아무키나 입력하여 주세요."); //이것을 입력하여 준 것은,
                success= false; // 실패의 경우를 변수에 저장
                bite = true;
                timer2.cancel();
            }else{
                System.out.println("[실패] 낚시대에 입질이 오지 않았습니다");
                System.out.println( "아무키나 입력하여 주세요.");
                success= false; // 실패의 경우를 변수에 저장
                timer2.cancel();
            }

        }
    };

    TimerTask task2 = new TimerTask(){

        public void run(){

            if (count < rnd){
                System.out.println(".");
                count++;
                bite = true;
            }else{
                System.out.print("!");
                bite = false;
            }

        }
    };

    public boolean getInput() throws Exception{

        timer.schedule(task, 5*1000); //입력을 위한 낚시 총 5초뒤에 출력

        System.out.println( "낚시를 시작하겠습니다!" );
        System.out.println( "\n '!' 가 뜨면, 'f' 를 입력하여 주세요!");

        timer2.schedule(task2, 2000,800); //표시하기 위한 2초뒤에 출력 0.5초당 . 혹은 !를 출력 5초안에 입질이 온다.

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine();

        if(count < rnd){ // timer 클래스가 미리 실행되는 것을 방지하기 위해서 count가 맥스값에 차지 않았을 때, 여전히 타이밍 실패로 만들기 위함
            success = false;

        }


        timer2.cancel();
        timer.cancel();

    if(!success && bite){
        System.out.println("[실패] 아쉽습니다, 다음에 다시시도해주세요.\n");
    }else if(!success && count < rnd){ //시간 초과에 의한 실패 when !success
        System.out.println( "\n[실패] 너무 일찍 입력하셨습니다, 조금만 천천히 눌러주세요..\n");
    }else if(!str.equals("f")){ // 아무키나 입력할 경우에도 여기에 포함되지만 위에서 else 구문을 통하여서 걸렀기 때문에 이쪽은 들어가지 않음, 에입력은 하였으나 (==스트링의 변화는 있었으나), 잘못 입력한 경우
        System.out.println("\n[실패] 잘못된 문자를 입력하여 낚시에 실패하였습니다. 'f' 키를 입력해주세요.\n");
    }else{ //성공
        System.out.println( "\n[성공] 축하합니다! 낚시에 성공하셨습니다.\n");

    }
    return success;

    }
}

