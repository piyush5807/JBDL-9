package com.example.jbdl9.minorproject2;

import com.google.api.client.util.DateTime;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Video {

    private String videoId;
    private String title;
    private String description;
    private JSONObject thumbnailUrl;
    private String channelId;
    private String channelTitle;
//    private DateTime publishedAt;
    private String tag;

    public Video(String videoId, String title, String description, JSONObject thumbnailUrl,
                 String channelId, String channelTitle, String tag) {
        this.videoId = videoId;
        this.title = title;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.channelId = channelId;
        this.channelTitle = channelTitle;
//        this.publishedAt = publishedAt;
        this.tag = tag;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
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

    public JSONObject getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(JSONObject thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

//    public DateTime getPublishedAt() {
//        return publishedAt;
//    }
//
//    public void setPublishedAt(DateTime publishedAt) {
//        this.publishedAt = publishedAt;
//    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
