package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	public HttpSession session;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("email", "");
		request.setAttribute("password1", "");
		session = request.getSession( false );
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		session = request.getSession( false );
		session.invalidate();
		request.getRequestDispatcher( "/WEB-INF/loginPage.jsp" ).forward( request, response );
	}
}