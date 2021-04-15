package com.github.alina1999mikh.linkService;

import com.github.alina1999mikh.linkService.LinksReprository.LinksMapRepository;
import com.github.alina1999mikh.model.Link;

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
