package com.example.jbdl9.minorproject2;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.List;

public class VideoThread extends Thread{

    private static Logger logger = LoggerFactory.getLogger(VideoThread.class);

    private String keyword;
    private List<Video> videos;
    private String api_key;
    private YouTube youTube;
    YoutubeRepository youtubeRepository;
    private String pageToken;


    public VideoThread(String keyword, YoutubeRepository youtubeRepository, String api_key, YouTube youTube) {
        this.keyword = keyword;
        this.youtubeRepository = youtubeRepository;
        this.youTube = youTube;
        this.api_key = api_key;
    }

    @Override
    public void run() {
        // fetching data from youtube
        try {
            while(true) {
                YouTube.Search.List searchList = this.youTube.search().list("id,snippet");
                searchList.setKey(this.api_key);
                searchList.setQ(keyword);
                searchList.setMaxResults(3L);
                searchList.setPageToken(this.pageToken);


                SearchListResponse response = searchList.execute();
                logger.warn("response is {}", response);
                List<SearchResult> items = response.getItems();
                List<Video> videos = new ArrayList<>();
                for (SearchResult result : items) {
                    if(!result.getId().getKind().equals("youtube#video")){
                        System.out.println("not a video");
                        continue;
                    }
                    SearchResultSnippet snippet = result.getSnippet();
                    String videoId = result.getId().getVideoId();
                    String channelTitle = snippet.getChannelTitle();
                    String channelId = snippet.getChannelId();
                    String title = snippet.getTitle();
                    String description = snippet.getDescription();
//            String mediumUrl = snippet.getThumbnails().getMedium().getUrl();
//            String defaultUrl = snippet.getThumbnails().getDefault().getUrl();
//            String highUrl = snippet.getThumbnails().getHigh().getUrl();
//            JSONObject thumbnails = new JSONObject();
//            thumbnails.put("high", highUrl);
//            thumbnails.put("medium", mediumUrl);
//            thumbnails.put("low", defaultUrl);

                    JSONObject thumbnails = (JSONObject) JSONValue.parseWithException(snippet.getThumbnails().toString());
//                    DateTime publishedAt = snippet.getPublishedAt();
                    Video video = new Video(videoId, title, description, thumbnails, channelId, channelTitle, this.keyword);
                    videos.add(video);
                }

                youtubeRepository.saveAllVideos(videos);
                this.pageToken = response.getNextPageToken();

                logger.warn("saved videos in the thread - {}", currentThread().getName());

                Thread.sleep(10000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
