package controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans_modele.BDImage;
import dao_modele.BDImageDao;
import dao_modele.ConnexionDao;

@WebServlet("/lecture")
public class BDImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private BDImageDao bdImageDao;
	

	public void init() throws ServletException{
		ConnexionDao.getInstance();
	}
	
    public BDImageServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.bdImageDao = connectDao.getBDImageDao();
		
		try {		
			
			int idBd = Integer.parseInt(request.getParameter("param1"));
			List<BDImage> listImage = bdImageDao.selectAllImages(idBd);
			request.setAttribute("listImage", listImage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lecturePage.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ConnexionDao connectDao = ConnexionDao.getInstance();
		this.bdImageDao = connectDao.getBDImageDao();
		
		
		try {
			int idBd = Integer.parseInt(request.getParameter("param1"));	
			List<BDImage> listImage = bdImageDao.selectAllImages(idBd);
			request.setAttribute("listImage", listImage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lecturePage.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
}
