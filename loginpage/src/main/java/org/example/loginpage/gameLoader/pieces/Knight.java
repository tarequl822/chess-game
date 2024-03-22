package org.example.loginpage.gameLoader.pieces;

import org.example.loginpage.gameLoader.GamePanel;

public class Knight extends piece{
    public Knight(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wKnight");
        } else {
            setImage("/piece/BKinght");
        }
    }
}
