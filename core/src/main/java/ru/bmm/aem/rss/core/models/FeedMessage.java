package ru.bmm.aem.rss.core.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.bmm.aem.rss.core.service.contstants.RssConstants;

/*
 * Represents one RSS message
 */
public class FeedMessage {

    private String description = RssConstants.BLANK_STRING;
    private String title = RssConstants.BLANK_STRING;
    private String link = RssConstants.BLANK_STRING;
    private String language = RssConstants.BLANK_STRING;
    private String copyright = RssConstants.BLANK_STRING;
    private String author = RssConstants.BLANK_STRING;
    private String pubdate = RssConstants.BLANK_STRING;
    private String guid = RssConstants.BLANK_STRING;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}