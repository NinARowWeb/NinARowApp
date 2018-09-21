package responses;

import Engine.BoardCell;
import Engine.generated.Game;

import java.util.List;

public class StatisticsContentResponse {
    private String PlayerNameTurn;
    private String Time;
    private String Target;
    private String Varient;
    private String NextPlayerName;
    private String GameActive;
    private final String ComputerTurn;
    private final String Winners;


    public StatisticsContentResponse(String i_PlayerTurnName, String i_Time, String i_Target, String i_Varient, String i_NextPlayerName, String i_Status,
                                     String computerTurn, String winners){
        PlayerNameTurn = i_PlayerTurnName;
        Time = i_Time;
        Target = i_Target;
        Varient = i_Varient;
        NextPlayerName = i_NextPlayerName;
        GameActive = i_Status;
        ComputerTurn = computerTurn;
        Winners = winners;
    }
}