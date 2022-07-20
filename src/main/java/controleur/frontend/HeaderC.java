package controleur.frontend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategorieD;
import dao.SousCategorieD;
import modele.CategorieM;
import modele.SousCategorieM;

/**
 * Servlet implementation class HeaderC
 */
@WebServlet("/header")
public class HeaderC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeaderC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		if (session.getAttribute("isConnected") == null) {
			session.setAttribute("isConnected", false);
		}
		
		CategorieD categorieD = new CategorieD();
		ArrayList<CategorieM> listeCategories = new ArrayList<>();
		listeCategories = categorieD.read();
		request.setAttribute("listeCategories", listeCategories);
		
		
		SousCategorieD sousCategorieD = new SousCategorieD();
		ArrayList<SousCategorieM> listeSousCategories = new ArrayList<>();
		listeSousCategories = sousCategorieD.read();
		request.setAttribute("listeSousCategories", listeSousCategories);
		
		request.getRequestDispatcher("vue/frontend/header.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
