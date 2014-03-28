package com.qq.weixin.sdk.message.response.passiveresponse;

public class ResponcePictureAndTextMessage extends ResponceMultimediaMessage {

	private String articleCount;
	private String articles;
	private String title;
	private String description;
	private String picUrl;
	private String url;

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public String getArticles() {
		return articles;
	}

	public void setArticles(String articles) {
		this.articles = articles;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String generatorXml() {
		// TODO Auto-generated method stub
		return null;
	}

}
