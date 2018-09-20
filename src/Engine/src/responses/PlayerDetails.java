package responses;

public class PlayerDetails {
    private final String PlayerName;
    private final String Type;
    private final String Color;
    private final String Turns;
//    private final String Status; //TODO: retired player

    public PlayerDetails(String playerName, String type, String color, String turns) {
        PlayerName = playerName;
        Type = type;
        Color = color;
        Turns = turns;
//        Status = status;
    }
}
