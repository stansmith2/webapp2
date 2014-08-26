package net.kronosjp.dto;


public class UserDto {
	
	/** ユーザID */
	private String userId;
	
	/** パスワード */
	private String password;
	
	/** 指名 */
	private String name;
	
	/** 登録日 */
	private String addDate;
	
	/** 更新日 */
	private String updateDate;
	
	/** 登録ユーザID */
	private String addUserId;
	
	/** 更新ユーザID */
	private String updateUserId;

	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param password パスワード
	 * @param name　氏名
	 * @param addDate　登録日
	 * @param updateDate　更新日
	 * @param addUserId　登録ユーザID
	 * @param updateUserId　更新ユーザID
	 */
	public UserDto (String userId, String password, String name, String addDate, String updateDate, String addUserId, String updateUserId) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.addDate = addDate;
		this.updateDate = updateDate;
		this.addUserId = addUserId;
		this.updateUserId = updateUserId;
		
	}
	

	/**
	 * コンストラクタ
	 * @param ユーザID
	 * @param password パスワード
	 * @param name　氏名
	 */
	public UserDto (String userId, String password, String name) {
		this.userId = userId;
		this.password = password;
		this.name = name;
	}
	
	
	/**
	 * コンストラクタ
	 * @param userId ユーザID
	 * @param name　氏名
	 */
	public UserDto (String userId, String name) {
		this.userId = userId;
		this.name = name;

	}
	
	/** 引数なしコンストラクタ */
	public UserDto() {}
	
	
	/**
	 * ユーザIDを取得します
	 * @return　ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDをセットします
	 * @param userId　ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * パスワードを取得します
	 * @return　パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードをセットします
	 * @param password　パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 氏名を取得します
	 * @return　氏名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 氏名をセットします
	 * @param name　氏名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 登録日を取得します
	 * @return　登録日
	 */
	public String getAddDate() {
		return addDate;
	}

	/**
	 * 登録日をセットします
	 * @param addDate　登録日
	 */
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	/**
	 * 更新日を取得します
	 * @return　更新日
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日をセットします
	 * @param updateDate　更新日
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 登録ユーザIDを取得します
	 * @return　登録ユーザID
	 */
	public String getAddUserId() {
		return addUserId;
	}

	/**
	 * 登録ユーザIDをセットします
	 * @param addUserId　登録ユーザID
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	/**
	 * 更新ユーザIDを取得します
	 * @return
	 */
	public String getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 更新ユーザIDをセットします
	 * @param updateUserId　更新ユーザID
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
}
