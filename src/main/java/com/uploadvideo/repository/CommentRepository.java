package com.uploadvideo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uploadvideo.model.Comment;

@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long>{
	
	List<Comment> findByvideoId(long videoId);
}
 