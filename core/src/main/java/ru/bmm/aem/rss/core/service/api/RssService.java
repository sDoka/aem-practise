package ru.bmm.aem.rss.core.service.api;

import ru.bmm.aem.rss.core.models.FeedMessage;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Michael on 31.05.2017.
 */
public interface RssService {

    /**
     * Reads RSS file, defined under current url.
     * @param url - http address of current RSS - feed.
     * @return - List of feed items (messages/posts)
     * @throws RSSServiceException - if url is incorrect, or no feed/items defined under current link,
     *                                  or error occures while parsing XML
     */
    List<FeedMessage> getItems(String url) throws RSSServiceException;
}
