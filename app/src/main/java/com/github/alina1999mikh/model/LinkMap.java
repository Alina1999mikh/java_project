package com.github.alina1999mikh.model;

import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class LinkMap {
    @Getter
    private Map<Link, Link> hashMap = new HashMap<>();

    public LinkMap() {
    }

    public void put(Link shortUrl, Link fullUrl) {
        hashMap.put(shortUrl, fullUrl);
    }

    public Link getKey(Link value) {
        for (Map.Entry<Link, Link> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}