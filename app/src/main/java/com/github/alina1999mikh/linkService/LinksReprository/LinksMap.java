package com.github.alina1999mikh.linkService.LinksReprository;

import com.github.alina1999mikh.model.Link;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Data
public class LinksMap {
    @Getter
    private static Map<Link, Link> hashMap = new HashMap<>();

    public static void put(Link shortUrl, Link fullUrl) {
        hashMap.put(shortUrl, fullUrl);
    }

    public static Link getKey(Link value) {
        for (Map.Entry<Link, Link> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }
}