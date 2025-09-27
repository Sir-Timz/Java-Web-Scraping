package com.scraping;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BoxOfficeScraper {
    public static void main(String[] args) throws IOException {
        String url = "https://www.boxofficemojo.com/date/?ref_=bo_nb_hm_secondarytab";
        Document doc = Jsoup.connect(url)
                .userAgent(("Mozilla"))
                        .timeout(5000)
                                .referrer("https://www.boxofficemojo.com").get();
        Elements dates = doc.select("td.a-text-left.mojo-header-column.mojo-truncate.mojo-field-type-date_interval.mojo-sort-column");
        Elements names = doc.select("td.a-text-left.mojo-field-type-release.mojo-cell-wide");
        Elements money = doc.select("td.a-text-right.mojo-field-type-money");

        for (Element date : dates) {
            int index = dates.indexOf(date);
            System.out.print(date.text() + "\t");
            System.out.print(names.get(index).text() + "\t");
            System.out.println(money.get(index).text());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }



    }
}