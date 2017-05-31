package ru.bmm.aem.rss.core.service.api;

import ru.bmm.aem.rss.core.models.FeedMessage;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Michael on 31.05.2017.
 */
public interface RssService {
    List<FeedMessage> getItems(String url) throws RSSServiceException;
}
