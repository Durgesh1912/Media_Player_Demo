package com.uploadvideo.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table
public class Comment {

   
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getDisLikeCount() {
		return disLikeCount;
	}
	public void setDisLikeCount(Integer disLikeCount) {
		this.disLikeCount = disLikeCount;
	}
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentId;
	@Column
    private String text;
	@Column
    private String authorId;
	@Column
    private Integer likeCount;
	@Column
    private Integer disLikeCount;
	@Column
	private long videoId;
	@Column
	private Date comment_date;
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public long getVideoId() {
		return videoId;
	}
	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	
}

