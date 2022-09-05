package com.uploadvideo.model;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Videos {

    
    public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long videoId;
	@Column
    private String title;
	@Override
	public String toString() {
		return "Videos [videoId=" + videoId + ", title=" + title + ", description=" + description + ", userId=" + userId
				+ ", likes=" + likes + ", disLikes=" + disLikes + ", videoUrl=" + videoUrl + ", videoStatus="
				+ videoStatus + ", viewCount=" + viewCount + ", thumbnailUrl=" + thumbnailUrl + "]";
	}

	@Column
    private String description;
	@Column
    private String userId;
	@Column
	private Date upload_date;
    public Date getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}

	private AtomicInteger likes = new AtomicInteger(0);
    private AtomicInteger disLikes = new AtomicInteger(0);
   
   // private Set<String> tags;
    @Column
    private String videoUrl;
    @Column
    private VideoStatus videoStatus;
    @Column
    private String videoName;
    public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	private AtomicInteger viewCount = new AtomicInteger(0);
    private String thumbnailUrl;
  //  private List<Comment> commentList = new CopyOnWriteArrayList<>();

    public void incrementLikes() {
        likes.incrementAndGet();
    }

    public void decrementLikes() {
        likes.decrementAndGet();
    }

    public void incrementDisLikes() {
        disLikes.incrementAndGet();
    }

    public void decrementDisLikes() {
        disLikes.decrementAndGet();
    }

    public void incrementViewCount() {
        viewCount.incrementAndGet();
    }

    public void addComment(Comment comment) {
      //  commentList.add(comment);
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public AtomicInteger getLikes() {
		return likes;
	}

	public void setLikes(AtomicInteger likes) {
		this.likes = likes;
	}

	public AtomicInteger getDisLikes() {
		return disLikes;
	}

	public void setDisLikes(AtomicInteger disLikes) {
		this.disLikes = disLikes;
	}

	/*
	 * public Set<String> getTags() { return tags; }
	 */

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public VideoStatus getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(VideoStatus videoStatus) {
		this.videoStatus = videoStatus;
	}

	public AtomicInteger getViewCount() {
		return viewCount;
	}

	public void setViewCount(AtomicInteger viewCount) {
		this.viewCount = viewCount;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public void setTags(Set<String> tags) {
		// TODO Auto-generated method stub
		
	}

//	public List<Comment> getCommentList() {
	/*
	 * public void setCommentList(List<Comment> commentList) { this.commentList =
	 * commentList; }
	 */

	
}
