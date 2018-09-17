package responses;

public class HistoryContentResponse {
    private String LastMove;
    private int index;

    public HistoryContentResponse(String i_LastMove, int i_Index){
        LastMove = i_LastMove;
        index = i_Index;
    }
}
