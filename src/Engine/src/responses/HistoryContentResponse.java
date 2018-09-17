package responses;

public class HistoryContentResponse {
    private String LastMove;
    private int index;
    private String GameActive;

    public HistoryContentResponse(String i_LastMove, int i_Index,String i_Status){
        LastMove = i_LastMove;
        index = i_Index;
        GameActive = i_Status;
    }
}
