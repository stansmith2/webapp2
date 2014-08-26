package net.kronosjp.webapp2;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kronosjp.dao.KronosDBDAO;
import net.kronosjp.dto.RecipeDto;

/**
 * Servlet implementation class ImageGet
 */
@WebServlet("/ImageGet")
public class ImageGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageGet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// レシピIDを取得
		String recipeId = request.getParameter("id");

		// レシピIDに紐づく画像データとMIME/TYPEを取得
 		RecipeDto dto = KronosDBDAO.getRecipeImage(recipeId);

		// 出力する画像データのタイプをセット
		response.setContentType(dto.getMimeType());

		BufferedOutputStream output = null;

		try {
			// 出力ストリームを取得
			output = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];

			// 画像のデータを出力ストリームに書き込む
			for (int length; (length = dto.getPictureData().read(buffer)) > -1;) {
				output.write(buffer, 0, length);
			}
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (output != null) {
				output.close();
				
			}
		}

	}

}
