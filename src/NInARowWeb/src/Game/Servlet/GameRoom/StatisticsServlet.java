package Game.Servlet.GameRoom;

import Game.Utils.ServletUtils;
import Game.Utils.SessionUtils;
import boards.Board;
import boards.BoardsManager;
import com.google.gson.Gson;
import constants.Constants;
import responses.BoardGameContentResponse;
import responses.StatisticsContentResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StatisticsServlet", urlPatterns = {"/Statistics"})
public class StatisticsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String boardName = SessionUtils.getAttribute(request,Constants.BOARD_GAME);
        BoardsManager manager = ServletUtils.getBoardsManager(getServletContext());
        Board game = manager.getGameBoard(boardName);
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String jsonResponse;
        if(game.getStatus() == "GAMING")
            jsonResponse = gson.toJson(new StatisticsContentResponse(game.getPlayerName(game.getTurn()),game.getTime(),game.getTarget(),game.getVarient(),game.getPlayerName((game.getTurn() + 1)%game.getAmountOfRegistersPlayers()),game.getStatus()));
        else
            jsonResponse = gson.toJson(new StatisticsContentResponse(null,null,game.getTarget(),game.getVarient(),null,game.getStatus()));
        out.print(jsonResponse);
    }
}
