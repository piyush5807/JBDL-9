package com.example.jbdl9.minorproject2;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VideoService {

    private static Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Value("${app.youtube.api-key}")
    private String api_key;
    private YouTube youTube;
    private volatile String pageToken;

    @Autowired
    YoutubeRepository youtubeRepository;

    @Autowired
    MongoOperations mongoOperations;

    VideoService(){
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        this.youTube = new YouTube.Builder(httpTransport, jsonFactory, new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {

            }
        }).build();
    }

    public void fetchVideos(List<String> keywords){

        for(int i=0;i<keywords.size();i++){
            VideoThread thread = new VideoThread(keywords.get(i), youtubeRepository, this.api_key, this.youTube);
            thread.start();
        }

    }

    public void initDB(){
        logger.warn("Index getting created on videoId field...");
        mongoOperations.indexOps(Video.class).ensureIndex(new Index().on("videoId", Sort.Direction.ASC).unique().background());
    }
}
