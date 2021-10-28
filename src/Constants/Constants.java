package Constants;

import java.awt.*;

public class Constants {

    //StartFrame
    public static final String STUDENT_NUMBER = "2017271131";
    public static final String START = "시작";
    public static final String DEFAULT_NAME = "천지우";
    public static final int START_FRAME_WIDTH = 150;
    public static final int START_FRAME_HEIGHT = 100;
    public static final Font FONT_TYPE = new Font("궁서 보통", Font.PLAIN, 15);
    public static final Font FONT_TYPE2 = new Font("궁서 보통", Font.BOLD, 15);

    //MainFrame
    public static final String MAINFRAME_TITLE = "BLUE MARBLE";
    public static final int MAINFRAME_WIDTH = 577;
    public static final int MAINFRAME_HEIGHT = 750;

    //Agent
    public static final int PLAYER_X = 4;
    public static final int PLAYER_Y = 86;
    public static final int COMPUTER_X = 38;
    public static final int COMPUTER_Y = 96;
    public static final int AGENT_WIDTH = 40;
    public static final int AGENT_HEIGHT = 40;
    public static final int REWARD = 200000;
    public static final int ISLAND_COUNT = 1;

    //Dice
    public static final int DICE_X = 240;
    public static final int DICE_Y = 290;
    public static final int DICE_SIZE = 6;
    public static final int DICE_WIDTH = 90;
    public static final int DICE_HEIGHT = 90;

    //BlueMarbleBoard -> setTile() & drawBoard()
    public static final int TILE_X = 0;
    public static final int TILE_Y = 60;
    public static final int TILE_WIDTH = 80;
    public static final int TILE_HEIGHT = 80;
    public static final int TILE_TEXT_X = 28;
    public static final int TILE_TEXT_Y = 20;
    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 7;

    //MainFrame -> tabPanel
    public static final String ROLL_DICE_BUTTON = "주사위 굴리기";
    public static final String THE_END = "게임 종료";
    public static final String COM_BALANCE = "컴퓨터 잔액";
    public static final String PLAYER_BALANCE = " 잔액";
    public static final int MAX_BALANCE = 1000000;

    //BlueMarbleBoard -> setCityList() & getCost()
    //중앙의 빈 공간을 구분하기 위해,
    //CITIES 마지막에 NONE 을 추가.
    public static final int CITY_SIZE = 24;
    public static final int CITY_BUYABLE = 20;
    public static final int CITY_COST = 200000;
    public static final int CITY_EARN = 100000;
    public static final int NON_CITY_COST = 0;
    public static final String START_IN_STRING = "시 작";
    public static final String ISLAND_IN_STRING = "무인도";
    public static final String NONE_IN_STRING = "NONE";
    public static final String[] CITIES = {START_IN_STRING, "수 원", "용 인", "익 산", "전 주", "군 산", ISLAND_IN_STRING,
            "경 주", "포 항", "대 구", "창 원", "부 산", ISLAND_IN_STRING,
            "제 주", "여 수", "광 주", "춘 천", "강 릉", ISLAND_IN_STRING,
            "청 주", "천 안", "대 전", "인 천", "서 울", NONE_IN_STRING};

    //경로를 간단히 하기 위함.
    public static final String DIR_RESOURCE = "/resource/";
    public static final String DIR_TILE = "tile/";
    public static final String DIR_AGENT = "agent/";
    public static final String DIR_DICE = "dice/";
    public static final String DIR_STRUCTURE = "structure/";

    public static final String COMPUTER_FILE_NAME = "COMPUTER.png";
    public static final String PLAYER_FILE_NAME = "PLAYER.png";

    public static final String COMPUTER = "COMPUTER";
    public static final String PLAYER = "PLAYER";
    public enum CITY_STATE {
        free, owned_by_play, owned_by_computer, none;
        public String toOwnerString(){
            if(this == owned_by_computer) return "COMPUTER";
            else if(this == owned_by_play) return "PLAYER";
            else return null;
        }
    }

    //컴퓨터 승 0
    //플레이어 승 1
    //무승부 2
    public static final int COMPUTER_WIN = 0;
    public static final int PLAYER_WIN = 1;
    public static final int DRAW = 2;

    //타일의 형태를 나타내기 위한 enum,
    //형태에 따라 이미지 경로를 분리하여 리턴하는 메소드도 추가함.
    public enum TILE_TYPE {
        start, island, city, player, computer, none;
        public String toFileName(){
            if(this == start) return "START.png";
            else if(this == island) return "ISLAND.png";
            else if(this == city) return "CITY.png";
            else if(this == player) return "PLAYER.png";
            else if(this == computer) return "COM.png";
            else return "TILE.png";
        }
    }
}
