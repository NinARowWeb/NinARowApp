package Game.Servlet.GameRoom;

import Game.Utils.ServletUtils;
import Game.Utils.SessionUtils;
import boards.Board;
import boards.BoardsManager;
import constants.Constants;
import users.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LogoutGameServlet", urlPatterns = {"/LogoutGame"})
public class LogoutGameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boardName = SessionUtils.getAttribute(request, Constants.BOARD_GAME);
        BoardsManager manager = ServletUtils.getBoardsManager(getServletContext());
        Board game = manager.getGameBoard(boardName);
        String uniqueIDFromSession = SessionUtils.getAttribute(request, Constants.UNIQUE_ID);
        String isComputerFromSession = SessionUtils.getAttribute(request, Constants.TYPE);
        SessionUtils.removeAttribute(request, Constants.UNIQUE_ID);
        SessionUtils.removeAttribute(request, Constants.BOARD_GAME);
        game.quitGame(Integer.parseInt(uniqueIDFromSession),isComputerFromSession == "true"? true : false);
        response.sendRedirect(Constants.LOBBY_ROOM_URL);
    }
}
