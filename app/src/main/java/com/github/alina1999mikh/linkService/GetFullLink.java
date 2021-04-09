package com.github.alina1999mikh.linkService;

import com.github.alina1999mikh.linkService.LinksReprository.LinksMap;
import com.github.alina1999mikh.model.Link;

public class GetFullLink {
    public  Link get(Link shortUrl){
        return LinksMap.getHashMap().get(shortUrl);
    }
}
