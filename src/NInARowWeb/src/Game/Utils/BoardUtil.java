package Game.Utils;

import Engine.EngineGame;

public class BoardUtil {
    private final String m_GameName;
    private final String m_CreatedUserName;
    private int m_AmountOfRegistersPlayers;
    private final int m_CapacityOfPlayers;
    private final EngineGame m_Engine;
    private boolean m_ActiveGame;

    public BoardUtil(String i_GameName, String i_CreatedUserName, int i_CapacityOfPlayers, EngineGame i_Engine){
        m_GameName = i_GameName;
        m_CreatedUserName = i_CreatedUserName;
        m_CapacityOfPlayers = i_CapacityOfPlayers;
        m_AmountOfRegistersPlayers = 0;
        m_Engine = i_Engine;
        m_ActiveGame = false;
    }

    public int getAmountOfRegistersPlayers() {
        return m_AmountOfRegistersPlayers;
    }

    public void setAmountOfRegistersPlayers(int m_AmountOfRegistersPlayers) {
        this.m_AmountOfRegistersPlayers = m_AmountOfRegistersPlayers;
    }

    public boolean isActiveGame() {
        return m_ActiveGame;
    }

    public void setActiveGame(boolean m_ActiveGame) {
        this.m_ActiveGame = m_ActiveGame;
    }

    public String getGameName() {
        return m_GameName;
    }

    public String getCreatedUserName() {
        return m_CreatedUserName;
    }

    public int getCapacityOfPlayers() {
        return m_CapacityOfPlayers;
    }

    public int getRows(){
        return m_Engine.getRows();
    }

    public int getCols(){
        return m_Engine.getMaxCol();
    }

    public int getSequence(){
        return m_Engine.getSequence();
    }

    public String getVarient(){
        return m_Engine.getVarient().name();
    }
}
