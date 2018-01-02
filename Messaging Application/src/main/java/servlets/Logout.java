package servlets;

import backend.Agent;
import backend.MessagingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        // Search for source agent
        Agent sourceAgent = null;
        for(int i = 0; i < StaticVariables.agents.size(); i++){
            if(StaticVariables.agents.get(i).getId().equals(session.getAttribute("id"))){
                sourceAgent = StaticVariables.agents.get(i);
            }
        }

        // Logout and delete source agent
        StaticVariables.agents.remove(sourceAgent);
        session.setAttribute("approval", "");
        session.setAttribute("id", "");
        session.setAttribute("name", "");;
        session.setAttribute("loginkey", "");

        // Redirect user to index page
        RequestDispatcher rd  = request.getRequestDispatcher("/index.jsp");
        rd.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
