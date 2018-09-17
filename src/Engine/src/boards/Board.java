package boards;

import Engine.BoardCell;
import Engine.DataHistoryDisc;
import Engine.EngineGame;
import Engine.SignOnBoardEnum;
import JavaFX.ColorOnBoardEnum;

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

    public String getTime(){
        final int secondsInMinutes = 60;
        long seconds = Engine.getTimeInSeconds();
        return(String.format(" %02d:%02d", (seconds / secondsInMinutes), (seconds % secondsInMinutes)));
    }

    public String getStatus(){
        return Status;
    }

    public int getAmountOfRegistersPlayers() {
        return Engine.getAmountOfPlayers();
    }


/*
    public void setAmountOfRegistersPlayers(int i_AmountOfRegistersPlayers) {
        RegisteredPlayers = i_AmountOfRegistersPlayers;
        RegisteredPlayersToResponse = RegisteredPlayers + "/" + CapacityOfPlayers;
    }
*/

    public boolean isActiveGame() {
        return ActiveGame == "Yes";
    }

    public void setActiveGame(boolean i_ActiveGame) {
        this.ActiveGame = i_ActiveGame == true? "Yes" : "No";
    }

/*    public String getGameName() {
        return GameName;
    }

    public String getCreatedUserName() {
        return CreatedUserName;
    }

    public int getCapacityOfPlayers() {
        return CapacityOfPlayers;
    }
    public int getSequence(){
        return Engine.getSequence();
    }
*/

    private SignOnBoardEnum purseSign(char i_Sign){
        for(SignOnBoardEnum currentSign : SignOnBoardEnum.values()){
            if(currentSign.getSign() == i_Sign)
                return currentSign;
        }
        return null;
    }

    public String getLastMove(){
        String lastMoveMessage;
        DataHistoryDisc lastMove = Engine.getLastMove();
        SignOnBoardEnum currentSign = purseSign(lastMove.getSign());
        lastMoveMessage =  " " + lastMove.getName() + ", Color: " + ColorOnBoardEnum.valueOf((currentSign.name())).getColor() +
                ", " + (lastMove.getPopout() ? "Popout: " : "Insert: ") +"(" +(lastMove.getLastMoveCoordinate().y + 1) + "," + (lastMove.getLastMoveCoordinate().x + 1) + ")";

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

    public String getPlayerName(int i_Turn){
        return Engine.getPlayerTurnName(i_Turn);
    }

    public void addPlayer(String i_RegisterPlayerName, boolean i_IsComputerPlayer) {
        Engine.addPlayer(i_RegisterPlayerName,i_IsComputerPlayer,RegisteredPlayers);
        RegisteredPlayers++;
        RegisteredPlayersToResponse = RegisteredPlayers + "/" + CapacityOfPlayers;
        if(RegisteredPlayers == CapacityOfPlayers){
            ActiveGame = "Yes";
        }
    }
}
