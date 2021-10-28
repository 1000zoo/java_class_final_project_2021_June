package Agent;

import Constants.Constants;

import java.awt.*;

public class Player extends Agent {
    public Player(int x, int y, int width, int height, Image image,
                  int balance) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.image = image;
        super.balance = balance;
        super.name = Constants.PLAYER;
    }
}
