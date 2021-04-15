package com.github.alina1999mikh.linkService.LinksReprository;

import com.github.alina1999mikh.model.Link;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class LinksMapRepository implements LinksRepository {
    @Getter
    private Map<Link, Link> hashMap = new HashMap<>();

    @Override
    public void put(Link shortUrl, Link fullUrl) {
        hashMap.put(shortUrl, fullUrl);
    }

    @Override
    public Link getMatch(Link value) {
        for (Map.Entry<Link, Link> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}