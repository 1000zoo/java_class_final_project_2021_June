package Board;

import City.City;
import Constants.Constants;

import java.awt.*;

public class Tile {
    private int x;
    private int y;
    private City city;
    private Constants.TILE_TYPE tileType;


    public Tile(int x, int y, City city){
        this.x = x;
        this.y = y;
        this.city = city;
        setTileType();
    }
    //BlueMarbleBoard 의 tileRoad 를 위한 복사생성자
    public Tile(Tile tile){
        this.x = tile.x;
        this.y = tile.y;
        this.city = tile.city;
        this.tileType = tile.tileType;
    }

    //x 를 리턴.
    public int getX(){ return x; }

    //y 를 리턴.
    public int getY(){ return y; }

    //city 를 리턴
    public City getCity(){ return city; }

    public Constants.TILE_TYPE getTileType(){ return tileType; }

    //칸의 type 을 변경해 주는 메소드
    public void resetTileType(Constants.TILE_TYPE tileType){
        this.tileType = tileType;
    }
    //City 객체의 cityState 는 none 을 제외하면
    //모두 게임 내 말들이 움직이는 칸.
    //cityState 가 start, island 일 때를 제외하면
    //나머지 칸은 모두 도시.
    private void setTileType(){
        if(city.getState().equals(Constants.CITY_STATE.none)){
            tileType = Constants.TILE_TYPE.none;
        } else if (city.getCityName().equals(Constants.START_IN_STRING)){
            tileType = Constants.TILE_TYPE.start;
        } else if (city.getCityName().equals(Constants.ISLAND_IN_STRING)){
            tileType = Constants.TILE_TYPE.island;
        } else {
            tileType = Constants.TILE_TYPE.city;
        }
    }
}
