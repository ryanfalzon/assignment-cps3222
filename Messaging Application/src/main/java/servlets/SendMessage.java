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
import java.io.PrintWriter;

@WebServlet(name = "SendMessage")
public class SendMessage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        MessagingSystem system = new MessagingSystem();

        // Check if target agent exists
        Agent sourceAgent = null;
        Agent targetAgent = null;
        for(int i = 0; i < StaticVariables.agents.size(); i++){
            if(StaticVariables.agents.get(i).getId().equals(request.getParameter("targetagent"))){
                targetAgent = StaticVariables.agents.get(i);
            }
        }

        // If target agent does not exist
        if(targetAgent == null){
            targetAgent = new Agent(request.getParameter("targetagent"), "");
            StaticVariables.agents.add(targetAgent);
        }

        // Search for source agent
        for(int i = 0; i < StaticVariables.agents.size(); i++){
            if(StaticVariables.agents.get(i).getId().equals(StaticVariables.currentUserID)){
                sourceAgent = StaticVariables.agents.get(i);
            }
        }

        // Send message
        out.write("Session ID: " + StaticVariables.sessionId);
        session.setAttribute("error", system.sendMessage(sourceAgent, targetAgent, request.getParameter("message")));
        session.setAttribute("hasmessages", "");
        session.setAttribute("message", "");

        // Check if agent has exceeded message limit and needs to be logged out
        if(session.getAttribute("error").toString().contains("10")){
            RequestDispatcher rd  = request.getRequestDispatcher("/automaticlogout.jsp");
            rd.forward(request,response);
        }
        else{
            RequestDispatcher rd  = request.getRequestDispatcher("/message.jsp");
            rd.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
