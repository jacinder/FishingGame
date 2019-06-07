import java.io.*;


class Main{
   public static int money;
   public static int rodLevel;
   private static String userName;

   public static void main(String[] args){
      LoadFile loadFile =new LoadFile();
      loadFile.play();
      Sound sound = new Sound();
      sound.playSound(new File("music/UTSS.wav"), 1.0f, true);
      Intro intro = new Intro(sound);
      intro.play();

   }
   public static void setUserName(String userName) {
      Main.userName = userName;
   }
}
