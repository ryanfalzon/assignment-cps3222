package servlets;

import UnitTesting.Agent;
import UnitTesting.MessagingSystem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MessagingSystem system = new MessagingSystem();
        HttpSession session = request.getSession(true);

        // Search for source agent
        Agent sourceAgent = null;
        for(int i = 0; i < StaticVariables.agents.size(); i++){
            if(StaticVariables.agents.get(i).getId().equals(StaticVariables.currentUserID)){
                sourceAgent = StaticVariables.agents.get(i);
            }
        }

        // Logout and delete source agent
        system.logout(sourceAgent);
        StaticVariables.agents.remove(sourceAgent);
        session.setAttribute("approval", "");
        StaticVariables.currentUserID = "";
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
