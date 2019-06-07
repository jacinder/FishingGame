import java.io.*;
import java.util.*;

public class LoadFile{

  private int money,rodLevel;
  private String fileName,buffer;
  private File file;

   public LoadFile(){
      fileName= "user.txt";
      file = new File(fileName);
      buffer = null;
      money=0;
      rodLevel=0;
    }

    public void play(){
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
       }
     }

 }
