package com.example.jbdl9.minorproject2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YoutubeController {

    @Autowired
    YoutubeRepository youtubeRepository;

    @GetMapping("/get_videos")
    public List<Video> getVideos(@RequestParam("query") String query,
                                 @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
        return youtubeRepository.getVideos(query, pageNo, pageSize);
    }
}
