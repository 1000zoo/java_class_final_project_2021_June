package MainFrame;

import Board.BlueMarbleBoard;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    public BlueMarbleBoard board;

    public MainPanel() {
        this.board = new BlueMarbleBoard();
    }

    //시스템에서 직접 호출하는 그리기 메소드.
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.board != null){
            this.board.drawBoard(g);
            this.board.drawAgent(g);
            this.board.drawDice(g);
        }
    }
}
