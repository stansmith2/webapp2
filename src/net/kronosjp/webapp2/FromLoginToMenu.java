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
import net.kronosjp.dto.UserDto;
import net.kronosjp.exception.ValidationException;
import net.kronosjp.util.Validation;

/**
 * Servlet implementation class FromLoginToMenu
 */
@WebServlet("/FromLoginToMenu")
public class FromLoginToMenu extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//　エラー画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/error.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード設定
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// ログイン画面から送信された値を取得
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		try {
			// 入力チェック
			Validation.checkLoginKey(id, password);
			
		} catch (ValidationException e) {

			// エラーメッセージをセット
			request.setAttribute("erroMg", e.getMessage());

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		UserDto dto = KronosDBDAO.getLoginUserInfo(id, password);

		if (dto == null) {
			// エラーメッセージを設定
			request.setAttribute("erroMg", "ユーザID/パスワードが無効です");

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// セッションにユーザ情報を保持
		HttpSession session = request.getSession(true);
		session.setAttribute("loginUser", dto);

		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/menu.jsp");
		dispatcher.forward(request, response);
	}

}
