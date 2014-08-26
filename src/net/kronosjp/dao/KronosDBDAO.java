package net.kronosjp.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.kronosjp.dto.RecipeDto;
import net.kronosjp.dto.UserDto;
import net.kronosjp.exception.MayoException;
import net.kronosjp.util.KronosDBConnection;
import net.kronosjp.util.Utility;

public class KronosDBDAO {


	/**
	 * userテーブルの情報を取得
	 * @return userテーブルの情報リスト
	 */
	public static List<UserDto> getUserList () { 
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<UserDto> list = null;

		try {
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from user where deleteFlg = '0' order by updateDate desc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			list = new ArrayList<UserDto>();

			// SQL結果を取得
			while (rs.next()) {
				String userId = rs.getString("userId");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String addDate = sdf.format(rs.getTimestamp("addDate"));
				String updateDate = sdf.format(rs.getTimestamp("updateDate"));
				String addUserId = rs.getString("addUserId");
				String updateUserId = rs.getString("updateUserId");

				UserDto dto = new UserDto(userId,password,name,addDate,updateDate,addUserId,updateUserId);				
				list.add(dto);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return list;
	}


	/**
	 * ログインユーザの情報を取得する
	 * @param id ログインID
	 * @param password パスワード
	 * @return　ログインユーザ情報
	 */
	public static UserDto getLoginUserInfo (String id, String password) {
		Connection conn = null;
		ResultSet rs = null;
		UserDto dto = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from user where userId = ? and password = ? and deleteFlg = '0'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			// SQL結果を取得
			while (rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("name");
				dto = new UserDto(userId, name);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
			Utility.resultSetClose(rs);
		}
		return dto;
	}

	/**
	 * ユーザを追加
	 * @param name　氏名
	 * @param id　ユーザID
	 * @param password パスワード
	 * @param dto ユーザ登録を実施したユーザ情報
	 */
	public static void userAdd (String name, String id, String password, UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "insert into user (userId, password, name, addDate, updateDate, addUserId, updateUserId) values(?, ?, ?, ?, ?, ?, ?)";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setTimestamp(4, date);
			pstmt.setTimestamp(5, date);
			pstmt.setString(6, dto.getUserId());
			pstmt.setString(7, dto.getUserId());

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}
	}


	/**
	 * ユーザの情報を取得する
	 * @param id ユーザID
	 * @return　ユーザ情報
	 */
	public static UserDto getUserInfo (String id) {
		Connection conn = null;
		ResultSet rs = null;
		UserDto dto = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from user where userId = ? and deleteFlg = '0'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// SQL結果を取得
			while (rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("name");
				String password = rs.getString("password");
				dto = new UserDto(userId, password,name);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
			Utility.resultSetClose(rs);
		}
		return dto;
	}


	/**
	 * ユーザを削除
	 * @param id 削除するユーザのユーザid
	 * @param dto 削除を実施するユーザの情報
	 */
	public static void userDelete (String id, UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "update user set deleteFlg = '1', updateDate = ?, updateUserId = ? where userId = ?";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);

			pstmt.setTimestamp(1, date);
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, id);

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}
	}


	/**
	 * ユーザを検索
	 * @param search 氏名
	 * @return　ユーザ情報
	 */
	public static List<UserDto> searchUser (String search) {

		Connection conn = null;
		ResultSet rs = null;
		List<UserDto> list = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from user where name like ?  order by updateDate desc";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, search + "%");
			rs = pstmt.executeQuery();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			list = new ArrayList<UserDto>();

			// SQL結果を取得
			while (rs.next()) {
				String userId = rs.getString("userId");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String addDate = sdf.format(rs.getTimestamp("addDate"));
				String updateDate = sdf.format(rs.getTimestamp("updateDate"));
				String addUserId = rs.getString("addUserId");
				String updateUserId = rs.getString("updateUserId");

				UserDto dto = new UserDto(userId,password,name,addDate,updateDate,addUserId,updateUserId);				
				list.add(dto);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return list;
	}


	/**
	 * ユーザ情報を更新する
	 * @param id 削除するユーザのユーザid
	 * @param dto 削除を実施するユーザの情報
	 */
	public static void userUpdate (String userId, String beforeUserId, String name, String password, UserDto dto) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "update user set userId = ?, name = ?, password = ?, updateDate = ?, updateUserId = ? where userId = ?";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setTimestamp(4, date);
			pstmt.setString(5, dto.getUserId());
			pstmt.setString(6, beforeUserId);

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}
	}


	/**
	 *  Recipeテーブルを取得
	 * @return 　Recipeテーブルの情報リスト
	 */
	public static List<RecipeDto> getRecipeList() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<RecipeDto> list = null;

		try {
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from recipe where deleteFlg = '0' order by updateDate desc";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			list = new ArrayList<RecipeDto>();

			// SQL結果を取得
			while (rs.next()) {
				String recipeId = rs.getString("recipeId");
				String title = rs.getString("title");
				String addDay = sdf.format(rs.getTimestamp("addDay"));

				RecipeDto dto = new RecipeDto(recipeId,title,addDay);				
				list.add(dto);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return list;
	}


	/**
	 * レシピの詳細情報を取得
	 * @param id 情報を取得するレシピNO
	 * @return レシピ情報
	 */
	public static RecipeDto getRecipeInfo(String id) {

		Connection conn = null;
		ResultSet rs = null;
		RecipeDto dto = null;
		InputStream is = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from recipe where recipeId = ? and deleteFlg = '0'";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// SQL結果を取得
			while (rs.next()) {
				String recipeId = rs.getString("recipeId");
				String title = rs.getString("title");
				String materia = rs.getString("materia");
				Blob gazou = rs.getBlob("pictureData");
				is = gazou.getBinaryStream();
				String procedureiCooking = rs.getString("procedureiCooking");
				String cookingTimeMin = String.valueOf(rs.getInt("cookingTimeMin"));

				// 
				dto = new RecipeDto(recipeId, title,materia, is,procedureiCooking, cookingTimeMin);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
			Utility.resultSetClose(rs);
		}
		return dto;
	}


	/**
	 *  Recipeテーブルを取得
	 * @return 　Recipeテーブルの情報リスト
	 */
	public static List<RecipeDto> searchRecipe(String title) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<RecipeDto> list = null;

		try {
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select * from recipe where title like ? and deleteFlg = '0' order by updateDate desc";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title + "%");
			rs = pstmt.executeQuery();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

			list = new ArrayList<RecipeDto>();

			// SQL結果を取得
			while (rs.next()) {
				String searchRecipeId = rs.getString("recipeId");
				String searchTitle = rs.getString("title");
				String SearchAddDay = sdf.format(rs.getTimestamp("addDay"));

				RecipeDto dto = new RecipeDto(searchRecipeId, searchTitle, SearchAddDay);				
				list.add(dto);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return list;
	}


	/**
	 * userテーブルにuserIDが存在するかチェックする
	 * @param id ユーザID
	 * @return　ユーザ情報
	 */
	public static UserDto checkUserId(String id) {
		Connection conn = null;
		ResultSet rs = null;
		UserDto dto = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select userId, name from user where userId = ? ";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			// SQL結果を取得
			while (rs.next()) {
				String userId = rs.getString("userId");
				String name = rs.getString("name");
				dto = new UserDto(userId, name);
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
			Utility.resultSetClose(rs);
		}
		return dto;
	}



	/**
	 * レシピの追加
	 * @param title　タイトル
	 * @param materia　材料
	 * @param procedureiCooking　調理手順
	 * @param pic　画像データ
	 * @param FileName　ファイル名
	 * @param type 拡張子
	 * @param cookingTimeMin　調理時間
	 * @param dto　レシピ追加ユーザ情報
	 */
	public static void RecipeAdd (String title, String materia, String procedureiCooking,InputStream pic, String FileName, String type,  int cookingTimeMin, UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "insert into recipe (recipeId, title, materia, procedureiCooking, pictureData, pictureFileName, mimeType, cookingTimeMin, lookCount, addDay, updateDate, addUserId, updateUserId) values(?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?, ?, ?)";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, String.valueOf(getRecipeCount()));
			pstmt.setString(2, title);
			pstmt.setString(3, materia);
			pstmt.setString(4, procedureiCooking);
			pstmt.setBlob(5, pic);
			pstmt.setString(6, FileName);
			pstmt.setString(7, type);
			pstmt.setInt(8, cookingTimeMin);
			pstmt.setTimestamp(9, date);
			pstmt.setTimestamp(10, date);
			pstmt.setString(11, dto.getUserId());
			pstmt.setString(12, dto.getUserId());

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}
	}

	/**
	 *  Recipeテーブルの行数 + 1を取得
	 * @return 　Recipeテーブルの行数
	 */
	public static int getRecipeCount() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select count(*) from recipe";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// SQL結果を取得
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return count + 1;
	}


	/**
	 * recipeの画像を取得
	 * @return 　画像
	 */
	public static RecipeDto getRecipeImage(String recipeId) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		RecipeDto dto = null;

		try {
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "select pictureData, mimeType from recipe where recipeId = ? and deleteflg = '0'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recipeId);
			rs = pstmt.executeQuery();
			dto = new RecipeDto();


			// SQL結果を取得
			while (rs.next()) {
				dto.setPictureData(rs.getBinaryStream("pictureData"));
				dto.setMimeType(rs.getString("mimeType"));
			}

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			Utility.resultSetClose(rs);
			Utility.connectionClose(conn);
		}

		return dto;
	}

	/**
	 * レシピを削除
	 * @param recipeId 削除するレシピID
	 * @param dto 削除を実施するユーザの情報
	 */
	public static void recipeDelete (String recipeId, UserDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "update recipe set deleteFlg = '1', updateDate = ?, updateUserId = ? where recipeId = ?";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);

			pstmt.setTimestamp(1, date);
			pstmt.setString(2, dto.getUserId());
			pstmt.setString(3, recipeId);

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}
	}


	/**
	 * recipeテーブルを更新する
	 * @param title タイトル
	 * @param material　材料
	 * @param process　手順
	 * @param gazou　画像データ
	 * @param fileName　ファイル名
	 * @param contentType　画像タイプ
	 * @param parseInt　調理時間
	 * @param dto　更新実施ユーザ
	 */
	public static void RecipeUpdate(String recipeId, String title, String material,
			String procedureiCooking, InputStream gazou, String fileName,
			String contentType, int cookingTimeMin, UserDto dto, int result) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// コネクションを取得
			KronosDBConnection connection = new KronosDBConnection();
			conn = connection.getConnection();

			// SQL文作成
			String sql = "update recipe set title = ?, materia = ?, procedureiCooking = ? ";

			// 更新時に画像が選択されていない場合
			if (result != 5) {
				sql += " ,pictureData = ?, pictureFileName = ?, mimeType = ? ";

			}

			sql += ", cookingTimeMin = ?, updateDate = ?, updateUserId = ? where recipeId = ? ";

			// 現在時刻を取得
			Timestamp date = new Timestamp(Calendar.getInstance().getTimeInMillis());

			// SQLに情報をセット
			pstmt = conn.prepareStatement(sql);
			
			int i = 1;
			pstmt.setString(i++, title);
			pstmt.setString(i++, material);
			pstmt.setString(i++, procedureiCooking);
			
			if (result != 5) {
				pstmt.setBlob(i++, gazou);
				pstmt.setString(i++, fileName);
				pstmt.setString(i++, contentType);
				
			}
			pstmt.setInt(i++, cookingTimeMin);
			pstmt.setTimestamp(i++, date);
			pstmt.setString(i++, dto.getUserId());
			pstmt.setString(i++, recipeId);

			// テーブルにユーザを追加
			pstmt.executeUpdate();

		} catch (SQLException e){
			System.out.println(e.getMessage());

		} catch (MayoException e){
			System.out.println(e.getMessage());

		} finally {
			//　後片付け
			Utility.connectionClose(conn);
		}

	}

}
