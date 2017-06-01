package ru.bmm.aem.rss.core.servlets;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import ru.bmm.aem.rss.core.models.FeedMessage;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 31.05.2017.
 */
@SlingServlet(paths = {"/bin/rss-aem"}, methods = {"POST", "GET"})
public class RssReaderServlet extends SlingAllMethodsServlet {

    final int NEW_ITEMS_COUNT = 10;

    @Reference
    private RssService rssService;


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    /*  jQuery.get('/bin/rss-aem'); */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        List<FeedMessage> items;
        String currentFeedUrl = request.getParameter("rssFeedUrl");
        int currentItemsCount = Integer.parseInt(request.getParameter("itemsCount"));
        try {
            items = rssService.getItems(currentFeedUrl);
            //FIXME 5: find symbol, that brakes JSON parsing.
            //TODO add exception handling
            /*if (items.size() >= currentItemsCount + NEW_ITEMS_COUNT) {
                items = items.subList(currentItemsCount, currentItemsCount + NEW_ITEMS_COUNT);
            } else {
                items = items.subList(currentItemsCount, items.size() - 1);
            }*/
            items = items.subList(currentItemsCount, currentItemsCount + 4 );

            writer.write(items.toString());
        } catch (RSSServiceException e) {
            writer.write("{ErrorMessage : \"Failed to get rss feed items\"}");
        }
    }
}

