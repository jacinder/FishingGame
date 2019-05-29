public class User{
	private String name;
	private int level;
	private int money;
	private int rodLevel;

	User(String name){
		this.name = name;
		rodLevel = 1;
	}
	public void setMoney(int money){
		this.money += money;
	}
	public int getMoney(){
		return money;
	}
	public void setRodLevel(int rodLevel){
		this.rodLevel = rodLevel;
	}
	public int getRodLevel(){
		return rodLevel;
	}
	public void sell(Fish fish){
		 money += fish.getfishPrice();
	}
    public String getName(){
        return name;
    }
}
