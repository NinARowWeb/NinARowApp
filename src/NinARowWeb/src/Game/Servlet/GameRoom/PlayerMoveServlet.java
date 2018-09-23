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
            String popout = request.getParameter("Popout");
            boolean isPopout = false;
            if(popout.equals("true")){
                isPopout = true;
            }
            int col = Integer.parseInt(request.getParameter("Col"));
            Point move = game.playerMove(col, isPopout);
            if (move == null) {
                if (isPopout) {
                    out.print(String.format("Your popout move is not legal"));
                } else {
                    out.print(String.format("col: %d is fulled. please enter another col in range", (col + 1)));
                }
            }
        }
        else{
            SessionUtils.setAttribute(request,Constants.PLAYER_TURN_ERROR,String.format("This is not your turn"));
            out.print("This is not your turn");
        }
    }
}
