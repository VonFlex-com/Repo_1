package controleur;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_modele.BeanException;
import formulaires_modele.RegisterForm;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		request.setAttribute("email", "");
		request.setAttribute("pseudo", "");
		request.setAttribute("password1", "");
		request.setAttribute("password2", "");		
		request.getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegisterForm regForm = new RegisterForm();
		try {
			regForm.verifierCreationCompte(request, response);
		} catch (BeanException e) {
			e.printStackTrace();
		}
		request.setAttribute("regForm", regForm);
		
		request.getRequestDispatcher("/WEB-INF/registerPage.jsp").forward(request, response);
	}
}
