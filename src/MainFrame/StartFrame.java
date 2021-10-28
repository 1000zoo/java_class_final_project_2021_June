package MainFrame;

import Constants.Constants;
import Launcher.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//사용자의 이름을 입력받고 실행하기 위한 클래스.
public class StartFrame extends JFrame{

    public StartFrame(){

        JPanel startPanel = new JPanel();
        //이름을 입력하기 위한 입력창, 입력하지 않을 시 기본 이름으로 실행
        JTextField inputName = new JTextField(Constants.DEFAULT_NAME);
        JButton startButton = new JButton(Constants.START);

        //시작 버튼을 위한 리스너.
        //시작 버튼을 누르면 JTextField 의 메소드 getText 로
        //사용자의 이름을 mainFrame 의 파라미터로 전달.
        startButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(startButton.isEnabled()){
                            MainFrame mainFrame =
                                    new MainFrame(inputName.getText());
                            mainFrame.init();
                            mainFrame.setVisible(true);
                            MyApp.runCenter(mainFrame);
                            mainFrame.setFont(Constants.FONT_TYPE);
                        }
                    }
                }
        );

        startPanel.add(inputName);
        startPanel.add(startButton);

        //Panel 을 Frame 에 추가
        this.add(startPanel);
    }

    //StartFrame 의 초기화를 위한 함수.
    public void init(){
        this.setSize(Constants.START_FRAME_WIDTH, Constants.START_FRAME_HEIGHT);
        this.setTitle(Constants.STUDENT_NUMBER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}

