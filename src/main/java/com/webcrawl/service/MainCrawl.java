package com.webcrawl.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcrawl.service.tools.Helper;
import com.webcrawl.service.Impl.CrawlLookupServiceImpl;
import com.webcrawl.service.domain.CrawlResponse;

public class MainCrawl {
    public static String integerRegEx = "^[0-9]{1}$|10$";
    public static void main (String[] args) {
        Helper helper = new Helper();
        String error = helper.validateInput(args);
        if ( error == null) {
            CrawlLookupService crawlLookUp = new CrawlLookupServiceImpl();
            String url = args[0];
            int stage = Integer.parseInt(args[1]);
            CrawlResponse response = crawlLookUp.crawlUrl(url, stage, null);
            if (response != null) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(response);
                System.out.println(json);
            }
        }else {
            System.out.println(error);
        }
    }
}
