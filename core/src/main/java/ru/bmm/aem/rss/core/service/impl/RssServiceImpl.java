package ru.bmm.aem.rss.core.service.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import ru.bmm.aem.rss.core.models.FeedMessage;
import ru.bmm.aem.rss.core.service.api.RssService;
import ru.bmm.aem.rss.core.service.api.exceptions.RSSServiceException;
import ru.bmm.aem.rss.core.service.contstants.RssConstants;

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

    private final XMLInputFactory inputFactory = XMLInputFactory.newInstance();
    private FeedMessage feedMessage = new FeedMessage();
    private List<FeedMessage> items;

    @Override
    public List<FeedMessage> getItems(String url) throws RSSServiceException {
        items = new ArrayList<>();
        URL rssURL;
        try {
            try {
                rssURL = new URL(url);
            } catch (MalformedURLException e) {
                throw new RSSServiceException("Failed to load RSS from URL : " + url + ". ", e);
            }

            InputStream in = readRssXml(rssURL);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

            // readRssXml the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName().getLocalPart();
                    switch (localPart) {
                        case RssConstants.ITEM:
                            feedMessage = new FeedMessage();
                            break;
                        case RssConstants.TITLE:
                            feedMessage.setTitle(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.DESCRIPTION:
                            feedMessage.setDescription(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.LINK:
                            feedMessage.setLink(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.GUID:
                            feedMessage.setGuid(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.LANGUAGE:
                            feedMessage.setLanguage(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.AUTHOR:
                            feedMessage.setAuthor(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.PUB_DATE:
                            feedMessage.setPubdate(getCharacterData(event, eventReader));
                            break;
                        case RssConstants.COPYRIGHT:
                            feedMessage.setCopyright(getCharacterData(event, eventReader));
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (RssConstants.ITEM.equals(event.asEndElement().getName().getLocalPart())) {
                        items.add(feedMessage);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RSSServiceException("XML stream exception occurred while reading xml stream. ", e);
        }
        return items;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = RssConstants.BLANK_STRING;
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream readRssXml(URL url) throws RSSServiceException {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RSSServiceException("Failed to read Rss xml file from URL : " + url.toString() + ". ", e);
        }
    }
}
