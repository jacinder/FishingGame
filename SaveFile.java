import java.io.*;
import java.util.*;


public class SaveFile{
	User user;
	String fileName;
	PrintWriter outputStream;
	
   public SaveFile(User user){
	   this.user=user;
   }
   
   public void play() {
		  Print print=new Print("saveFile","Saved:)");
		  print.play();
		  
	      fileName= "user.txt";
	      outputStream= null;
	      
	      try{
	    	  outputStream= new PrintWriter(fileName);
	      }
	      
	      catch(FileNotFoundException f){
		      System.out.println("Error opening the file"+ fileName);
		      System.exit(0);
	      }
	      Scanner keyboard= new Scanner(System.in);
	      outputStream.println(user.getMoney());
	      outputStream.println(user.getRodLevel());
	      outputStream.close();
	   }
}
