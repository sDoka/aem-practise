package ru.bmm.aem.rss.core.models;

import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;
import org.junit.Assert;
import org.junit.Test;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;
import ru.bmm.aem.rss.core.service.impl.RssServiceImpl;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Michael on 31.05.2017.
 */
public class RSSReaderTest {

    @Tested
    private RssServiceImpl rssServiceImpl = new RssServiceImpl();

    private String correctFeedUrl = "https://www.apple.com/main/rss/hotnews/hotnews.rss";
    private String incorrectFeedUrl = "http://www.apple.com/main/rss/hotnews/hotnews.rs";

    @Test
    public void feedParsed() throws RSSServiceException {
        /*new MockUp<RssServiceImpl>() {
            @Mock
            private List<FeedMessage> parseFeed(String feedUrl) {

            }
        };*/

        List<FeedMessage> items = rssServiceImpl.getItems(correctFeedUrl);
        assertNotNull(items);
    }

    @Test (expected = RSSServiceException.class)
    public void wrongFeedUrl() throws  RSSServiceException {
            rssServiceImpl.getItems(incorrectFeedUrl);
    }
}
