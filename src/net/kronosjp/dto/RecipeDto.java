package net.kronosjp.dto;

import java.io.InputStream;

public class RecipeDto {
	
	/** レシピNO */
	private String recipeId;

	/** タイトル */
	private String title;
	
	/** 材料 */
	private String material;
	
	/** 手順 */
	private String procedureiCooking;
	
	/** 画像データ */
	private InputStream pictureData;
	
	/** 画像ファイル名 */
	private String pictureName;

	/** MIME/TYPE */
	private String mimeType;
	
	/** 調理時間(分) */
	private String cookingMin;

	/** 閲覧回数 */
	private String viewsCount;
	
	/** 登録日 */
	private String addDate;
	
	/** 更新日 */
	private String updateDate;
	
	/** 登録ユーザID */
	private String addUserId;
	
	/** 更新ユーザID */
	private String updateUserId;
	
	

	public RecipeDto(String recipeId, String title, String addDate) {
		this.recipeId  = recipeId;
		this.title = title;
		this.addDate = addDate;
	}

	public RecipeDto(String recipeId, String title, String materia,
			String procedureiCooking, String cookingTimeMin) {
		this.recipeId = recipeId;
		this.title = title;
		this.material = materia;
		this.procedureiCooking = procedureiCooking;
		this.cookingMin = cookingTimeMin;
	}

	public RecipeDto(String recipeId, String title, String materia, InputStream is, String procedureiCooking, String cookingTimeMin) {
		this.recipeId = recipeId;
		this.title = title;
		this.material = materia;
		this.pictureData =is;
		this.procedureiCooking = procedureiCooking;
		this.cookingMin = cookingTimeMin;
		
	}

	public RecipeDto() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * レシピNOを取得します
	 * @return レシピNO
	 */
	public String getRecipeId() {
		return recipeId;
	}

	/**
	 * レシピNOをセットします
	 * @param recipeNo レシピNO
	 */
	public void setRecipeId(String recipeId) {
		this.recipeId = recipeId;
	}

	/**
	 * タイトルを取得します
	 * @return タイトル
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * タイトルをセットします
	 * @param title タイトル
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 材料を取得します
	 * @return　材料
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * 材料をセットします
	 * @param material 材料
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	

	/**
	 * 画像データを取得します
	 * @return　画像データ
	 */
	public InputStream getPictureData() {
		return pictureData;
	}

	
	/**
	 * 画像データをセットします
	 * @param pictureData　画像データ
	 */
	public void setPictureData(InputStream pictureData) {
		this.pictureData = pictureData;
	}

	/**
	 * 画像ファイル名を取得します
	 * @return　画像ファイル名
	 */
	public String getPictureName() {
		return pictureName;
	}

	/**
	 * 画像ファイル名をセットします
	 * @param pictureName　画像ファイル名
	 */
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	/**
	 * MIME/TYPEを取得します
	 * @return　MIME/TYPE
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * MIME/TYPEをセットします
	 * @param mimeType　MIME/TYPE
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * 調理時間(分)を取得します
	 * @return　調理時間(分)
	 */
	public String getCookingMin() {
		return cookingMin;
	}

	/**
	 * 調理時間(分)をセットします
	 * @param cookingMin 調理時間(分)
	 */
	public void setCookingMin(String cookingMin) {
		this.cookingMin = cookingMin;
	}

	/**
	 * 閲覧回数を取得します
	 * @return　閲覧回数
	 */
	public String getViewsCount() {
		return viewsCount;
	}

	/**
	 * 閲覧回数をセットします
	 * @param viewsCount　閲覧回数
	 */
	public void setViewsCount(String viewsCount) {
		this.viewsCount = viewsCount;
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
	 * 登録ユーザIDを取得します
	 * @param addUserId　登録ユーザID
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}

	/**
	 * 更新ユーザIDを取得します
	 * @return　更新ユーザID
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

	public String getProcedureiCooking() {
		return procedureiCooking;
	}

	public void setProcedureiCooking(String procedureiCooking) {
		this.procedureiCooking = procedureiCooking;
	}
}
