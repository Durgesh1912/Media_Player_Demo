package com.uploadvideo.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uploadvideo.model.Videos;

@Repository
public interface VideoRepository extends CrudRepository<Videos, Long>  {

	//var save(var video);

	//Object findById(String videoId);
   Videos findById(long videoId);
	//Iterable<Videos> findAll();

	//<S> S save(Videos video);

}
