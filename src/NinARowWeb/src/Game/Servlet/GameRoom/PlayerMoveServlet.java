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

@WebServlet(name = "PlayerMoveServlet", urlPatterns = {"/PlayerMove"})
public class PlayerMoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String boardName = SessionUtils.getAttribute(request, Constants.BOARD_GAME);
        BoardsManager manager = ServletUtils.getBoardsManager(getServletContext());
        Board game = manager.getGameBoard(boardName);
        boolean popout = request.getParameter("Popout") == "True"? true:false;
        int col = Integer.parseInt(request.getParameter("Col"));
        Point move = game.playerMove(col,popout);
        if(move == null){
            if(popout){
                SessionUtils.setAttribute(request,"ErrorMove",String.format("Your popout move is not legal"));
            }
            else{
                SessionUtils.setAttribute(request,"ErrorMove",String.format("col: {0} is fulled. please enter another col in range",(col + 1)));

            }
        }
    }
}
