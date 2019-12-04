package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans_modele.BeanException;

import formulaires_modele.LoginForm;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		request.setAttribute("email", "");
		request.setAttribute("password1", "");
		request.getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginForm	logForm = new LoginForm();
		try {
			logForm.verifierLogin(request, response);			
			
		}catch(BeanException e) {
			e.printStackTrace();
		}
		request.setAttribute("logForm", logForm);
		response.sendRedirect(logForm.linkPage);
	}	
}