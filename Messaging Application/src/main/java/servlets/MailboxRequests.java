package servlets;

import UnitTesting.Mailbox;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MailboxRequests")
public class MailboxRequests extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Mailbox current = null;

        // Get mailbox of logged in user
        for(int i = 0; i < StaticVariables.mailboxes.size(); i++){
            if(StaticVariables.mailboxes.get(i).getAgentID().equals(session.getAttribute("id"))){
                current = StaticVariables.mailboxes.get(i);
            }
        }

        // Check which button has been pressed
        if(request.getParameter("count") != null){
            if(current != null){
                session.setAttribute("hasmessages", current.hasMessages());
            }
            else{
                session.setAttribute("hasmessages", "false");
            }
            RequestDispatcher rd  = request.getRequestDispatcher("/message.jsp");
            rd.forward(request,response);
        }
        else if(request.getParameter("next") != null){
            if(current != null){
                session.setAttribute("message", current.consumeNextMessage());
            }
            else{
                session.setAttribute("message", "Mailbox Empty");
            }
            RequestDispatcher rd  = request.getRequestDispatcher("/message.jsp");
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
