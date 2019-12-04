package controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans_modele.Genre;
import dao_modele.ConnexionDao;
import dao_modele.GenreDao;


/**
 * Servlet implementation class GenreServlet
 */
@WebServlet("/genres")
public class GenreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private GenreDao genreDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GenreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.genreDao = connectDao.getGenreDao();


		try {
			List <Genre> listGenre = genreDao.listerGenre();
			request.setAttribute("listGenre", listGenre);
			RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/genrePage.jsp");
			dispatcher.forward(request, response);

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
