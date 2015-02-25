package com.pepe.tutorial.guestbook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.util.Properties;

/**
 * Servlet implementation class GuestbookServlet
 */
public class GuestbookServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1459285444085409737L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestbookServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("testing") == null) {
            response.setContentType("text/plain");
            response.getWriter().println("Hello, this is a testing servlet. \n\n");
            Properties p = System.getProperties();
            p.list(response.getWriter());

        } else {
            UserService userService = UserServiceFactory.getUserService();
            User currentUser = userService.getCurrentUser();

            if (currentUser != null) {
                response.setContentType("text/plain");
                response.getWriter().println("Hello, " + currentUser.getNickname());
            } else {
                response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
            }
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
