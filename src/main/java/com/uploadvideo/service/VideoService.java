package com.uploadvideo.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.uploadvideo.dto.CommentDto;
import com.uploadvideo.dto.VideoDto;
import com.uploadvideo.model.Videos;


public interface VideoService {

	 public String uploadVideo(String file);
	  public VideoDto saveVideo(VideoDto videoDto);
	
	 public long uploadThumbnail(MultipartFile file, String videoId, String fileName);
	
	public Videos getVideoDetails(String videoId);
	
	public List<Videos> getAllVideos();
	public void addComment(long videoId, CommentDto commentDto);
	public List<CommentDto> getAllComments(long videoId);
}
