package servlets;

import backend.Mailbox;

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
                out.write(String.valueOf(current.hasMessages()));
            }
            else{
                out.write("No Messages");
            }
        }
        else if(request.getParameter("next") != null){
            if(current != null){
                out.write(current.consumeNextMessage());
            }
            else{
                out.write("No Messages");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
