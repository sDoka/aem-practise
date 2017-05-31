package ru.bmm.aem.rss.core.servlets;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import ru.bmm.aem.rss.core.service.api.RssService;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by Michael on 31.05.2017.
 */
@SlingServlet(paths = {"/bin/rss-aem"}, methods = {"POST", "GET"})
public class RssReaderServlet extends SlingAllMethodsServlet {

    @Reference
    private RssService rssService;


    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    /*  jQuery.get('/bin/rss-aem'); */
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("blablalsfdad");
    }
}
