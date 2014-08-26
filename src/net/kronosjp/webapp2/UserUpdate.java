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
import net.kronosjp.exception.ValidationException;
import net.kronosjp.util.Validation;

/**
 * Servlet implementation class UserUpdate
 */
@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdate() {
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


		// ユーザ情報を取得
		UserDto dto = (UserDto) session.getAttribute("loginUser");
		
		//　更新するユーザ情報をセット
		String userId = request.getParameter("userId");
		String beforeUserId = request.getParameter("beforeUserId");
		String name = request.getParameter("name");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		// 更新か挿入か判定する情報を取得
		String flg = request.getParameter("flg");
		
		try {
			// 入力チェック
			Validation.checkUpdateUserDate(userId, name, password1, password2);
			Validation.CheckUserId(userId);
			
		} catch (ValidationException e) {
			
			UserDto beforeDto = new UserDto();
			beforeDto.setUserId(userId);
			beforeDto.setName(name);
			beforeDto.setPassword(password1);
			
			request.setAttribute("userDto", beforeDto);		
			
			//　エラーメッセージをセット
			request.setAttribute("checkError", e.getMessage());
			
			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/user_detail.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		if ("1".equals(flg)) {

			// ユーザ情報の更新
			KronosDBDAO.userUpdate(userId, beforeUserId, name, password1, dto);
			
		} else {
		
			// userテーブルにユーザを追加
			KronosDBDAO.userAdd(name, userId, password1, dto);
			
		}
		
		// userテーブル情報を取得
		List<UserDto> list = KronosDBDAO.getUserList();
		request.setAttribute("list", list);

		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/user_list.jsp");
		dispatcher.forward(request, response);

	}

}
