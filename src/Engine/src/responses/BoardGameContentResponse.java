package responses;

import Engine.BoardCell;
import boards.Board;

public class BoardGameContentResponse {
    private BoardCell[][] Board;
    private int Rows;
    private int Cols;
    private String Varient;
    private String ActiveGame;
    private String GameStatus;

    public BoardGameContentResponse(BoardCell[][] i_Board,int i_Rows, int i_Cols, String i_Varient, String i_ActiveGame, String i_GameStatus){
        Board = i_Board;
        Rows = i_Rows;
        Cols = i_Cols;
        Varient = i_Varient;
        ActiveGame = i_ActiveGame;
        GameStatus = i_GameStatus;
    }
}
