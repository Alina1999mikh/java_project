package com.github.alina1999mikh.repository;

import com.github.alina1999mikh.model.Link;

public interface LinksRepository {
    void save(Link shortUrl, Link fullUrl);

    Link findBy(Link value);
}
