package techexercise;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * List of user database given from an administrative view.
 */
@WebServlet("/Registry")
public class Registry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registry() {	
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DBConnection.createConnection(getServletContext());
			Connection connection = DBConnection.connection;
			
			String testuname = request.getParameter("username");
			String testpass = request.getParameter("password");
			
			String adminquery = "SELECT * FROM registry WHERE id=1;";
			PreparedStatement adminstate = connection.prepareStatement(adminquery);
			ResultSet adminset = adminstate.executeQuery();
			
			PrintWriter out = response.getWriter();
			
			if (testuname.contentEquals(adminset.getString("uname")) && testpass.contentEquals(adminset.getString("pass")))
			{
				//TODO
				out.println("<!DOCTYPE html><html><head><title>User Registry</title><style ");
			}
			else
			{
				//TODO Output error page
			}
			
			
			
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
