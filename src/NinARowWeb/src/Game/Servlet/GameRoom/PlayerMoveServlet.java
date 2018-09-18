package Game.Servlet.GameRoom;

import Game.Utils.ServletUtils;
import Game.Utils.SessionUtils;
import boards.Board;
import boards.BoardsManager;
import constants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PlayerMoveServlet", urlPatterns = {"/PlayerMove"})
public class PlayerMoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardName = SessionUtils.getAttribute(request, Constants.BOARD_GAME);
        BoardsManager manager = ServletUtils.getBoardsManager(getServletContext());
        Board game = manager.getGameBoard(boardName);
        PrintWriter out = response.getWriter();
        if(SessionUtils.getAttribute(request,Constants.UNIQUE_ID).equals(Integer.toString(game.getCurrentPlayerUniqueID()))) {
            boolean popout = request.getParameter("Popout") == "True" ? true : false;
            int col = Integer.parseInt(request.getParameter("Col"));
            Point move = game.playerMove(col, popout);
            out.print(SessionUtils.getAttribute(request, Constants.ERROR_LOGIN_MESSAGE));
            if (move == null) {
                if (popout) {
                    out.print(String.format("Your popout move is not legal"));
                } else {
                    out.print(String.format("col: {0} is fulled. please enter another col in range", (col + 1)));

                }
            }
        }
        else{
            out.print(String.format("This is not your turn"));
        }
    }
}
