package net.kronosjp.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Utility {

	/**
	 * コネクションをクローズする
	 * @param conn コネクション
	 */
	public static void connectionClose (Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	/**
	 * ResultSetをクローズする
	 * @param rs ResultSet
	 */
	public static void resultSetClose (ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 拡張子を取得する
	 * @param fileName　ファイル名
	 * @return　拡張子
	 */
	public static String getSuffix(String fileName) {
	    if (fileName == null)
	        return null;
	    int point = fileName.lastIndexOf(".");
	    if (point != -1) {
	        return fileName.substring(point + 1);
	    }
	    return fileName;
	}

}
