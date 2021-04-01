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
		
		try {
			DBConnection.createConnection(getServletContext());
			Connection connection = DBConnection.connection;
			
			String tempstate  = "INSERT INTO registry VALUES('" + uname + "', '" + fname + "', '" + lname + "', '" + email + "', '" + pass + "', '" + regdate + "');";
			PreparedStatement insertStatement = connection.prepareStatement(tempstate);
			insertStatement.executeUpdate();
		} catch (IOException | SQLException e) {
			// TODO Output an error webpage
			e.printStackTrace();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>\n" +
				"<head>\n" +
				"<title>Registration Successful</title>\n" +
				"</head>\n" +
				"<body>\n");
		out.println("<p style=\"font-family:Times New Roman; font-size:12\" align=\"center\">\n" +
				"Congratulations " + uname + "! Your account has been successfully registered.\n" +
				"</p>\n" +
				"</body>\n");
		out.println("<footer style=\"font-family:Times New Roman; font-size:12\" align=\"center\">\n" +
				"<a href=\"/WEB-INF/admin.html\">Click here to view all users registered.</a>\n" +
				"</footer>\n" +
				"</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
