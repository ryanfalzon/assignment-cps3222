package servlets;

import backend.Agent;
import backend.LoginKey;
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

@WebServlet(name = "LoginCheck")
public class LoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Agent current = null;
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);

        // Get the agent and the key
        for(int i = 0; i < StaticVariables.agents.size(); i++){
            if(StaticVariables.agents.get(i).getId().equals(request.getParameter("id"))){
                current = StaticVariables.agents.get(i);
            }
        }

        // Check if current agent exists
        if(current != null){
            MessagingSystem system = new MessagingSystem();
            if(system.login(current, request.getParameter("loginkey")).equals("Login Successful")){
                session.setAttribute("id", current.getId());
                RequestDispatcher rd  = request.getRequestDispatcher("/message.jsp");
                rd.forward(request,response);
            }
        }
        else{
            out.write("ID Does Not Exist!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
