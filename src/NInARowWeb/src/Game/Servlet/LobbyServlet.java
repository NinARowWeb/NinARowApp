package Game.Servlet;

import Game.Utils.ErrorUtils;
import Game.Utils.ServletUtils;
import Game.Utils.SessionUtils;
import boards.Board;
import boards.BoardsManager;
import com.google.gson.Gson;
import responses.LobbyContentResponse;
import users.User;
import users.UserManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@WebServlet(name = "lobbyServlet", urlPatterns = {"/lobbyArea"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class LobbyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BoardsManager boardsManager = ServletUtils.getBoardsManager(getServletContext());
        UserManager userManager = ServletUtils.getUserManager(getServletContext());
        Gson gson = new Gson();
        String jsonResponse;
        Set<User> users = userManager.getUsers();
        List<Board> boards = boardsManager.getBoards();
        jsonResponse = gson.toJson(new LobbyContentResponse(users,boards,ErrorUtils.getErrorUploadMessage()));
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        Collection<Part> parts = request.getParts();
        BoardsManager boardsManager = ServletUtils.getBoardsManager(getServletContext());
        try{
            for(Part gameDetails : parts){
                boardsManager.addGame(gameDetails.getInputStream(), SessionUtils.getUsername(request));
            }
        }
        catch (Exception ex){
            ErrorUtils.setErrorUploadGameError(new Error(ex.getMessage()));
        }

    }
}
