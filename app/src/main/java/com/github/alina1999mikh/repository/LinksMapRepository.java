package com.github.alina1999mikh.repository;

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
    public void save(Link shortUrl, Link fullUrl) {
        hashMap.put(shortUrl, fullUrl);
    }

    @Override
    public Link findBy(Link value) {
        for (Map.Entry<Link, Link> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}