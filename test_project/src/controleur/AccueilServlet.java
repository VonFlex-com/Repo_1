package controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_modele.BDItemAccueil;
import dao_modele.BDItemDao;
import dao_modele.ConnexionDao;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet(urlPatterns="/accueil", loadOnStartup=1)

public class AccueilServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BDItemDao bdItemDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException{
		ConnexionDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.bdItemDao = connectDao.getBDItemDao();

		try {

			List <BDItemAccueil> listBDItemAccueil = bdItemDao.listerBDItem();
			request.setAttribute("listBDItemAccueil", listBDItemAccueil);
			RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/accueilPage.jsp");
			dispatcher.forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
