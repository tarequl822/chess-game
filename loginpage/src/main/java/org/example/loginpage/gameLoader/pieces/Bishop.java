package org.example.loginpage.gameLoader.pieces;

import org.example.loginpage.gameLoader.GamePanel;

public class Bishop extends piece{
    public Bishop(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wbishop");
        } else {
            setImage("/piece/Bbishop");
        }
    }
}
