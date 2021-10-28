package Launcher;

import Constants.Constants;
import MainFrame.*;

import javax.swing.*;
import java.awt.*;

public class MyApp {

    public static void main(String[] args){

        StartFrame startFrame = new StartFrame(); //시작창 생성
        startFrame.init(); //시작창 제목 및 크기 초기화
        startFrame.setFont(Constants.FONT_TYPE); //시작창 폰트 설정
        runCenter(startFrame); //시작창 실행 위치를 중앙으로
        startFrame.setVisible(true); //시작창 보여주기

    }
    //화면 중앙에서 시작창을 실행시키기 위한 함수.
    //사용자의 화면크기를 받아와 미리 설정해둔
    //startFrame 의 크기를 계산해서 실행될 위치를 설정해 놓음.
    public static void runCenter(JFrame frame){
        Dimension frameSize = frame.getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2);
    }
}
