package com.xemphim.WebXemPhim.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	@EmbeddedId
	private CommentId id;
	
	@Column(name = "comment_content")
	private String commentContent;	
	
	@Column(name = "comment_date")
	private Date commentDate;
	
	public CommentId getId() {
		return id;
	}

	public void setId(CommentId id) {
		this.id = id;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
}