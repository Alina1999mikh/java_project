package com.github.alina1999mikh;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.*;

@RestController
public class HelloController {

    private Map<String, String> hashMap = new HashMap<>();
    private int lengthShortUrl = 1;
    private char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    private ArrayList<Integer> list = initializeList();

    private ArrayList<Integer> initializeList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < lengthShortUrl; i++) list.add(-1);
        return list;
    }

    private String getShortUrl(String value) {
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private void doIncreaseUrl(int index) {

        int thisIndex = list.size() - index;
        if (list.get(thisIndex) == alphabet.length - 1)//если индекс юрл равен последнему индексу (букве) алфавита и требует замены ранее стоящего
        {
            list.set(thisIndex, 0);
            if (thisIndex == 0 && list.get(thisIndex) == alphabet[alphabet.length-1]) {  //проверка требует ли увеличения длины ссылк
                lengthShortUrl++;
                list.add(0);
                return;
            }
            doIncreaseUrl(index+1);
        } else {
            list.set(thisIndex, list.get(thisIndex) + 1);
        }
    }

    private String createNewUrl() {
        doIncreaseUrl(1);
        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = alphabet[list.get(i)];
        }
        return new String(chars);
    }

    private String createShortUrl(String fullUrl) {  //создаем урл короткий
        String shortUrl = getShortUrl(fullUrl);      // проверяем есть ли такая ссылка уже, чтобы выдать готовый урл а не новый
        if (shortUrl == null) {
            return createNewUrl();
        } else return shortUrl;
    }

    private String connectToMap(String fullUrl) { //возвращает shortUrl с которым добавили
        String shortUrl=createShortUrl(fullUrl);
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
        return "http://localhost:8080/short?q=" + connectToMap(Q);
    }
}