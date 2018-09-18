package boards;

import Engine.BoardCell;
import Engine.DataHistoryDisc;
import Engine.EngineGame;
import Engine.SignOnBoardEnum;
import JavaFX.ColorOnBoardEnum;

import java.awt.Point;
import java.util.List;

public class Board {
    private final String GameName;
    private final String CreatedUserName;
    private int RegisteredPlayers;
    private String RegisteredPlayersToResponse;
    private final int CapacityOfPlayers;
    private final EngineGame Engine;
    private String ActiveGame;
    private int Target;
    private String BoardSize;
    private String Status;

    public Board(String i_GameName, String i_CreatedUserName, int i_CapacityOfPlayers, EngineGame i_Engine, String i_Status){
        GameName = i_GameName;
        CreatedUserName = i_CreatedUserName;
        CapacityOfPlayers = i_CapacityOfPlayers;
        RegisteredPlayers = 0;
        RegisteredPlayersToResponse = RegisteredPlayers + "/" + CapacityOfPlayers;
        Engine = i_Engine;
        ActiveGame = "No";
        Target = Engine.getSequence();
        BoardSize = Engine.getRows() + "X" + Engine.getMaxCol();
        Status = i_Status;
    }

    public void startGame(){
        Engine.startGame();
    }

    public String getTime(){
        final int secondsInMinutes = 60;
        long seconds = Engine.getTimeInSeconds();
        return(String.format(" %02d:%02d", (seconds / secondsInMinutes), (seconds % secondsInMinutes)));
    }

    public int getLastMoveIndex(){return  Engine.getAmountOfMoves();}

    public String getStatus(){
        return Status;
    }

    public void updateStatus(){
        Status = Engine.getStatus().name();
    }

    public int getAmountOfRegistersPlayers() {
        return Engine.getAmountOfPlayers();
    }
    
    public boolean isActiveGame() {
        return ActiveGame == "Yes";
    }

    public void setActiveGame(boolean i_ActiveGame) {
        this.ActiveGame = i_ActiveGame == true? "Yes" : "No";
    }
    
    private SignOnBoardEnum purseSign(char i_Sign){
        for(SignOnBoardEnum currentSign : SignOnBoardEnum.values()){
            if(currentSign.getSign() == i_Sign)
                return currentSign;
        }
        return null;
    }

    public String getHistoryMove(int i_Index){
        String lastMoveMessage;
        if(Engine.getAmountOfMoves() > 0) {
            DataHistoryDisc lastMove = Engine.getLastMove();
            SignOnBoardEnum currentSign = purseSign(lastMove.getSign());
            return " " + lastMove.getName() + ", Color: " + ColorOnBoardEnum.valueOf((currentSign.name())).getColor() +
                    ", " + (lastMove.getPopout() ? "Popout: " : "Insert: ") + "(" + (lastMove.getLastMoveCoordinate().y + 1) + "," + (lastMove.getLastMoveCoordinate().x + 1) + ")";
        }
        return null;
    }

    public BoardCell[][] getBoard(){
        return Engine.getBoardForDisplay();
    }

    public int getRows(){
        return Engine.getRows();
    }

    public int getCols(){
        return Engine.getMaxCol();
    }

    public String getVarient(){
        return Engine.getVarient().name();
    }

    public String getTarget(){
        return Integer.toString(Engine.getSequence());
    }

    public int getTurn(){return Engine.getTurn();}

    public String getPlayerName(int i_Index){
        return Engine.getPlayerName(i_Index);
    }

    public String getPlayerType(int i_Index){return Engine.getPlayerType(i_Index)? "Computer" : "Human Player";}

    public String getPlayerTurns(int i_Index){return Integer.toString(Engine.getPlayerTurns(i_Index));}

    public String getColor(int i_Index)
    {
        char signOnBoard = Engine.getPlayerSignOnBoard(i_Index);
        for(SignOnBoardEnum currentSign: SignOnBoardEnum.values()){
            if(currentSign.getSign() == signOnBoard)
                return currentSign.name();
        }
        return null;
    }

    public List<Point> getWinnersCoordinates(){
        return Engine.getWinnersTaraget();
    }

    public int getRegisteredPlayers(){
        return RegisteredPlayers;
    }

    public int getCurrentPlayerUniqueID(){
        return Engine.getUniqueID();
    }

    public void addPlayer(String i_RegisterPlayerName, boolean i_IsComputerPlayer) {
        Engine.addPlayer(i_RegisterPlayerName,i_IsComputerPlayer,RegisteredPlayers);
        RegisteredPlayers++;
        RegisteredPlayersToResponse = RegisteredPlayers + "/" + CapacityOfPlayers;
        if(RegisteredPlayers == CapacityOfPlayers){
            ActiveGame = "Yes";
        }
    }

    public Point playerMove(int i_Col, boolean i_Popout){
        Point move = Engine.humanMove(i_Col,i_Popout);
        if(move != null) {
            Engine.finishedTurn(move);
            if(Engine.checkFinishedGame()){
                updateStatus();
            }
        }
        return move;
    }
}
