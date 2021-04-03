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
		
		PrintWriter out = response.getWriter();
		
		try {
			DBConnection.createConnection(getServletContext());
			Connection connection = DBConnection.connection;
			
			String testuname = request.getParameter("username");
			String testpass = request.getParameter("password");
			
			String adminquery = "SELECT * FROM registry WHERE id=1;";
			PreparedStatement adminstate = connection.prepareStatement(adminquery);
			ResultSet adminset = adminstate.executeQuery();

			adminset.next();
			if (testuname.contentEquals(adminset.getString("uname")) && testpass.contentEquals(adminset.getString("pass")))
			{
				String tempquery = "SELECT * FROM registry;";
				PreparedStatement resultstate = connection.prepareStatement(tempquery);
				ResultSet resultset = resultstate.executeQuery();
				
				out.println("<!DOCTYPE html><html><head><title>User Registry</title><style> body { text-align:left; font-family:serif; font-size:14; } h1 { text-align:center; } table { width:400px; }</style></head>");
				out.println("<body><h1>Registry</h1><br><table><tr><th>id</th><th>Username</th><th>Last Name</th><th>First Name</th><th>Email</th><th>Password</th><th>Registration Date</th></tr>");
				while (resultset.next())
				{
					out.printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>**********</td><td>%s</td></tr>\n", resultset.getString("id"), resultset.getString("uname"), resultset.getString("lname"), resultset.getString("fname"), resultset.getString("email"), resultset.getString("regdate"));
				}
				out.println("</table></body></html>");
				out.close();
			}
			else
			{
				out.println("<!DOCTYPE html><html><head><title>Login Failure</title><style> body { text-align:left; font-family:Times New Roman; font-size:12; } h1 { text-align:left; font-family:Times New Roman; font-size:16; font-weight: bold; }</style></head>");
				out.println("<body><h1>Login Failure</h1> <br> Incorrect login and password. Make sure you are logging in with the administrator account and try again by clicking <a href=\"/techexercise/admin.html\">here</a>");
				out.println("</body></html>");
				out.close();
			}
			
		} catch (IOException | SQLException | ClassNotFoundException e) {
			out.println("<!DOCTYPE html><html><head><title>Listing Failure</title><style> body { text-align:left; font-family:Times New Roman; font-size:12; } h1 { text-align:left; font-family:Times New Roman; font-size:16; font-weight: bold; }</style></head>");
			out.println("<body><h1>Listing Failure</h1> <br> Failed to connect to the main database. Try again later.");
			out.println("</body></html>");
			out.close();
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
