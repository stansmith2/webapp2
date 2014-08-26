package net.kronosjp.webapp2;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.kronosjp.dao.KronosDBDAO;
import net.kronosjp.dto.RecipeDto;
import net.kronosjp.exception.ValidationException;
import net.kronosjp.util.Validation;

/**
 * Servlet implementation class RecipeSearch
 */
@WebServlet("/RecipeSearch")
public class RecipeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecipeSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			
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

		String search = request.getParameter("search");

		try {
			// 検索キーの入力チェック
			Validation.checkRecipeSearchKey(search);

		} catch (ValidationException e) {
			
			// チェックエラー場合
			request.setAttribute("errorMsg", e.getMessage());
			
			List<RecipeDto> list = KronosDBDAO.getRecipeList();
			request.setAttribute("list", list);

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_list.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 検索を実施
		List<RecipeDto> searchRuslt = KronosDBDAO.searchRecipe(search);

		// 該当するユーザが存在しない場合
		if (searchRuslt.size() == 0) {
			request.setAttribute("errorMsg", "検索結果が0件です");

			// searchテーブル情報を取得し、セット
			List<RecipeDto> list = KronosDBDAO.getRecipeList();
			request.setAttribute("list", list);

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_list.jsp");
			dispatcher.forward(request, response);
			return;
		}

		request.setAttribute("list", searchRuslt);

		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_list.jsp");
		dispatcher.forward(request, response);


	}

}
