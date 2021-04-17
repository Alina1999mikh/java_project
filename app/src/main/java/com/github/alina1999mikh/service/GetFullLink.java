package com.github.alina1999mikh.service;

import com.github.alina1999mikh.model.Link;
import com.github.alina1999mikh.repository.LinksMapRepository;

public class GetFullLink implements GetLink {
    LinksMapRepository linksMap;

    public GetFullLink(LinksMapRepository linksMap) {
        this.linksMap = linksMap;
    }

    @Override
    public Link get(Link shortUrl) {
        return linksMap.getHashMap().get(shortUrl);
    }
}
