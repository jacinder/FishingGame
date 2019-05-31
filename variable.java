class variable {
    public interest(User user){
        System.out.println(user+"은 현재 학자금 대출이 있습니다.");
        System.out.println("매달 10000원의 이자를 내야합니다.");
        money=user.getMoney()-10000;
        user.setMoney(money);
    }//interest
}//class variable