package org.example.loginpage.gameLoader.pieces;

import org.example.loginpage.gameLoader.GamePanel;

public class King extends piece{
    public King(int color, int col, int row) {
        super(color, col, row);
        if (color == GamePanel.WHITE) {
            setImage("/piece/wking");
        } else {
            setImage("/piece/BKing");
        }
    }
    public boolean possibleMove(int NextCol, int NextRow){
        if(inBoard(NextCol,NextRow)){
            if(Math.abs(NextCol - preCol)+ Math.abs(NextRow -preRow)==1 ||
                    Math.abs(NextCol- preCol)*Math.abs(NextRow-preRow)==1){
                if(isValidSquare(NextCol,NextRow)){
                    return true;
                }
            }
        }
        return false;
    }
}
