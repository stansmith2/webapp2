package net.kronosjp.webapp2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.kronosjp.dao.KronosDBDAO;
import net.kronosjp.dto.RecipeDto;

/**
 * Servlet implementation class FromRecipeListToRecipeDetail
 */
@WebServlet("/FromRecipeListToRecipeDetail")
public class FromRecipeListToRecipeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FromRecipeListToRecipeDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			
			// エラーメッセージを設定
			request.setAttribute("erroMg", "ユーザID/パスワードを入力し、ログインしてください");

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 文字コード設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		String id = request.getParameter("id");
		RecipeDto dto = KronosDBDAO.getRecipeInfo(id);
		
		request.setAttribute("recipeDto", dto);
		request.setAttribute("detailFlg", "1");
		
		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_detail.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("detailFlg", null);
		RecipeDto dto = new RecipeDto();
		dto.setRecipeId(String.valueOf(KronosDBDAO.getRecipeCount()));

		request.setAttribute("recipeDto", dto);
		
		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_detail.jsp");
		dispatcher.forward(request, response);	
	}

}
