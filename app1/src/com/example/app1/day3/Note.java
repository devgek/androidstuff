package com.example.app1.day3;

public class Note {
	private int id;
	private String sender;
	private String description;
	private String content;
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Note() {}
	public Note(int id, String sender, String description, String content) {
		this.id = id;
		this.sender = sender;
		this.description = description;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
