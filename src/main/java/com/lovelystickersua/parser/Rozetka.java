package com.lovelystickersua.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by devnull on 15.11.16.
 */
public class Rozetka {

    protected static void parse() throws IOException {
        Document document = Jsoup.connect("https://ru.aliexpress.com/category/202005903/office-electronics.html?spm=2114.21011208.2.1.c58zUm").get();
        String string = document.select("span.value").text();
        System.out.println(string);
        String[] array = string.split("руб.");
        for (String s: array) {
            System.out.println(s);
        }
    }
}
