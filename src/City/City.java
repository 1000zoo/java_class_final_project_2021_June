package City;

import Constants.Constants;

public class City {
    private String cityName;
    private int cost;
    private Constants.CITY_STATE cityState;

    public City(String cityName, int cost, Constants.CITY_STATE cityState){
        this.cityName = cityName;
        this.cost = cost;
        this.cityState = cityState;
    }

    //도시의 이름을 리턴해주는 메소드
    public String getCityName(){
        return cityName;
    }
    public void resetState(Constants.CITY_STATE cityState){
        this.cityState = cityState;
    }

    //객체의 상태(CITY_STATE)를 리턴해주는 메소드.
    public Constants.CITY_STATE getState(){
        return cityState;
    }

    public int getCost(){
        return cost;
    }
}
