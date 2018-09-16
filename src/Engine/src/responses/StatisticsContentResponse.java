package responses;

import Engine.BoardCell;

public class StatisticsContentResponse {
    private String PlayerNameTurn;
    private String Time;
    private String Target;
    private String Varient;
    private String NextPlayerName;

    public StatisticsContentResponse(String i_PlayerTurnName, String i_Time, String i_Target, String i_Varient, String i_NextPlayerName){
        PlayerNameTurn = i_PlayerTurnName;
        Time = i_Time;
        Target = i_Target;
        Varient = i_Varient;
        NextPlayerName = i_NextPlayerName;
    }
}