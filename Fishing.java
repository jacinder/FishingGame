import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;//
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
            if(str.equals("")){ //이번과 스트링이 같다, 즉 아무것도 입력이 되지 않은 경우(못잡은 경우) 는 크게 두 가지 경우입니다 1)입질이 안 와서 입력을 못한경우 2)시간초과로인하여서 입력을 못한 경우이다.
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
                timer2.cancel();

            }
        }
    };

    TimerTask task2 = new TimerTask(){ // TimerTask 클래스 task2로서 객체를 선언하여주고,

        public void run(){ // 마찬가지로 run 메소드를 오버라이딩 한다.

            if (count < rnd){ // count = 0 이였고, 실행될때마다 랜덤으로 정해진 rnd의 값보다 작을 경우 실행한다,
                System.out.println("."); // 내용 출력 ' . '
                count++; // count 를 1씩 증가시켜주준다 else 구문 ( '! ') 을 출력하기 위함
                bite = false; //입질안옴
            }else{
                System.out.print("!"); // 내용 출력 '!'
                bite = true; //입질옴
            }

        }
    };

    public boolean getInput() throws Exception{ // 에러체크와 동시에 boolean getInput() 메소드 선언

        timer.schedule(task, 5*1000); // timer.schedule의 메소드를 이용하여 task를 5초뒤에 실행한다! 그리고 바로 아래 실행

        System.out.println( "Fishing begins.." ); // 낚시시작을 알리는 내용 출력
        System.out.println( "\n Input f with Enter key as soon as you see this letter '!' "); // 낚시 방법을 알려줌

        timer2.schedule(task2, 2000,800); // timer2.schedule의 메소드를 이용하여 task를 2초뒤에 실행한다 0.8초간격으로 실행! 그리고 바로 아래 실행

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        str = in.readLine(); // 한줄 입력받을 것을 대기한다.

        if(count < rnd){ // timer 클래스가 미리 실행되는 것을 방지하기 위해서 count가 맥스값에 차지 않았을 때, 여전히 타이밍 실패로 만들기 위함 이걸 하지 않으면, 언제 입력하든 항상 성공을 표시 됨
            // 궁금할 경우 이 라인을 제거하고, . . . 구간에 f를 입력해보면 됨
            success = false; // 실패임을 알려줌

        }

        //(timer 클래스의 핵심) task 를 5초뒤에 처리한다고 하더라도, 5초 안에 cancel()메소드 라인에 도달하게 되면 task는 실행하지 않는다.
        // 같은말로서, 위에서 입력 스트림을 통해서 입력을 받았을 경우에 바로 아래로 내려와서 밑에 메소드를 실행하게 된다, 그렇다면 5초 뒤에 실행된다 하더라도 53번 줄에 들어가서 처리할 task는 실행되지 않고 멈춘다.
        timer2.cancel(); // 시간안에 이게 실행되면, task 업무는 처리 안함 timer2.cancel() 가 멈춤!
        timer.cancel(); // timer.cancel() 메소드를 통해서 task 가 계속 출력되는 것을 방지하게 됨!

    if(!success && count < rnd && bite){ //실패이면서, count <rnd 일때면서, 입질이 안온경우
        System.out.println( "\n[Fail] You entered too early, please press the key slowly.\n");
    }else if(!success && !bite){ // 실패이면서, 입질이 없었을때 나오는 메시지
        System.out.println("[Fail] that was so close, try again next\n");
    }else if(!str.equals("f")){ // f 가 아닌 문자를 입력하였을 경우
        System.out.println("\n[Fail] You entered with wrong key. please try to enter with key 'f' \n");
    }else{ //성공했을 경우
        System.out.println( "\n[Success] Congratuation! You caught the fish!\n");
    }
    return success; //그 성공여부를 return 하여준다.

    }
}




