package org.example.loginpage.gameLoader;


import com.sun.java.accessibility.util.AWTEventMonitor;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.loginpage.gameLoader.pieces.*;


import java.util.ArrayList;

public class GamePanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private final int FPS = 60;
    private Canvas canvas;
    private ArrayList<piece> Pieces = new ArrayList<>();
    public static ArrayList<piece> simPieces = new ArrayList<>();
    private Board board = new Board();
    MouseController mouseHandler = new MouseController();
    piece activeP;
    boolean canMove, validSquare;
    private AnimationTimer gameLoop;
    public static  final int WHITE =0;public static  final int BLACK =1;
    int currentColor = WHITE;
    public GamePanel() {
        canvas = new Canvas(WIDTH, HEIGHT);

        canvas.setOnMousePressed(mouseHandler::mousePressed);
            canvas.setOnMouseReleased(mouseHandler::mouseReleased);
            canvas.setOnMouseDragged(mouseHandler::mouseDragged);
            canvas.setOnMouseMoved(mouseHandler::mouseMoved);

        setPieces();
        copyPieces(Pieces, simPieces);
    }
    public void launchGame(){
        gameLoop = new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 1_000_000_000 / FPS) {
                    update();
                    draw();
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();
    }
    private void setPieces() {

        Pieces.add(new Pawn(WHITE,0,6));
        Pieces.add(new Pawn(WHITE,1,6));
        Pieces.add(new Pawn(WHITE,2,6));
        Pieces.add(new Pawn(WHITE,3,6));
        Pieces.add(new Pawn(WHITE,4,6));
        Pieces.add(new Pawn(WHITE,5,6));
        Pieces.add(new Pawn(WHITE,6,6));
        Pieces.add(new Pawn(WHITE,7,6));
        Pieces.add(new Rook(WHITE,1,7));
        Pieces.add(new Rook(WHITE,6,7));

        Pieces.add(new Knight(WHITE,0,7));
        Pieces.add(new Knight(WHITE,7,7));
        Pieces.add(new Bishop(WHITE,2,7));
        Pieces.add(new Bishop(WHITE,5,7));
        Pieces.add(new Queen(WHITE,3,7));
        Pieces.add(new King(WHITE,4,5));
//Black;
        Pieces.add(new Pawn(BLACK,0,1));
        Pieces.add(new Pawn(BLACK,1,1));
        Pieces.add(new Pawn(BLACK,2,1));
        Pieces.add(new Pawn(BLACK,3,1));
        Pieces.add(new Pawn(BLACK,4,1));
        Pieces.add(new Pawn(BLACK,5,1));
        Pieces.add(new Pawn(BLACK,6,1));
        Pieces.add(new Pawn(BLACK,7,1));

        Pieces.add(new Rook(BLACK,1,0));
        Pieces.add(new Rook(BLACK,6,0));
        Pieces.add(new Knight(BLACK,0,0));
        Pieces.add(new Knight(BLACK,7,0));
        Pieces.add(new Bishop(BLACK,2,0));
        Pieces.add(new Bishop(BLACK,5,0));
        Pieces.add(new Queen(BLACK,3,0));
        Pieces.add(new King(BLACK,4,0));
    }
    private void copyPieces(ArrayList<piece> source, ArrayList<piece> target) {
        target.clear();
        target.addAll(source);
    }
    private void update() {
        if (mouseHandler.pressed) {
            if (activeP == null) {
                for (piece pieces : simPieces) {
                    if (pieces.color == currentColor && pieces.col == mouseHandler.x / Board.Square_size &&
                            pieces.row == mouseHandler.y / Board.Square_size) {
                        activeP = pieces;
                    }
                }
            } else {
                simulate();
            }
        }
        if (mouseHandler.pressed == false) {
            if (activeP != null) {
                if (validSquare) {
                    copyPieces(simPieces,Pieces);
                    activeP.updatePosition();
                } else {
                    copyPieces(Pieces,simPieces);
                    activeP.resetPosition();
                    activeP = null;
                }
            }
        }
    }
    private void simulate () {
            canMove = false;
            validSquare = false;
            copyPieces(Pieces,simPieces);

            activeP.x = mouseHandler.x - Board.Half_Square_size;
            activeP.y = mouseHandler.y - Board.Half_Square_size;
            activeP.col = activeP.getCol(activeP.x);
            activeP.row = activeP.getRow(activeP.y);

            if (activeP.possibleMove(activeP.col, activeP.row)) {
                canMove = true;
                //removed pieces
                if(activeP.overwritePieces != null){
                    simPieces.remove(activeP.overwritePieces.getIndex());
                }
                validSquare = true;
            }
    }
    private void draw () {
            GraphicsContext gc = canvas.getGraphicsContext2D();

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            board.draw(gc);

            for (piece p : simPieces) {
                p.draw(gc);
            }

            if (activeP != null) {
                if (canMove) {
                    gc.setFill(Color.rgb(255, 255, 255, 0.7)); // Set color with opacity
                    gc.fillRect(activeP.col * Board.Square_size, activeP.row * Board.Square_size, Board.Square_size, Board.Square_size);
                    gc.setGlobalAlpha(1.0);
                }
                activeP.draw(gc);
            }
    }
    public Canvas getCanvas () {
            return canvas;
    }


}
