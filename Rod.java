class Rod{
    private String rodName;
    private int rodPrice;
    
    
    public int getPrice(){
		return rodPrice;
	}
    
    public int getName(){
        return rodName;
    }
    public void setPrice(int selectRod){
        if(selectRod == 1)
            this.rodPrice = 10000;
        if(selectrod == 2)
            this.rodPrice = 100000;
        if(selectRod == 3)
            this.rodPrice = 1000000;
        
        
    }
    public void setName(int selectRod){
        if(selectRod == 1)
            this.rodName = "초급";
        if(selectrod == 2)
            this.rodName = "중급";
        if(selectRod == 3)
            this.rodname = "고급";
 
    }
    public void messageStore(){
        System.out.println(“상점에 오신 것을 환영합니다.”);
        System.out.println(“어떤 낚시대를 원하십니까?”);
        System.out.println(“1: 초급 낚시대: 10000원);
        System.out.println(“2: 중급 낚시대: 100000원);
        System.out.println(“3: 고급 낚시대: 1000000원);
    }
    
}
