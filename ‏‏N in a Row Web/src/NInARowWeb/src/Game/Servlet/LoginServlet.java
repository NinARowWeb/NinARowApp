package Game.Servlet;

import Game.Utils.ServletUtils;
import Game.Utils.SessionUtils;
import constants.Constants;
import users.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String usernameFromSession = SessionUtils.getUsername(request);
        UserManager userManager = ServletUtils.getUserManager(getServletContext());
        if (usernameFromSession == null) {
            //user is not logged in yet
            String usernameFromParameter = request.getParameter(Constants.USERNAME);
            if (usernameFromParameter == null) {
                //no username in session and no username in parameter -
                //redirect back to the index page
                //this return an HTTP code back to the browser telling it to load
                response.sendError(403,"You need to insert a name");
//                response.sendRedirect(Constants.SIGN_UP_URL);
            } else {
                //normalize the username value
                usernameFromParameter = usernameFromParameter.trim();
                synchronized (this) {
                    if (userManager.isUserExists(usernameFromParameter)) {
                        String errorMessage = "Username " + usernameFromParameter + " already exists. Please enter a different username.";
                        // username already exists, forward the request back to index.jsp
                        // with a parameter that indicates that an error should be displayed
                        // the request dispatcher obtained from the servlet context is one that MUST get an absolute path (starting with'/')
                        // and is relative to the web app root
                        // see this link for more details:
                        // http://timjansen.github.io/jarfiller/guide/servlet25/requestdispatcher.xhtml
                        response.sendError(403,errorMessage);
//                        request.setAttribute(Constants.USER_NAME_ERROR, errorMessage);
                    } else {
                        String PlayerType = request.getParameter(Constants.TYPE);
                        boolean computer = PlayerType.equals("True") ? true : false;
                        //add the new user to the users list
                        userManager.addUser(usernameFromParameter,computer);
                        //set the username in a session so it will be available on each request
                        //the true parameter means that if a session object does not exists yet
                        //create a new one
                        request.getSession(true).setAttribute(Constants.USERNAME, usernameFromParameter);

                        //redirect the request to the chat room - in order to actually change the URL
                        response.sendRedirect(Constants.LOBBY_ROOM_URL);
                    }
                }
            }
        } else {
            //user is already logged in
            response.sendRedirect(Constants.LOBBY_ROOM_URL);
        }
    }
}
