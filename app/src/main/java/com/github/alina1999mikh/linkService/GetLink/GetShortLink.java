package com.github.alina1999mikh.linkService.GetLink;

import com.github.alina1999mikh.linkService.LinksReprository.LinksMapRepository;
import com.github.alina1999mikh.model.Link;

public class GetShortLink implements GetLink {

    LinksMapRepository linksMap;
    private final ShortStringGenerationService shortStringGenerationServiceProcess;

    public GetShortLink(LinksMapRepository linksMap) {
        shortStringGenerationServiceProcess = new ShortStringGenerationService();
        this.linksMap = linksMap;
    }

    public GetShortLink() {
        shortStringGenerationServiceProcess = new ShortStringGenerationService();
    }

    @Override
    public Link get(Link fullUrl) { //возвращает shortUrl с которым добавили
        Link shortUrl = getExistUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            shortUrl = shortStringGenerationServiceProcess.createNewUrl();
        }
        linksMap.put(shortUrl, fullUrl);
        return shortUrl;
    }

    private Link getExistUrl(Link value) {
        return linksMap.getMatch(value);
    }
}