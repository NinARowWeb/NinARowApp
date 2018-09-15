package Game.Servlet;

import Game.Utils.ErrorUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClearUploadErrorServlet", urlPatterns = {"/ClearUploadError"})
public class ClearUploadErrorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ErrorUtils.setErrorUploadGameError(null);
    }
}
