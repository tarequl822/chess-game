package org.example.loginpage.gameLoader.pieces;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.loginpage.gameLoader.Board;
import org.example.loginpage.gameLoader.GamePanel;

import java.util.Collections;

public class piece {
    private Image image;
    public int x, y;
    public int col, row, preCol, preRow;
    public int color;
    public piece overwritePieces;

    public piece(int color, int col, int row) {
        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    public void setImage(String imagePath) {
        try {
            this.image = new Image(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getX(int col) {
        return col * Board.Square_size;
    }

    public int getY(int row) {
        return row * Board.Square_size;
    }

    public int getCol(int x){
        return (x+ Board.Half_Square_size)/Board.Square_size;
    }
    public int getRow(int y){
        return (y+ Board.Half_Square_size)/Board.Square_size;
    }
    public void updatePosition(){
        x= getX(col);
        y=getY(row);
        preCol =getCol(x);
        preRow = getRow(y);
    }
    public boolean possibleMove(int targetCol, int targetRow){
        return false;
    }
    public boolean inBoard(int nextCol, int NextRow){
        if(nextCol >=0&& nextCol<=7 && NextRow >=0&& NextRow <=7){
            return true;
        }
        return false;
    }
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x= getX(col);
        y= getY(row);
    }
    public piece CheckOverWrite(int updateCol, int updateRow){
        for(piece piece:GamePanel.simPieces){
            if(piece.col == updateCol && piece.row == updateRow && piece!=this ){
                return piece;
            }
        }
        return null;
    }
    public boolean isValidSquare(int updateCol, int UpdateRow){
        overwritePieces = CheckOverWrite(updateCol,UpdateRow);
        if(overwritePieces == null){
            return true;
        }
        else {
            if(overwritePieces.color != this.color){
                return true;
            }
            else {
                overwritePieces =null;
            }
        }
        return false;
    }
    public int getIndex(){
        for(int index =0; index < GamePanel.simPieces.size(); index++){
            if(GamePanel.simPieces.get(index)== this){
                return index;
            }
        }
        return 0;
    }
    public void draw(GraphicsContext gc) {
        if (image != null) {
            gc.drawImage(image, x, y, Board.Square_size, Board.Square_size);
        }
    }
}
