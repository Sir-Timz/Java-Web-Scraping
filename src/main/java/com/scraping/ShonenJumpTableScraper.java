package com.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ShonenJumpTableScraper {
    public static void main (String[] args) throws IOException {
        String url = "https://www.jajanken.net/en/";
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla")
                .timeout(5000)
                .referrer("https://www.google.com")
                .get();

        Elements ranks = doc.select("td.avg-rank");
        Elements names = doc.select("td.sakuhin-name");

        for (Element rank : ranks) {
            int index = ranks.indexOf(rank);
            System.out.print(rank.text() + "\t");
            System.out.println(names.get(index).text());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
