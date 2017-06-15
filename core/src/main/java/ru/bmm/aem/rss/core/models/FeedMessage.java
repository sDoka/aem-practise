package ru.bmm.aem.rss.core.models;

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
        this.title = title.replaceAll("\'","").replaceAll("\"","");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description.replaceAll("\'","").replaceAll("\"","");
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
        return "{\"FeedMessage\":{"
                + "\"title\":\"" + title + "\""
                + ", \"description\":\"" + description + "\""
                + ", \"link\":\"" + link + "\""
                + "}}";
    }
}