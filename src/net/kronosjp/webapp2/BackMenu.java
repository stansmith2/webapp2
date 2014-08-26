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
import net.kronosjp.dto.UserDto;

/**
 * Servlet implementation class BackMenu
 */
@WebServlet("/BackMenu")
public class BackMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackMenu() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginUser") == null) {
			
			// エラーメッセージを設定
			request.setAttribute("erroMg", "ユーザID/パスワードを入力し、ログインしてください");

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// userテーブル情報を取得
		List<UserDto> list = KronosDBDAO.getUserList();
		request.setAttribute("list", list);

		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/menu.jsp");
		dispatcher.forward(request, response);

	}

}
