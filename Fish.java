public class Fish(){
   private String fishName;
   private int fishPrice;
   private double fishWeight;

   Fish(String fishName,double fishWeight){
      this.fishName = fishName;
      this.fishWeight = fishWeight;
   }
   public int getfishPrice(){
      return fishPrice;
   }
   public void sell(User user){
      user.setMoney(user.getMoney() + fishPrice);
   }
}
