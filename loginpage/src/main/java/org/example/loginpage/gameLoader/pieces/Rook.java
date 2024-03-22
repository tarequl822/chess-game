package org.example.loginpage.gameLoader.pieces;
import org.example.loginpage.gameLoader.GamePanel;
public class Rook extends piece{
    public Rook(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wRook");
        } else {
            setImage("/piece/BRook");
        }
    }
}