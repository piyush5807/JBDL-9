package com.example.jbdl9.minorproject2;

import com.mongodb.MongoBulkWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class YoutubeRepository {

    @Autowired
    MongoOperations mongoOperations;

    public void save(Video video) throws Exception{
        try {
            mongoOperations.save(video);
        }catch (DuplicateKeyException e){
            e.printStackTrace();
        }catch (Exception e){
            throw new Exception("some exception");
        }
    }

    public void saveAllVideos(List<Video> videos) throws Exception {
        try {
            mongoOperations.insertAll(videos);
        }catch (DuplicateKeyException e){
            for(Video video: videos){
                save(video);
            }
        }
    }

    public List<Video> getVideos(String keyword, int pageNo, int pageSize){
        Criteria criteria = Criteria.where("tag").is(keyword);

        Query query = new Query();
        query.addCriteria(criteria).skip((pageNo-1)*pageSize).limit(pageSize);

        return mongoOperations.find(query, Video.class);
    }
}
