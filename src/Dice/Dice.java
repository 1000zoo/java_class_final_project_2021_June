package Dice;

import Constants.Constants;
import MainFrame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Dice {
    private int x;
    private int y;
    private Image[] images;
    private int currentNum;

    //주사위의 이미지, 이미지의 위치, 주사위의 숫자.
    public Dice() {
        images = new Image[Constants.DICE_SIZE];
        x = Constants.DICE_X;
        y = Constants.DICE_Y;
        currentNum = 1;
        loadImages();
    }

    //현재의 주사위 사진을 그리는 메소드.
    public void draw(Graphics g) {
        g.drawImage(images[currentNum - 1], x, y, Constants.DICE_WIDTH,
                Constants.DICE_HEIGHT, null);
    }

    //1~6 사이의 정수를 뽑고
    //그림을 그리는 메소드 호출.
    public void rollDice(Graphics g) {
        Random rand = new Random();
        currentNum = rand.nextInt(6) + 1;
        draw(g);
    }

    public int getCurrentNum(){ return currentNum; }

    //1부터 6까지 주사위 사진들을 불러옴.
    private void loadImages() {
        for (int i = 0; i < images.length; i++) {
            images[i] =
                    new ImageIcon(MainFrame.class.getResource(
                            getImagePath(i + 1))).getImage();
        }
    }

    //주사위의 이미지 경로를 리턴해주는 메소드.
    private String getImagePath(int diceNumber) {
        return ".." + Constants.DIR_RESOURCE +
                Constants.DIR_DICE + "DICE" + diceNumber + ".png";
    }
}
