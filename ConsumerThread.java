
public class ConsumerThread extends Thread{
    MyLabel con;
    ConsumerThread(MyLabel con){
        this.con=con;
    }
    public void run(){
        while(true){
            try{
                sleep(500);
                con.consume();
            }
            catch(Exception e){
                return;
            }
        }
    }
}