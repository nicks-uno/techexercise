package techexercise;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Account registration servlet.
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("username");
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		
		Date date = new Date(System.currentTimeMillis());
		String regdate = date.toString();
		
		PrintWriter out = response.getWriter();
		
		try {
			DBConnection.createConnection(getServletContext());
			Connection connection = DBConnection.connection;
			
			String tempstate  = "INSERT INTO registry(uname, fname, lname, email, pass, regdate) VALUES('" + uname + "', '" + fname + "', '" + lname + "', '" + email + "', '" + pass + "', '" + regdate + "');";
			PreparedStatement insertStatement = connection.prepareStatement(tempstate);
			insertStatement.executeUpdate();
			
			response.setContentType("text/html");
			
			out.println("<html>\n" +
					"<head>\n" +
					"<title>Registration Successful</title>\n" +
					"</head>\n" +
					"<body>\n");
			out.println("<p style=\"font-family:Times New Roman; font-size:14; text-align:center\" align=\"center\">\n" +
					"Congratulations " + uname + "! Your account has been successfully registered.\n" +
					"</p>\n" +
					"</body>\n");
			out.println("<footer style=\"font-family:Times New Roman; font-size:14; text-align:center\" align=\"center\">\n" +
					"<a href=\"/techexercise/admin.html\">Click here to view all users registered.</a>\n" +
					"</footer>\n" +
					"</html>");
			out.close();
		} catch (IOException | SQLException | ClassNotFoundException e) {
			out.println("<!DOCTYPE html><html><head><title>Registration Failure</title><style> body { text-align:left; font-family:Times New Roman; font-size:14; } h1 { text-align:left; font-family:Times New Roman; font-size:16; font-weight: bold; }");
			out.println("footer { text-align:center; font-family:Times New Roman; font-size:14; }</style></head>");
			out.println("<body><h1>Registration Failure</h1> <br> Failed to connect to the main database. Try again later.");
			out.println("</body><footer><a href=\"/techexercise/Welcome.html\">Register an account.</a></footer></html>");
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
