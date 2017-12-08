package com.webcrawl.service;

import com.webcrawl.service.Impl.CrawlLookupServiceImpl;
import com.webcrawl.service.domain.CrawlResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CrawlLookupServiceImplTest {

   private CrawlLookupService crawlerService;

    @Before
    public void setUp() {
        crawlerService = new CrawlLookupServiceImpl();
    }

    @Test
    public void testInvalidUrl() {
        CrawlResponse response = crawlerService.crawlUrl("google.com", 2, null);
        Assert.assertNull(response);
    }

    @Test
    public void testZeroStageCount() {
        CrawlResponse response = crawlerService.crawlUrl("http://www.google.com", 0, null);
        Assert.assertEquals(response.getUrl(), "http://www.google.com");
        Assert.assertEquals(0, response.getNodes().size());
    }

    @Test
    public void testCrawlStage1() {
        CrawlResponse response = crawlerService.crawlUrl("http://www.google.com", 1, null);
        Assert.assertEquals(response.getUrl(), "http://www.google.com");
        Assert.assertNotEquals(0, response.getNodes().size());
    }

}