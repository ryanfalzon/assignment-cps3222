package servlets;

import backend.Agent;
import backend.JohnDoe;

import java.io.IOException;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet(name = "ContactSupervisor")
public class ContactSupervisor extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        // Create an agent and get a login key
        Agent current = new Agent(request.getParameter("id"), request.getParameter("name"));
        current.contactSupervisor(new JohnDoe());
        StaticVariables.agents.add(current);

        // Output some details
        PrintWriter out = response.getWriter();
        out.print("POST\nAgent ID: " + current.getId() + "\nAgent Name: " + current.getName() + "\nLogin Key: " + current.getKey().getKey());
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
