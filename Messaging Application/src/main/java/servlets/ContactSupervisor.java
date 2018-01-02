package servlets;

import backend.Agent;
import backend.JohnDoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "ContactSupervisor")
public class ContactSupervisor extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        HttpSession session = request.getSession(true);

        // Create an agent and get a login key
        Agent current = new Agent(request.getParameter("id"), request.getParameter("name"));
        current.contactSupervisor(new JohnDoe());

        // Output some details
        if(current.getKey() != null) {
            StaticVariables.agents.add(current);
            session.setAttribute("approval", "Request Approved");
            session.setAttribute("id", current.getId());
            session.setAttribute("name", current.getName());
            session.setAttribute("loginkey", current.getKey().getKey());
        }
        else{
            session.setAttribute("approval", "Request Not Approved");
            session.setAttribute("id", request.getParameter("id"));
            session.setAttribute("name", request.getParameter("name"));
            session.setAttribute("loginkey", "N/A");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/loginkey.jsp");
        rd.forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
