package com.uploadvideo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uploadvideo.dto.CommentDto;
import com.uploadvideo.dto.VideoDto;
import com.uploadvideo.model.Comment;
import com.uploadvideo.model.Videos;
import com.uploadvideo.repository.CommentRepository;
import com.uploadvideo.repository.VideoRepository;
import com.uploadvideo.service.VideoService;

import java.util.List;


@Service
//@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService{

 //   private final S3Service s3Service;
	
	@Autowired
   public VideoRepository videoRepository;
	@Autowired
	   public CommentRepository commentRepository;
	
	public String uploadVideo(String fileName) {
       // String videoUrl = s3Service.uploadFile(multipartFile);
       // var video = (var) new Video();
       // ((UploadVideoResponse) video).setVideoUrl(fileName);

        //var savedVideo = (var) videoRepository.save(video);
        //return new UploadVideoResponse(savedVideo.get, savedVideo.getVideoUrl());
return fileName;
    }
	 
	 public VideoDto saveVideo(VideoDto videoDto) {
	        // Find the video by videoId
		  
	      Videos savedVideo=new Videos();
	        System.out.println("video tittle"+videoDto.getTitle()+" video thumbnail-"+videoDto.getThumbnailUrl()+" video id--"+videoDto.getVideoId());
	        // Map the videoDto fields to video
	        savedVideo.setTitle(videoDto.getTitle());
	        savedVideo.setDescription(videoDto.getDescription());
	        savedVideo.setTags(videoDto.getTags());
	        String videoThumb=getVideoDetailsById(videoDto.getVideoId());
	       // savedVideo.setThumbnailUrl("assets/images/"+videoDto.getThumbnailUrl());
	        System.out.println("video thumbnaisjdiasdsi---"+videoThumb);
	        savedVideo.setThumbnailUrl(videoThumb);
	        savedVideo.setVideoStatus(videoDto.getVideoStatus());
	        savedVideo.setVideoName(videoDto.getVideoName());
	        savedVideo.setVideoUrl(videoDto.getVideoUrl());
	        savedVideo.setUpload_date(videoDto.getUpload_date());

	        // save the video  to the database 
	        savedVideo.setVideoId(videoDto.getVideoId());
	       videoRepository.save(savedVideo);
	        return videoDto;
	    }
public String getVideoDetailsById(long videoId) {
	Videos videoObj;
	videoObj=videoRepository.findById(videoId);
	return videoObj.getThumbnailUrl();
}
    public VideoDto editVideo(VideoDto videoDto) {
        // Find the video by videoId
		/*
		 * Videos savedVideo = (Videos) getVideoById(videoDto.getId()); // Map the
		 * videoDto fields to video savedVideo.setTitle(videoDto.getTitle());
		 * savedVideo.setDescription(videoDto.getDescription());
		 * //savedVideo.setTags(videoDto.getTags());
		 * savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
		 * savedVideo.setVideoStatus(videoDto.getVideoStatus());
		 */

        // save the video  to the database
       // videoRepository.save(savedVideo);
        return videoDto;
    }

    public long uploadThumbnail(MultipartFile file, String videoId, String fileName) {
        Videos savedVideo =new  Videos();//(Videos) getVideoById(videoId);
        savedVideo.setThumbnailUrl("assets/video/"+fileName);
      
         Videos videosObj;
  //      String thumbnailUrl = "";//s3Service.uploadFile(file);
         System.out.println("uploadThumbnailjkshdjsjdjs thumbnailurl---"+fileName);
       // savedVideo.setThumbnailUrl(thumbnailUrl);
 
         videosObj=videoRepository.save(savedVideo);
        return videosObj.getVideoId();
    }

  

    public Videos getVideoDetails(String videoId) {
       long vidId=0;
       try {
    	   vidId=Long.parseLong(videoId);
       }
       catch(Exception er) {
    	   er.printStackTrace();
       }
    	return getVideoById(vidId);
    }

    

	 

    private VideoDto mapToVideoDto(Videos videoById) {
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
       // videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        //videoDto.setTags(videoById.getTags());
        videoDto.setVideoStatus(videoById.getVideoStatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDislikeCount(videoById.getDisLikes().get());
        videoDto.setViewCount(videoById.getViewCount().get());
        return videoDto;
    }

   
	/*
	 * public List < VideoDto > findAll() { return videoRepository.findAll(); }
	 */
    @Override
    public List<Videos> getAllVideos() {
    	 List<Videos> video= (List<Videos>) videoRepository.findAll();
    	 try
    	 {
    		 System.out.println("video list size-----------"+video.size());
    	 }
    	 catch(Exception er) {System.out.println("List is null----"+er.getMessage());}
    	return video;
    }

	
		
    public void addComment(long videoId, CommentDto commentDto) {
       Videos video = getVideoById(videoId);
        System.out.println("addComment--------"+videoId);
        Comment comment = new Comment();
        comment.setText(commentDto.getCommentText());
        System.out.println("addComment--------"+videoId);
        commentDto.setAuthorId("1");
        comment.setAuthorId(commentDto.getAuthorId());
        video.addComment(comment);
        comment.setVideoId(videoId);
        comment.setComment_date(commentDto.getComment_date());

        commentRepository.save(comment);
    }
	
    Videos getVideoById(long videoId) {
        return videoRepository.findById(videoId);
              
    }
    public List<CommentDto> getAllComments(long videoId) {
     System.out.println("video iddd----------"+videoId);
    	//Videos video = getVideoById(videoId);
     List<Comment> dto=commentRepository.findByvideoId(videoId);
        //List<Comment> commentList = video.getCommentList();
    	
      return dto.stream().map(this::mapToCommentDto).toList();
    }
    private CommentDto mapToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(comment.getAuthorId());
        return commentDto;
    }
}

