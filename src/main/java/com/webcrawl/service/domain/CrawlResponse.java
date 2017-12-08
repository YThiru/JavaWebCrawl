package com.webcrawl.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@SuppressWarnings("serial")
public class CrawlResponse implements Serializable {

    private String url;
    private String title;
    private List<CrawlResponse> nodes;

    public CrawlResponse addCrawlItem(final CrawlResponse responseItem) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        if (responseItem != null) {
            nodes.add(responseItem);
        }
        return this;
    }
    public CrawlResponse nodes(final List<CrawlResponse> nodes) {
        this.nodes = nodes;
        return this;
    }
}
