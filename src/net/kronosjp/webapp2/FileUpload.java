package net.kronosjp.webapp2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.kronosjp.dao.KronosDBDAO;
import net.kronosjp.dto.RecipeDto;
import net.kronosjp.dto.UserDto;
import net.kronosjp.exception.ValidationException;
import net.kronosjp.util.Validation;

@WebServlet(name="FileUpload", urlPatterns={"/FileUpload"})
@MultipartConfig(fileSizeThreshold=5000000,maxFileSize=10000000,location="/Users/tatsuya/Documents/workspace/webapp2/WebContent/img")
public class FileUpload extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

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
		response.setCharacterEncoding("UTF-8");

		// ユーザ情報を取得
		UserDto dto = (UserDto) session.getAttribute("loginUser");

		// パラメータを取得
		String recipeId = request.getParameter("recipeNo2");
		String title = request.getParameter("title");
		String material = request.getParameter("material");
		String process = request.getParameter("process");        
		String cookingTime = request.getParameter("cookingTime");
		String flg = request.getParameter("flg");

		// <INPUT type=”file” name="file”> で指定した名前より取得
		Part part = request.getPart("file");
		
		// ファイル名の取得
		String fileName = getFilename(part);
		
		int result;
		// 入力チェック
		try {
			result = Validation.checkUpdateRecipeData(title, material, process, part, cookingTime, flg);

		} catch (ValidationException e) {

			RecipeDto beforeDto = new RecipeDto();
			beforeDto.setRecipeId(recipeId);
			beforeDto.setTitle(title);
			beforeDto.setMaterial(material);
			beforeDto.setProcedureiCooking(process);
			beforeDto.setCookingMin(cookingTime);

			request.setAttribute("recipeDto", beforeDto);
			
			if ("1".equals(flg)) {
			request.setAttribute("detailFlg", "1");
			
			}
			//　エラーメッセージをセット
			request.setAttribute("checkError", e.getMessage());

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_detail.jsp");
			dispatcher.forward(request, response);
			return;

		}
		
		InputStream gazou = part.getInputStream();
		
		// recipeテーブルを更新
		if ("1".equals(flg)) {
			KronosDBDAO.RecipeUpdate(recipeId, title, material, process,  gazou, fileName, part.getContentType(), Integer.parseInt(cookingTime), dto, result);
			
			// recipeテーブル情報を取得
			List<RecipeDto> list = KronosDBDAO.getRecipeList();
			request.setAttribute("list", list);

			//　画面遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_list.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// recipeテーブルにデータを追加
		KronosDBDAO.RecipeAdd(title, material, process,  gazou, fileName, part.getContentType(), Integer.parseInt(cookingTime), dto);

		// recipeテーブル情報を取得
		List<RecipeDto> list = KronosDBDAO.getRecipeList();
		request.setAttribute("list", list);

		//　画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/html/recipe_list.jsp");
		dispatcher.forward(request, response);
		
	}

	
	/**
	 * ファイル名を取得
	 * @param part　画像データ
	 * @return 画像ファイル名
	 */
	private String getFilename(Part part) {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}


