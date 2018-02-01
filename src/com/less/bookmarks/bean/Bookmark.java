package com.less.bookmarks.bean;

public class Bookmark {

	private String name;

	private String url;

	private String cate;

	private String cmd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public String toString() {
		return "Bookmark [name=" + name + ", url=" + url + ", cate=" + cate + ", cmd=" + cmd + "]";
	}
}
