package org.example.loginpage.gameLoader.pieces;


import org.example.loginpage.gameLoader.GamePanel;

public class Pawn extends piece {
    public Pawn(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wPawn");
        } else {
            setImage("/piece/BPawn");
        }
    }
}
