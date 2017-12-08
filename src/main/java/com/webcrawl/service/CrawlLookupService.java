package com.webcrawl.service;

import com.webcrawl.service.domain.CrawlResponse;

import java.util.List;

public interface CrawlLookupService {

    /**
     * Nested-crawling all links on a URL page.
     *
     * @param url - url to web crawl
     * @param crawlStage - number of nested web crawl required
     * @param crawledUrls - Already crawled urls
     * @return if successful, the crawlResponseList is returned.
     */
    CrawlResponse crawlUrl(String url, int crawlStage, List<String> crawledUrls);
}

