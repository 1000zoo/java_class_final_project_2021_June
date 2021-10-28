package Agent;

import Constants.Constants;

import java.awt.*;

public abstract class Agent {
    protected int x; //말의 x좌표
    protected int y; //말의 y좌표
    protected int width; //말의 너비
    protected int height;//말의 높이
    protected Image image; //말의 이미지
    protected int balance; //총 보유 잔액
    protected String name;

    public void draw(Graphics g){ //말을 그리기 위한 함수
        g.drawImage(image, x, y, width, height, null);
    }

    //agent 의 위치를 재설정
    public void moveAgent(int x, int y){
        this.x = x;
        this.y = y;
    }
    //visitor 가 owner 에게 돈을 지불
    public void pay(Agent owner, int cost){
        owner.balance += cost;
        this.balance -= cost;
    }
    public String getName(){
        return this.name;
    }

    //한 바퀴를 돌 때마다 실행
    public void reward(){
        this.balance += Constants.REWARD;
    }
    //빈 도시에 갈 때마다 실행
    public void purchase(int cost){
        this.balance -= cost;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getBalance(){
        return balance;
    } //balance 를 리턴
}
