import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.File;


class Main {
  public static void main(String[] args) {
    String userName;
    String fileName= "user.txt";
    Scanner keyboard = new Scanner(System.in);
    Random random = new Random();

    //물고기 배열
      String[] fishArray = {"Salmon", "Flatfish", "Squid", "Octopus", "Minnow", "Shrimp", "Carp", "Tuna", "Mackerel", "Saury"};
      //이야기의 시작
    //이야기의 시작
	  System.out.print("What is your name? >> ");
    userName = keyboard.next();
    //파일 불러오기
    File file = new File(fileName);
    String buffer;
    int money=0;
    int rodLevel=1;
    try{
      Scanner sc = new Scanner(file);
      buffer = sc.nextLine(); //돈
      money = Integer.parseInt(buffer);
      buffer = sc.nextLine(); //로드레벨
      rodLevel = Integer.parseInt(buffer);
      sc.close();
    }//try문 닫는 괄호
    catch(FileNotFoundException e){
      System.out.println("Error occurred while trying to read the file");
      //file.createNewFile();
    }
    User user = new User(userName, money, rodLevel);
   	System.out.println("In Handong University Electronic Engineering Department " + userName + "starts fishing to earn money for insufficient tuition. . . .");
//콘솔창 clear
//타이틀
    System.out.println("\t The king of Fisherman " + userName + "!");
    System.out.println();
//콘솔창 clear
    boolean gameEND = false;
    int menu;

//반복문 안에 메뉴 선택 스위치문으로
    while(!gameEND){
    	// 메뉴선택
        System.out.println("\t" + userName + "(Current Money: " + user.getMoney() + " | Rod Level: " + user.getRodLevel() + ")");
        System.out.println("\t1. Go Fishing");
        System.out.println("\t2. Go Store");
        System.out.println("\t3. Save");
        System.out.println("\t4. Help");
        System.out.println("\t5. Exit");
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
            System.out.println("The money you have isn't enough");
        }else{
            user.setStore(rod.getPrice()); //파라미터로 넣은 값만큼 현재 금액에서 자동적으로 빼줌
            System.out.println(rod.getName() + " You got new fishing rod!");}
      	break;

      case 3: //저장하기
      	savefile(user);
      	break;

      case 4: //물고기 리스트, 낚시대 리스트 등
        int selectHelp;
        int count = 0;
        System.out.println("Creator: How can I help you?\n");
        System.out.println("1: Show me the fish list");
        System.out.println("2: Show me the fishing nod list");
        System.out.println("3: Show me the creators who made this prgoram");
        selectHelp = keyboard.nextInt();
              
        if(selectHelp == 1){
        
            int length = fishArray.length;
            count = 0;
            
            System.out.println("\nFish list: ");
            
            while(count != length){
                System.out.println(count+1 + ":" + fishArray[count]);
                count++;
            }
        }else if (selectHelp == 2){ //nod 를 array 만들어 놓지 않는 한 이렇게,,
              String name = null;
              Rod rod2 = new Rod();
            
              System.out.println("\nFishing nod list: ");
            
              while(count < 3){
                  rod2.setName(count+1);
                  name = rod2.getName();
                  System.out.println(name);
                  count ++;
              }
            
        }else if (selectHelp == 3){
            System.out.println("\nThis program was made by Kiwoong Kim, Narin Kang, Geonha Baek, Goeun Lee, and Hyerim Lee for Java Team Project in 2019 spring semester\n");
            System.out.println("This game is also supported by Prof. Ahn in Handong Global University\n");
            System.out.println("Any inquries, just Contact us: Handong@hanodong.edu\n");
            
        }else{
            System.out.println("\nYou entered wrong option");
        }
              
        break;
              

      case 5: //게임종료
      	System.out.println("Game End");
      	savefile(user);
      	keyboard.close();
      	System.exit(0);
      	break;
      }//switch문 닫는 괄호
      if(user.getMoney() >= 3000000) gameEND = true;
    }//while문 닫는 괄호


    System.out.println("\n\n The legend of Fishermen " + userName + "fianlly come back to School\n");
    System.out.println("And his story has been told for a long time \n\n\n");
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
    outputStream.println("Name : "+user.getName() +"  Current Money : " +user.getMoney() + "  Level : "+user.getRodLevel());
    outputStream.close();

    System.out.println("File's saved");

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
