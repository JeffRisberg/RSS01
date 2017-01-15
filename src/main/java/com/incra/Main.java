package com.incra;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.List;

/**
 * @author jeff
 * @since 1/14/17
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Start");

        String url = "http://blog.rescuetime.com/feed";

        try {
            XmlReader reader = new XmlReader(new URL(url));

            SyndFeed feed = new SyndFeedInput().build(reader);
            List<SyndEntry> entries = feed.getEntries();

            for (SyndEntry entry : entries) {
                System.out.println(entry.getTitle());
                System.out.println(entry.getLink());
                System.out.println(entry.getPublishedDate());
                System.out.println(entry.getAuthor());
                System.out.println(entry.getDescription().getValue().trim());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL u = new URL(url);
            Document doc = builder.parse(u.openStream());
            NodeList nodes = doc.getElementsByTagName("item");

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);

                System.out.println("Title: " + getElementValue(element, "title"));
                System.out.println("Link: " + getElementValue(element, "link"));
                System.out.println("Publish Date: " + getElementValue(element, "pubDate"));
                System.out.println("Author: " + getElementValue(element, "dc:creator"));
                System.out.println("Description: " + getElementValue(element, "description").trim());
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static String getElementValue(Element parent,String label) {
        return getCharacterDataFromElement((Element)parent.getElementsByTagName(label).item(0));
    }
    private static String getCharacterDataFromElement(Element e) {
        try {
            Node child = e.getFirstChild();
            if(child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                return cd.getData();
            }
        } catch(Exception ex) {
        }
        return "";
    }
}
