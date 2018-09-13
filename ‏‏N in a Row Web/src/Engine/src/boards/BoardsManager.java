package boards;

import Engine.EngineGame;
import Game.Utils.BoardUtil;

import java.util.*;

//Board: gameName,createdUserName,amountOfRegistersPlayers,capacityOfPlayers,Engine,activeGame,

public class BoardsManager {
    private Map<String,BoardUtil> m_BoardsGame;
    public synchronized void addGameName(String i_GameName, String i_CreatedUserName, int i_CapacityOfPlayers, EngineGame i_Engine) {
        m_BoardsGame.put(i_GameName,new BoardUtil(i_GameName, i_CreatedUserName, i_CapacityOfPlayers, i_Engine));
    }

    public synchronized void removeGameName(String i_GameName) {
        m_BoardsGame.remove(i_GameName);
    }

    public synchronized List<BoardUtil> getBoards() {
        return (List)m_BoardsGame.values();
    }

    public boolean isUserExists(String i_GameName) {
        return m_BoardsGame.containsKey(i_GameName);
    }
}
