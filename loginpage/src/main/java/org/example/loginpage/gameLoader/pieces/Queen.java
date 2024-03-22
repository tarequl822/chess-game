package org.example.loginpage.gameLoader.pieces;

import org.example.loginpage.gameLoader.GamePanel;

public class Queen extends piece{
    public Queen(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wQueen");
        } else {
            setImage("/piece/BQueen");
        }
    }
}