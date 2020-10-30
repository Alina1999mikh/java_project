package com.github.alina1999mikh;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class HelloController {

    private Map<String, String> hashMap = new HashMap<>();
    private int lengthShortUrl = 4;
    char[] chars = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();

    private String getShortUrl(String value) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private String randomUrl() {
        StringBuilder sb = new StringBuilder(lengthShortUrl);
        Random random = new Random();
        for (int i = 0; i < lengthShortUrl; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private String createShortUrl(String fullUrl) {  //создаем урл короткий
        String shortUrl = getShortUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            do {
                shortUrl = randomUrl();
            }
            while (hashMap.get(shortUrl) != null);   //рандомим урл пока он не будет уникальный (вдруг повторяется)
            return shortUrl;
        } else return shortUrl;
    }

    private String addToMap(String fullUrl) { //возвращает shortUrl с которым добавили
        String shortUrl = createShortUrl(fullUrl);
        hashMap.put(shortUrl, fullUrl);
        return shortUrl;
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public String index(@RequestParam String name) {
        return "You are " + name;
    }

    @RequestMapping(value = "/short", method = RequestMethod.GET)
    public ResponseEntity getFullLink(@RequestParam String q) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://localhost:8080/full?Q=" + hashMap.get(q)));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @RequestMapping(value = "/full", method = RequestMethod.GET)
    public String getShortLink(@RequestParam String Q) {
        String shortUrl = addToMap(Q);
        return "http://localhost:8080/short?q=" + shortUrl;
    }
}