package com.github.alina1999mikh.service;

import com.github.alina1999mikh.model.Link;
import com.github.alina1999mikh.repository.LinksMapRepository;

public class GetLink {
    LinksMapRepository linksMap;

    private final ShortStringGenerationService shortStringGenerationServiceProcess = new ShortStringGenerationService();

    public GetLink(LinksMapRepository linksMap) {
        this.linksMap = linksMap;
    }

    public Link getFullLink(Link shortUrl) {
        return linksMap.getHashMap().get(shortUrl);
    }

    public Link getShortLink(Link fullUrl) { //возвращает shortUrl с которым добавили
        Link shortUrl = getExistUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            shortUrl = shortStringGenerationServiceProcess.createNewUrl();
        }
        linksMap.save(shortUrl, fullUrl);
        return shortUrl;
    }

    private Link getExistUrl(Link value) {
        return linksMap.findBy(value);
    }
}