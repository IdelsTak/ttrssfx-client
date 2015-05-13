package ru.penkrat.ttrssclient.domain;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

public class Article {
	private int id;
	private String title;
	private String content;
	private boolean unread;
	private LocalDateTime updated;
	private String link;

	public Article(JsonValue value) {
		JsonObject obj = (JsonObject) value;

		id = obj.get("id").getValueType() == ValueType.NUMBER ? obj.getInt("id") : Integer
				.parseInt(obj.getString("id"));
		title = obj.getString("title");
		unread = obj.getBoolean("unread", true);

		updated = LocalDateTime.ofEpochSecond(Long.valueOf(obj.getInt("updated")), 0, ZoneOffset.ofHours(0));
		link = obj.getString("link");
	}

	public String getContent() {
		return content;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	@Override
	public String toString() {
		return title;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void update(JsonValue value) {
		JsonObject obj = (JsonObject) value;
		content = obj.getString("content");
	}

}
