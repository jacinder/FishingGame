import java.io.*;
import javax.sound.sampled.*;


public class Sound{
  Clip bgmclip;
  public void playBgm(File file, float vol, boolean repeat){
     try{
       bgmclip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
       bgmclip.open(AudioSystem.getAudioInputStream(file));
       bgmclip.addLineListener(new LineListener() {
         @Override
         public void update(LineEvent event) {
           // TODO Auto-generated method stub
           System.out.println("" + event.getType());
           if(event.getType()==LineEvent.Type.STOP) bgmclip.close();
         }
       });
       FloatControl volume = (FloatControl)bgmclip.getControl(FloatControl.Type.MASTER_GAIN);
       volume.setValue(vol);
       bgmclip.start();
       if(repeat) bgmclip.loop(bgmclip.LOOP_CONTINUOUSLY);
     }catch(Exception e){
             e.printStackTrace();
     }
   }
   public void stopBgm(){
     if(bgmclip!=null && bgmclip.isRunning()){
       bgmclip.stop();
       bgmclip.close();
     }
   }
   public void playSound(File file, float vol, boolean repeat){
     try{
       final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
       clip.open(AudioSystem.getAudioInputStream(file));
       clip.addLineListener(new LineListener() {
         @Override
         public void update(LineEvent event) {
           // TODO Auto-generated method stub
           if(event.getType()==LineEvent.Type.STOP) clip.close();
         }
       });
       FloatControl volume = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
       volume.setValue(vol);
       clip.start();
       if(repeat) clip.loop(Clip.LOOP_CONTINUOUSLY);
     }catch(Exception e){
       e.printStackTrace();
     }
   }
}
