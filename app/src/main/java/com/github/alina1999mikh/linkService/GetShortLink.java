package com.github.alina1999mikh.linkService;

import com.github.alina1999mikh.linkService.LinksReprository.LinksMap;
import com.github.alina1999mikh.model.Link;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class GetShortLink {

    public Link get(Link fullUrl) { //возвращает shortUrl с которым добавили
        Link shortUrl = getExistUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            shortUrl = ShortStringGenerationService.createNewUrl();
        }
        LinksMap.put(shortUrl, fullUrl);
        return shortUrl;
    }

    private Link getExistUrl(Link value) {
        return LinksMap.getKey(value);
    }
}
