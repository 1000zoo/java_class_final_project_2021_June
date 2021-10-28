package Board;

import Constants.Constants;
import Agent.*;
import City.City;
import Dice.Dice;
import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BlueMarbleBoard {
    private int width; //게임 판의 총 너비
    private int height;//게임 판의 총 높이
    private int playerIndex;
    private int computerIndex;
    private int computerIslandCount;//무인도에 몇 턴 머물렀는지 세주는 변수
    private Tile[][] tileMap;//그리기 위한 타일 객체
    private Tile[] tileRoad;//실제 게임속에서 사용하기 위한 객체
    private Agent computer;
    private Agent player;
    private Dice dice;
    private City[] cityList;

    public int cityCount = Constants.CITY_BUYABLE;

    public BlueMarbleBoard() {
        width = Constants.BOARD_WIDTH; //게임 판의 총 너비 설정
        height = Constants.BOARD_HEIGHT;//게임 판의 총 높이 설정
        tileMap = new Tile[width][height];//각 칸들을 생성
        tileRoad = new Tile[Constants.CITY_SIZE];//사용되는 칸들의 배열 생성
        playerIndex = 0;
        computerIndex = 0;
        computerIslandCount = 0;
        setCityList();//도시들 세팅.
        setTile(); //칸들을 세팅.

        //컴퓨터와 플레이어 객체 생성
        computer = new Computer(
                Constants.COMPUTER_X,
                Constants.COMPUTER_Y,
                Constants.AGENT_WIDTH,
                Constants.AGENT_HEIGHT,
                getAgentImage(Constants.COMPUTER_FILE_NAME),
                Constants.MAX_BALANCE);
        player = new Player(
                Constants.PLAYER_X,
                Constants.PLAYER_Y,
                Constants.AGENT_WIDTH,
                Constants.AGENT_HEIGHT,
                getAgentImage(Constants.PLAYER_FILE_NAME),
                Constants.MAX_BALANCE);

        dice = new Dice();
    }

    //Plan B : Tile 을 하나 더 생성하지말고,
    //int 형 배열을 이용하는 방식으로 생각해 보자.

    //각 칸들의 객체 변수들을 초기화 해주는 메소드.
    //각 칸들이 그려질 위치를 x, y 좌표 값으로 넣어줌.
    //각 칸들의 도시명도 넣어줌.
    private void setTile() {
        int index = 0;
        int x = Constants.TILE_X;
        int y = Constants.TILE_Y;
        int noneX = Constants.TILE_X + Constants.TILE_WIDTH;
        int noneY = Constants.TILE_Y + Constants.TILE_HEIGHT;

        //"시작"칸 부터 첫 번째 무인도 칸 전까지.
        for (int i = 0; i < Constants.BOARD_WIDTH - 1; i++) {
            tileMap[0][i] = new Tile(x, y, cityList[index]);
            tileRoad[index++] = new Tile(tileMap[0][i]);
            x += Constants.TILE_WIDTH;
        }
        //첫 번째 무인도 칸 부터 두 번째 무인도 칸 까지.
        for (int i = 0; i < Constants.BOARD_HEIGHT - 1; i++) {
            tileMap[i][Constants.BOARD_WIDTH - 1] = new Tile(x, y,
                    cityList[index]);
            tileRoad[index++] =
                    new Tile(tileMap[i][Constants.BOARD_WIDTH - 1]);
            y += Constants.TILE_HEIGHT;
        }
        //두 번째 무인도 칸 부터 세 번째 무인도 칸 까지.
        for (int i = Constants.BOARD_WIDTH - 1; i > 0; i--) {
            tileMap[Constants.BOARD_HEIGHT - 1][i] = new Tile(x, y,
                    cityList[index]);
            tileRoad[index++] =
                    new Tile(tileMap[Constants.BOARD_WIDTH - 1][i]);
            x -= Constants.TILE_WIDTH;
        }
        //세 번째 무인도 칸 부터 시작 칸 까지.
        for (int i = Constants.BOARD_HEIGHT - 1; i > 0; i--) {
            tileMap[i][0] = new Tile(x, y, cityList[index]);
            tileRoad[index++] =
                    new Tile(tileMap[i][0]);
            y -= Constants.TILE_HEIGHT;
        }

        //나머지 칸들을 none 타입으로 취급해줌.
        for (int i = 1; i < Constants.BOARD_HEIGHT - 1; i++) {
            for (int j = 1; j < Constants.BOARD_WIDTH - 1; j++) {
                tileMap[i][j] = new Tile(noneX, noneY, cityList[index]);
                noneX += Constants.TILE_WIDTH;
            }
            noneX = Constants.TILE_X + Constants.TILE_WIDTH;
            noneY += Constants.TILE_HEIGHT;
        }
    }

    //컴퓨터의 잔액 반환.
    public int getComputerBalance() {
        return computer.getBalance();
    }

    //플레이어의 잔액 반환
    public int getPlayerBalance() {
        return player.getBalance();
    }

    //보드의 칸들을 그리는 메소드.
    //TILE_TYPE none 이 아닐 때만 타일의 이름을 표시.
    public void drawBoard(Graphics g) {
        for (int col = 0; col < Constants.BOARD_WIDTH; col++) {
            for (int row = 0; row < Constants.BOARD_HEIGHT; row++) {
                Tile tile = tileMap[row][col];
                if (tile != null) {
                    g.drawImage(getTileImage(tile.getTileType()), tile.getX(),
                            tile.getY(),
                            Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
                    if (!tile.getTileType().equals(Constants.TILE_TYPE.none)){
                        g.drawString(tile.getCity().getCityName(),
                                tile.getX() + Constants.TILE_TEXT_X,
                                tile.getY() + Constants.TILE_TEXT_Y);
                    }
                }
            }
        }
        //칸의 도시 소유자 색 덮이기
        for(int i = 0; i < Constants.CITY_SIZE; i++){
            if(tileRoad[i].getTileType() == Constants.TILE_TYPE.player ||
                    tileRoad[i].getTileType() == Constants.TILE_TYPE.computer){
                g.drawImage(getTileImage(tileRoad[i].getTileType()),
                        tileRoad[i].getX(),
                        tileRoad[i].getY(), Constants.TILE_WIDTH,
                        Constants.TILE_HEIGHT, null);
            }
        }
    }

    //컴퓨터는 사용자가 주사위를 던진 후 랜덤으로 이동하므로
    //사용자가 주사위를 굴리면 그 후 바로 컴퓨터가 주사위를
    //던지는 동작을 하도록 한다.
    public void afterRoll(Graphics g) {
        //플레이어 차례
        playerTurn(g);

        //플레이어가 주사위를 던진 후 1초간 딜레이
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //컴퓨터 차례
        //1/2 확률로 구매하는 것 외에는
        //플레이어의 동작과 동일
        //컴퓨터는 플레이어와 다르게
        //무인도에 도착했을 경우
        //플레이어가 주사위를 한 번 더 던져야 하므로
        //무인도에 갇혀있는 턴을 카운트 하는 변수 생성
        if(computerIslandCount > 0){
            computerIslandCount--;
        } else{
            computerTurn(g);
        }
    }

    //플레이어, 컴퓨터의 위치를 옮겨주는 메소드.
    //x값은 아이콘의 초기 x값 + 해당 칸의 x값
    //y값은 해당 칸의 y값이 칸의 크기만큼 올라가 있어서 빼줌
    public void movePlayer(){
        player.moveAgent(Constants.PLAYER_X + tileRoad[playerIndex].getX(),
                Constants.PLAYER_Y + tileRoad[playerIndex].getY() - Constants.TILE_Y);
    }
    public void moveComputer() {
        computer.moveAgent(Constants.COMPUTER_X + tileRoad[computerIndex].getX(),
                Constants.COMPUTER_Y + tileRoad[computerIndex].getY() - Constants.TILE_Y);
    }

    public void playerTurn(Graphics g){
        playerIndex += dice.getCurrentNum();
        //playerIndex 가 24를 넘지 못하게
        //시작지점을 넘으면 reward 메소드를 통해
        //돈을 지급
        if (playerIndex >= Constants.CITY_SIZE) {
            playerIndex -= Constants.CITY_SIZE;
            player.reward();
        }
        movePlayer();
        //플레이어가 밟은 칸의 type 이 city 라면,
        //플레이어의 보유 금액에서 city 의 금액만큼 차감
        //cityList 와 tileRoad 에서 해당 칸의 type 을 변경
        //도시를 소유하게 되면 게임의 끝을 알리기 위해
        //cityCount 를 1씩 감소
        if (tileRoad[playerIndex].getTileType() == Constants.TILE_TYPE.city) {
            player.purchase(cityList[playerIndex].getCost());
            cityList[playerIndex].resetState(Constants.CITY_STATE.owned_by_play);
            tileRoad[playerIndex].resetTileType(Constants.TILE_TYPE.player);
            cityCount--;
        }
        //플레이어가 무인도에 있을 때
        //컴퓨터가 무인도에 있지 않다면
        //컴퓨터의 차례로 넘어감
        //afterRoll 메소드에 컴퓨터의 차례가 한 번 더 나오므로
        //플레이어가 무인도에 도착한 시점으로부터 컴퓨터의 턴이 두 번
        //플레이어가 움직이자마자 컴퓨터가 움직여서
        //플레이어가 움직인 후 약간의 시간차를 둠
        else if(tileRoad[playerIndex].getTileType() == Constants.TILE_TYPE.island){
            if(computerIslandCount == 0){
                drawBoard(g);
                drawDice(g);
                drawAgent(g);
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                computerTurn(g);
            }
        } else if(tileRoad[playerIndex].getTileType() == Constants.TILE_TYPE.computer){
            player.pay(computer, Constants.CITY_EARN);
        }
        //움직이기 전의 말 그림을 지울 방법이 생각나지 않아서
        //이 메소드가 끝나기 전까지 임시적으로 새로운 보드그림을 그려주어
        //이 전의 말 그림을 덮어준다.
        drawBoard(g);
        drawDice(g);
        drawAgent(g);
    }

    //확률을 추가한 것을 제외하고는 playerTurn 과 같음
    public void computerTurn(Graphics g){
        Random random = new Random();
        int p;

        rollDice(g);
        computerIndex += dice.getCurrentNum();
        if (computerIndex >= Constants.CITY_SIZE) {
            computerIndex -= Constants.CITY_SIZE;
            computer.reward();
        }
        moveComputer();
        if (tileRoad[computerIndex].getTileType() == Constants.TILE_TYPE.city){
            p = random.nextInt(2);
            if(p == 1){
                computer.purchase(cityList[computerIndex].getCost());
                cityList[computerIndex].resetState(Constants.CITY_STATE.owned_by_computer);
                tileRoad[computerIndex].resetTileType(Constants.TILE_TYPE.computer);
                cityCount--;
            }
        } else if(tileRoad[computerIndex].getTileType() == Constants.TILE_TYPE.island){
            computerIslandCount = Constants.ISLAND_COUNT;
        } else if(tileRoad[computerIndex].getTileType() == Constants.TILE_TYPE.player){
            computer.pay(player, Constants.CITY_EARN);
        }
        drawBoard(g);
        drawDice(g);
        drawAgent(g);
    }

    //컴퓨터와 플레이어의 말을 그리는 메소드.
    public void drawAgent(Graphics g) {
        computer.draw(g);
        player.draw(g);
    }

    public void drawDice(Graphics g) {
        dice.draw(g);
    }

    //바뀐 주사위를 그리는 역할.
    //MainFrame 에서 Dice 로 바로 넘어가지 못하여,
    //두 클래스 사이에 다리역할을 함.
    public void rollDice(Graphics g) {
        dice.rollDice(g);
    }

    //타일의 이미지를 불러옴.
    public Image getTileImage(Constants.TILE_TYPE tileType) {
        return new ImageIcon(MainFrame.class.
                getResource(getTileImagePath(tileType))).getImage();
    }

    //타일의 이미지 경로를 리턴해줌.
    //Constants 클래스에 있는 DIR_ 활용.
    //타일의 경우, enum 의 메소드를 이용하여,
    //tileType 에 따라 이미지 파일의 이름을 리턴받아,
    //이미지의 경로를 완성하게 됨.
    public String getTileImagePath(Constants.TILE_TYPE tileType) {
        return ".." + Constants.DIR_RESOURCE + Constants.DIR_TILE
                + tileType.toFileName();
    }

    //Agent 의 이미지를 불러옴.
    private Image getAgentImage(String fileName) {
        return new ImageIcon(MainFrame.class.
                getResource(getAgentImagePath(fileName))).getImage();
    }

    //Agent 의 이미지 경로를 리턴해줌.
    private String getAgentImagePath(String fileName) {
        return ".." + Constants.DIR_RESOURCE + Constants.DIR_AGENT + fileName;
    }


    //cityList 에 Constants 클래스 내부에 위치한 String 배열 복사.
    //빈 칸을 구분해 주기 위해서,
    //cityList 마지막에 none 을 추가.
    private void setCityList() {
        cityList = new City[Constants.CITY_SIZE + 1];
        for (int i = 0; i < cityList.length; i++) {
            String cityName = Constants.CITIES[i];
            cityList[i] = new City(cityName, getCost(cityName),
                    Constants.CITY_STATE.free);
        }
        cityList[Constants.CITY_SIZE] = new City(Constants.NONE_IN_STRING,
                getCost(Constants.NONE_IN_STRING), Constants.CITY_STATE.none);
    }

    //임의의 보드 칸 (중앙의 칸들 제외) 이 시작 지점이나 무인도 지점이 아니라면
    //cost 를 설정해줌.
    private int getCost(String cityName) {
        if (!cityName.equals(Constants.START_IN_STRING) &&
                !cityName.equals(Constants.ISLAND_IN_STRING) &&
                !cityName.equals(Constants.NONE_IN_STRING)) {
            return Constants.CITY_COST;
        }
        return Constants.NON_CITY_COST;
    }

    //Agent 소유의 땅을 찾아서 그 땅의 가격만큼
    //현재의 balance 에 더해주면서 최종 금액을 계산
    public int finalBalance(Agent agent){
        int finBalance = agent.getBalance();
        for(int i = 0; i < Constants.CITY_SIZE; i++){
            if(cityList[i].getState().toOwnerString() == agent.getName()){
                finBalance += cityList[i].getCost();
            }
        }
        agent.setBalance(finBalance);
        return finBalance;
    }
}
