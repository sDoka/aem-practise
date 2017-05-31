package ru.bmm.aem.rss.core.models;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class Feed {

    private RssService rssService;

    @Inject
    @Via("resource")
    @Named("title")
    private String title;

    @Inject
    @Via("resource")
    @Named("rssUrl")
    private String rssUrl;

    @Inject
    @Via("resource")
    @Named("fileReference") @Optional
    private String imagePath;

    private List<FeedMessage> items = new ArrayList<FeedMessage>();

    private boolean hasError = false;
    private String errorMessage = "";

    private SlingHttpServletRequest request;

    @Inject
    public Feed(final RssService rssService, SlingHttpServletRequest request) {
        this.rssService = rssService;
        if (null == rssService) {
            System.out.println("Rss Service is undefined");
        }

        this.request = request;
        if (null == request) {
            System.out.println("Request is undefined");
        }

        if (rssUrl == null) {
            System.out.println("RssUrl is undefined");
            this.rssUrl = "http://www.vogella.com/article.rss";
        }

        try {
            this.items = rssService.getItems(this.getRssUrl());
        } catch (RSSServiceException e) {
            //TODO log it
        }

    }

    public Feed() {}

    public List<FeedMessage> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
