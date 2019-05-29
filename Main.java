import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

class Main {
  public static void main(String[] args) {
    String userName;
    String fileName= "user.txt";
    Scanner keyboard = new Scanner(System.in);
    Random random = new Random();

    //물고기 배열
    String[] fishArray = {"연어", "광어", "오징어", "문어", "피라미", "새우", "붕어", "참치", "고등어", "꽁치"};
    //이야기의 시작
	System.out.print("당신의 이름은? >> ");
    userName = keyboard.next();

    /*
    //파일 불러오기
    File file = new File(fileName);
    try{
      Scanner sc = new Scanner(file);
      int money = sc.nextInt(); //돈
      int rodLevel = sc.nextInt(); //로드레벨
      sc.close();
    }//try문 닫는 괄호
    catch(FileNotFoundException e){
      System.out.println("파일을 읽어오는 도중에 오류가 발생했습니다");
      file.createNewFile();
    }

    User user = new User(name, money, rodLevel);

   	System.out.println("한동대학교 전산전자공학부 " + userName + "은 부족한 학비를 모으기 위해 낚시를 시작하게 되는데 . . .");
// delay 후 콘솔창 clear

     */

    System.out.println("한동대학교 전산전자공학부 " + userName + "은 부족한 학비를 모으기 위해 낚시를 시작하게 되는데 . . .\n\n");





//콘솔창 clear
//타이틀
    System.out.println("\t낚시왕 " + userName + "!");
    System.out.println();
//콘솔창 clear

    User user = new User(userName);
    boolean gameEND = false;
    int menu;

//반복문 안에 메뉴 선택 스위치문으로
    while(!gameEND){
    	// 메뉴선택
      System.out.println("\t" + userName + "(돈: " + user.getMoney() + " | 낚싯대 레벨: " + user.getRodLevel() + ")");
      System.out.println("\t1. 낚시하러 가기");
      System.out.println("\t2. 상점 가기");
      System.out.println("\t3. 저장하기");
      System.out.println("\t4. 종료하기");
      System.out.print("\t>> ");

      menu = keyboard.nextInt();
      // 콘솔창 clear
      switch(menu){
      case 1: // 낚시하러 수정해야할 것: 초반에 돈이 없어서 프라이스 가격이 안만들어지네요ㅜ
      //Fish fish = new Fish(랜덤한 물고기 이름, 랜덤한 물고기 무게);
        int randomInt = random.nextInt(fishArray.length -1);
        double fishWeight = 10 * random.nextDouble() + 1;
        int fishPrice = (int)(fishWeight * 10000 * user.getRodLevel());
        Fish fish = new Fish(fishArray[randomInt], fishWeight, fishPrice);
        Fishing fishing = new Fishing();

        try
          {
              if(!fishing.getInput()){//낚시 실패하면
                  System.out.println("놓친 어종: " + fishArray[randomInt]);
                  System.out.printf("무게: %.2fkg\n", fishWeight);
                  System.out.println("가격: " + fishPrice + "원");

              }else{ //낚시 성공시
                  System.out.println("잡힌 어종: " + fishArray[randomInt]);
                  System.out.printf("무게: %.2fkg\n", fishWeight);
                  System.out.println("가격: " + fishPrice + "원");
                  user.setMoney(fishPrice);
              }

          }
              catch(Exception e)
          {
              System.out.println(e);
          }
              System.out.println( "낚시가 끝났습니다!\n" );





        break;

      case 2: // 상점으로  유저의 돈을 파라미터로 받아서 계산하는 buy 메소드 추가해야해야할 것 같음

      	Rod rod = new Rod ();
      	rod.messageStore();
      	Scanner key = new Scanner(System.in);
		int selectRod = key.nextInt();
        rod.setName(selectRod);
        rod.setPrice(selectRod);
      	int currentMoney= user.getMoney() - rod.getPrice();
        if(currentMoney <0){
            System.out.println("돈이 부족합니다.");
        }else{
            user.setMoney(currentMoney);
            System.out.println(rod.getName() + " 낚시대를 구매하셨습니다");}
      	break;

      case 3: //저장하기
      	savefile(user);
      	break;

      case 4: //게임종료
      	System.out.println("게임을 종료합니다");
      	savefile(user);
      	keyboard.close();
      	System.exit(0);
      	break;
      }//switch문 닫는 괄호
      if(user.getMoney() >= 3000000) gameEND = true;
    }//while문 닫는 괄호


    System.out.println("\n\n부족한 학비를 모으기 위해 낚시를 시작한 " + userName + "은 학비를 다 모은 후 학교로 돌아가고. . .\n");
    System.out.println("그렇게 낚시계의 전설로 남게되었다. . .\n\n\n");
    System.out.println("\tT  H  E\n");
    System.out.println("\tE  N  D");


  }//main함수 닫는 괄호

  public void fishing(){ //낚시하기

  }//fishing 함수 닫는 괄호

  public void shop(){//상점

  }//shop 함수 닫는 괄호

  public static void savefile(User user){//user정보 저장
	String fileName= "user.txt";
    PrintWriter outputStream= null;
	try{
      outputStream= new PrintWriter(fileName);
    }
    catch(FileNotFoundException e){
      System.out.println("Error opening the file"+ fileName);
      System.exit(0);
    }
    Scanner keyboard= new Scanner(System.in);
    outputStream.println("이름 : "user.getName() +"  돈 : " +user.getMoney() + "  레벨 : "+user.getRodLevel());
    outputStream.close();

    System.out.println("저장 완료");

  }//savefile 함수 닫는 괄호

}//class Main 닫는 괄호














/*

//<User가 여러명일때>

//Scanner inputStream= null;
try{
inputStream= newScanner(newFile(fileName));
}
catch(FileNotFoundExceptione){
System.out.println("Error opening the file "+ fileName);
System.exit(0);
}
while(inputStream.hasNextLine()){
String line= inputStream.nextLine();
if(line==userName){
	System.out.println("이전의 기록된 사용자가 있습니다. 이어서 하시겠습니까?(1.예 2.아니오)");
	int n=keyboard.nextInt();
	if(n==1){
이름 돈 낚싯대 레벨
}
}//if
}//while문
inputStream.close();

while()
String name=sc.next();
if(userName==name)//그대로이어서
없으면 새로 만들기

< user.txt >
user
0
0

*/
