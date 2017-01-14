package com.incra;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.URL;

/**
 * @author jeff
 * @since 1/14/17
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Start");
        try {
            String url = "http://blog.justgive.org/feed";
            SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
            System.out.println(feed.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
