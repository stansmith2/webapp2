package net.kronosjp.util;
import javax.servlet.http.Part;

import net.kronosjp.dao.KronosDBDAO;
import net.kronosjp.dto.UserDto;
import net.kronosjp.exception.ValidationException;

public class Validation {

	public static void checkPassword(String password1, String password2) throws ValidationException {

		if (password1.equals("") || password2.equals("")) {
			throw new ValidationException("パスワードを入力してください");
		}

		if (!password1.equals(password2)) {
			throw new ValidationException("パスワードが一致しません");
		}

	}

	/**
	 * ログイン時に入力された値をチェック
	 * @param id ユーザID
	 * @param password パスワード
	 * @throws ValidationException  入力チェックエラー
	 */
	public static void checkLoginKey(String id, String password) throws ValidationException {

		// 値の有無をチェック
		if (id == null || "".equals(id)) {
			throw new ValidationException("ユーザIDを入力してください");
		}

		// 桁数をチェック
		if (id.length() > 20) {
			throw new ValidationException("ユーザIDは20文字以内です");
		}

		// 値の有無をチェック
		if (password == null || "".equals(password)) {
			throw new ValidationException("パスワードを入力してください");
		}

		// 桁数をチェック
		if (password.length() > 20) {
			throw new ValidationException("パスワードは20文字以内です");
		}
	}

	/**
	 * ユーザ検索画面で入力された検索キーのチェック
	 * @param search　検索キー
	 * @throws ValidationException チェックエラー
	 */
	public static void checkUserSearchKey(String search) throws ValidationException {
		// 検索文字が入力されていない場合
		if (search == null || "".equals(search)) {

			throw new ValidationException("");
		}

		// 桁数をチェック
		if (search.length() > 20) {
			throw new ValidationException("20文字以内で入力してください");

		}
	}

	/**
	 * ユーザデータ更新時の入力チェック
	 * @param userId ユーザID
	 * @param name　氏名
	 * @param password1 パスワード
	 * @param password2 パスワード(確認用)
	 * @throws ValidationException 
	 */
	public static void checkUpdateUserDate(String userId, String name,
			String password1, String password2) throws ValidationException {

		// 入力チェック
		if (userId == null || "".equals(userId)) {
			throw new ValidationException("ユーザIDを入力してください");
		}

		// 桁数をチェック
		if (userId.length() > 20) {
			throw new ValidationException("ユーザIDは20文字以内です");
		}

		// 入力チェック
		if (name == null || "".equals(name)) {
			throw new ValidationException("氏名を入力してください");
		}

		// 桁数をチェック
		if (name.length() > 20) {
			throw new ValidationException("氏名は20文字以内です");
		}

		// 入力チェック
		if (password1 == null || "".equals(password1)) {
			throw new ValidationException("パスワードを入力してください");
		}

		// 入力チェック
		if (password2 == null || "".equals(password2)) {
			throw new ValidationException("パスワード(確認用)を入力してください");
		}

		if (!password1.equals(password2)) {
			throw new ValidationException("パスワードが一致しません");
		}

		// 桁数をチェック
		if (password1.length() > 20) {
			throw new ValidationException("パスワードは20文字以内です");
		}
	}

	/**
	 * userテーブルにユーザIDが登録されているか確認する
	 * @param userId
	 * @throws ValidationException 
	 */
	public static void CheckUserId(String userId) throws ValidationException {

		//　入力されたユーザIDでuserテーブルを検索
		UserDto dto = KronosDBDAO.checkUserId(userId);

		if (dto != null) {
			// 入力されたユーザIDが存在する場合
			throw new ValidationException("既に存在するユーザIDです");

		}
	}

	/**
	 * レシピ検索画面で入力された検索キーのチェック
	 * @param search　検索キー
	 * @throws ValidationException チェックエラー
	 */
	public static void checkRecipeSearchKey(String search) throws ValidationException {
		// 検索文字が入力されていない場合
		if (search == null || "".equals(search)) {

			throw new ValidationException("");
		}

		// 桁数をチェック
		if (search.length() > 100) {
			throw new ValidationException("100文字以内で入力してください");

		}
	}

	/**
	 * レシピ更新時の入力チェック
	 * @param title タイトル
	 * @param material 材料
	 * @param process　手順
	 * @param part 画像データ
	 * @param cookingTime　調理時間
	 * @throws ValidationException 
	 */
	public static int checkUpdateRecipeData(String title, String material,
			String process, Part part, String cookingTime, String flg) throws ValidationException {

		if (title == null || "".equals(title)) {
			throw new ValidationException("タイトルを入力してください");
		}

		if (title.length() > 100) {
			throw new ValidationException("タイトルは100文字以内で入力してください");
		}

		if (material == null || "".equals(material)) {
			throw new ValidationException("素材を入力してください");
		}

		if (material.length() > 1000) {
			throw new ValidationException("素材は1000文字以内で入力してください");
		}

		if (title.length() > 100) {
			throw new ValidationException("タイトルは100文字以内で入力してください");
		}

		if (process == null || "".equals(process)) {
			throw new ValidationException("手順を入力してください");
		}

		if (process.length() > 1000) {
			throw new ValidationException("手順は1000文字以内で入力してください");
		}

		if (cookingTime == null || "".equals(cookingTime)) {
			throw new ValidationException("調理時間を入力してください");
		}

		if (cookingTime.length() > 3) {
			throw new ValidationException("調理時間は3桁で入力してください");
		}

		try {
			Integer.parseInt(cookingTime);

		} catch (NumberFormatException e) {
			throw new ValidationException("調理時間は数値を入力してください");

		}

		// レシピ変更
		if (flg != null) {
			// ファイルが選択されていない場合
			if (part.getContentType().equals("application/octet-stream")) {
				return 5;
			}

			// ファイルが選択されている場合
			if (!(part.getContentType().equals("image/jpeg")) & !(part.getContentType().equals("image/png"))) {
				throw new ValidationException("拡張子が不正です:" + part.getContentType());
			}

		// レシピ追加
		} else {
			if (part.getContentType().equals("application/octet-stream")) {
				throw new ValidationException("ファイルを選択してください");

			}

			if (!(part.getContentType().equals("image/jpeg")) & !(part.getContentType().equals("image/png"))) {
				throw new ValidationException("拡張子が不正です:" + part.getContentType());
			}
		}
		return 0;
	}


}
