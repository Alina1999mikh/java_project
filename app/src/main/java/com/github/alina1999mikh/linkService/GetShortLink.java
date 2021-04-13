package com.github.alina1999mikh.linkService;

import com.github.alina1999mikh.linkService.LinksReprository.LinksMap;
import com.github.alina1999mikh.model.Link;

public class GetShortLink implements GetLink{

    private ShortStringGenerationService shortStringGenerationServiceProcess;

    public GetShortLink() {
         shortStringGenerationServiceProcess = new ShortStringGenerationService();
    }

    public  Link get(Link fullUrl) { //возвращает shortUrl с которым добавили
        Link shortUrl = getExistUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            shortUrl = shortStringGenerationServiceProcess.createNewUrl();
        }
        LinksMap.put(shortUrl, fullUrl);
        return shortUrl;
    }

    private static Link getExistUrl(Link value) {
        return LinksMap.getKey(value);
    }
}