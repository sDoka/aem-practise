package ru.bmm.aem.rss.core.service.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import ru.bmm.aem.rss.core.models.FeedMessage;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by Michael on 31.05.2017.
 */
@Component(immediate = true)
@Service(RssService.class)
public class RssServiceImpl implements RssService {
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CHANNEL = "channel";
    private static final String LANGUAGE = "language";
    private static final String COPYRIGHT = "copyright";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String ITEM = "item";
    private static final String PUB_DATE = "pubDate";
    private static final String GUID = "guid";

    @Override
    public List<FeedMessage> getItems(String url) throws RSSServiceException {

        URL rssURL;
        List<FeedMessage> items = new ArrayList<>();

        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader

            try {
                rssURL = new URL(url);
            } catch (MalformedURLException e) {
                throw new RSSServiceException("", e);
            }

            InputStream in = readRssXml(rssURL);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // readRssXml the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                //TODO make this class service and get feed correctly
                                items = new ArrayList<FeedMessage>();
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedMessage message = new FeedMessage();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        items.add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream readRssXml(URL url) {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
