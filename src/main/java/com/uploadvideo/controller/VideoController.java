package com.uploadvideo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.uploadvideo.dto.CommentDto;
import com.uploadvideo.dto.VideoDto;
import com.uploadvideo.exception.StorageException;
import com.uploadvideo.model.Videos;
import com.uploadvideo.service.VideoService;
import com.uploadvideo.serviceImpl.FileSystemStorageService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/videos")
public class VideoController {
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	@Autowired
    private VideoService videoService;
	 @Autowired
	  private FileSystemStorageService storageService;
	 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) {
    	logger.info("Uploading browse video to directory ");
    	String fileName="";
    	try {       
    	fileName=storageService.store(file);
        //return "addVideo";
    	        fileName=videoService.uploadVideo(fileName);
    	        if(fileName.isEmpty())
    	        	 throw new StorageException("Directory is not found");
    	        System.out.println("dkjkdjfjdkf file name ins pring----------"+fileName);
    	        return new ResponseEntity<String>(fileName,HttpStatus.CREATED);
    	}catch(StorageException er) {
    		logger.error("Video is not uploaded : ");
    		return new ResponseEntity<String>(fileName,HttpStatus.INTERNAL_SERVER_ERROR);
    		
    	}
   // return fileName;

    }
    
    @PostMapping("/saveUploadVideo") 
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveVideo(@RequestBody VideoDto videoDto) {
       System.out.println("video date------"+videoDto.getUpload_date());
        //VideoDto videodto;
      logger.info("Saving video details to database");
      try {
     videoService.saveVideo(videoDto);
     return new ResponseEntity<String>("Successfully Saved",HttpStatus.CREATED);
      }catch(Exception er) {
    	  logger.error("video details is not saved! Something went wrong "); 
    	  return new ResponseEntity<String>("Internal Error",HttpStatus.INTERNAL_SERVER_ERROR);
      }
      
      
   // return "Successfull";

    }

   
    
    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videod) {
    	logger.info("Uploading browse video thumbnail");
    	String fileName="";long videoId = 0;
    	try {
    	fileName=storageService.store(file);
    	videoId=videoService.uploadThumbnail(file, videod,fileName);
    	return ResponseEntity.status(HttpStatus.OK)
		        .body(videoId);  
    	}catch(Exception er) {
    		logger.error("Video's thumbnail is not uploaded ");
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    		        .body(videoId);
    		
    	}
    //	return 
    }

   

    @GetMapping
        public ResponseEntity<List<Videos>>  getAllVideos() {
    	logger.info("Fetching uploaded videos list");
    	System.out.println("getAllvideos metjdjfodofod valled inside controlelr");
    	List<Videos> tmpvideos = null;
    	try {
    		tmpvideos=videoService.getAllVideos();
    		return new ResponseEntity<List<Videos>>(tmpvideos,HttpStatus.OK);
    	}catch(StorageException er) {
    		logger.error("Error Occured While Fetching uploaded videos list");
    		return new ResponseEntity<List<Videos>>(tmpvideos,HttpStatus.INTERNAL_SERVER_ERROR);
    	}
      
    } 
    
    
    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public Videos getVideoDetails(@PathVariable String videoId) {
     System.out.print(" I am inside getVideoDetails0-------video id0---"+videoId);
    	return videoService.getVideoDetails(videoId);
    }

    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@PathVariable long videoId, @RequestBody CommentDto commentDto) {
        videoService.addComment(videoId, commentDto);
    }
   
    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable long videoId) {
    	 List<CommentDto> templist = null;
    	try {
    	 
    	  templist=videoService.getAllComments(videoId);
    	 // System.out.println("templist--------"+templist.toString());
    	  if(templist.isEmpty())
    		logger.info("There is no comment on this video");
    	  else
    		  logger.info("Commented on this video"+templist.size());
    	  //templist.stream().forEach((n)->System.out.println(n));
      }
      catch(Exception er) {
    	  er.printStackTrace();
      }
    	return templist;
    }

}
