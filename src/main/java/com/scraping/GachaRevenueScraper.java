package com.scraping;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GachaRevenueScraper {
    public static void main(String[] args) throws IOException {
        String url = "https://revenue.ennead.cc/revenue";
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Runs Chrome with no GUI
        options.addArguments("--disable-dev-shm-usage"); // Uses /tmp folder to avoid small /dev/shm crash
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://revenue.ennead.cc/revenue");

        String tBodySelector = "body > main > div.max-w-\\[850px\\].mx-auto.px-4.pt-2.sm\\:pt-3.pb-8.sm\\:pb-12 > astro-island:nth-child(2) > div > div.mt-4 > div.rounded-xl.border.bg-card.text-card-foreground.shadow.relative.overflow-hidden.mt-4 > div > div > div > table > tbody";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tBody = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(tBodySelector)));
        String html = "<table>" + tBody.getAttribute("outerHTML") + "<table>";


        Document doc = Jsoup.parse(html);
        //System.out.println(doc);
        //System.out.println("contains td? " + html.contains("<td"));
        Elements ranks = doc.select("td.p-2.sm\\:p-3\\.5.text-muted-foreground > div > span");
        Elements names = doc.select("td.p-2.sm\\:p-3\\.5.w-\\[180px\\] > div > div > div > div > button");
        Elements money = doc.select("td > span.text-muted-foreground");
        //System.out.println(ranks.size());
        for (Element rank : ranks) {
            int index = ranks.indexOf(rank);
            System.out.print(rank.text() + "\t");
            System.out.print(names.get(index).text() + "\t");
            System.out.println(money.get(index).text());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
