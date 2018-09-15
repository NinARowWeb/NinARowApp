package responses;

import boards.Board;
import users.User;

import java.util.List;
import java.util.Set;

public class LobbyContentResponse {
    private Set<User> users;
    private List<Board> boards;
    private String errorMessage;

    public LobbyContentResponse(Set<User> i_Users, List<Board> i_Boards, String i_ErrorMessage){
        users = i_Users;
        boards = i_Boards;
        errorMessage = i_ErrorMessage;
    }
}
