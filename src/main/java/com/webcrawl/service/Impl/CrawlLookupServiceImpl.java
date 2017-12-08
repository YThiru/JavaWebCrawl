package com.webcrawl.service.Impl;

import com.webcrawl.service.CrawlLookupService;
import com.webcrawl.service.domain.CrawlResponse;
import com.webcrawl.service.domain.PageDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CrawlLookupServiceImpl implements CrawlLookupService {
    private static final int TIME_OUT_PERIOD = 3000;


    @Override
    public CrawlResponse crawlUrl(String url, int crawlStage, List<String> crawledUrls){
        if (!isValidCrawl(url, crawlStage)) {
            return null;
        }
        try {
            final List<String> updatedCrawledUrls = Optional.ofNullable(crawledUrls).orElse(new ArrayList<>());
            if (!updatedCrawledUrls.contains(url)) {
                updatedCrawledUrls.add(url);
                CrawlResponse crawlResponse = new CrawlResponse();
                crawlResponse.setUrl(url);
                crawl(url).ifPresent(pageDetail -> {
                    crawlResponse.setTitle(pageDetail.getTitle());
                    pageDetail.getLinks().forEach(link -> {
                        crawlResponse.addCrawlItem(crawlUrl(link.attr("abs:href"), crawlStage - 1, updatedCrawledUrls));
                    });
                });
                log.info("Number of child node avaible in URL {} : {}" , url, crawlResponse.getNodes().size());
                return crawlResponse;
            } else {
                return null;
            }
        }catch(Exception e) {
            log.error("Failed to retrieve URL : {} : Error: {}" , url , e.getMessage());
            return null;
        }
    }

    /**
     * Crawl child URL
     *
     * @param url - crawl url using JSOUP
     * @return if successful, the PageDetail is returned.
     */
    private Optional<PageDetail> crawl(final String url)  throws IOException {

        log.info("Fetching contents for url: {}", url);
        try {
            final Document doc = Jsoup.connect(url)
                    .timeout(TIME_OUT_PERIOD)
                    .get();
            final Elements links = doc.select("a[href]");
            final String title = doc.title();
            log.debug("Fetched title: {}, links[{}] for url: {}", title, links.nextAll(), url);
            return Optional.of(new PageDetail(url, title, links));
        } catch (final IOException | IllegalArgumentException e) {
            log.error(String.format("Error getting contents of url {}", url), e);
            return Optional.empty();
        }
    }

    /**
     * Check given URL is valid  or not
     * Check whether crawl reached its max crawl number or not
     * @param url - crawl url
     * @param crawlStage  - required nested crawl in number
     * @return if successful, the PageDetail is returned.
     */
    private boolean isValidCrawl(String url, int crawlStage) {
        if (crawlStage < 0) {
            log.info("Nested crawl stage must be greater than 1 for url {}", url);
            return false;
        }

        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }
}
