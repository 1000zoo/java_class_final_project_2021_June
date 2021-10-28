package MainFrame;

import Constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame(String playerName) {

        JPanel tabPanel = new JPanel(); //실행 창 위의 현황판을 위한 panel.
        MainPanel mainPanel = new MainPanel(); //게임 보드가 그려질 main panel.

        //주사위 버튼 생성 및 리스너 생성
        //버튼을 누르면 rollDice 함수가 실행됨.
        //Graphics 타입의 g 를 getGraphics 로 가져오고,
        //BlueMarbleBoard 의 메소드인 rollDice 실행.
        //repaint 로 주사위 그림을 바꿔줌.
        //컴퓨터의 잔액을 적을 Label 선언
        JLabel comNameText = new JLabel(Constants.COM_BALANCE + ": ");
        JLabel comBalance = new JLabel(mainPanel.board.getComputerBalance() + "");
        //플레이어의 잔액을 적을 Label 선언
        JLabel playerNameText =
                new JLabel(playerName + Constants.PLAYER_BALANCE + ": ");
        JLabel playerBalance =
                new JLabel(mainPanel.board.getPlayerBalance() + "");

        //버튼을 누르면
        //주사위를 굴리면서 주사위 사진을 바꾸고
        //주사위가 바뀐 후의 동작을 afterRoll 이 수행한다.
        //그 이후 말의 위치, 칸의 소유자를 나타내는 그림으로 바꿔주기 위해
        //repaint 메소드를 사용해준다.
        //cityCount 가 0이라면 게임 종료
        JButton rollDiceButton = new JButton(Constants.ROLL_DICE_BUTTON);
        rollDiceButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (rollDiceButton.isEnabled()) {
                            if(!isEnd(mainPanel.board.cityCount)){
                                mainPanel.board.rollDice(mainPanel.getGraphics());
                                mainPanel.board.afterRoll(mainPanel.getGraphics());
                                mainPanel.repaint();
                                comBalance.setText(mainPanel.board.getComputerBalance() + "");
                                playerBalance.setText(mainPanel.board.getPlayerBalance() + "");
                            }else{
                                rollDiceButton.setEnabled(false);
                                rollDiceButton.setText(Constants.THE_END);
                                comNameText.setFont(Constants.FONT_TYPE2);
                                playerNameText.setFont(Constants.FONT_TYPE2);
                                comBalance.setFont(Constants.FONT_TYPE2);
                                playerBalance.setFont(Constants.FONT_TYPE2);
                                int result =
                                        end(mainPanel.board.getPlayerBalance(),
                                        mainPanel.board.getComputerBalance());
                                if(result == Constants.COMPUTER_WIN){
                                    comNameText.setForeground(Color.RED);
                                    comBalance.setForeground(Color.RED);
                                    playerNameText.setForeground(Color.BLUE);
                                    playerBalance.setForeground(Color.BLUE);
                                } else if(result == Constants.PLAYER_WIN){
                                    comNameText.setForeground(Color.BLUE);
                                    comBalance.setForeground(Color.BLUE);
                                    playerNameText.setForeground(Color.RED);
                                    playerBalance.setForeground(Color.RED);
                                } else if(result == Constants.DRAW){
                                    comNameText.setForeground(Color.RED);
                                    comBalance.setForeground(Color.RED);
                                    playerNameText.setForeground(Color.RED);
                                    playerBalance.setForeground(Color.RED);
                                }
                            }
                        }
                    }
                }
        );

        //좌측 중앙 우측 순으로 정렬
        tabPanel.add(comNameText);
        tabPanel.add(comBalance);
        tabPanel.add(rollDiceButton);
        tabPanel.add(playerNameText);
        tabPanel.add(playerBalance);

        //만들어둔 Panel 들을 전체 Frame 에 추가.
        this.add(tabPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    //mainFrame 설정 초기화.
    public void init() {
        this.setSize(Constants.MAINFRAME_WIDTH, Constants.MAINFRAME_HEIGHT);
        this.setTitle(Constants.MAINFRAME_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //최종 플레이어와 컴퓨터의 잔액을 비교하여
    //결과를 리턴하는 메소드
    public int end(int p1, int p2){
        if(p1 > p2) return Constants.PLAYER_WIN;
        else if(p1 < p2) return Constants.COMPUTER_WIN;
        else return Constants.DRAW;
    }
    public boolean isEnd(int cityCount){
        if(cityCount == 0){
            return true;
        } else{
            return false;
        }
    }
}
