package com.github.alina1999mikh.linkService.LinksReprository;

import com.github.alina1999mikh.model.Link;

public interface LinksRepository {
    void put(Link shortUrl, Link fullUrl);
}
