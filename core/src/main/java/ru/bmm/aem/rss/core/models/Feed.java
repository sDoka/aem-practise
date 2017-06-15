package ru.bmm.aem.rss.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.slf4j.Logger;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;
import ru.bmm.aem.rss.core.service.contstants.RssConstants;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/*
 * Stores an RSS feed
 * f.e. http://www.vogella.com/article.rss or
 * any .rss link from here https://www.geeklog.net/staticpages/index.php/20030206094434245
 * @Model - declares a model class or interface
 * @Inject - marks a field or method as injectable
 * @Named - declare a name for the injection (otherwise, defaults based on field or method name).
 * @Optional - marks a field or method injection as optional
 * @Source - explictly tie an injected field or method to a particular injector (by name). Can also be on other annotations.
 * @Filter - an OSGi service filter
 * @PostConstruct - methods to call upon model option creation (only for model classes)
 * @Via - use a JavaBean property of the adaptable as the source of the injection
 * @Default - set default values for a field or method
 * @Path - only used together with the resource-path injector to specify the path of a resource
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class Feed {

    @Inject
    private RssService rssService;

    @Inject
    @Named("log")
    private Logger log;

    @Inject @Via("resource")
    @Default (values = "Apple Hot News")
    private String title;

    @Inject @Via("resource")
    @Default (values = "https://www.apple.com/main/rss/hotnews/hotnews.rss")
    private String rssUrl;

    @Inject @Via("resource")
    @Named("rssMaxItems")
    @Default (intValues = 10)
    private int maxRssItemsCount;

    @Inject @Via("resource")
    @Named("fileReference") @Optional
    private String imagePath;

    private List<FeedMessage> items = new ArrayList<>();

    private boolean hasError = false;
    private String errorMessage = RssConstants.BLANK_STRING;

    @PostConstruct
    public void init() {
        try {
            this.items = rssService.getItems(this.getRssUrl());
            if (items.size() > maxRssItemsCount) {
                this.items = items.subList(0, maxRssItemsCount);
            }

        } catch (RSSServiceException e) {
            log.error(e.getMessage());
            this.hasError = true;
            this.errorMessage = "Failed to parse feed for link : " + rssUrl + ". Please, try another one.";
        }
    }

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
