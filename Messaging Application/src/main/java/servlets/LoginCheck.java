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

@WebServlet(name = "LoginCheck")
public class LoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Agent current = null;
        HttpSession session = request.getSession(true);

        // Check which button has been pressed
        if (request.getParameter("loginbutton") != null) {
            // Get the agent and the key
            for (int i = 0; i < StaticVariables.agents.size(); i++) {
                if (StaticVariables.agents.get(i).getId().equals(request.getParameter("id"))) {
                    current = StaticVariables.agents.get(i);
                }
            }

            // Check if current agent exists
            if (current != null) {
                MessagingSystem system = new MessagingSystem();
                String message = system.login(current, request.getParameter("loginkey"));
                if (message.equals("Login Successful")) {
                    StaticVariables.currentUserID = current.getId();
                    session.setAttribute("id", current.getId());
                    session.setAttribute("error", "");
                    session.setAttribute("hasmessages", "");
                    session.setAttribute("message", "");
                    RequestDispatcher rd = request.getRequestDispatcher("/message.jsp");
                    rd.forward(request, response);
                } else {
                    session.setAttribute("errorlogin", message);
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                }
            } else {
                session.setAttribute("errorlogin", "ID Does Not Exist");
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
        }
        else if (request.getParameter("backButton") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
